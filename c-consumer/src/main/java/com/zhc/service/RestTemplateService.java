package com.zhc.service;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.CseHttpEntity;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.provider.springmvc.reference.async.CseAsyncRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.zhc.common.Teacher;

@RestSchema(schemaId = "restTemplateService")
@RequestMapping(path = "/consumer/v0")
public class RestTemplateService {
  private static final Logger LOG = LoggerFactory.getLogger(RestTemplateService.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  CseAsyncRestTemplate cseAsyncRestTemplate = new CseAsyncRestTemplate();

  @GetMapping("/asyncRest")
  public String asyncRest() throws Exception {
    String url = "cse://provider/provider/v0/hello?name=delay";
    ListenableFuture<ResponseEntity<String>> responseEntityListenableFuture = cseAsyncRestTemplate
        .getForEntity(url, String.class);
    ResponseEntity<String> responseEntity = responseEntityListenableFuture.get();
    String result = responseEntity.getBody();
    LOG.info("asyncRest() is invoked, response:{}", result);
    return result;
  }

  @PostMapping("/asyncRest2")
  public void asyncRest2(@RequestBody Teacher teacher) throws Exception {
    HttpEntity<Teacher> entity = new HttpEntity<>(teacher);
    ListenableFuture<ResponseEntity<Teacher>> listenableFuture = cseAsyncRestTemplate
        .exchange("cse://provider/provider/v0/queryTeacher", HttpMethod.POST, entity, Teacher.class);
    //    ResponseEntity<String> responseEntity1 = listenableFuture.get();
    //    System.out.println("AsyncRestTemplate Consumer sayHello services: " + responseEntity1.getBody());
    //设置回调函数
    listenableFuture.addCallback(
        new ListenableFutureCallback<ResponseEntity<Teacher>>() {
          @Override
          public void onFailure(Throwable ex) {
            LOG.error("AsyncResTemplate Consumer catched exception when queryTeacher, ", ex);
          }

          @Override
          public void onSuccess(ResponseEntity<Teacher> result) {
            LOG.info("asyncRest2() is invoked, response:{}", result.getBody());
          }
        });
  }

  @PostMapping("/asyncRest3")
  public Teacher asyncRest3(@RequestBody Teacher teacher) throws Exception {
    String url = "cse://provider/provider/v0/queryTeacher";
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add("name", "JavaChassis");
    ListenableFuture<ResponseEntity<Teacher>> entity = cseAsyncRestTemplate
        .postForEntity(url, new CseHttpEntity<>(teacher, headers), Teacher.class);
    ResponseEntity<Teacher> responseEntity = entity.get();
    Teacher result = responseEntity.getBody();
    LOG.info("asyncRest3() is invoked, response:{}", result);
    return result;
  }

  @PostMapping("/restCall")
  public Teacher restCall(@RequestBody Teacher teacher) throws Exception {
    String url = "cse://provider/provider/v0/queryTeacher";
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add("name", "JavaChassis");
    ResponseEntity<Teacher> responseEntity = restTemplate
        .postForEntity(url, new CseHttpEntity<>(teacher, headers), Teacher.class);
    Teacher result = responseEntity.getBody();
    LOG.info("restCall() is invoked, response:{}", result);
    return result;
  }

  @PostMapping("/restExchange")
  public Teacher restExchange(@RequestBody Teacher teacher) throws Exception {
    HttpEntity<Teacher> entity = new HttpEntity<>(teacher);
    String url = "cse://provider/provider/v0/queryTeacher";
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add("name", "JavaChassis");
    ResponseEntity<Teacher> responseEntity = restTemplate
        .exchange(url, HttpMethod.POST, entity, Teacher.class);
    Teacher result = responseEntity.getBody();
    LOG.info("restExchange() is invoked, response:{}", result);
    return result;
  }

  @GetMapping("/restlistExchange")
  public List<Teacher> restlistExchange(String name) throws Exception {
    HttpEntity<String> entity = new HttpEntity<>(name);
    String url = "cse://provider/provider/v0/listTeacher";
//    MultiValueMap<String, String> headers = new HttpHeaders();
//    headers.add("name", "JavaChassis");
    ResponseEntity<?> responseEntity = restTemplate
        .exchange(url, HttpMethod.POST, entity, ResponseEntity.class);
    List<Teacher> result = (List<Teacher>) responseEntity.getBody();
    LOG.info("restlistExchange() is invoked, response:{}", result);
    return result;
  }
}

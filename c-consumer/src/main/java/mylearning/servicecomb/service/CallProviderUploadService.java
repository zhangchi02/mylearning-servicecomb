package mylearning.servicecomb.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import org.apache.servicecomb.core.provider.consumer.InvokerUtils;
import org.apache.servicecomb.foundation.common.part.FilePart;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.swagger.invocation.converter.PartToMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import mylearning.servicecomb.common.Person;
import mylearning.servicecomb.common.TaskData;

@RestSchema(schemaId = "CallProviderUploadService")
@RequestMapping(path = "/consumer/v0")
public class CallProviderUploadService {
  private static final Logger LOG = LoggerFactory.getLogger(CallProviderUploadService.class);

  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @RpcReference(microserviceName = "provider", schemaId = "uploadSpringMVCService")
  private IUploadSpringMVCService upload;

  @RpcReference(microserviceName = "provider", schemaId = "uploadJaxRSService")
  private IUploadJaxRSService upload2;

  @GetMapping("callProviderUploadwithObject")
  public String callProviderUploadwithObject(@RequestParam(name = "method") String method) throws IOException {
    File file1 = File.createTempFile("jaxrstest1", ".txt");
//    FileSystemResource fileSystemResource1 = new FileSystemResource(file1);
    Part part = new FilePart("fileName", file1);

    Person person1 = new Person("mm", "female");
    Person person2 = new Person("mm", "female");
    List<Person> list = new ArrayList();
    list.add(person1);
    list.add(person2);
    TaskData taskData = new TaskData("ServiceComb-Java-Chassis", 3, list);

    MultipartFile multipartFile = new PartToMultipartFile(part);
    if ("springmvc".equalsIgnoreCase(method)) {
      upload.uploadFileWithSpringMVCAndObject(multipartFile, taskData);
    }
    if ("jaxrs".equalsIgnoreCase(method)) {
      upload2.uploadFileWithJaxRSAndObject(part, taskData);
    }
    return "success";
  }

  @GetMapping("/callProviderUploadService")
  public String callProviderUploadService(@RequestParam(name = "method") String method) throws IOException {
    LOG.info("callProviderUploadService() is invoked.");
    String url = String.format("cse://provider/provider/v0/%s", method);

    File file1 = File.createTempFile("jaxrstest1", ".txt");
    FileSystemResource fileSystemResource1 = new FileSystemResource(file1);

    File file = null;

    Person person = new Person("mm", "female");
    List list = new ArrayList();
    list.add(person);
    TaskData taskData = new TaskData("ServiceComb", 3, list);

    Map<String, Object> map0 = new HashMap<>();
    map0.put("fileName", fileSystemResource1);

    Map<String, Object> map1 = new HashMap<>();
    map1.put("fileName", fileSystemResource1);
    map1.put("message", "This is my message.");

    Map<String, Object> map2 = new HashMap<>();
    map2.put("fileName", fileSystemResource1);
    map2.put("taskData", taskData);

    HttpHeaders headers0 = new HttpHeaders();
    headers0.setContentType(MediaType.MULTIPART_FORM_DATA);

    HttpHeaders headers1 = new HttpHeaders();
    headers1.setContentType(MediaType.APPLICATION_OCTET_STREAM);

    String result = "";

    if ("uploadFileWithSpringMVCToLocal".equals(method)) {
      result = restTemplate.postForEntity(url, new HttpEntity<>(map0, headers0), String.class).getBody();
    }
    if ("uploadFileWithSpringMVCAndString".equals(method)) {
      result = restTemplate.postForEntity(url, new HttpEntity<>(map1, headers0), String.class).getBody();
    }
    if ("uploadFileWithSpringMVCAndObject".equals(method)) {
//      java.lang.Error: not supported method
      result = restTemplate.postForEntity(url, new HttpEntity<>(map2, headers0), String.class).getBody();
    }
    if ("uploadFileWithSpringMVCUseOctetStream".equals(method)) {
      result = restTemplate.postForEntity(url, new HttpEntity<>(map0, headers1), String.class).getBody();
    }
    if ("uploadFileWithJaxRSToLocal".equals(method)) {
      result = restTemplate.postForEntity(url, new HttpEntity<>(map0, headers0), String.class).getBody();
    }
    if ("uploadFileWithJaxRSAndString".equals(method)) {
      result = restTemplate.postForEntity(url, new HttpEntity<>(map1, headers0), String.class).getBody();
    }
    if ("uploadFileWithJaxRSAndObject".equals(method)) {
//      java.lang.Error: not supported method
      result = restTemplate.postForEntity(url, new HttpEntity<>(map2, headers0), String.class).getBody();
    }
    if ("uploadFileWithJaxRSUseOctetStream".equals(method)) {
      result = restTemplate.postForEntity(url, new HttpEntity<>(map0, headers1), String.class).getBody();
    }
    return result;
  }

  public void downUrlTxt(String fileName, String fileUrl, String downPath) {
    // 创建文件保存路径
    File savePath = new File(downPath);
    if (!savePath.exists()) {
      savePath.mkdir();
    }
    String[] urlname = fileUrl.split("/");
    int len = urlname.length - 1;
    String uname = urlname[len];//获取文件名
    try {
      File file = new File(savePath + "/" + uname);//创建新文件
      if (file != null && !file.exists()) {
        file.createNewFile();
      }
      OutputStream oputstream = new FileOutputStream(file);
      URL url = new URL(fileUrl);
      HttpURLConnection uc = (HttpURLConnection) url.openConnection();
      uc.setDoInput(true);//设置是否要从 URL 连接读取数据636f7079e79fa5e9819331333335306265,默认为true
      uc.connect();
      InputStream iputstream = uc.getInputStream();
      System.out.println("file size is:" + uc.getContentLength());//打印文件长度
      byte[] buffer = new byte[4 * 1024];
      int byteRead = -1;
      while ((byteRead = (iputstream.read(buffer))) != -1) {
        oputstream.write(buffer, 0, byteRead);
      }
      oputstream.flush();
      iputstream.close();
      oputstream.close();
    } catch (Exception e) {
      System.out.println("读取失败！");
      e.printStackTrace();
    }
    System.out.println("生成文件路径：" + downPath + fileName);
  }

  public void shareUrlFile() throws IOException {
    File remoteFile = new File("//192.168.7.146/test/1.txt");// 192.168.7.146是对方机器IP，test是对方那个共享文件夹名字，如果没有共享是访问不到的
    //远程文件其实主要是地址，地址弄对了就和本地文件没什么区别，windows里面//或者\\\\开头就表示这个文件是网络路径了其实这个地址就像我们再windows里面，点击开始
    //然后点击运行，然后输入 \\192.168.7.146/test/1.txt访问远程文件一样的
    BufferedReader br = new BufferedReader(new FileReader(remoteFile));
    String str;
    while ((str = br.readLine()) != null) {
      System.out.println(str);
    }
    br.close();
  }
}

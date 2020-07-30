package mylearning.servicecomb.springboot2.servlet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;
import javax.ws.rs.FormParam;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "UploadSpringMVCService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/springboot2servletjar/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class UploadSpringMVCService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadSpringMVCService.class);

  @PostMapping(path = "/uploadFileWithSpringMVCToLocal", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFileWithSpringMVCToLocal(@RequestPart(name = "fileName") MultipartFile file) {
    LOGGER.info("uploadFileWithSpringMVCToLocal() is invoked. file:{}", file.getSize());
    return "success";
  }

  @PostMapping(path = "/uploadFileWithJaxRSToLocal", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFileWithJaxRSToLocal(@FormParam("fileName") Part file) {
    LOGGER.info("uploadFileWithJaxRSToLocal() is invoked. file:{}", file.getSize());
    return "success";
  }


  @PostMapping(path = "/uploadFiles", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFiles(@RequestPart(name = "files", required = false) MultipartFile[] files,
      @RequestAttribute("message") String message) {
    LOGGER.info("uploadFiles() is invoked. files size:{}.", files.length);
    return "success";
  }


  @PostMapping(path = "/uploadFiles2")
  public String uploadFiles2(@RequestPart(name = "files") MultipartFile[] files,
      @RequestAttribute("message") String message) {
    LOGGER.info("uploadFiles2() is invoked. files size:{}.", files.length);
    return "success";
  }

  @PostMapping(path = "/uploadFiles3", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFiles3(@RequestPart(name = "files") MultipartFile[] files) {
    LOGGER.info("uploadFiles() is invoked. files size:{}.", files.length);
    return "success";
  }

  public String upload(MultipartFile file) {
    byte[] buffer = new byte[10240];
//    String fileId = UUID.randomUUID().toString();
    String fileName = file.getOriginalFilename();
    LOGGER.info("begin upload {}.", fileName);
    File dir = new File("file");
    if (!dir.exists()) {
      dir.mkdir();
    }
    File outFile = new File(dir, fileName);
    LOGGER.info("outFile:{}.", outFile.getAbsoluteFile());
    int len;
    try (InputStream is = file.getInputStream(); OutputStream os = new FileOutputStream(outFile)) {
      while ((len = is.read(buffer)) != -1) {
        os.write(buffer, 0, len);
      }
    } catch (IOException e) {
      LOGGER.error("upload failed.", e);
    }
    LOGGER.info("{} upload success", fileName);
    return fileName;
  }
}

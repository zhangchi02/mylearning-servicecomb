package mylearning.servicecomb.springboot.servlet.jar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "uploadSpringMVCService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class UploadSpringMVCService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadSpringMVCService.class);

  @PostMapping(path = "/uploadFileWithSpringMVCToLocal", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFileWithSpringMVCToLocal(@RequestPart(name = "fileName") MultipartFile file) {
    LOGGER.info("uploadFileWithSpringMVCToLocal() is invoked.");
    return upload(file);
  }

  @PostMapping(path = "/uploadFileWithSpringMVCAndString", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFileWithSpringMVCAndString(@RequestPart(name = "fileName") MultipartFile file,
      @RequestAttribute("message") String message) {
    LOGGER.info("uploadFileWithSpringMVCAndString() is invoked, message is :{}", message);
    return upload(file);
  }

  @PostMapping(path = "/uploadFileWithSpringMVCUseOctetStream", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public String uploadFileWithSpringMVCUseOctetStream(@RequestPart(name = "fileName") MultipartFile file) {
    LOGGER.info("uploadFileWithSpringMVCUseOctetStream() is invoked.");
    return upload(file);
  }

  @PostMapping(path = "/uploadFiles", produces = MediaType.TEXT_PLAIN_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadFiles(@RequestPart(name = "files") MultipartFile[] files,
      @RequestAttribute("message") String message) {
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

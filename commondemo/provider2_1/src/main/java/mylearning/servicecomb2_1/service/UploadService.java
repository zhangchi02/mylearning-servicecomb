package mylearning.servicecomb2_1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import mylearning.servicecomb.common.TaskData;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * @author zhangchi02
 * @date 2019年3月5日
 */
@RestSchema(schemaId = "uploadService") //该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") //RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class UploadService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

  @PostMapping(path = "/uploadand", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//  @ApiImplicitParams({
//      @ApiImplicitParam(name = "taskData", dataType = "object", paramType = "body"),
//  })
  public String uploadFile(@RequestBody TaskData taskData, @RequestPart(name = "fileName") MultipartFile file) {
    LOGGER.info("uploadFile() is invoked,taskData is :{}", taskData);
    return upload(file);
  }

  @PostMapping(path = "/uploadLocal", produces = MediaType.TEXT_PLAIN_VALUE)
  public String uploadFiletoLocal(@RequestPart(name = "fileName") MultipartFile file) {
    return upload(file);
  }

  public String upload(MultipartFile file) {
    byte[] buffer = new byte[10240];
    String fileId = UUID.randomUUID().toString();

    File outFile = new File(new File("."), fileId);
    int len;
    try (InputStream is = file.getInputStream(); OutputStream os = new FileOutputStream(outFile)) {
      while ((len = is.read(buffer)) != -1) {
        os.write(buffer, 0, len);
      }
    } catch (IOException e) {

      return null;
    }
    return fileId;
  }

  //  使用obs SDK往OBS桶中上传文件
  @RequestMapping(path = "/uploadobs", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.POST)
  public String uploadFileToOBS(@RequestPart(name = "file") MultipartFile file) {
    LOGGER.info("invoke  uploadFile method");
    String endPoint = "https://obs.cn-north-1.myhuaweicloud.com";
    String ak = "ak";
    String sk = "sk";

    ObsClient obsClient = new ObsClient(ak, sk, endPoint);
    try {
      Date date = new Date();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      String dateNowStr = simpleDateFormat.format(date);
      String bucketName = "obs-8470-fgy";
      String path = "uploadobs/" + dateNowStr + "-" + file.getOriginalFilename();

      obsClient.putObject(bucketName, path, file.getInputStream());
      String downloadUrl = endPoint + "/" + path;

      return downloadUrl;
    } catch (ObsException | IOException e) {
      LOGGER.error("putObject  error: ", e);
    } finally {
      try {
        obsClient.close();
      } catch (IOException e) {
        LOGGER.error("close obsClient error: ", e);
      }
    }
    return null;
  }
}

>ServiceComb框架默认使用的是`Log4j`，若想切换成`log4j2`，只需要按照如下几个步骤操作即可：

1. 排除掉log4j相关的依赖
```xml
<dependency>
  <groupId>com.huawei.paas.cse</groupId>
  <artifactId>cse-solution-service-engine</artifactId>
    <exclusions>
      <exclusion>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
      </exclusion>
      <exclusion>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
      </exclusion>
    </exclusions>
</dependency>
```
​2.引入log4j2相关的依赖
```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-bom</artifactId>
      <version>2.11.1</version>
      <scope>import</scope>
      <type>pom</type>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-web</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.4.2</version>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
  </dependency>
</dependencies>
```
3.添加log4j2.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="5">
  <Properties>
    <Property name="logDir" value="logs123"/>
    <Property name="genericPattern" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    <Property name="genericFilePattern" value="%d{yyyy-MM-dd}-%i"/>
  </Properties>
  <Appenders>
    <Console name="consoleAppender" target="SYSTEM_OUT">
      <PatternLayout pattern="${genericPattern}"/>
    </Console>
    <File name="accesslog" fileName="logs123/access.log" append="true">
      <PatternLayout pattern="%msg%n"/>
    </File>
    <RollingRandomAccessFile name="rollingRandomAccessFileAppender" fileName="${logDir}/service-with-log4j2.log"
      filePattern="${logDir}/service-with-log4j2-${genericFilePattern}.log.zip" append="true">
      <PatternLayout pattern="${genericPattern}"/>
      <Policies>
        <TimeBasedTriggeringPolicy interval="1"/>
        <SizeBasedTriggeringPolicy size="100 MB"/>
      </Policies>
      <DefaultRolloverStrategy max="1000000" compressionLevel="9"/>
    </RollingRandomAccessFile>
  </Appenders>
  <Loggers>
    <asyncRoot level="info">
      <AppenderRef ref="consoleAppender"/>
    </asyncRoot>
    <Logger name="org.apache.servicecomb" level="info" additivity="false">
      <AppenderRef ref="consoleAppender"/>
      <AppenderRef ref="rollingRandomAccessFileAppender"/>
    </Logger>
    <!-- 定义一个name为accesslog的logger -->
    <Logger name="accesslog" level="info" additivity="false">
      <AppenderRef ref="accesslog"/>
    </Logger>
  </Loggers>
</Configuration>
```
4.注释掉Log4jUtils.init()；
```java
import org.apache.servicecomb.foundation.common.utils.BeanUtils;

public class Log4j2Main {

  public static void main(String[] args){
//    Log4jUtils.init();
    BeanUtils.init();
  }
}
```
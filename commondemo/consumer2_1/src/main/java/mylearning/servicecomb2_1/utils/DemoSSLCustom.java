/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mylearning.servicecomb2_1.utils;

import java.net.URL;

import org.apache.servicecomb.foundation.ssl.SSLCustom;

public class DemoSSLCustom extends SSLCustom {
  @Override
  public char[] decode(char[] encrypted) {
    return encrypted;
  }

  @Override
  public String getFullPath(String filename) {
//    linux环境需要拷贝classes文件夹到jar所在目录
//    String fileFolderPath = "./classes/certificates/";
//    LOG.info("fileFolderPath: {},filename: {}.", fileFolderPath, filename);
//    return fileFolderPath + filename;
    URL url = Thread.currentThread().getContextClassLoader().getResource("certificates/" + filename);
    if (url == null) {
      return filename;
    }

    return url.getPath();
  }
}

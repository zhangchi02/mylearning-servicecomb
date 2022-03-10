> [本地轻量化服务中心](https://support.huaweicloud.com/productdesc-servicestage/cse_productdesc_0007.html)下载的服务中心默认没有开启SSL模式，如果想开启该模式，可以参考如下步骤进行操作：

### 制作证书

#### 签发证书

> 参考[使用keytool和OpenSSL自行签发TLS证书](http://seatime.online/2019/11/13/CreateTLSCertificateByKeytoolAndOpenssl/)签发证书

#### 导出服务中心需要的证书

> 服务中心需要用到cert_pwd、server.cer、server_key.pem和trust.cer

* cert_pwd

> 创建cert_pwd文件，内容为身份证书密码

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/23/21161216wmannanecuvwfz.png)

* server.cer

> 双击打开server.crt，将其复制到server.cer证书。

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/23/210033m6qjcptqbuh1ae6q.png)

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/23/2108054ffyerjkqsrllp7l.png)

* trust.cer

> 双击打开ca.crt，将其复制到trust.cer证书。

* server_key.pem

> 执行如下命令将server.key导出server_key.pem，需要输入身份证书的密码。

```jshelllanguage
openssl rsa -in server.key -out server_key.pem
```

### 修改配置文件

> 修改conf/app.conf文件中ssl_mode=1

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/23/2119011fcles66cmyy0nva.png)

* 修改后的文件夹对比

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/23/21273507x55lnb1wfgjmn9.png)

> 需要注意的是，开启SSL模式后前台页面将无法使用，可以通过调用服务中心的api查看服务的注册信息，关于该api的介绍，请参考[查询所有微服务信息](https://support.huaweicloud.com/api-servicestage/cse_02_0006.html)。

```jshelllanguage
curl -i -k --cert server.cer --pass Changeme_123 --key server_key.pem -H "x-domain-name:default" https://127.0.0.1:30100/v4/default/registry/microservices
```

![image.png](https://bbs-img-cbc-cn.obs.cn-north-1.myhuaweicloud.com/data/attachment/forum/202004/23/213525ixorutmcz4hmvv3w.png)

package com.zhc.service;

import java.util.concurrent.CompletableFuture;

/**
* @author zhangchi02
* @date 2019年3月22日
*/
public interface CommonServiceReactive {
	CompletableFuture<String> sayHello(String name);
}

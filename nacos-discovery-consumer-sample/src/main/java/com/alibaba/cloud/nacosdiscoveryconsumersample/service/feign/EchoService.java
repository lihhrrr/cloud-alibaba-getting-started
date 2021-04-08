package com.alibaba.cloud.nacosdiscoveryconsumersample.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("nacos-discovery-provider-sample")
public interface EchoService {

    // Java 编译器不会讲接口方法参数名添加到 Java 字节码中。
    @GetMapping("/echo/{message}")
    String echo(@PathVariable("message") String message);

}

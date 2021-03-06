package com.alibaba.cloud.nacosdiscoveryprovidersample.demos.nacosdiscoveryprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoServiceController {

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        return "[ECHO] " + message;
    }

}

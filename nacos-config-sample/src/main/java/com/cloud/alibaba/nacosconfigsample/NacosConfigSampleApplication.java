package com.cloud.alibaba.nacosconfigsample;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RefreshScope
@RestController
@EnableConfigurationProperties(User.class)
@SpringBootApplication
public class NacosConfigSampleApplication {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private Integer userAge;

    @Autowired
    private User user;

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @GetMapping("/user")
    public String user() {
        return String.format("[http] user name : %s , age : %d", userName, userAge);
    }

    @GetMapping("/user/autowired")
    public String autowiredUser() {
        return "[http] " + user;
    }

    @PostConstruct
    public void init() {
        System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
    }

    @PreDestroy
    public void destroy() {
        System.out.printf("[destoy] user name : %s , age : %d", userName, userAge);
    }


    public static void main(String[] args) {
        SpringApplication.run(NacosConfigSampleApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            String dataId = "nacos-config-sample.properties";
            String group = "DEFAULT_GROUP";
            nacosConfigManager.getConfigService().addListener(dataId, group, new AbstractListener() {
                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("[listener] " + s);
                }
            });
        };
    }

}

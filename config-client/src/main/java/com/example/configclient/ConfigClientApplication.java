package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
@EnableAsync
@RestController
public class ConfigClientApplication {

    /**
     * http://localhost:8881/actuator/bus-refresh
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${foo}")
    String foo;

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }

    @RequestMapping("/refresh")
    public String refresh(){
        systemService.updateConfig();
        String text = "Updating···";
        return text;
    }
}

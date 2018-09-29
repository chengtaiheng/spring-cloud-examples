package com.github.yingzhuo.spring.cloud.examples.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApplicationBoot {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(ApplicationBoot.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }

}

package com.techm.mt;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.techm.mt.base", "com.techm.mt.config", "com.techm.mt.tenantsupport.tenants.servlet"})
@EnableAutoConfiguration
@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
        springApplicationBuilder.application().run(Application.class, args);
    }
}

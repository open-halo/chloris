package com.lvonce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * For GraalVM, gclib proxies should be disabled, proxyBeanMethods = false
 */
@SpringBootApplication(proxyBeanMethods = false)
@EnableAspectJAutoProxy
@MapperScan("com.lvonce")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
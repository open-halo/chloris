package com.lvonce.api.adapter.rest;

import lombok.extern.slf4j.Slf4j;
import com.lvonce.api.rest.IHelloResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController implements IHelloResource {
    @Override
    public String sayHello() {
        log.info("sayHello()");
        return "Hello world!";
    }
}

package com.dragon.testdaospringbootstarter.controller;

import com.dragon.testdaospringbootstarter.service.TestService;
import com.github.jun.starter.dao.demo.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private TestService testService;

    @GetMapping("/test1")
    public String test1(){
        return helloService.sayHello("JackMa");
    }

    @GetMapping("/test2")
    public List<Map> test2(){
        return testService.getBuyerList();
    }

}

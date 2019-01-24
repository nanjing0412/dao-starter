package com.github.jun.starter.dao.demo;

import com.github.jun.starter.dao.demo.HelloProperties;

/**
 * @author: hujw
 * @date: 2019/1/22
 * @description:
 */
public class HelloService {

    private HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name) {
        return helloProperties.getPrefix() + "," + name + "," + helloProperties.getSuffix();
    }

}

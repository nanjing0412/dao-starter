package com.github.jun.starter.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: hujw
 * @date: 2019/1/22
 * @description:
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private String prefix = "defaultPrefix";
    private String suffix = "defaultSuffix";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

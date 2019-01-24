package com.github.jun.starter.dao.dsp.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
public class SpringHolder {

    private static ApplicationContext contextOfEngine = null;
    private static AbstractApplicationContext abstractContext = null;
    private static Properties properties = new Properties();
    private static SpringHolder instance = new SpringHolder();

    private SpringHolder() {
        contextOfEngine = new FileSystemXmlApplicationContext(new String[]{"classpath*:/config/spring/spring-*.xml", "classpath*:/config/spring/*/spring-*.xml", "classpath*:/config/spring/*/*/spring-*.xml", "classpath*:/config/spring/*/*/*/spring-*.xml", "classpath*:/config/pageList/list-*.xml", "classpath*:/config/pageList/*/list-*.xml", "classpath*:/config/pageList/*/*/list-*.xml", "classpath*:/config/pageList/*/*/*/list-*.xml"});

        try {
            abstractContext = (AbstractApplicationContext)contextOfEngine;
            String[] postProcessorNames = abstractContext.getBeanNamesForType(BeanFactoryPostProcessor.class, true, true);
            String[] arr$ = postProcessorNames;
            int len$ = postProcessorNames.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String ppName = arr$[i$];
                BeanFactoryPostProcessor beanProcessor = (BeanFactoryPostProcessor)abstractContext.getBean(ppName, BeanFactoryPostProcessor.class);
                if (beanProcessor instanceof PropertyResourceConfigurer) {
                    PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer)beanProcessor;
                    Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
                    mergeProperties.setAccessible(true);
                    Properties props = (Properties)mergeProperties.invoke(propertyResourceConfigurer);
                    Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties", Properties.class);
                    convertProperties.setAccessible(true);
                    convertProperties.invoke(propertyResourceConfigurer, props);
                    properties.putAll(props);
                }
            }

        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }
    }

    public static synchronized SpringHolder getInstance() {
        if (instance == null) {
            instance = new SpringHolder();
        }

        return instance;
    }

    public static ApplicationContext getContextOfEngine() {
        getInstance();
        return contextOfEngine;
    }

    public static Object getBean(String name) throws BeansException {
        getInstance();
        return contextOfEngine.getBean(name);
    }

    public static String getProperty(String propertyName) {
        getInstance();
        return properties.getProperty(propertyName);
    }
}

package com.dragon.testdaospringbootstarter.controller;

import com.dragon.testdaospringbootstarter.common.R;
import com.dragon.testdaospringbootstarter.service.TestService;
import com.github.jun.starter.dao.demo.HelloService;
import com.github.jun.starter.dao.dsp.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@RestController
@RequestMapping(value = "/test")
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

    //========================
    @RequestMapping(value = "/testPage")
    public R testGetList() {
        List<Map> list = testService.testGetList();
        return R.ok().put("list", list);
    }

    @RequestMapping(value = "/testGetPageList")
    public R testGetPageList() {
        PageBean pageBean = testService.testGetPageList();
        return R.ok().put("pageBean", pageBean);
    }

    @RequestMapping(value = "/testGetMap")
    public R testGetMap() {
        Map map = testService.testGetMap();
        return R.ok().put("map", map);
    }

    @RequestMapping(value = "/testGetObject")
    public R testGetObject() {
        String str = testService.testGetObject();
        return R.ok().put("str", str);
    }

    @RequestMapping(value = "/testUpdate")
    public R testUpdate() {
        testService.testUpdate();
        return R.ok();
    }

    @RequestMapping(value = "/testBatchUpdate")
    public R testBatchUpdate() {
        testService.testBatchUpdate();
        return R.ok();
    }

    @RequestMapping(value = "/testGetCount")
    public R testGetCount() {
        int count = testService.testGetCount();
        return R.ok().put("count", count);
    }

    @RequestMapping(value = "/testExists")
    public R testExists() {
        boolean exists = testService.testExists();
        return R.ok().put("exists", exists);
    }

}

package com.dragon.testdaospringbootstarter.service;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import com.github.jun.starter.dao.dsp.bean.SqlDao;
import com.github.jun.starter.dao.dsp.util.PageBean;
import com.github.jun.starter.dao.dsp.util.PageParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/24
 * @description:
 */
@Service
@Slf4j
public class TestService {

    @Autowired
    private SqlDao sqlDao;

    public List<Map> getBuyerList(){
        return sqlDao.getList("test", "getBuyerList", new HashMap());
    }

    //========================
    public List<Map> testGetList() {
        List<Map> list = sqlDao.getList("test", "testGetList", MapUtil.newHashMap());
        return list;
    }

    public PageBean testGetPageList() {
        PageBean pageBean = sqlDao.getPageList(
                "test",
                "testGetList",
                MapUtil.newHashMap(),
                PageParams.builder().pageNo(1).pageSize(10).build(),
                false
                );
        return pageBean;
    }

    public Map testGetMap() {
        Map map = sqlDao.getMap("test", "testGetList", MapUtil.of("username", "业务员"));
        return map;
    }

    public String testGetObject() {
        String str = sqlDao.getObject("test", "testGetObject", MapUtil.of("id", "1"), String.class);
        return str;
    }

    public void testUpdate() {
        int updateNum = sqlDao.update("test", "testUpdate",
                new MapBuilder(MapUtil.newHashMap()).put("id", "1").put("username", "超级管理员2").build());
        log.info("updateNum = {}", updateNum);
    }

    public void testBatchUpdate() {
        List<Map<String, Object>> whereList = new ArrayList<>();
        whereList.add(new MapBuilder(MapUtil.newHashMap()).put("id", "1").put("username", "超级管理员2").build());
        whereList.add(new MapBuilder(MapUtil.newHashMap()).put("id", "2").put("username", "业务员2").build());
        int[] updateResult = sqlDao.batchUpdate("test", "testUpdate", whereList);
        log.info("updateResult = {}", updateResult);
    }

    public int testGetCount() {
        int count = sqlDao.getCount("test", "testGetList", MapUtil.newHashMap());
        return count;
    }

    public boolean testExists() {
        return sqlDao.exists("test", "testGetList", MapUtil.of("username", "业务员"));
    }

}

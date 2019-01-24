package com.dragon.testdaospringbootstarter.service;

import com.github.jun.starter.dao.dsp.bean.SqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/24
 * @description:
 */
@Service
public class TestService {

    @Autowired
    private SqlDao sqlDao;

    public List<Map> getBuyerList(){
        return sqlDao.getList("test", "getBuyerList", new HashMap());
    }


}

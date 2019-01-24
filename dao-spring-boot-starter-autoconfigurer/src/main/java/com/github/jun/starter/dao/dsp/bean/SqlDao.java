package com.github.jun.starter.dao.dsp.bean;

import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
public interface SqlDao {

    List<Map> getList(String handlerId, String entryId, Map<String, Object> whereMap);

}

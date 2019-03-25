package com.github.jun.starter.dao.dsp.bean;

import com.github.jun.starter.dao.dsp.util.PageBean;
import com.github.jun.starter.dao.dsp.util.PageParams;

import java.util.List;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
public interface SqlDao {

    List<Map> getList(String handlerId, String entryId, Map<String, Object> whereMap);

    PageBean getPageList(String handlerId, String entryId, Map<String, Object> whereMap, PageParams pageParams, boolean startPageNoEqualsOne);

    Map<String, Object> getMap(String handlerId, String entryId, Map<String, Object> whereMap);

    <T> T getObject(String handlerId, String entryId, Map<String, Object> whereMap, Class<T> requiredType);

    int update(String handlerId, String entryId, Map<String, Object> whereMap);

    int[] batchUpdate(String handlerId, String entryId, List<Map<String, Object>> whereList);

    int getCount(String handlerId, String entryId, Map<String, Object> whereMap);

    boolean exists(String handlerId, String entryId, Map<String, Object> whereMap);

}

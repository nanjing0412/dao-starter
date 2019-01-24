package com.github.jun.starter.dao.dsp.sqlHandle;

import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
public interface TextHandle {

    StringBuffer manipulate(StringBuffer sqlText, Map whereClause);

}

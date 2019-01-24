package com.github.jun.starter.dao.dsp.sqlHandle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SqlParams {

    private StringBuffer sql;
    private Object[] params;

}

package com.github.jun.starter.dao.dsp.sqlHandle;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Setter
@NoArgsConstructor
public class TextHandleBigTokenReplace {

    private String startToken = "{";
    private String endToken = "}";
    private String nullValue = "";

    public SqlParams manipulate(StringBuffer text, Map model) {
        if (model == null) {
            model = Collections.EMPTY_MAP;
        }

        LinkedList arguments = new LinkedList();
        int i = 0;

        int start;
        for (int end = 0; (start = text.toString().indexOf(123, end)) >= 0; ++i) {
            end = text.toString().indexOf(125, start);
            String key = text.substring(start + 1, end);
            Object value = model.get(key);
            if (value == null) {
                throw new NullPointerException("Property '" + key + "' was not provided.");
            }

            arguments.add(value);
            text.replace(start, end + 1, " ? ");
            end -= key.length() + 2;
        }

        Object[] retureParams = arguments.toArray();
        return new SqlParams(text, retureParams);
    }

}

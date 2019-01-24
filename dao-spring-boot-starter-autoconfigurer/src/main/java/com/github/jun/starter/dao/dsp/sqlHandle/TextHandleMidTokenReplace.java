package com.github.jun.starter.dao.dsp.sqlHandle;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Setter
@NoArgsConstructor
public class TextHandleMidTokenReplace implements TextHandle {

    private String startToken = "[";
    private String endToken = "]";
    private String nullValue = "";

    public StringBuffer manipulate(StringBuffer text, Map model) {
        if (model == null) {
            model = Collections.EMPTY_MAP;
        }

        int i = 0;

        int start;
        for (int end = 0; (start = text.toString().indexOf(this.startToken, end)) >= 0; ++i) {
            end = text.toString().indexOf(this.endToken, start);
            String key = text.substring(start + 1, end);
            Object modelValue = model.get(key);
            text.replace(start, end + 1, modelValue == null ? this.nullValue : modelValue.toString());
            end -= key.length() + 2;
        }

        return text;
    }

}

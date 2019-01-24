package com.github.jun.starter.dao.dsp.sqlHandle;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Setter
@NoArgsConstructor
public class TextHandleFilter implements TextHandle {

    private String startToken = "/~";
    private String endToken = "~/";

    public StringBuffer manipulate(StringBuffer text, Map model) {
        if (model == null) {
            model = Collections.EMPTY_MAP;
        }

        boolean inverse = false;
        text.indexOf(this.endToken);

        int end;
        while ((end = text.indexOf(this.endToken)) >= 0) {
            int start = text.lastIndexOf(this.startToken, end);
            String key = text.substring(start + 2, text.indexOf(":", start));
            StringTokenizer st;
            Object modelValue;
            if (key.indexOf(44) != -1) {
                st = new StringTokenizer(key, ",");

                label85:
                do {
                    do {
                        if (!st.hasMoreElements()) {
                            break label85;
                        }

                        modelValue = model.get(key = st.nextToken());
                    } while (modelValue instanceof String && ((String) modelValue).length() == 0);
                } while (modelValue == null);
            } else if (key.indexOf(124) == -1) {
                if (key.indexOf(38) != -1) {
                    st = new StringTokenizer(key, "&");

                    while (st.hasMoreElements()) {
                        modelValue = model.get(key = st.nextToken());
                        if (modelValue == null || modelValue instanceof String && ((String) modelValue).length() == 0) {
                            break;
                        }
                    }
                }
            } else {
                st = new StringTokenizer(key, "|");

                label97:
                do {
                    do {
                        if (!st.hasMoreElements()) {
                            break label97;
                        }

                        modelValue = model.get(key = st.nextToken());
                    } while (modelValue instanceof String && ((String) modelValue).length() == 0);
                } while (modelValue == null);
            }

            if (inverse = key.startsWith("!")) {
                key = key.substring(1, key.length());
            }

            modelValue = model.get(key);
            if (modelValue instanceof String && ((String) modelValue).length() == 0) {
                modelValue = null;
            }

            if (inverse) {
                if (modelValue == null) {
                    text.replace(start, end + 2, text.substring(text.indexOf(":", start) + 1, end));
                } else {
                    text.replace(start, end + 2, "");
                }
            } else if (modelValue != null) {
                text.replace(start, end + 2, text.substring(text.indexOf(":", start) + 1, end));
            } else {
                text.replace(start, end + 2, "");
            }
        }

        return text;
    }

}

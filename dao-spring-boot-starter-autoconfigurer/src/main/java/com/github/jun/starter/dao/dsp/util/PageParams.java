package com.github.jun.starter.dao.dsp.util;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PageParams implements Serializable {

    private int pageNo;
    private int pageSize;

}

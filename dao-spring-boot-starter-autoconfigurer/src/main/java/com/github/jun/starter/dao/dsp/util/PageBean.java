package com.github.jun.starter.dao.dsp.util;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class PageBean<T> implements Serializable {

    private int pageNo = 0;  //第几页，页面从0开始，兼容JPA的分页起始页码
    private int pageSize = 10; //每页记录数
    private int totalCount;  //总记录数
    private int totalPages;  //总页数
    private List<T> contentList;  //分页之后的那一页的记录

    public static int calculateTotalPages(int totalCount, int pageSize) {
        return (totalCount % pageSize == 0) ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    //页码从0开始，影响起始页startIndex，分页内容contentList
    public PageBean (int pageNo, int pageSize, List<T> totalList){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalList.size();
        this.totalPages = calculateTotalPages(this.totalCount, this.pageSize);
                //(this.totalCount % this.pageSize == 0) ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;

        if ( !(pageNo >= 0) ){
            throw new IllegalArgumentException("页码pageNo不能小于0");
        }
        if( !(pageNo <= (totalPages==0 ? 0 : totalPages-1) ) ){
            throw new IllegalArgumentException("页码pageNo超过总页数");
        }

        int startIndex=pageNo*pageSize;
        int endIndex=startIndex+pageSize<=this.totalCount ? startIndex+pageSize : this.totalCount;

        this.contentList= totalList.subList(startIndex, endIndex);
    }

    //不拘泥于页码从0开始还是从1开始，因为已经计算出了分页内容contentList
    public PageBean (int pageNo, int pageSize, int totalCount, int totalPages, List<T> contentList){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
        this.contentList = contentList;
    }

}

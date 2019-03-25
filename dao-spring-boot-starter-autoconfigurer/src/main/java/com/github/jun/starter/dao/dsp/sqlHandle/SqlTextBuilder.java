package com.github.jun.starter.dao.dsp.sqlHandle;

import com.github.jun.starter.dao.dsp.util.PageParams;
import com.github.jun.starter.dao.dsp.util.SQLUtil;
import com.github.jun.starter.dao.dsp.util.SpringHolder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author: hujw
 * @date: 2019/1/23
 * @description:
 */
@Getter
@Setter
public class SqlTextBuilder {

    private List sqlTextHandleList;
    private String handlerId;
    private String entryId;
    private Map whereClause;
    private TextHandleBigTokenReplace bigTokenReplace;
    private Adapter adapter;
    private String querySql;
    private Object[] queryParams;

    public SqlTextBuilder(String handlerId, String entryId, Map whereClause) {
        if (this.sqlTextHandleList == null) {
            this.sqlTextHandleList = new LinkedList();
            this.sqlTextHandleList.add(new TextHandleFilter());
            this.sqlTextHandleList.add(new TextHandleMidTokenReplace());
        }

        this.bigTokenReplace = new TextHandleBigTokenReplace();
        this.handlerId = handlerId;
        this.entryId = entryId;
        this.whereClause = whereClause;
        SqlConfigure sqlConfigure = (SqlConfigure) SpringHolder.getBean(handlerId);
        Map adapterMap = sqlConfigure.getAdapters();
        this.adapter = (Adapter)adapterMap.get(entryId);
        if (this.adapter == null) {
            throw new RuntimeException("未找到对应的SQL【指定的entryId=" + entryId + "不存在】");
        } else {
            StringBuffer sqlText = new StringBuffer(this.adapter.getSql());
            if (whereClause == null) {
                whereClause = Collections.EMPTY_MAP;
            }

            Iterator iter = this.sqlTextHandleList.iterator();

            while(iter.hasNext()) {
                TextHandle handle = (TextHandle)iter.next();
                handle.manipulate(sqlText, whereClause);
            }

            SqlParams sqlQueryParams = this.bigTokenReplace.manipulate(sqlText, whereClause);
            this.querySql = sqlQueryParams.getSql().toString().replace("\t", " ").replace("\n", " ").trim();
            this.queryParams = sqlQueryParams.getParams();
        }
    }

    public String getQueryPagingSql(PageParams pageParams) {
        return SQLUtil.builderPageSQL(this.querySql, this.adapter.getDbType(), pageParams);
    }

    public String getCountEntiesSql() {
        return SQLUtil.builderCountSQL(this.querySql);
    }

}

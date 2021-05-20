package com.shuwen.network.bean;

/**
 * 列表请求体model
 * @param <Other> 其他字段
 */
public class ListRequestBodyModel<Other> {
    /**页号*/
    public int pageNo;
    /**每页记录条数*/
    public int pageSize;
    /**其他字段*/
    public Other other;
}

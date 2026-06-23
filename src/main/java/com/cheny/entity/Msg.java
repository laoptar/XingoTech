package com.cheny.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Msg {
    // HTTP 状态码
    private int result;
    // 状态描述
    private String msg;
    // 列表数据（分页查询时使用）
    private List<?> rows;
    // 对象数据（单个对象查询时使用）
    private Object data;
    // 扩展字段
    private Map<String, Object> extInfo = new HashMap<>();
    // 当前页数（分页列表时追加）
    private Integer pageIndex;
    // 每页条目数（分页列表时追加）
    private Integer pageSize;
    // 总条目数（分页列表时追加）
    private Long total;
    // 业务层（对象查询时追加）
    private String service;

    public static Msg success() {
        Msg result = new Msg();
        result.setResult(200);
        result.setMsg("OK");
        return result;
    }

    public static Msg failure() {
        Msg result = new Msg();
        result.setResult(400);
        result.setMsg("Bad Request");
        return result;
    }

    public Msg add(String key, Object value) {
        this.extInfo.put(key, value);
        return this;
    }

    // --- fluent setter ---

    public Msg setResult(int result) {
        this.result = result;
        return this;
    }

    public Msg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Msg setRows(List<?> rows) {
        this.rows = rows;
        return this;
    }

    public Msg setData(Object data) {
        this.data = data;
        return this;
    }

    public Msg setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public Msg setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Msg setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Msg setService(String service) {
        this.service = service;
        return this;
    }

    // --- getter / setter ---

    public int getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public List<?> getRows() {
        return rows;
    }

    public Object getData() {
        return data;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public String getService() {
        return service;
    }
}
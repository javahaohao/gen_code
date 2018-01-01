package com.github.javahao.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * usedfor：模板属性
 * Created by javahao on 2017/7/3.
 * auth：JavaHao
 */
public class Template {
    /**
     * 模板源
     */
    private String source;
    /**
     * 模板名字
     */
    private String name;
    /**
     * 模板输出位置
     */
    private String target;

    /**
     * table标签的内部扩展变量，可以覆盖全局变量
     */
    private Map<String,Object> extVars = new HashMap<String, Object>();

    public Template() {
    }

    public Template(String name, String target) {
        this.name = name;
        this.target = target;
    }

    public String getSource() {
        return source==null?"":source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Map<String, Object> getExtVars() {
        return extVars;
    }

    public void addExtVars(String key,Object value) {
        this.extVars.put(key,value);
    }
}

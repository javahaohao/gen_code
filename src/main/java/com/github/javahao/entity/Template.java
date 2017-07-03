package com.github.javahao.entity;

/**
 * usedfor：模板属性
 * Created by javahao on 2017/7/3.
 * auth：JavaHao
 */
public class Template {
    /**
     * 模板名字
     */
    private String name;
    /**
     * 模板输出位置
     */
    private String target;

    public Template() {
    }

    public Template(String name, String target) {
        this.name = name;
        this.target = target;
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
}

package com.github.javahao.config;

import com.github.javahao.entity.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * usedfor：生成表的配置
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class TableConfig {
    private String catalog;
    /**
     * 生成表的名称
     */
    private String tableName;
    /**
     * 生成名称
     */
    private String genName;
    /**
     * 所属数据库
     */
    private String schema;
    /**
     * 主键列名
     */
    private String primary;

    private String types;

    private String var;
    private String gen;
    /**
     * table标签的内部扩展变量，可以覆盖全局变量
     */
    private Map<String,Object> extVars = new HashMap<String, Object>();

    /**
     * 模板文件配置
     */
    private Map<String,Template> templateConfig = new HashMap<String, Template>();

    /**
     * 将配置信息加入到全局变量中
     * @return 返回扩展的全局变量
     */
    public Map<String,Object> cpProToVars(){
        Map<String,Object> data = new HashMap<String, Object>();
        data.putAll(CoreConfig.getVars());
        data.put("catalog",getCatalog());
        data.put("tableName",getTableName());
        data.put("schema",getSchema());
        data.put("genName",getGenName());
        data.put("primary",getPrimary());
        data.put("types",getTypes());
        data.put("gen",getGen());
        //增加table的扩展属性
        data.putAll(getExtVars());
        return data;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String[] getTypes() {
        return types!=null?types.split(","):null;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Map<String, Template> getTemplateConfig() {
        return templateConfig;
    }

    public void addTemplateConfig(Map<String, Template> templateConfig) {
        this.templateConfig.putAll(templateConfig);
    }

    public void addTemplateConfig(String name, Template template) {
        this.templateConfig.put(name,template);
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
    public boolean isGen(){
        return Boolean.TRUE.toString().equals(getGen());
    }

    public Map<String, Object> getExtVars() {
        return extVars;
    }

    public void addExtVars(String key,Object value) {
        this.extVars.put(key,value);
    }
}

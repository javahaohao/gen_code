package com.github.javahao.config;

import com.github.javahao.entity.Relation;
import com.github.javahao.entity.Table;
import com.github.javahao.entity.Template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 表结构
     */
    private Table table;
    /**
     * 关系
     */
    private List<Relation> relations = new ArrayList<Relation>();
    private Map<Relation,TableConfig> parents = new HashMap<Relation,TableConfig>();
    private Map<Relation,TableConfig> childs = new HashMap<Relation,TableConfig>();

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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void addRelations(Relation relation) {
        if(relation==null)
            return;
        this.relations.add(relation);
    }

    public Map<Relation,TableConfig> getParents() {
        return parents;
    }

    public void addParents(Relation relation,TableConfig parent) {
        if(parent==null)
            return;
        this.parents.put(relation,parent);
    }

    public Map<Relation,TableConfig> getChilds() {
        return childs;
    }

    public void addChilds(Relation relation,TableConfig child) {
        if(child==null)
            return;
        this.childs.put(relation,child);
    }
}

package com.github.javahao.entity;

import com.github.javahao.config.TableConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * usedfor：表的实体类
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class Table {
    private String tableCat;
    /**
     * 表所属数据库
     */
    private String tableSchema;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表类型
     */
    private String tableType;
    /**
     * 表引擎类型
     */
    private String engine;
    /**
     * 表备注
     */
    private String tableComment;
    /**
     * 拥有的字段
     */
    private List<Column> columns = new ArrayList<Column>();
    /**
     * 生成表的配置
     */
    private TableConfig tableConfig;

    public Table() {
    }

    public Table(String tableSchema, String tableName, String tableType, String engine, String tableComment) {
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.tableType = tableType;
        this.engine = engine;
        this.tableComment = tableComment;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName==null?"":tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void addColumns(Column c) {
        this.columns.add(c);
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchema='" + tableSchema + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableType='" + tableType + '\'' +
                ", engine='" + engine + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", columns=" + columns +
                ", tableConfig=" + tableConfig +
                '}';
    }
}

package com.github.javahao.entity;

/**
 * usedfor：字段实体类
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class Column {
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段类型char(30)
     */
    private String columnType;
    /**
     * 数据类型char
     */
    private String dataType;
    /**
     * 字段备注
     */
    private String columnComment;
    /**
     * 键值类型
     */
    private String columnKey;
    /**
     * 是否为空
     */
    private boolean nullable;
    /**
     * 字段长度
     */
    private Long columnLength;
    /**
     * 所属表
     */
    private Table table;
    /**
     * 是否主键
     */
    private boolean primary;

    public Column() {
    }

    public Column(String columnName, String columnType, String dataType, String columnComment, String columnKey, boolean isNullable, Long columnLength, Table table) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
        this.nullable = isNullable;
        this.columnLength = columnLength;
        this.table=table;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getDataType() {
        return dataType!=null?dataType.toUpperCase():dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment==null?"":columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public Long getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(Long columnLength) {
        this.columnLength = columnLength;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}

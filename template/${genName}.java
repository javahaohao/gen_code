package com.choice.schedule.crud.entity;

import com.choice.base.Base;
import java.util.Date;
/**
 * usedfor：${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
public class ${camel(table.tableName)?cap_first} extends Base<${camel(table.tableName)?cap_first}>{
	<#list table.columns as column>
    <#if !contains(excludeColumns,column.columnName)>
    /**
     * ${column.columnComment}
     */
    private ${typeSwitch[column.dataType]} ${camel(column.columnName)};
    </#if>
    </#list>

    <#list table.columns as column>
	<#if !contains(excludeColumns,column.columnName)>
    /**
     * 获取${column.columnComment}
     *
     */
    public ${typeSwitch[column.dataType]} get${camel(column.columnName)?cap_first}() {
        return ${camel(column.columnName)};
    }

    /**
     * 设置${column.columnComment}
     *
     */
    public void set${camel(column.columnName)?cap_first}(${typeSwitch[column.dataType]} ${camel(column.columnName)}) {
        this.${camel(column.columnName)} = ${camel(column.columnName)};
    }
	</#if>
    </#list>
}

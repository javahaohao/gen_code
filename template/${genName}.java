package com.github.${extVars.pkg}.entity.${extVars.module};

import com.github.javahao.base.BaseBean;
import java.util.List;
<#list parents?values as parent>
import com.github.${parent.extVars.pkg}.entity.${parent.extVars.module}.${parent.genName};
</#list>
<#list childs?values as child>
import com.github.${child.extVars.pkg}.entity.${child.extVars.module}.${child.genName};
</#list>
/**
 * usedfor：${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
public class ${genName} extends BaseBean<${genName}>{
	<#list table.columns as column>
    <#if !contains(excludeColumns,column.columnName)>
    /**
     * ${column.columnComment}
     */
    private ${typeSwitch[column.dataType]} ${camel(column.columnName)};
    </#if>
    </#list>
    <#list parents?values as parent>
    /**
     * ${parent.table.tableComment}
     */
    private ${parent.genName} ${camel(parent.genName)};
    </#list>

    <#list childs?values as child>
    /**
     * ${child.table.tableComment}集合
     */
    private List<${child.genName}> ${camel(child.genName)}List;
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

    <#list parents?values as parent>
    /**
     * 获取${parent.table.tableComment}
     *
     */
    public ${parent.genName} get${parent.genName}() {
        return ${camel(parent.genName)};
    }

    /**
     * 获取${parent.table.tableComment}
     *
     */
    public void set${parent.genName}(${parent.genName} ${camel(parent.genName)}) {
        this.${camel(parent.genName)} = ${camel(parent.genName)};
    }
    </#list>
    <#list childs?values as child>
    /**
     * 获取${child.table.tableComment}集合
     *
     */
    public List<${child.genName}> get${child.genName}List() {
        return ${camel(child.genName)}List;
    }

    /**
     * 获取${child.table.tableComment}集合
     *
     */
    public void set${child.genName}List(List<${child.genName}> ${camel(child.genName)}List) {
        this.${camel(child.genName)}List = ${camel(child.genName)}List;
    }
    </#list>
}

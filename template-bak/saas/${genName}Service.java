package com.yazuo.ipos.chains.service.scmzb;
import com.yazuo.ipos.chains.bean.dao.scmzb.${camel(table.tableName)?cap_first};

/**
 * usedfor：[service]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
public interface ${camel(table.tableName)?cap_first}Service extends ScmBaseService<${camel(table.tableName)?cap_first}> {
}
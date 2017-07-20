package com.yazuo.ipos.chains.dao.scmzb;
import com.yazuo.ipos.chains.bean.dao.scmzb.${camel(table.tableName)?cap_first};

/**
 * usedfor：[DAO]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
public interface ${camel(table.tableName)?cap_first}Dao extends ScmBaseDao<${camel(table.tableName)?cap_first}> {
}
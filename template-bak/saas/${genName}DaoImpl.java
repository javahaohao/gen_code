package com.yazuo.ipos.chains.dao.impl.scmzb;
import com.yazuo.ipos.chains.bean.dao.scmzb.${camel(table.tableName)?cap_first};
import com.yazuo.ipos.chains.dao.scmzb.${camel(table.tableName)?cap_first}Dao;
import org.springframework.stereotype.Repository;

/**
 * usedfor：[daoimpl]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Repository
public class ${camel(table.tableName)?cap_first}DaoImpl extends ScmBaseDaoImpl<${camel(table.tableName)?cap_first}> implements ${camel(table.tableName)?cap_first}Dao {
}
package com.yazuo.ipos.chains.service.impl.scmzb;
import com.yazuo.ipos.chains.bean.dao.scmzb.${camel(table.tableName)?cap_first};
import com.yazuo.ipos.chains.dao.scmzb.${camel(table.tableName)?cap_first}Dao;
import com.yazuo.ipos.chains.service.scmzb.${camel(table.tableName)?cap_first}Service;
import org.springframework.stereotype.Service;

/**
 * usedfor：[serviceimpl]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Service
public class ${camel(table.tableName)?cap_first}ServiceImpl extends ScmBaseServiceImpl<${camel(table.tableName)?cap_first},${camel(table.tableName)?cap_first}Dao> implements ${camel(table.tableName)?cap_first}Service {
}
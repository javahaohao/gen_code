package com.choice.schedule.crud.mapper;

import com.choice.base.MyBatisMapper;
import com.choice.schedule.crud.entity.${camel(table.tableName)?cap_first};
import com.choice.base.BaseMapper;
/**
 * usedfor：[DAO]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@MyBatisMapper
public interface ${camel(table.tableName)?cap_first}Mapper extends BaseMapper<${camel(table.tableName)?cap_first}>{
}
package com.choice.schedule.crud.service;

import org.springframework.stereotype.Service;
import com.choice.base.BaseService;
import com.choice.schedule.crud.entity.${camel(table.tableName)?cap_first};
import com.choice.schedule.crud.service.${camel(table.tableName)?cap_first}Mapper;
/**
 * usedfor：[service]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Service
public class ${camel(table.tableName)?cap_first}Service extends BaseService<${camel(table.tableName)?cap_first},${camel(table.tableName)?cap_first}Mapper>{
}
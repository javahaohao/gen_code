package com.github.${extVars.pkg}.serviceimpl.${extVars.module};

import com.github.javahao.base.BaseService;
import com.github.${extVars.pkg}.dao.${extVars.module}.${genName}Mapper;
import com.github.${extVars.pkg}.entity.${extVars.module}.${genName};
import com.github.${extVars.pkg}.service.${extVars.module}.${genName}Service;
import org.springframework.stereotype.Service;

/**
 * usedfor：[serviceimpl]${table.tableComment}
 * created by ${auth} on ${.now?string("yyyy/mm/dd hh:mm:ss")}.
 * auth：${auth}
 */
@Service("${camel(genName)}Service")
public class ${genName}ServiceImpl extends BaseService<${genName},${genName}Mapper> implements ${genName}Service{
}
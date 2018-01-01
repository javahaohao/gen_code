package com.github.${extVars.pkg}.dao.${extVars.module};

import com.github.javahao.annotation.MyBatisMapper;
import com.github.javahao.base.BaseMapper;
import com.github.${extVars.pkg}.entity.${extVars.module}.${genName};
/**
 * usedfor：[DAO]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@MyBatisMapper
public interface ${genName}Mapper extends BaseMapper<${genName}>{
}
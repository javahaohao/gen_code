package ;

import java.util.List;
/**
 * usedfor：[DAO]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
public interface ${camel(table.tableName)?cap_first}Mapper{
    List<${camel(table.tableName)?cap_first}> list(${camel(table.tableName)?cap_first} ${camel(table.tableName)});

    int insert(${camel(table.tableName)?cap_first} ${camel(table.tableName)});

    int update(${camel(table.tableName)?cap_first} ${camel(table.tableName)});

    int deleteBatch(List<String> idList);
}
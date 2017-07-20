package ;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * usedfor：[service]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Service
public interface ${camel(table.tableName)?cap_first}Service{
    private final transient Log log = LogFactory.getLog(${camel(table.tableName)?cap_first}Service.class);
    @Autowired
    private ${camel(table.tableName)?cap_first}Mapper ${camel(table.tableName)}Mapper;
    @Autowired
    private PageManager<${camel(table.tableName)?cap_first}> pageManager;
    /**
     * 增删改查
     * @param ${camel(table.tableName)}
     * @return
     * @throws CRUDException
     */
    public List<${camel(table.tableName)?cap_first}> list(${camel(table.tableName)?cap_first} ${camel(table.tableName)},Page page) throws CRUDException{
        try{
            return pageManager.selectPage(${camel(table.tableName)}, page, ${camel(table.tableName)?cap_first}Mapper.class.getName()+".list");
        }catch(Exception e){
            log.error(e);
            throw new CRUDException(e);
        }
    }
    public ${camel(table.tableName)?cap_first} get(String id){
        ${camel(table.tableName)?cap_first} query = new ${camel(table.tableName)?cap_first}();
        query.setId(id);
        List<${camel(table.tableName)?cap_first}> list = ${camel(table.tableName)}Mapper.list(query);
        if(list!=null&&list.size()>0)
            return list.get(0);
        return null;
    }
    public void save(${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        try{
            ${camel(table.tableName)}.setId(CodeHelper.createUUID());
            ${camel(table.tableName)}Mapper.insert(${camel(table.tableName)});
        }catch(Exception e){
            log.error(e);
            throw new CRUDException(e);
        }
    }
    public void update(${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        try{
            ${camel(table.tableName)}Mapper.update(${camel(table.tableName)});
        }catch(Exception e){
            log.error(e);
            throw new CRUDException(e);
        }
    }
    public void delete(${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        try{
            ${camel(table.tableName)}Mapper.deleteBatch(Arrays.asList(${camel(table.tableName)}.getId().split(",")));
        }catch(Exception e){
            log.error(e);
            throw new CRUDException(e);
        }
    }
}
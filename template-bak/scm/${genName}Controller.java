package ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * usedfor：[控制层]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Controller
@RequestMapping("${(camel(table.tableName)?cap_first)?lower_case}")
public class ${camel(table.tableName)?cap_first}Controller{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ${camel(table.tableName)?cap_first}Service ${camel(table.tableName)}Service;

    @RequestMapping("/")
    public ModelAndView list(ModelMap modelMap,${camel(table.tableName)?cap_first} ${camel(table.tableName)}, Page page) throws CRUDException{
        modelMap.put("list", ${camel(table.tableName)}Service.list(${camel(table.tableName)},page));
        modelMap.put("pageobj", page);
        return new ModelAndView("${camel(table.tableName)?lower_case}/list",modelMap);
    }
    @RequestMapping("/edit")
    public ModelAndView edit(ModelMap modelMap,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        if(null!=${camel(table.tableName)}.getId()&&${camel(table.tableName)}.getId().length>0)
            modelMap.put("${camel(table.tableName)}", ${camel(table.tableName)}Service.get(${camel(table.tableName)}.getId()));
        return new ModelAndView("${camel(table.tableName)?lower_case}/edit", modelMap);
    }
    @RequestMapping("/save")
    public ModelAndView save(ModelMap modelMap,${camel(table.tableName)?cap_first} ${camel(table.tableName)},@ModelAttribute("accountId")String accountId) throws CRUDException{
        ${camel(table.tableName)}Service.save(${camel(table.tableName)});
        return new ModelAndView(StringConstant.ACTION_DONE, modelMap);
    }
    @RequestMapping("/update")
    public ModelAndView update(ModelMap modelMap,${camel(table.tableName)?cap_first} ${camel(table.tableName)},@ModelAttribute("accountId")String accountId) throws CRUDException{
        ${camel(table.tableName)}Service.update(${camel(table.tableName)});
        return new ModelAndView(StringConstant.ACTION_DONE, modelMap);
    }
    @RequestMapping("/delete")
    public String delete(ModelMap modelMap,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        ${camel(table.tableName)}Service.delete(${camel(table.tableName)});
        return "redirect:/${camel(table.tableName)?lower_case}.do";
    }
}
package com.choice.schedule.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.Date;
import com.choice.config.CodeHelper;
import com.choice.base.CRUDException;
import com.choice.schedule.crud.entity.${camel(table.tableName)?cap_first};
import com.choice.schedule.crud.service.${camel(table.tableName)?cap_first}Service;
import java.util.Arrays;

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
    public String list(Model model,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        model.addAttribute("list", ${camel(table.tableName)}Service.find(${camel(table.tableName)}));
        return "${camel(table.tableName)?lower_case}/list";
    }
    @RequestMapping("/edit")
    public String edit(Model model,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        if(null!=${camel(table.tableName)}.getId()&&${camel(table.tableName)}.getId().length()>0)
            model.addAttribute("${camel(table.tableName)}", ${camel(table.tableName)}Service.findOne(${camel(table.tableName)}));
        return "${camel(table.tableName)?lower_case}/edit";
    }
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(Model model,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        ${camel(table.tableName)}.setId(CodeHelper.createUUID());
        ${camel(table.tableName)}.setCreateTime(new Date());
        ${camel(table.tableName)}Service.save(${camel(table.tableName)});
        return "redirect:/${(camel(table.tableName)?cap_first)?lower_case}/";
    }
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String update(Model model,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        ${camel(table.tableName)}.setUpdateTime(new Date());
        ${camel(table.tableName)}Service.update(${camel(table.tableName)});
        return "redirect:/${(camel(table.tableName)?cap_first)?lower_case}/";
    }
    @RequestMapping("/delete")
    public String delete(Model model,${camel(table.tableName)?cap_first} ${camel(table.tableName)}) throws CRUDException{
        ${camel(table.tableName)}Service.deleteBatch(Arrays.asList(${camel(table.tableName)}.getId().split(",")));
        return "redirect:/${(camel(table.tableName)?cap_first)?lower_case}/";
    }
}
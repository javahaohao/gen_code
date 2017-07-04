package com.yazuo.ipos.chains.controller.scmzb;
import com.yazuo.ipos.chains.bean.dao.scmzb.${camel(table.tableName)?cap_first};
import com.yazuo.ipos.chains.bean.dto.Page;
import com.yazuo.ipos.chains.controller.BaseController;
import com.yazuo.ipos.chains.service.UserService;
import com.yazuo.ipos.chains.service.scmzb.${camel(table.tableName)?cap_first}Service;
import com.yazuo.ipos.chains.utils.common.AirUtils;
import com.yazuo.ipos.dto.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * usedfor：[控制层]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Controller
@RequestMapping("scm/${(camel(table.tableName)?cap_first)?lower_case}")
public class ${camel(table.tableName)?cap_first}Controller extends BaseController<${camel(table.tableName)?cap_first}> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ${camel(table.tableName)?cap_first}Service ${camel(table.tableName)}Service;
    @Autowired
    private UserService userService;
    /**
     * 按照分页查询数据
     * @param ${camel(table.tableName)} 查询条件
     * @return 返回结果
     * @throws Exception 异常
     */
    @RequestMapping(value = "/findDataForPage", method = RequestMethod.POST)
    @ResponseBody
    public MessageBody findDataForPage(@RequestBody ${camel(table.tableName)?cap_first} ${camel(table.tableName)})
            throws Exception {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            Page<${camel(table.tableName)?cap_first}> page = this.getPage();
            ${camel(table.tableName)}.setTenantId(userService.findTenantId());
            if (AirUtils.hv(${camel(table.tableName)}.getPage()))	page.setPage(${camel(table.tableName)}.getPage());
            if (AirUtils.hv(${camel(table.tableName)}.getRows()))	page.setLimit(${camel(table.tableName)}.getRows());
            page = ${camel(table.tableName)}Service.findDataForPage(page, ${camel(table.tableName)});
            map.put("page", page);
            return MessageBody.getMessageBody(true, map);
        } catch (Exception e) {
            logger.error(this.getClass().getName()+".findDataForPage", e);
            return MessageBody.getErrorMessageBody("系统报错了!");
        }
    }

    /**
     * 新增对数据
     * @param ${camel(table.tableName)} 数据
     * @return 返回结果
     * @throws Exception 异常
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public MessageBody add(@RequestBody ${camel(table.tableName)?cap_first} ${camel(table.tableName)})
            throws Exception {
        try {
            return MessageBody.getMessageBody(${camel(table.tableName)}Service.add(${camel(table.tableName)})>0);
        } catch (Exception e) {
            logger.error(this.getClass().getName()+".add", e);
            return MessageBody.getErrorMessageBody("系统报错了!");
        }
    }

    /**
     * 修改数据
     * @param ${camel(table.tableName)} 数据
     * @return 返回结果
     * @throws Exception 异常
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public MessageBody update(@RequestBody ${camel(table.tableName)?cap_first} ${camel(table.tableName)})  throws Exception{
        try {
            return MessageBody.getMessageBody(${camel(table.tableName)}Service.update(${camel(table.tableName)})>0);
        } catch (Exception e) {
            logger.error(this.getClass().getName()+".update", e);
            return MessageBody.getErrorMessageBody("系统报错了!");
        }
    }

    /**
     * 删除数据
     * @param ${camel(table.tableName)} 删除对象
     * @return 返回结果
     * @throws Exception 异常
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public MessageBody delete(@RequestBody ${camel(table.tableName)?cap_first} ${camel(table.tableName)})  throws Exception{
        try {
            return MessageBody.getMessageBody(${camel(table.tableName)}Service.delete(${camel(table.tableName)})>0);
        } catch (Exception e) {
            logger.error(this.getClass().getName()+".delete", e);
            return MessageBody.getErrorMessageBody("系统报错了!");
        }
    }
}
package com.github.${extVars.pkg}.controller.${extVars.module};

import com.github.javahao.base.BaseController;
import com.github.${extVars.pkg}.entity.${extVars.module}.${genName};
import com.github.${extVars.pkg}.service.${extVars.module}.${genName}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import com.github.javahao.annotation.Module;
/**
 * usedfor：[控制层]${table.tableComment}
 * Created by ${auth} on ${.now?string("yyyy/MM/dd HH:mm:ss")}.
 * auth：${auth}
 */
@Module("${extVars.module}")
@Controller
@RequestMapping("/${extVars.module}")
public class ${genName}Controller extends BaseController<${genName},${genName}Service> {
    @Resource
    private ${genName}Service ${genName?uncap_first}Service;

    @Override
    protected String pkg() {
        return "${extVars.pkg}/${extVars.module}";
    }

    @Override
    protected ${genName}Service getService() {
        return ${genName?uncap_first}Service;
    }
    @Override
    protected String model() {
        return "${genName?uncap_first}";
    }

    @Override
    protected String modelMsg() {
        return "${table.tableComment}";
    }
}
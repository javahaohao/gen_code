package com.github.javahao.gen;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.config.TableConfig;
import com.github.javahao.db.TableQuery;
import com.github.javahao.entity.Table;
import com.github.javahao.entity.Template;
import com.github.javahao.util.FreeMarkerUtil;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * usedfor：代码生成类
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class GenCode {
    private static GenCode genCode = new GenCode();
    public static GenCode getInstance(){
        return genCode;
    }
    /**
     * 按照配置生成代码
     */
    public void gen(){
        List<TableConfig> tableConfigs= CoreConfig.getGens();
        if (tableConfigs!=null&&tableConfigs.size()>0){
            Writer writer = null;
            for (TableConfig tc:tableConfigs){
                Map<String,Template> templates = tc.getTemplateConfig();
                Table table = TableQuery.getInstance().getTable(tc);
                if(table==null)
                    continue;
                System.out.println("[GenCode]  ================ Begin Generate Table ["+tc.getTableName()+"("+table.getColumns().size()+" columns)],Loading........====");
                Map<String,Object> vars = tc.cpProToVars();
                vars.put(tc.getVar(),table);
                for(Map.Entry<String,Template> entry:templates.entrySet()){
                    try {
                        File target = new File(entry.getValue().getTarget()+File.separator+FreeMarkerUtil.renderStr(entry.getKey(),vars));
                        writer = new OutputStreamWriter(new FileOutputStream(target), "utf-8");
                        FreeMarkerUtil.process(entry.getKey(),vars,writer);
                        System.out.println("[GenCode] The File ["+target.getCanonicalPath()+"] Already Generated！");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[GenCode] ================       End Generate Table ["+tc.getTableName()+"] Success!.    ================");
            }
        }
    }
}

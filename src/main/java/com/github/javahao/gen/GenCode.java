package com.github.javahao.gen;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.config.TableConfig;
import com.github.javahao.db.TableQuery;
import com.github.javahao.entity.Table;
import com.github.javahao.entity.Template;
import com.github.javahao.util.FreeMarkerUtil;

import java.io.*;
import java.util.HashMap;
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
            Map<String,Object> configTables = new HashMap<String, Object>();
            //先加载所有配置表信息，方便在模板中实现一对多代码生成
            for (int i=0;i<tableConfigs.size();i++){
                Table table = TableQuery.getInstance().getTable(tableConfigs.get(i),i==tableConfigs.size()-1);
                configTables.put(tableConfigs.get(i).getVar(),table);
            }
            for (TableConfig tc:tableConfigs){
                if (!tc.isGen())
                    continue;
                Map<String,Template> templates = tc.getTemplateConfig();
                Table table = (Table) configTables.get(tc.getVar());
                if(table==null)
                    continue;
                System.out.println("[GenCode]  ================ Begin Generate Table ["+tc.getTableName()+"("+table.getColumns().size()+" columns)],Loading........====");
                Map<String,Object> vars = tc.cpProToVars();
                vars.putAll(configTables);
                for(Map.Entry<String,Template> entry:templates.entrySet()){
                    try {
                        File target = new File(FreeMarkerUtil.renderStr((entry.getValue().getTarget()+File.separator+entry.getKey()),vars));
                        if(!target.getParentFile().exists())
                            target.getParentFile().mkdirs();
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

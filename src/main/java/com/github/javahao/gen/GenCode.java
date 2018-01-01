package com.github.javahao.gen;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.config.TableConfig;
import com.github.javahao.db.TableQuery;
import com.github.javahao.entity.Relation;
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
            combinationRelations();
            for (TableConfig tc:tableConfigs){
                if (!tc.isGen()) {
                    continue;
                }
                Map<String,Object> configTables = CoreConfig.getConfigMap();
                Map<String,Template> templates = tc.getTemplateConfig();
                TableConfig tableConfig = (TableConfig) configTables.get(tc.getVar());
                if(tableConfig==null||tableConfig.getTable()==null)
                    continue;
                System.out.println("[GenCode]  ================ Begin Generate Table ["+tc.getTableName()+"("+tableConfig.getTable().getColumns().size()+" columns)],Loading........====");
                for(Map.Entry<String,Template> entry:templates.entrySet()){
                    try {
                        File target = new File(FreeMarkerUtil.renderStr((entry.getValue().getTarget()+File.separator+entry.getKey()),tc));
                        if(!target.getParentFile().exists())
                            target.getParentFile().mkdirs();
                        writer = new OutputStreamWriter(new FileOutputStream(target), "utf-8");
                        FreeMarkerUtil.process(entry.getValue().getSource()+entry.getKey(),tc,writer);
                        System.out.println("[GenCode] The File ["+target.getCanonicalPath()+"] Already Generated！");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[GenCode] ================       End Generate Table ["+tc.getTableName()+"] Success!.    ================");
            }
        }
    }

    /**
     * 组合表之间的关系
     */
    public void combinationRelations(){
        List<TableConfig> tableConfigs= CoreConfig.getGens();
        if (tableConfigs!=null&&tableConfigs.size()>0){
            Map<String,Object> configMap = CoreConfig.getConfigMap();
            //先加载所有配置表信息，方便在模板中实现多关系代码生成
            for (int i=0;i<tableConfigs.size();i++){
                TableQuery.getInstance().getTable(tableConfigs.get(i),i==tableConfigs.size()-1);
            }
            for(TableConfig tc : tableConfigs){
                for(Relation r:tc.getRelations()){
                    switch (r.getType()){
                        case ONE_TO_ONE:
                            tc.addParents(r,(TableConfig) configMap.get(r.getTarget()));
                            break;
                        case MANY_TO_ONE:
                            tc.addParents(r,(TableConfig) configMap.get(r.getTarget()));
                            break;
                        case ONE_TO_MANY:
                            tc.addChilds(r,(TableConfig) configMap.get(r.getTarget()));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}

package com.github.javahao.parser;

import com.github.javahao.config.CoreConfig;
import com.github.javahao.config.TableConfig;
import com.github.javahao.entity.Dialog;
import com.github.javahao.entity.Relation;
import com.github.javahao.entity.Template;
import com.github.javahao.util.AnalysisObject;
import com.github.javahao.util.FreeMarkerUtil;
import freemarker.template.TemplateModelException;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * usedfor：配置文件解析类
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class ConfigParser {
    /**
     * 配置文件解析
     * @param configPath 配置文件路径
     */
    public static void parserConfig(String configPath){
        System.out.println("[GenCode] |================ Begin Loading [GenCode] Settings Wait Please!  ================|");
        //获取根节点元素对象
        Element node = XmlParser.getDocument(configPath).getRootElement();
        //加载jdbc配置
        parserJdbcConfig(node);
        //加载全局变量
        parserSettings(node.element("settings"));
        System.out.println("[GenCode] |---------------- Loaded Global Variable Configuration Success!  ----------------|");
        //加载到方言配置
        parserDialogs(node.element("dialogs"));
        //加载freemarker模板插件配置
        parserFreeMarkerConfig(node);
        System.out.println("[GenCode] |---------------- Loaded Database Dialogs Configuration Success! ----------------|");
        System.out.println("[GenCode] |================ End Loading [GenCode] Settings Success!        ================|");
    }

    /**
     * 增加freemarker插件的配置类
     * @param node config节点
     */
    public static void parserFreeMarkerConfig(Element node){
        if(node!=null){
            Element freemarker = node.element("freemarker");
            if(freemarker!=null){
                List<Element> plugins = freemarker.elements();
                if(plugins!=null&&plugins.size()>0){
                    for(Element p : plugins){
                        try {
                            FreeMarkerUtil.addSharedVariable(p.attributeValue("name"),Class.forName(p.attributeValue("value")).newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (TemplateModelException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    /**
     * 解析jdbc配置
     * @param node settings节点
     */
    public static void parserJdbcConfig(Element node){
        if(node!=null){
            Element jdbcConfig = node.element("jdbcConfig");
            if(jdbcConfig!=null){
                List<Element> properties = jdbcConfig.elements();
                if(properties!=null&&properties.size()>0){
                    for(Element e : properties){
                        CoreConfig.addJdbcConfig(e.getName(),e.getText());
                    }
                }
            }
        }
    }
    /**
     * 解析设置配置
     * @param node settings节点
     */
    public static void parserSettings(Element node){
        if(node!=null){
            CoreConfig.addVars(parserVars(node));
            CoreConfig.setLibDir(FreeMarkerUtil.render(node.elementText("libDir")));
            CoreConfig.setTemplatePath(FreeMarkerUtil.render(node.elementText("templatePath")));
            CoreConfig.setGenPath(FreeMarkerUtil.render(node.elementText("genPath")));
            CoreConfig.setEncoding(FreeMarkerUtil.render(node.elementText("encoding")));
            CoreConfig.addGens(parserGens(node));
        }
    }

    /**
     * 加载生成表的配置
     * @param node settings节点
     * @return 返回生成配置
     */
    public static List<TableConfig> parserGens(Element node){
        List<TableConfig> tableConfigs = new ArrayList<TableConfig>();
        if(node!=null){
            Element gensE = node.element("gens");
            if(gensE!=null){
                List<Element> tables = gensE.elements("table");
                if(tables!=null&&tables.size()>0){
                    for(Element te : tables){
                        final TableConfig tc = new TableConfig();
                        //模板配置
                        List<Element> templates = te.elements("template");
                        if(templates!=null&&templates.size()>0){
                            for(Element teme : templates){
                                final Template temp = new Template();
                                fillObj(temp,teme,false,new ParserHandler() {
                                    public void handler(String field, Object value) {
                                        temp.addExtVars(field,value);
                                    }
                                });
                                tc.addTemplateConfig(temp.getName(),temp);
                            }
                        }else
                            tc.addTemplateConfig(getTemplates());
                        //关系配置
                        List<Element> relations = te.elements("relation");
                        if(relations!=null&&relations.size()>0){
                            for(Element r : relations){
                                final Relation relation = new Relation();
                                fillObj(relation,r,false,null);
                                tc.addRelations(relation);
                            }
                        }
                        tableConfigs.add(fillObj(tc, te, true, new ParserHandler() {
                            public void handler(String field, Object value) {
                                tc.addExtVars(field,value);
                            }
                        }));
                        CoreConfig.addConfigMap(tc.getVar(),tc);
                    }
                }
            }
        }
        return tableConfigs;
    }
    /**
     * 获取模板路径下的所有模板文件
     * @return 所有模板文件
     */
    public static Map<String,Template> getTemplates(){
        File tempFolder = new File(CoreConfig.getTemplatePath());
        File[] files = null;
        if(tempFolder.exists()&&tempFolder.isDirectory()){
            files = tempFolder.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return true;
                    //name.endsWith(".ftl");
                }
            });
        }
        Map<String,Template> templateConfig =  new HashMap<String, Template>();
        for(File f : files){
            templateConfig.put(f.getName(),new Template(f.getName(),CoreConfig.getGenPath()));
        }
        return templateConfig;
    }
    /**
     * 解析变量配置
     * @param node  settings节点
     * @return 返回解析完的所有变量
     */
    public static Map<String,Object> parserVars(Element node){
        Map<String,Object> vars = new HashMap<String, Object>();
        if(node!=null){
            Element varE = node.element("vars");
            if(varE!=null){
                //加在普通变量
                List<Element> childs = varE.elements("var");
                if(childs!=null&&childs.size()>0){
                    for(Element c:childs){
                        vars.put(c.attributeValue("name"),c.attributeValue("value"));
                    }
                }
                //加载map变量
                vars.putAll(parserVarMap(varE));
                //加载list变量
                vars.putAll(parserVarList(varE));
            }
        }
        return vars;
    }

    /**
     * 解析全局变量map类型
     * @param node vars节点
     * @return 返回map类型全局变量
     */
    public static Map<String,Object> parserVarMap(Element node){
        Map<String,Object> vars = new HashMap<String, Object>();
        if(node!=null){
            List<Element> childs = node.elements("map");
            if(childs!=null&&childs.size()>0){
                for(Element map:childs){
                    Map<String,Object> mapItems = new HashMap<String, Object>();
                    List<Element> mapValues = map.elements();
                    if(mapValues!=null&&mapValues.size()>0){
                        for(Element value : mapValues){
                            mapItems.put(value.attributeValue("name"),value.attributeValue("value"));
                        }
                    }
                    vars.put(map.attributeValue("name"),mapItems);
                }
            }
        }
        return vars;
    }

    /**
     * 解析list类型全局变量
     * @param node vars节点
     * @return 返回所有list类型变量
     */
    public static Map<String,Object> parserVarList(Element node){
        Map<String,Object> vars = new HashMap<String, Object>();
        if(node!=null){
            List<Element> childs = node.elements("list");
            if(childs!=null&&childs.size()>0){
                for(Element list : childs){
                    List<Element> itemsE = list.elements();
                    List<String> itemsContainer = new ArrayList<String>();
                    for(Element item : itemsE){
                        itemsContainer.add(item.getText());
                    }
                    vars.put(list.attributeValue("name"),itemsContainer);
                }
            }
        }
        return vars;
    }
    /**
     * 加载方言配置
     * @param node 方言节点
     */
    public static void parserDialogs(Element node){
        if(null!=node){
            //设置当前使用的数据库方言
            CoreConfig.setUse(node.attribute("use").getValue());
            System.out.println("[GenCode] |---------------- Use Database Dialogs Configuration ["+node.attribute("use").getValue()+"]! --------------|");
            List<Element> elements = node.elements("dialog");
            for(Element e : elements){
                Dialog dialog = new Dialog();
                CoreConfig.addDialogs(fillObj(dialog,e));
            }
        }
    }

    /**
     * 将指定节点的属性设置到对象里面
     * @param obj 对象
     * @param element 节点
     * @return 返回结果
     */
    public static <T> T fillObj(T obj, Element element){
        return fillObj(obj,element,true,null);
    }
    /**
     * 将指定节点的属性设置到对象里面
     * @param obj 对象
     * @param element 节点
     * @param tempflag 模板化标识
     * @return 返回结果
     */
    public static <T> T fillObj(T obj, Element element,boolean tempflag,ParserHandler parserHandler){
        if(element!=null){
            List<Attribute> attributes = element.attributes();
            for(Attribute a:attributes){
                if(parserHandler!=null&&!AnalysisObject.containsField(obj.getClass(),a.getName())){
                    parserHandler.handler(a.getName(),a.getValue());
                    continue;
                }
                AnalysisObject.invokeSetter(obj,a.getName(),
                        tempflag?FreeMarkerUtil.render(a.getValue()):a.getValue());
            }
        }
        return obj;
    }
}

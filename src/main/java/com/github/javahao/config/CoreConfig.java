package com.github.javahao.config;

import com.github.javahao.entity.Dialog;
import com.github.javahao.freemarker.CollectionContains;
import com.github.javahao.freemarker.UnderlineCapFirst;

import java.util.*;

/**
 * usedfor：核心配置
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class CoreConfig {
    /**
     * jdbc配置
     */
    private static Properties jdbcConfig = new Properties();
    /**
     * 数据库方言配置容器
     */
    private static Map<String,Dialog> dialogConfigs = new HashMap<String, Dialog>();

    /***
     * 当前使用的方言名称
     */
    private static String use;
    /**
     * 生成器的jar包文件夹路径
     */
    private static String libDir;
    /**
     * 生成器的模板路径
     */
    private static String templatePath;
    /**
     * 生成器省成位置路径
     */
    private static String genPath;
    /**
     * 编码
     */
    private static String encoding;
    /**
     * 全局变量配置
     */
    private static Map<String,Object> vars = new HashMap<String, Object>(){
        {
            put("camel",new UnderlineCapFirst());
            put("contains",new CollectionContains());
        }
    };
    /**
     * 生成表的配置
     */
    private static List<TableConfig> gens = new ArrayList<TableConfig>();

    public static Properties getJdbcConfig() {
        return jdbcConfig;
    }

    public static void addJdbcConfig(String key,String value) {
        CoreConfig.jdbcConfig.put(key,value);
    }

    /**
     * 增加数据库连接的方言配置
     * @param dialogs 多方言
     */
    public static void addDialogs(Map<String,Dialog> dialogs){
        dialogConfigs.putAll(dialogs);
    }
    /**
     * 增加数据库连接的方言配置
     * @param dialog 单个方言
     */
    public static void addDialogs(Dialog dialog){
        dialogConfigs.put(dialog.getName(),dialog);
    }
    /**
     * 获取方言配置
     * @return 返回结果
     */
    public static Map<String,Dialog> getDialogConfigs(){
        return dialogConfigs;
    }
    /**
     * 按照方言名称获取方言配置
     * @param name 方言名称
     * @return 返回结果
     */
    public static Dialog getDialog(String name){
        return getDialogConfigs().get(name);
    }
    /**
     * 获取当前用的方言
     * @return 返回结果
     */
    public static Dialog getDialog(){
        return getDialogConfigs().get(CoreConfig.use);
    }

    /**
     * 当前使用的方言名称
     * @param use 方言名称
     */
    public static void setUse(String use) {
        CoreConfig.use = use;
    }

    public static String getLibDir() {
        return libDir;
    }

    public static void setLibDir(String libDir) {
        CoreConfig.libDir = libDir;
    }

    public static String getTemplatePath() {
        return templatePath;
    }

    public static void setTemplatePath(String templatePath) {
        CoreConfig.templatePath = templatePath;
    }

    public static String getGenPath() {
        return genPath;
    }

    public static void setGenPath(String genPath) {
        CoreConfig.genPath = genPath;
    }

    public static Map<String, Object> getVars() {
        return vars;
    }

    public static void addVars(Map<String, Object> vars) {
        CoreConfig.vars.putAll(vars);
    }
    public static void addVars(String name,Object value) {
        CoreConfig.vars.put(name,value);
    }

    public static List<TableConfig> getGens() {
        return gens;
    }

    public static void addGens(List<TableConfig> gens) {
        CoreConfig.gens.addAll(gens);
    }
    public static void addGens(TableConfig gen) {
        CoreConfig.gens.add(gen);
    }

    public static String getEncoding() {
        return encoding;
    }

    public static void setEncoding(String encoding) {
        CoreConfig.encoding = encoding;
    }
}

package com.github.javahao.util;

import com.github.javahao.config.CoreConfig;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

/**
 * usedfor：模板引擎工具类
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class FreeMarkerUtil {
    private static Configuration config;
    static {
        config = new Configuration();
        config.setDefaultEncoding(CoreConfig.getEncoding());
        config.setObjectWrapper(new DefaultObjectWrapper());
    }
    /**
     * 获取渲染之后的字符串
     * @param content 渲染的字符串
     * @param data 模板中渲染的参数
     * @return 返回结果
     */
    public static String renderStr(String content,Map<String,Object> data){
        String result = null;
        try {
            Template t = new Template(null,new StringReader(content),null);
            StringWriter writer = new StringWriter();
            t.process(data,writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 将全局变量也同时应用到配置中
     * @param content 配置内容
     * @return 返回结果
     */
    public static String render(String content){
        if (content!=null&&!"".equals(content))
            return FreeMarkerUtil.renderStr(content,CoreConfig.getVars());
        return null;
    }

    /**
     * 执行模板渲染
     * @param temp 模板文件或者字符串
     * @param data 数据
     * @param writer 输出方式
     */
    public static void process(String temp, Map<String,Object> data, Writer writer){
        try {
            config.setDirectoryForTemplateLoading(new File(System .getProperty("user.dir")+File.separator+CoreConfig.getTemplatePath()));
            Template template = config.getTemplate(temp,CoreConfig.getEncoding());
            template.process(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭write
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (Exception ex) {
            }
        }
    }
}

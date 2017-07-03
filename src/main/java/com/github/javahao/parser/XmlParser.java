package com.github.javahao.parser;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * usedfor：xml解析器
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class XmlParser {
    /**
     * 获取document对象
     * @param path 读取的文件路径
     * @return 返回Document对象
     */
    public static Document getDocument(String path){
        return getDocument(new File(path));
    }

    /**
     * 获取document对象
     * @param file 读取的文件对象
     * @return 返回Document对象
     */
    public static Document getDocument(File file){
        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}

package com.github.javahao.parser;

import java.util.Map;

/**
 * usedfor：解析回调函数
 * Created by javahao on 2017/8/29.
 * auth：JavaHao
 */
public interface ParserHandler {
    /**
     * 回调函数
     * @param field 参数属性
     * @param value 参数值
     */
    public void handler(String field,Object value);
}

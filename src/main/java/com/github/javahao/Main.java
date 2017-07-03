package com.github.javahao;

import com.github.javahao.gen.GenCode;
import com.github.javahao.parser.ConfigParser;

/**
 * usedfor：程序主入口
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class Main {
    public static void main(String[] args) {
        ConfigParser.parserConfig("core-config.xml");
        GenCode.getInstance().gen();
    }
}

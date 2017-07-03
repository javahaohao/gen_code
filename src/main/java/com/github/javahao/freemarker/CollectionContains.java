package com.github.javahao.freemarker;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

import java.util.List;

/**
 * usedfor：判断集合是否包含指定元素
 * Created by javahao on 2017/7/1.
 * auth：JavaHao
 */
public class CollectionContains implements TemplateMethodModelEx {
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() < 1) {
            throw new TemplateModelException("Wrong arguments");
        }
        //所有参数都要先转成SimpleSequence
        SimpleSequence arg0 = (SimpleSequence)arguments.get(0);
        //把参数转换为list
        List<String> list = arg0.toList();
        TemplateScalarModel value = (TemplateScalarModel)arguments.get(1);
        return list.contains(value.getAsString());
    }
}

package com.github.javahao.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnderlineCapFirst
  implements TemplateMethodModelEx
{
  public Object exec(List list)
    throws TemplateModelException
  {
    if (list.size() < 1) {
      throw new TemplateModelException("Wrong arguments");
    }

    TemplateScalarModel model = (TemplateScalarModel)list.get(0);

    if ((null == model) || (null == model.getAsString())) {
      return "";
    }

    return camel(model.getAsString());
  }

  private String camel(String source)
  {
    if (null == source) {
      return "";
    }

    Pattern p = Pattern.compile("_[a-z]");
    Matcher m = p.matcher(source.toLowerCase());
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      String firstChar = m.group().substring(1, 2);
      m.appendReplacement(sb, firstChar.toUpperCase());
    }
    m.appendTail(sb);

    return sb.toString();
  }
}

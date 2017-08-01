# gen_code
    打造中国最好用的代码生成器，让“程序猿”有更多的时间陪老婆孩子，让你爱上写代码。
    
### 版本升级
    *  1.2版本（2017-07-31）
        a、增加生成文件路径变量控制，优化代码
        b、增加生成代码模板
        c、完善生成路径不存在自动创建模块文件夹
    *  1.1版本（2017-07-08）
        a、优化生成器功能，优化代码
        b、增加代码生成器模板引擎插件支持
        c、增加表变量定义，可以满足复杂表关系的处理，例如一对多，多对一，多对多的代码生成
        d、将jar上传maven中央仓库，之后可以将jar包依赖入项目进行使用
        e、完善描述文档
    *  1.0版本（2017-07-02）
        a、增加代码生成器的基本功能

   此为生成器源码，可以将生成器程序化，参考格式如下：
## 生成器打包
|-----gen-folder                  默认生成文件存放目录文件，可通过core-config.xml修改

|-----jre						  jre运行环境

|-----lib						  程序运行依赖的lib包

|-----driver                      增加其他数据库支持，将驱动放置此包配置core-config.xml中dialog即可

|-----plugins                     freemarker插件扩展包，增加模板自定义标签可将实现类打包jar放入此包修改配置文件freemarker的节点

|-----template					  FreeMarker模板文件存储路径，可通过core-config.xml修改

|-----template-bak                作者模板备份，供开发使用者参考编写

|-----core-config.xml			  全局文件配置

|-----gencode-1.0-RELEASE.jar	  生成器主要程序

|-----run.bat				  	  windows运行启动程序

|-----run.sh				  	  linux运行启动程序

|-----READEME.md				  使用说明
## 注意
    如果在ide中运行生成器的话需要单独在`vm options`中单独设置`-Djava.ext.dirs=./lib;./driver;./plugins`即可
## 原理
    本程序使用FreeMarker模板引擎，采用数据库反向生成的思想研发的代码生成器，初次使用建议先阅读READEME.TXT以及core-config.xml配置文件
## 支持
目前测试通过数据库有`mysql`、`postgresql`、`oracle`等关系型数据库，目前还不支持文档型数据库，如果使用者使用其他关系型数据库，请自行加入jar包驱动以及增加dialog配置即可

## 插件支持
编写模板的过程中可以自定义一些FreeMarker的插件，只需要实现TemplateMethodModelEx类并且将实现类打包放到plugins文件夹下，并且将插件配置到core-config.xml文件的freemarker节点即可在模板中使用

## Maven依赖支持

    <dependency>
      <groupId>com.github.javahaohao</groupId>
      <artifactId>gen_code</artifactId>
      <version>1.2-RELEASE</version>
    </dependency>
    将依赖引入项目中，并按照core-config.xml进行配置即可
## 使用方式
*   步骤大体如下：

	    *   1、根据各自业务场景配置template下的模板文件
	    *   2、修改core-config.xml配置文件，进行个性化配置
	    *   3、运行run.bat/run.sh即可生成代码

*    模板变量属性参考

          *    表变量属性
```java
    private String tableCat;
    /**
     * 表所属数据库
     */
    private String tableSchema;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表类型
     */
    private String tableType;
    /**
     * 表引擎类型
     */
    private String engine;
    /**
     * 表备注
     */
    private String tableComment;
    /**
     * 拥有的字段
     */
    private List<Column> columns = new ArrayList<Column>();
    /**
     * 生成表的配置
     */
    private TableConfig tableConfig;
```

          *    字段变量属性

```java
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段类型char(30)
     */
    private String columnType;
    /**
     * 数据类型char
     */
    private String dataType;
    /**
     * 字段备注
     */
    private String columnComment;
    /**
     * 键值类型
     */
    private String columnKey;
    /**
     * 是否为空
     */
    private boolean nullable;
    /**
     * 字段长度
     */
    private Long columnLength;
    /**
     * 所属表
     */
    private Table table;
    /**
     * 是否主键
     */
    private boolean primary;
    /**
     * 是否自增长
     */
    private boolean autoincrement;

    private boolean generatedColumn;
    /**
     * 表中字段的索引位置
     */
    private int ordinalPosition;
```

## 使用技巧
    * 1、使用过程中的配置各种路径的时候可以采用相对路径的方式，将生成的代码直接定位到自己的工程里面，省去了繁琐的拷贝时间
    * 2、灵活的运用变量可以变相的解决一些复杂性的问题
    * 3、模板编写属于自由发挥的模块，模板编写过程要多运用FreeMarker的内置函数去实现
    * 4、前后端都做的“宝宝们”，尽量连前台的模板一并写出，即可前后端一并生成，如果有特殊需求，进行微调即可
    * 5、此代码生成器虽然是“数据库反向生成思想”，但是也可以摆脱数据库，配置一些非开发功能模板，采用配置变量作为数据源进行使用
    * 6、此点很重要，代码生成器使用依赖表的注释以及字段的注释，请设计表的过程尽量完善表的设计，生成的代码更友好

## 后续功能
    1、目前生成器1.0版本只支持基本的单表反向生成策略，后续有时间我会将有表关系的生成策略加上（策略已加上）
    2、目前生成器1.0版本只支持关系型数据库，像一些文档型非关系型数据库还不支持，后续会加上

## 联系方式
    AUTH:JavaHao
    QQ:353475081
    QQ群：424890813
    Email:353475081@qq.com

##### 有兴趣的一起进群交流经验，求支持者打赏，我会继续努力的

<figure class="half">
    <img src="http://git.oschina.net/javahao/gen_code/raw/master/pay/wx.png" width="200">
    <img src="http://git.oschina.net/javahao/gen_code/raw/master/pay/zfb.jpg" width="200">
</figure>

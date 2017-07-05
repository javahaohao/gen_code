# gen_code
    打造中国最好用的代码生成器，让“程序猿”有更多的时间陪老婆孩子，让你爱上写代码。
   此为生成器源码，可以将生成器程序化，参考格式如下：
## 生成器打包
|-----gen-folder                  默认生成文件存放目录文件，可通过core-config.xml修改

|-----jre						  jre运行环境

|-----lib						  程序运行依赖的lib包

|-----driver                      增加其他数据库支持，将驱动放置此包配置core-config.xml中dialog即可

|-----plugins                     freemarker插件扩展包，增加模板自定义标签可将实现类打包jar放入此包修改配置文件freemarker的节点

|-----template					  FreeMarker模板文件存储路径，可通过core-config.xml修改

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

## 使用方式
*   步骤大体如下：

	    *   1、根据各自业务场景配置template下的模板文件
	    *   2、修改core-config.xml配置文件，进行个性化配置
	    *   3、运行run.bat/run.sh即可生成代码

## 使用技巧
    * 1、使用过程中的配置各种路径的时候可以采用相对路径的方式，将生成的代码直接定位到自己的工程里面，省去了繁琐的拷贝时间
    * 2、灵活的运用变量可以变相的解决一些复杂性的问题
    * 3、模板编写属于自由发挥的模块，模板编写过程要多运用FreeMarker的内置函数去实现
    * 4、前后端都做的“宝宝们”，尽量连前台的模板一并写出，即可前后端一并生成，如果有特殊需求，进行微调即可
    * 5、此代码生成器虽然是“数据库反向生成思想”，但是也可以摆脱数据库，配置一些非开发功能模板，采用配置变量作为数据源进行使用

## 后续功能
    1、目前生成器1.0版本只支持基本的单表反向生成策略，后续有时间我会将有表关系的生成策略加上
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

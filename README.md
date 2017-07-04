#gen_code
    打造中国最好用的代码生成器，让“程序猿”有更多的时间陪老婆孩子，让你爱上写代码。
   此为生成器源码，可以将生成器程序化，参考格式如下：
##生成器打包
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
##注意
    如果在ide中运行生成器的话需要单独在`vm options`中单独设置`-Djava.ext.dirs=./lib;./driver;./plugins`即可
##原理
    本程序使用FreeMarker模板引擎，采用数据库反向生成的思想研发的代码生成器，初次使用建议先阅读READEME.TXT以及core-config.xml配置文件
##支持
目前测试通过数据库有`mysql`、`postgresql`、`oracle`等关系型数据库，目前还不支持文档型数据库，如果使用者使用其他关系型数据库，请自行加入jar包驱动以及增加dialog配置即可

##插件支持
编写模板的过程中可以自定义一些FreeMarker的插件，只需要实现TemplateMethodModelEx类并且将实现类打包放到plugins文件夹下，并且将插件配置到core-config.xml文件的freemarker节点即可在模板中使用

##使用方式
*   步骤大体如下：

	    *   1、根据各自业务场景配置template下的模板文件
	    *   2、修改core-config.xml配置文件，进行个性化配置
	    *   3、运行run.bat/run.sh即可生成代码

    #####AUTH:JavaHao
    #####QQ:353475081
    #####QQ群：424890813
    #####Email:353475081@qq.com

有兴趣的一起进群交流经验，求支持者打赏，我会继续努力的

![微信](pay/mm_facetoface_collect_qrcode_1499135773710.png =200x300)

![支付宝](pay/1499135847931.jpg =200x300)
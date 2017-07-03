#gen_code
打造中国最好用的代码生成器，让“程序猿”有更多的时间陪老婆孩子，让你爱上写代码。
此为生成器源码，可以将生成器程序化，参考格式如下：
##生成器打包
|-----gen-folder                  默认生成文件存放目录文件，可通过core-config.xml修改

|-----jre						  jre运行环境

|-----lib						  运行依赖的lib包，增加其他数据库支持，将驱动放置此包配置core-config.xml中dialog即可

|-----template					  FreeMarker模板文件存储路径，可通过core-config.xml修改

|-----core-config.xml			  全局文件配置

|-----gencode-1.0-RELEASE.jar	  生成器主要程序

|-----run.bat				  	  运行启动程序

|-----READEME.txt				  使用说明
##原理
    本程序使用FreeMarker模板引擎，采用数据库反向生成的思想研发的代码生成器，初次使用建议先阅读READEME.TXT以及core-config.xml配置文件
##支持
目前测试通过数据库有`mysql`、`postgresql`、`oracle`等关系型数据库，目前还不支持文档型数据库，如果使用者使用其他关系型数据库，请自行加入jar包驱动以及增加dialog配置即可

*   使用步骤大体如下：

	    *   1、根据各自业务场景配置template下的模板文件
	    *   2、修改core-config.xml配置文件，进行个性化配置
	    *   3、运行run.bat即可生成代码

    #####AUTH:JavaHao
    #####QQ:353475081
    #####Email:353475081@qq.com
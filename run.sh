#!/bin/sh
if [ "$JAVA_HOME" = "" ]  
then  
	echo "use jre env"
	jre\bin\java -Djava.ext.dirs=./lib:./driver:./plugins -jar gen_code-1.5-RELEASE.jar com.github.javahao.Main
else    
	echo "use local env"
	java -Djava.ext.dirs=./lib:./driver:./plugins -jar gen_code-1.5-RELEASE.jar com.github.javahao.Main
fi   
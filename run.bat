@echo off
if "%JAVA_HOME%"=="" (
	echo "use jre env"
	jre\bin\java -Djava.ext.dirs=./lib -jar gencode-1.0-RELEASE.jar com.github.javahao.Main
) else (
	echo "use local env"
	java -Djava.ext.dirs=./lib -jar gencode-1.0-RELEASE.jar com.github.javahao.Main
)
pause
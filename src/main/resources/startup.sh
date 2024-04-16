#!/bin/bash
#变量设置,如果以下变量名不能满足需求，则自行新增一个变量名，在启动脚本中引用，整个脚本格式不变

#service_name以进程中的唯一关键字命名，或者以jar包名命名
service_name="hft-bo-user.jar"

#要么端口写死，要么定义在配置文件中
#SERVER_PORT=9050

#jvm 参数配置，根据实际情况调整
JVM_OPTS="-Xmx1g -Xms1g -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -XX:+UseG1GC -XX:MaxGCPauseMillis=10 -XX:InitiatingHeapOccupancyPercent=35 -XX:ParallelGCThreads=20 -XX:ConcGCThreads=10 -XX:+HeapDumpOnOutOfMemoryError -XX:MaxInlineLevel=15 -Djava.awt.headless=true -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:app-gc.log"

#如有多个环境配置文件，则自定义，否则以默认配置文件启动
ENV_VALUE=${1:-'dev'}

#新增变量在此行下添加

#---启动脚本--（修改nohup 的启动参数）----
#判断进程号
PID_NUM=$(ps -ef |grep $service_name|grep -v grep |awk '{print $2}'|sed ':label;N;s/\n/ /;b label')
#判断进程号为空；则启动；
if [ -z "$PID_NUM" ]; then
  #脚本启动参数
  nohup java -jar $JVM_OPTS -Dfile.encoding=utf-8 ./$service_name  >/dev/null 2>&1 &
  sleep 8 #平均启动耗时时间大概在8秒，根据实际情况调整
  PID_NUM=$(ps -ef |grep $service_name|grep -v grep |awk '{print $2}'|sed ':label;N;s/\n/ /;b label')
  #如果仍然为空，则echo 启动失败；否则启动成功
  if [ -z $PID_NUM ]; then
    echo -e "$service_name \033[31m Start Failed \033[0m"
  else
    echo -e "$service_name \033[1;36m Running ,PID:$PID_NUM  \033[0m"
  fi
else
  echo -e "$service_name \033[1;36m Running ,PID:$PID_NUM  \033[0m"
fi

#!/bin/sh    
    
## java config enviroment
export JAVA_HOME=/usr/local/jdk1.8.0_92
export JRE_HOME=/usr/local/jdk1.8.0_92/jre

## service name
APP_NAME=homeinns
SERVICE_DIR=/xxl-job/xxl-job-executor-$APP_NAME/target
SERVICE_NAME=xxl-job-executor-$APP_NAME-springboot-2.0.1
JAR_NAME=$SERVICE_NAME\.jar
PID=$SERVICE_NAME\.pid

cd $SERVICE_DIR

case "$1" in

	start)
		P_ID=`ps -ef | grep -w "$JAR_NAME" | grep -v "grep" | awk '{print $2}'`
		echo "$P_ID"
		if [ -n "$P_ID" ];then
			echo "$SERVICE_NAME--已经运行,PID=$P_ID"
		else		
			nohup $JRE_HOME/bin/java -jar $JAR_NAME >/dev/null 2>&1 &
			echo $!  >  $SERVICE_DIR/$PID
			echo "=== start $SERVICE_NAME"
		fi
		;;

	stop)
		kill `cat $SERVICE_DIR/$PID`
		#rm -rf $SERVICE_DIR/$PID
		echo "=== stop $SERVICE_NAME"	
		
		sleep 1
		P_ID=$(cat $SERVICE_DIR/$PID)
		if [ "$P_ID" == "" ]; then
			echo "=== $SERVICE_NAME process not exists or stop success"  	
		else
			echo "=== $SERVICE_NAME process pid is:$P_ID"
			kill -9 $P_ID
		fi
		;;

	restart)
		$0 stop
		sleep 2
		$0 start
		echo "=== restart $SERVICE_NAME"
		;;

	*)
		echo "第一个参数请输入:start|stop|restart"
		;;

esac
exit 0

**Spark教程**

第一章 将Spark配置在Yarn之上

​	1 每台机器都安装spark并配置spark-defaults.conf spark-env.sh  workers 并分发脚本



spark-defaults.conf的配置

spark.eventLog.enabled           true
spark.eventLog.dir               hdfs://hadoop102:8020/sparklog#先在hdfs创建这个文件夹

spark.yarn.jars hdfs://hadoop102:8020/spark/jars/jars/ #提前上传运行时所用的jar包

spark-env.sh的配置

 	#JAVA_HOME
	export JAVA_HOME=/opt/module/jdk1.8.0_212
	export PATH=$PATH:$JAVA_HOME/bin
	#HADOOP_HOME
	export HADOOP_HOME=/opt/module/hadoop-3.1.3
	export PATH=$PATH:$HADOOP_HOME/bin
	export PATH=$PATH:$HADOOP_HOME/sbin

​    #SPARK ON YARN 配置spark寻找hadoop的配置路径和spark的历史服务器

​    export HADOOP_CONF_DIR=/opt/module/hadoop-3.1.3/etc/hadoop
​	#export YARN_CONF_DIR=/opt/module/hadoop-3.1.3/etc/hadoop
​	export HDFS_CONF_DIR=$HADOOP_HOME/etc/hadoop

​	export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native

​	export SPARK_HISTORY_OPTS="-Dspark.history.ui.port=4000
​	-Dspark.history.retainedApplications=10
​	-Dspark.history.fs.logDirectory=hdfs://hadoop102:8020/sparklog"

workers的配置

​	hadoop102

​	hadoop103

​	hadoop104

/etc/my_env.sh环境变量的配置 已经安装了pyspark

	#JAVA_HOME
	export JAVA_HOME=/opt/module/jdk1.8.0_212
	export PATH=$PATH:$JAVA_HOME/bin
	#HADOOP_HOME
	export HADOOP_HOME=/opt/module/hadoop-3.1.3
	export PATH=$PATH:$HADOOP_HOME/bin
	export PATH=$PATH:$HADOOP_HOME/sbin
	#HIVE_HOME 只在nodemanager节点上配置hive路径
	export HIVE_HOME=/opt/module/hive
	export PATH=$PATH:$HIVE_HOME/bin
	#SPARK_HOME
	export SPARK_HOME=/opt/module/spark
	export PATH=$PATH:$SPARK_HOME/bin
	#PYTHON_HOME
	export PYTHON_HOME=/opt/module/python
	export PATH=$PATH:$PYTHON_HOME/bin
	#spark 运行时找不到hadoop native的环境
	export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native
	export PATH=$PATH:$LD_LIBRARY_PATH
	#PYSPARK_HOME
	export PYSPARK_PYTHON=/opt/module/python/bin/python3
/etc/profile的配置

​	export PYTHON_HOME=/opt/module/python/bin
​	export PATH=$PATH:$PYTHON_HOME/bin

​	export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native
​	export PATH=$PATH:$LD_LIBRARY_PATH

​	export PYSPARK_PYTHON=/opt/module/python/bin/python3
第二章 spark core

 1 执行过程![](C:\Users\Administrator\Desktop\wordcount的执行过程.png)

2 核心代码

​	

```python
from pyspark import SparkConf
from pyspark import SparkContext

def func(value)->int:
    return value*10




class PySpark:
    def __init__(self):
        self.conf=SparkConf().setMaster("local[*]").setAppName("XJob")
        self.sparkContext=SparkContext(conf=self.conf)
    def mapFunc(self):
        RDD=self.sparkContext.parallelize([1,2,3,4,5])
        print(RDD.map(lambda value: value*10).collect())#返回元组
        print(RDD.map(func).collect())       
    def flatMapFunc(self):
        RDD=self.sparkContext.parallelize(["meng zhi yi","meng lu","meng zhen chuan"])
        print(RDD.flatMap(lambda value: value.split(" ")).collect())#将map的结果降维
        print(RDD.map(lambda value: value.split(" ")).collect())#返回一个个元组形式
    def reduceByKeyFunc(self):
        RDD=self.sparkContext.parallelize([("meng",1),("meng",1),("meng",1),("zhen",1),("chuan",1),("chuan",1)])#先按照key排序,再两两遍历
        print(RDD.reduceByKey(lambda firstValue,secondValue: firstValue+secondValue).collect())
    def groupByFunc(self):#用字段分组
        RDD=self.sparkContext.parallelize([1,2,3,4,5,6,7])
        newRDD=RDD.groupBy(lambda value: "奇数" if(value%2!=0) else "偶数")
        print(newRDD.map(lambda value: (value[0],list(value[1]))).collect())
    def filterFunc(self):#过滤 保留True元素
        RDD=self.sparkContext.parallelize([1,2,3,4,5,5,6,7,7])
        newRDD=RDD.filter(lambda value: True if(value%2==0) else False)
        print(newRDD.collect())
    def distinctFunc(self):#去重
        RDD=self.sparkContext.parallelize([1,2,3,4,3,5,3,1,3,4,5])
        print(RDD.distinct().collect())
    def unionFunc(self):#求并集
        RDD=self.sparkContext.parallelize([1,1,2,3,3,4,5,6])
        newRDD=self.sparkContext.parallelize([1,2,3,4,2,1,1,3,4])
        unionRDD=RDD.union(newRDD)
        print(unionRDD.collect())
    def joinFunc(self):#左外连接 右外连接 全连接 类似于MySQL
        pass
        RDD=self.sparkContext.parallelize([(1001,"meng"),(1002,"zhen"),(1003,"chuan")])
        newRDD=self.sparkContext.parallelize([(1001,"tech"),(1002,"sale")])
        resultRDD=RDD.leftOuterJoin(newRDD)
        print(resultRDD.collect())
    def intersectionFunc(self):
        RDD=self.sparkContext.parallelize([("meng",1),("zhen",1),("chuan",1)])
        newRDD=self.sparkContext.parallelize([("meng",1),("zhi",1),("yi",1)])
        print(RDD.intersection(newRDD).collect())
    def groupByKeyFunc(self):
        RDD=self.sparkContext.parallelize([("meng",1),("zhen",1),("chuan",1),("meng",1),("zhi",1),("yi",1)])
        groupRDD=RDD.groupByKey()
        resultRDD=groupRDD.flatMap(lambda value: (value[0],list(value[1])))
        print(resultRDD.collect()) 
 
    def sortByFunc(self):
        RDD=self.sparkContext.parallelize([("meng",1),("zhen",1),("chuan",1),("meng",1),("meng",1),("chuan",1)])
        resultRDD= RDD.sortBy(lambda key:key[0],ascending=False,numPartitions=2)
        print(resultRDD.collect())


if __name__ == '__main__':
    pySpark=PySpark()
    pySpark.sortByFunc()

```

3 案例实操
class PySpark:
    def __init__(self):
        self.conf=SparkConf().setMaster("local[*]").setAppName("XJob")
        self.sparkContext=SparkContext(conf=self.conf)     
    def processData(self):
        RDD=self.sparkContext.textFile("hdfs://hadoop102:8020/input/order.text")
        RDD1=RDD.flatMap(lambda value: value.split("|"))
        jsonDictRDD=RDD1.map(lambda value :json.loads(value))
        beijingRDD=jsonDictRDD.filter(lambda value: value["areaName"]=="北京")
        resultRDD=beijingRDD.map(lambda value:value["areaName"]+"_"+value["category"]+"_"+value["money"])
        print(resultRDD.collect())
        
if __name__ == '__main__':
    pySpark=PySpark()
    pySpark.processData()

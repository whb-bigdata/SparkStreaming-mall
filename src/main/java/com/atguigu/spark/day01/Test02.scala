package com.atguigu.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test02 {
  def main(args: Array[String]): Unit = {
    val sparkConf =new SparkConf().setMaster("local[*]").setAppName("spark")
    val sc = new SparkContext(sparkConf)
    val list: List[(String, Int)] = List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98))
    val listRDD: RDD[(String, Int)] = sc.makeRDD(list)
/*    listRDD.reduceByKey()
    listRDD.aggregateByKey()
    listRDD.combineByKey()
    listRDD.partitioner*/
   println(listRDD.dependencies)
val value: RDD[Int] = listRDD.map(_._2)
   println(value.dependencies)
val value1: RDD[Int] = value.map(T => T)
   println(value1.dependencies)
  }

}

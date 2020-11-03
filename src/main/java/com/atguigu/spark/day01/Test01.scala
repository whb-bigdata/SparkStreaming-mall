package com.atguigu.spark.day01

object Test01 {
  def main(args: Array[String]): Unit = {
    val list1: List[List[Int]] = List(List(1, 2), List(3, 6))
    //list1.flatten.foreach(print)
    list1.flatMap(lis => {
     // println(lis)
      lis

    })
    val strings = List("hadoop hive", "spark linux")
    val strings1: List[String] = strings.flatMap(s => {
    //  println(s)
      s.split(" ")
    })
   // println(strings1)

    val strings2: List[String] = List("hadoop", "hive", "spark", "linux")
    println(strings.groupBy(str => str.substring(0, 1)))
   val list2: List[(Int, String)] = List()
    val map1: Map[Int, String] = Map((12, "a"), (11, "b"))
    list2.map(t => t)
    val intToString: Map[Int, String] = map1.map {
      case (a, b) => {
        (a * 2, b * 3)
      }
    }
    println(intToString)

  }

}

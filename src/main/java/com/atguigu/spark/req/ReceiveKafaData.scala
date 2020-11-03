package com.atguigu.spark.req

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

object ReceiveKafaData {

  def main(args: Array[String]): Unit = {

    // TODO 消费Kafka中指定Topic数据
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamingWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // TODO Kafka一般就是用于实时数据传输，所以在SparkStreaming的操作过程中，
    //      可以使用工具类

    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop101:9092,hadoop102:9092,hadoop103:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "atguigu0317",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer"
    )

    // 通过工具类访问Kafka，传递Topic和连接配置即可
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("atguigu0317"), kafkaPara)
    )
    // 因为kafka传递数据是基于KV键值对的数据，所以这里(String, String)其实就是kv键值对
    // 因为一般传递数据时，key不传的，所以key为null,获取数据时，一般只需要value即可
    kafkaDStream.map(_.value()).print()

    ssc.start()
    ssc.awaitTermination()
  }
}

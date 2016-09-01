

import org.apache.spark.{SparkContext, SparkConf}


object SparkSentenceCount {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\Users\\npdar\\Desktop\\Acads\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("input")

    val wc=input.flatMap(line=>{line.split("\\.")}).map(word=>(word,1)).cache()

    val output=wc.reduceByKey(_+_).sortBy(c=>c._1, true)

   output.saveAsTextFile("output")


  }

}

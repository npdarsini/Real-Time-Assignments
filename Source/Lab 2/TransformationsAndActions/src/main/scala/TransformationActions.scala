import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by npdar on 9/8/2016.
  */

// Aims to take an input of two files and join them based on the ID

object TransformationActions {
  def main(args: Array[String]): Unit = {


    System.setProperty("hadoop.home.dir", "C:\\Users\\npdar\\Desktop\\Acads\\winutils");

    val sparkConf = new SparkConf().setAppName("TransformationActions").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val rating = sc.textFile("ratings.csv")
    val books = sc.textFile("books.csv")

    //Applying map transformation
    val ratingRdd = rating.map(line => line.split(";")).map(n => (n(1), n(2)))

    //Applying Map transformation

    val booksRdd = books.map(line => line.split(";")).map(b => (b(0), b(1)))

    //Applying join transformation

    val output = ratingRdd.join(booksRdd).distinct() //.foreach(println(_))

    //Applying saveAsTextFile action

    output.sortByKey(true)

    output.saveAsTextFile("Output")

    //Applying take action

    val takeOp = output.take(50)

    //Applying foreach action

    takeOp.foreach(println(_))

  }

}

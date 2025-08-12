import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import utils.DataFrameUtils._

object SalesDataProcessing {

  def main(args: Array[String]): Unit = {

    // Initialisation SparkSession
    val spark = SparkSession.builder()
      .appName("Sales Data Processing")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    // Lecture du fichier CSV
    val salesDF = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("./data/sales_data.csv")

    println("DataFrame Schema:")
    println(salesDF.printSchema())

    println("DataFrame values:")
    print(salesDF.show(5))

    val nullCounts = check_nulls(salesDF)
    println(s"Le DataFrame contient $nullCounts des valeurs nulles.")

    val cleanedDF = if (nullCounts > 0) {
      println("Supression des lignes contenant des valeurs nulles")
      salesDF.na.drop() 
    } else {
      salesDF
    }

    println("Nombre de valeur nulles apres nettoyage:" + check_nulls(cleanedDF))

    println("Count====================>"+cleanedDF.select("PRODUCTLINE").distinct().show())
    

  }
}

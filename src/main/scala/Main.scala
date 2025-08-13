import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import utils.DataFrameUtils._
import scala.io.Source


object SalesDataProcessing {

  def main(args: Array[String]): Unit = {

    // Initialisation SparkSession
    val spark = SparkSession.builder()
      .appName("Sales Data Processing")
      .master("local[*]")
      .getOrCreate()

    val env: Map[String, String] = Source.fromFile(".env")
      .getLines()
      .filter(line => line.contains("=") && !line.trim.startsWith("#"))
      .map { line =>
        val Array(key, value) = line.split("=", 2)
        key.trim -> value.trim
      }.toMap

    val accessKey = env("AWS_ACCESS_KEY_ID")
    val secretKey = env("AWS_SECRET_ACCESS_KEY")
    val region    = env("AWS_REGION")
    val bucket    = env("AWS_BUCKET")

   
  }
}

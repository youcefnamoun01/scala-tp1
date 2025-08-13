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

    // Nettoyage des valeurs nulles
    val nullCounts = check_nulls(salesDF)
    println(s"Le DataFrame contient $nullCounts des valeurs nulles.")

    val cleanedDF = if (nullCounts > 0) {
      println("Supression des lignes contenant des valeurs nulles")
      salesDF.na.drop() 
    } else {
      salesDF
    }

    println("Nombre de valeur nulles apres nettoyage: " + check_nulls(cleanedDF))
    
    // Calcule du total sales revenue par categorie de produit
    val totalSalesByCategory = cleanedDF.groupBy("PRODUCTLINE")
      .agg(sum("SALES").alias("TOTAL_SALES"))
      .orderBy(desc("TOTAL_SALES"))
    println("Total sales revenue par categorie de produit :")
    totalSalesByCategory.show()

    // Calcule des 5 Top produits par ventes
    val topProducts = cleanedDF.groupBy("PRODUCTCODE")
      .agg(sum("SALES").alias("TOTAL_SALES"))
      .orderBy(desc("TOTAL_SALES"))
      .limit(5)

    println("Top 5 produits par ventes :")
    topProducts.show()
    
    // calculer le nombre de ventes par mois
    val withMonth = cleanedDF.withColumn(
      "month",
      month(to_timestamp(col("ORDERDATE"), "M/d/yyyy H:mm"))
    )
    val salesByMonth = withMonth.groupBy("month")
      .agg(sum("SALES").alias("TOTAL_SALES"))
      .orderBy("month")

    println("Nombre de ventes par mois :")
    salesByMonth.show()

    // Sauvegarde
    cleanedDF.write.mode("overwrite").option("header", "true").csv("data/cleaned_data")
    topProducts.write.mode("overwrite").option("header", "true").csv("data/topProducts")
    salesByMonth.write.mode("overwrite").option("header", "true").csv("data/salesByMonth")

    // Fermeture de la SparkSession
    spark.stop()

  }
}

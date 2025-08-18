import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import utils.DataFrameUtils._
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

object SalesDataProcessing {

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()

    // Initialisation SparkSession
    val spark = SparkSession.builder()
      .appName("Sales Data Processing")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    // Chargement des variables de configuration
    val hadoopConf = spark.sparkContext.hadoopConfiguration
    hadoopConf.set("fs.s3a.access.key", config.getString("db.AWS_ACCESS_KEY_ID"))
    hadoopConf.set("fs.s3a.secret.key", config.getString("db.AWS_SECRET_ACCESS_KEY"))
    hadoopConf.set("fs.s3a.endpoint", s"s3.${config.getString("db.AWS_REGION")}.amazonaws.com")
    val bucket = config.getString("db.BUCKET_NAME")

    // Chargement du fichier CSV à partir de aws S3
    val salesDF = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(s"s3a://$bucket/sales_data.csv")

    // Affichage du dataframe
    println("DataFrame Schema:")
    salesDF.printSchema()

    println("DataFrame values:")
    salesDF.show(5)

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
    
    // calculer du totale des ventes par mois
    val withMonth = cleanedDF.withColumn(
      "month",
      month(to_timestamp(col("ORDERDATE"), "M/d/yyyy H:mm"))
    )
    val salesByMonth = withMonth.groupBy("month")
      .agg(sum("SALES").alias("TOTAL_SALES"))
      .orderBy("month")

    println("Totale des ventes par mois :")
    salesByMonth.show()

    // Sauvegarde dans aws S3
    cleanedDF.write.mode("overwrite").option("header", "true").csv(s"s3a://$bucket/reporting/cleaned_data")
    topProducts.write.mode("overwrite").option("header", "true").csv(s"s3a://$bucket/reporting/topProducts")
    salesByMonth.write.mode("overwrite").option("header", "true").csv(s"s3a://$bucket/reporting/salesByMonth")

    // Fermeture de la SparkSession
    spark.stop()


   
  }
}


package utils

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object DataFrameUtils {
    
    def check_nulls(df: DataFrame) = {
      df.filter(df.columns.map(c => col(c).isNull).reduce(_ || _)).count()
    }

}
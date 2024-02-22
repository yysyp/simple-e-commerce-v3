import com.google.cloud.bigquery.*;
import java.util.concurrent.*;

public class BigQueryParallelLoader {

public static void main(String[] args) throws Exception {

    // Create a BigQuery client
    BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
    
    // Specify the table to load data from
    TableId tableId = TableId.of("my_dataset", "my_table");
    
    // Create an executor service with multiple threads 
    ExecutorService executor = Executors.newFixedThreadPool(10);
    
    // Start 10 parallel loads 
    for (int i = 0; i < 10; i++) {
      executor.submit(() -> {
        TableResult result = bigquery.listTableData(tableId, 
          BigQuery.TableDataListOption.startIndex(i*1000));
        // Process result rows in parallel  
      });
    }
    
    // Wait for loads to complete
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.HOURS);

}

}

<dependency>
  <groupId>com.google.cloud</groupId>
  <artifactId>google-cloud-bigquery</artifactId>
  <version>1.127.0</version>
</dependency>
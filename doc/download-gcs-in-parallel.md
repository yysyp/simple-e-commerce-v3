import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GCSDownloader {

private static final int NUM_THREADS = 5; // Number of parallel download threads

public static void main(String[] args) {
String bucketName = "your-bucket-name";
List<String> objectNames = new ArrayList<>();
objectNames.add("file1.txt");
objectNames.add("file2.txt");
objectNames.add("file3.txt");
// Add more object names as needed

    downloadFiles(bucketName, objectNames);
}

private static void downloadFiles(String bucketName, List<String> objectNames) {
ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
Storage storage = StorageOptions.getDefaultInstance().getService();

    List<CompletableFuture<Void>> downloadFutures = new ArrayList<>();

    for (String objectName : objectNames) {
      CompletableFuture<Void> downloadFuture = CompletableFuture.runAsync(() -> {
        try {
          downloadFile(storage, bucketName, objectName);
        } catch (IOException e) {
          System.err.println("Error downloading file " + objectName + ": " + e.getMessage());
        }
      }, executorService);

      downloadFutures.add(downloadFuture);
    }

    // Wait for all download tasks to complete
    CompletableFuture.allOf(downloadFutures.toArray(new CompletableFuture[0]))
        .thenRun(() -> {
          executorService.shutdown();
          try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        })
        .join();
}

private static void downloadFile(Storage storage, String bucketName, String objectName) throws IOException {
BlobId blobId = BlobId.of(bucketName, objectName);
Blob blob = storage.get(blobId);

    if (blob == null) {
      System.err.println("File not found: " + objectName);
      return;
    }

    // Specify the local file path to save the downloaded file
    String localFilePath = "path/to/save/" + objectName;

    try (ReadableByteChannel reader = Channels.newChannel(blob.reader());
         FileOutputStream fileOutputStream = new FileOutputStream(localFilePath)) {
      fileOutputStream.getChannel().transferFrom(reader, 0, Long.MAX_VALUE);
      System.out.println("Downloaded file: " + objectName);
    }
}
}

<dependencies>
    <!-- Google Cloud Storage -->
    <dependency>
        <groupId>com.google.cloud</groupId>
        <artifactId>google-cloud-storage</artifactId>
        <version>2.3.0</version>
    </dependency>

    <!-- Google Cloud Core -->
    <dependency>
        <groupId>com.google.cloud</groupId>
        <artifactId>google-cloud-core</artifactId>
        <version>2.1.0</version>
    </dependency>

    <!-- Google Cloud Auth -->
    <dependency>
        <groupId>com.google.auth</groupId>
        <artifactId>google-auth-library-oauth2-http</artifactId>
        <version>2.1.0</version>
    </dependency>

    <!-- Google Cloud BOM -->
    <dependency>
        <groupId>com.google.cloud</groupId>
        <artifactId>libraries-bom</artifactId>
        <version>24.2.0</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>
</dependencies>


package StudentManagementSystem.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackUpTask implements Runnable {

    @Override
    public void run() {

        try {
            // Source file
            Path source = Path.of("student_records.txt");

            // Check whether the file exists
            if (!Files.exists(source)) {
                System.out.println("No student records available for backup!");
                return;
            }

            // Create backups folder
            File backupFolder = new File("backups");

            if (!backupFolder.exists()) {
                backupFolder.mkdirs();
            }

            // Generate timestamp
            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            // Destination file
            Path destination = Path.of(
                    "backups",
                    "student_backup_" + timestamp + ".txt"
            );

            // Copy file
            Files.copy(
                    source,
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Backup completed successfully: " + destination
            );

        } catch (IOException e) {
            System.out.println(
                    "Backup failed: " + e.getMessage()
            );
        }
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
    public static void main(String[] args) {
        String directoryName = "dscripts";
        String file1Name = "file1.txt";
        String file2Name = "file2.txt";

        // Create the directory if it doesn't exist
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Generate and write content to file1
        String file1Content = "This is file 1 content.";
        writeFile(directoryName + File.separator + file1Name, file1Content);

        // Generate and write content to file2
        String file2Content = "This is file 2 content.";
        writeFile(directoryName + File.separator + file2Name, file2Content);

        System.out.println("Files generated successfully.");
    }

    private static void writeFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

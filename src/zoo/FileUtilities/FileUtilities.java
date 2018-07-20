package zoo.FileUtilities;
import com.google.gson.Gson;
import zoo.Zoo.Zoo;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtilities {
    static Gson gson = new Gson();
    private static File file = new File("Zoo.json");

    public static Zoo readFromFile() {
        try {
            Path filePath = Paths.get("Zoo.json");
            String jsonString = new String(Files.readAllBytes(filePath));

            return gson.fromJson(jsonString, Zoo.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void writeToFile(Zoo zoo) {
        String zooJson = gson.toJson(zoo);
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(zooJson);
            printWriter.close();
        } catch (Exception e) {
        }
    }
}



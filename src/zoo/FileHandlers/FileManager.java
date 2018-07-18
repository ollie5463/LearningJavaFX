package zoo.FileHandlers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import zoo.Zoo.Zoo;
import zoo.ZooKeeper;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    static Gson gson = new Gson();

    static ArrayList<File> files = new ArrayList<>();


    public static void createDefaultFiles() {
        createFile("Zoo");
    }

    private static void createFile(String name) {
        try {
            File file = new File(name + ".json");
            files.add(file);
        } catch (Exception e) {
        }
    }

    public static Zoo readFromFile() throws Throwable {
        try {
            Path filePath = Paths.get("Zoo.json");
            String jsonString = new String(Files.readAllBytes(filePath));

            return gson.fromJson(jsonString, Zoo.class);
        } catch (Exception e) {
            throw new Throwable();
        }
    }

    public static void writeToFile(Zoo zoo) {
        String zooJson = gson.toJson(zoo);
        try {
            PrintWriter printWriter = new PrintWriter(files.get(0));
            printWriter.print(zooJson);
            printWriter.close();
        } catch (Exception e) {
        }
    }
}



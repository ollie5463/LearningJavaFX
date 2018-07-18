package test.FileHandlers;

import javafx.scene.input.ZoomEvent;
import org.junit.Assert;
import org.junit.Test;
import zoo.FileHandlers.FileManager;
import zoo.Zoo.Zoo;
import zoo.ZooKeeper;
import zoo.ZooManager;

import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class FileManagersUTest {

    @Test
    public void saveZooToFile(){
        FileManager.createDefaultFiles();
        Zoo zoo = ZooManager.createDefaultZoo();
        FileManager.writeToFile(zoo);
        try{
            Zoo zoo1 = FileManager.readFromFile();
            assertEquals(zoo, zoo1);
        }
        catch(Throwable e){
        }

    }




}

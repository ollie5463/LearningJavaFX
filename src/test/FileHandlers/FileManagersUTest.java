package test.FileHandlers;

import org.junit.Test;
import zoo.FileHandlers.FileManager;
import zoo.Pens.Pen;
import zoo.Pens.PenInstances.DryPen;
import zoo.Zoo.Zoo;
import zoo.ZooManager;

import static org.junit.Assert.assertEquals;

public class FileManagersUTest {

    @Test
    public void saveZooToFile(){
//        FileManager.createDefaultFiles();/
//        Zoo zoo = ZooManager.createDefaultZoo();
//        FileManager.writeToFile(zoo);
        try{
            Zoo zoo1 = FileManager.readFromFile();
//            assertEquals(zoo, zoo1);
//            Pen pen = zoo.getPens().get(0);
            Pen pen1 = zoo1.getPens().get(0);
//            assertEquals(pen, pen1);
        }
        catch(Throwable e){
        }

    }

}

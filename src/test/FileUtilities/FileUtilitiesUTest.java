package test.FileUtilities;

import org.junit.Test;
import zoo.FileUtilities.FileUtilities;
import zoo.Pens.Pen;
import zoo.Zoo.Zoo;

import static org.junit.Assert.assertEquals;

public class FileUtilitiesUTest {

    @Test
    public void saveZooToFile(){
//        FileUtilities.createDefaultFiles();
//        Zoo zoo = ZooManager.createDefaultZoo();
//        FileUtilities.writeToFile(zoo);
        try{
            Zoo zoo1 = FileUtilities.readFromFile();
//            assertEquals(zoo, zoo1);
//            Pen pen = zoo.getPens().get(0);
            Pen pen1 = zoo1.getPens().get(0);
//            assertEquals(pen, pen1);
        }
        catch(Throwable e){
        }

    }

}

package test;

import org.junit.Test;
import zoo.Animals.Animal;
import zoo.Pens.Pen;
import zoo.Zoo.Zoo;
import zoo.ZooKeeper;
import zoo.ZooManager;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ZooUTest {

    @Test
    public void createZoo(){
        ZooManager.createZooKeepers();
        ZooManager.createDefaultAnimals();
        ZooManager.createDefaultPens();
        ArrayList<ZooKeeper> zooKeepers = ZooManager.getZooKeepers();
        ArrayList<Animal> animals = ZooManager.getAnimals();
        ArrayList<Pen> pens = ZooManager.getPens();

        Zoo zoo = new Zoo(zooKeepers, animals, pens);
//        assertEquals(zoo, Object);
    }
}

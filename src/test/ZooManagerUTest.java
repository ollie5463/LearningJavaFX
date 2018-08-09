package test;

import org.junit.Assert;
import org.junit.Test;
import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.LandAnimal;
import zoo.Pens.PenType;
import zoo.ZooManager;

import java.util.ArrayList;

public class ZooManagerUTest {

    @Test
    public void addAnimal(){

        ZooManager.addLandAnimal(new ArrayList<>(){{add(PenType.PETTING);}}, "dog", false, 50);
        Animal animal = ZooManager.getAnimals().get(0);
        Assert.assertEquals(animal.getCurrentPen(), null);
        Assert.assertEquals(animal.getSuitablePens(), new ArrayList<>(){{add(PenType.PETTING);}});
        Assert.assertEquals(animal.getName(), "dog");
        LandAnimal landAnimal = (LandAnimal) animal;
        Assert.assertEquals(landAnimal.getLandNeeded(), 50);
    }

}

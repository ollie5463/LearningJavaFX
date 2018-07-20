package zoo;

import zoo.Animals.Animal;
import zoo.Pens.Pen;
import zoo.Pens.PenInstances.*;
import zoo.Zoo.Zoo;

import java.util.*;

public class ZooManager {
    private static ArrayList<ZooKeeper> zooKeepers = new ArrayList<>();
    private static ArrayList<Animal> animals = new ArrayList<>();
    private static ArrayList<Pen> pens = new ArrayList<>();
    private static Zoo currentZoo;


    public static void createZooKeepers(){

        createZooKeeper("farhad", PenType.AQUARIUM, PenType.AVIARY);
        createZooKeeper("hardip", PenType.DRY, PenType.AVIARY);
        createZooKeeper("alan", PenType.DRY, PenType.PETTING);
        createZooKeeper("alex", PenType.AQUARIUM, PenType.PARTWATERPARTDRY);
    }

    public static void createDefaultAnimals(){
        addAnimal("dog", PenType.DRY, PenType.PETTING, 50);
        addAnimal("cat", PenType.DRY, PenType.PETTING, 51);
    }

    public static void createDefaultPens() {
        createPen(PenType.DRY, 50,50,25,"dry pen 1");
        createPen(PenType.PETTING, 60,60,25, "petting pen 1");
        createPen(50,50,25,100, "aviary pen 1");
        createPen(PenType.AQUARIUM, 100, 100, 30, 10000, "aquarium pen 1");
        createPen(PenType.PARTWATERPARTDRY, 70, 70, 25, 500, "PWPD pen 1");
    }


    public static void addAnimal(String name, ArrayList<PenType> penTypes, int spaceNeeded){
        Animal animal = new Animal (penTypes, name, spaceNeeded);
        animals.add(animal);
    }

    public static void addAnimal(String name, PenType penType, PenType penType2, int spaceNeeded){ // todo need to delete
        Animal animal = new Animal(new ArrayList<>(){{add(penType);add(penType2);}}, name, spaceNeeded);
        animals.add(animal);
    }

    public static void addAnimal(String name, ArrayList<PenType> penTypes, int spaceNeeded, int waterVolumeNeeded){
        Animal animal = new Animal(penTypes , name, spaceNeeded, waterVolumeNeeded);
        animals.add(animal);
    }

    public static void createPen(PenType penType, int length, int width, int temp, String penName){

        switch(penType){
            case PETTING:
                Pen pettingPen = new PettingPen(length, width, temp, penName);
                pens.add(pettingPen);
                break;

            case DRY:
                Pen dryPen = new DryPen(length, width, temp, penName);
                pens.add(dryPen);
                break;
        }
    }

    public static void createPen(PenType penType, int length, int width, int temp, int waterVolume, String penName){
        switch (penType){
            case PARTWATERPARTDRY:
                Pen partWaterPartDry = new PartWaterPartDryPen(length, width, waterVolume, temp, penName);
                pens.add(partWaterPartDry);
                break;
            case AQUARIUM:
                Pen aquarium = new Aquarium(length, width, waterVolume, temp, penName);
                pens.add(aquarium);
                break;
        }
    }

    public static void createPen(int length, int width, int temp, int height, String penName){
            Pen aviary = new Aviary(length, width, temp, height, penName);
            pens.add(aviary);
    }

    private static void createZooKeeper(String name, PenType penType, PenType penType2){
        ZooKeeper zooKeeper = new ZooKeeper(name, new ArrayList<>(){{add(penType);add(penType2);}});
        zooKeepers.add(zooKeeper);
    }

    public static ZooKeeper getZooKeeper(ZooKeeper zooKeeperName)throws Throwable{
        for(ZooKeeper zooKeeper : zooKeepers){
            if(zooKeeper.equals(zooKeeperName)){
                return zooKeeper;
            }
        }
        throw new Throwable();
    }


    public static ArrayList<ZooKeeper> getZooKeepers() {
        return zooKeepers;
    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
    }

    public static Animal getAnimal(Animal animalName) throws Throwable {
        for(Animal animal: animals){
            if(animal.equals(animalName)){
                return animal;
            }
        }
        throw new Throwable();
    }

    public static ArrayList<Pen> getPens() {
        return pens;
    }

    public static Pen getPen(Pen penName) throws Throwable{
        for(Pen pen : pens){
            if(pen.equals(penName)){
                return pen;
            }
        }
        throw new Throwable();
    }
    public static Pen getPen(String penName) throws Throwable{
        for(Pen pen : pens){
            if(pen.toString().equals(penName)){
                return pen;
            }
        }
        throw new Throwable();
    }

    public static void createDefaultZoo() {
        createDefaultAnimals();
        createDefaultPens();
        createZooKeepers();
        Zoo zoo = new Zoo(zooKeepers, animals, pens);
        currentZoo = zoo;
    }

    public static void loadUpZoo(Zoo zoo){
        currentZoo = zoo;
        zooKeepers = zoo.getZooKeepers();
        animals = zoo.getAnimals();
        pens = zoo.getPens();
    }

    public static Zoo getZoo() {
        return currentZoo;
    }
}

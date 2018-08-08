package zoo;

import javafx.scene.control.ChoiceBox;
import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.AmphibiousAnimal;
import zoo.Animals.AnimalClassifications.AquaticAnimal;
import zoo.Animals.AnimalClassifications.AvesAnimal;
import zoo.Animals.AnimalClassifications.LandAnimal;
import zoo.Exceptions.UIException;
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
        addLandAnimal(new ArrayList<>(){{add(PenType.DRY);}}, "dog", false, 50);
        addLandAnimal(new ArrayList<>(){{add(PenType.PETTING);}}, "cat", false, 50);
    }

    public static void createDefaultPens() {
        createDryPen(50,50,25,"dry pen 1");
        createPettingPen(60,60,25, "petting pen 1");
        createPartWaterPartDryPen(100, 100, 30, 10000, "PWPD 1");
        createAviary( 70, 70, "aviary 1", 25, 50);
        createAquarium(70, 70, "aquarium 1", 25, 50);
    }


    public static void addAquaticAnimal(ArrayList<PenType> suitablePens, String name, boolean isPredator, int waterNeeded){
        animals.add(new AquaticAnimal(suitablePens, name, isPredator, waterNeeded));
    }
    public static void addAmphibiousAnimal(ArrayList<PenType> suitablePens, String name, boolean isPredator, int waterNeeded, int landNeeded){
        animals.add(new AmphibiousAnimal(suitablePens, name, isPredator, waterNeeded, landNeeded));
    }
    public static void addAvesAnimal(ArrayList<PenType> suitablePens, String name, boolean isPredator, int airNeeded){
        animals.add(new AvesAnimal(suitablePens, name, isPredator, airNeeded));
    }
    public static void addLandAnimal(ArrayList<PenType> suitablePens, String name, boolean isPredator, int landNeeded){
        animals.add(new LandAnimal(suitablePens, name, isPredator, landNeeded));
    }
    public static void createDryPen(int length, int width, int temp, String penName){
        pens.add(new DryPen(length, width, temp, penName));
    }
    public static void createPettingPen(int length, int width, int temp, String penName){
        pens.add(new PettingPen(length, width, temp, penName));
    }
    public static void createPartWaterPartDryPen(int length, int width, int temp, int waterVolume, String penName){
        pens.add(new PartWaterPartDryPen(length, width, waterVolume, temp, penName));
    }
    public static void createAquarium(int length, int width, String penName, int height, int temp){
        pens.add(new Aquarium(length, width, height, temp, penName));
    }
    public static void createAviary(int length, int width, String penName, int height, int temp){
        pens.add(new Aviary(length, width, height, temp, penName));
    }


    private static void createZooKeeper(String name, PenType penType, PenType penType2){
        zooKeepers.add(new ZooKeeper(name, new ArrayList<>(){{add(penType);add(penType2);}}));
    }

    public static ZooKeeper getZooKeeper(ZooKeeper zooKeeperName)throws Throwable{
        for(ZooKeeper zooKeeper : zooKeepers){
            if(zooKeeper.equals(zooKeeperName)){
                return zooKeeper;
            }
        }
        throw new Throwable();
    }

    public static ArrayList<ZooKeeper> getZooKeepers() { return zooKeepers; }

    public static ArrayList<Animal> getAnimals() { return animals; }

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

    public static Pen getPen(String penName) throws UIException{
        for(Pen pen : pens){
            if(pen.getPenName().equals(penName)){
                return pen;
            }
        }
        throw new UIException("Pen not found");
    }

    public static void createDefaultZoo() {
        createDefaultAnimals();
        createDefaultPens();
        createZooKeepers();
        currentZoo = new Zoo(zooKeepers, animals, pens);
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

    public static void assignAnimalToPen(Animal animalChoice, String penName) throws Throwable{
        // check that it can fit in the Pen
            Pen pen = getPen(penName);
            pen.addNewAnimal(animalChoice);
            animalChoice.setCurrentPen(penName);
    }

    public static ArrayList<Animal> getAnimalsNotAssignedToAPen()  {
        ArrayList<Animal> animalsNotInPens = new ArrayList<>();
        for(Animal animal : animals){
            if(animal.getCurrentPen() == null){
                animalsNotInPens.add(animal);
            }
        }
        return animalsNotInPens;
    }

    // think i assign the zookeeper to a pen, not the pen to a zoo keeper
    public static ArrayList<Pen> checkIfPensAreAssignedToStaff() {
        ArrayList<Pen> pensNotAssignedAZooKeeper = new ArrayList<>();
        for(Pen pen : pens){
            if(pen.getCurrentZookeeper() == null){
                pensNotAssignedAZooKeeper.add(pen);
            }
        }
        return pensNotAssignedAZooKeeper;
    }

    public static void assignZooKeeperToPen(ChoiceBox<ZooKeeper> zooKeepers, ChoiceBox<String> possiblePensForZooKeepers) {
        zooKeepers.getValue().setPensResponsibleFor(possiblePensForZooKeepers.getValue());
        try {
            getPen(possiblePensForZooKeepers.getValue()).setCurrentZookeeper(zooKeepers.getValue());
        }
        catch(Throwable penNotFound){

        }
    }

    public static void autoAssignAnimalToPen(Animal animal) {
        ArrayList<Pen> possiblePens = getPossiblePens(animal.getSuitablePens());
        if(!possiblePens.isEmpty()){
            for(Pen pen : possiblePens){
                try {
                    pen.addNewAnimal(animal);
                    animal.setCurrentPen(pen.getPenName());
                    break;
                }
                catch(Throwable throwable){
                }
            }
        }
    }

    private static ArrayList<Pen> getPossiblePens(ArrayList<PenType> penTypes){
        ArrayList<Pen> possiblePens = new ArrayList<>();
        for(PenType penType : penTypes){
            for(Pen pen : pens){
                if(penType.equals(pen.getPenType())){
                    possiblePens.add(pen);
                }
            }
        }
        return possiblePens;
    }

    public static void autoAssignPenToZooKeeper(Pen pen) {
        for(ZooKeeper zooKeeper : zooKeepers){
            ArrayList<PenType> penTypesResponsibleFor = zooKeeper.getPenTypesResponsibleFor();
            for(PenType zooKeeperPenType : penTypesResponsibleFor){
                if(zooKeeperPenType.equals(pen.getPenType())){
                    zooKeeper.setPensResponsibleFor(pen.getPenName());
                    pen.setCurrentZookeeper(zooKeeper);
                    break;
                }
            }
        }
    }

    public static ArrayList<Pen> getPensNotAssignedToAZooKeeper() {
        ArrayList<Pen> pensNotAssignedToZooKeeper = new ArrayList<>();
        for(Pen pen : pens){
            if(pen.getCurrentZookeeper() == null){
                pensNotAssignedToZooKeeper.add(pen);
            }
        }
        return pensNotAssignedToZooKeeper;
    }
}

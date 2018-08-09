package zoo.Zoo;

import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.AmphibiousAnimal;
import zoo.Animals.AnimalClassifications.AquaticAnimal;
import zoo.Animals.AnimalClassifications.AvesAnimal;
import zoo.Animals.AnimalClassifications.LandAnimal;
import zoo.Pens.Pen;
import zoo.Pens.PenInstances.*;
import zoo.ZooKeeper;

import java.util.ArrayList;

public class Zoo {

    private final ArrayList<ZooKeeper> zooKeepers;
    private final ArrayList<Aquarium> aquariums = new ArrayList<>();
    private final ArrayList<DryPen> dryPens= new ArrayList<>();
    private final ArrayList<PartWaterPartDryPen> partWaterPartDryPens= new ArrayList<>();
    private final ArrayList<Aviary> aviaries= new ArrayList<>();
    private final ArrayList<PettingPen> pettingPens= new ArrayList<>();
    private final ArrayList<AmphibiousAnimal> amphibiousAnimals = new ArrayList<>();
    private final ArrayList<AquaticAnimal> aquaticAnimals = new ArrayList<>();
    private final ArrayList<AvesAnimal> avesAnimals = new ArrayList<>();
    private final ArrayList<LandAnimal> landAnimals = new ArrayList<>();

    public Zoo(ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animals, ArrayList<Pen> pens) {
        this.zooKeepers = zooKeepers;
        addAnimalsToSpecificArrays(animals);
        addPensToSpecificArrays(pens);
    }

    private void addAnimalsToSpecificArrays(ArrayList<Animal> animals){
        for(Animal animal : animals){
            switch(animal.getClassification()){
                case AMPHIBIOUS:
                    amphibiousAnimals.add((AmphibiousAnimal)animal);
                    break;
                case AQUATIC:
                    aquaticAnimals.add((AquaticAnimal)animal);
                    break;
                case AVES:
                    avesAnimals.add((AvesAnimal) animal);
                    break;
                case LAND:
                    landAnimals.add((LandAnimal)animal);
                    break;
            }
        }
    }

    private void addPensToSpecificArrays(ArrayList<Pen> pens) {
        for(Pen pen : pens){
            switch (pen.getPenType()){
                case DRY:
                    dryPens.add((DryPen)pen);
                    break;
                case AQUARIUM:
                    aquariums.add((Aquarium)pen);
                    break;
                case PARTWATERPARTDRY:
                    partWaterPartDryPens.add((PartWaterPartDryPen)pen);
                    break;
                case AVIARY:
                    aviaries.add((Aviary)pen);
                    break;
                case PETTING:
                    pettingPens.add((PettingPen)pen);
                    break;
            }
        }
    }

    public ArrayList<ZooKeeper> getZooKeepers() {
        return zooKeepers;
    }

    public ArrayList<Animal> getAnimals() {
        return new ArrayList<>(){{
            addAll(aquaticAnimals);
            addAll(avesAnimals);
            addAll(amphibiousAnimals);
            addAll(landAnimals);
        }};
    }

    public ArrayList<Pen> getPens() {
        return new ArrayList<>(){{
        addAll(dryPens);
        addAll(aquariums);
        addAll(partWaterPartDryPens);
        addAll(aviaries);
        addAll(pettingPens);}};
    }
}
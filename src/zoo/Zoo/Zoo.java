package zoo.Zoo;

import zoo.Animals.Animal;
import zoo.PenType;
import zoo.Pens.Pen;
import zoo.Pens.PenInstances.*;
import zoo.ZooKeeper;

import java.util.ArrayList;

public class Zoo {

    private final ArrayList<ZooKeeper> zooKeepers;
    private final ArrayList<Animal> animals;
    private  ArrayList<Pen> pens = new ArrayList<>();
    private final ArrayList<Aquarium> aquariums = new ArrayList<>();
    private final ArrayList<DryPen> dryPens= new ArrayList<>();
    private final ArrayList<PartWaterPartDryPen> partWaterPartDryPens= new ArrayList<>();
    private final ArrayList<Aviary> aviaries= new ArrayList<>();
    private final ArrayList<PettingPen> pettingPens= new ArrayList<>();

    public Zoo(ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animals, ArrayList<Pen> pens) {
        this.zooKeepers = zooKeepers;
        this.animals = animals;
        addPensToSpecificArrays(pens);
    }

    private void addPensToSpecificArrays(ArrayList<Pen> pens) {
        for(Pen pen : pens){
            PenType penType = pen.getPenType();
            switch (penType){
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
        return animals;
    }

    public ArrayList<Pen> getPens() {
        pens.addAll(dryPens);
        pens.addAll(aquariums);
        pens.addAll(partWaterPartDryPens);
        pens.addAll(aviaries);
        pens.addAll(pettingPens);
        return pens;
    }
}
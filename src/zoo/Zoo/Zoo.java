package zoo.Zoo;

import zoo.Animals.Animal;
import zoo.Pens.Pen;
import zoo.ZooKeeper;

import java.util.ArrayList;

public class Zoo {

    private final ArrayList<ZooKeeper> zooKeepers;
    private final ArrayList<Animal> animals;
    private final ArrayList<Pen> pens;

    public Zoo(ArrayList<ZooKeeper> zooKeepers, ArrayList<Animal> animals, ArrayList<Pen> pens) {
        this.zooKeepers = zooKeepers;
        this.animals = animals;
        this.pens = pens;
    }
    public ArrayList<ZooKeeper> getZooKeepers() {
        return zooKeepers;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Pen> getPens() {
        return pens;
    }
}



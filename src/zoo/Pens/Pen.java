package zoo.Pens;

import zoo.Animals.Animal;
import zoo.Exceptions.UIException;
import zoo.ZooKeeper;

import java.util.ArrayList;

public abstract class  Pen {

    protected PenType penType;
    protected ArrayList<Animal> currentAnimals = new ArrayList<>();
    protected int noOfAnimals = 0;
    protected int length, width, temp;
    protected String penName;
    private ZooKeeper currentZookeeper;
    protected boolean doesPenContainPredator;


    public Pen(PenType penType, int length, int width, int temp, String penName) {
        this.penName = penName;
        this.length = length;
        this.width = width;
        this.temp = temp;
        this.penType = penType;
    }

    public Pen(PenType penType, String penName) {
        this.penName = penName;
        this.penType = penType;
    }

    @Override
    public String toString() { return this.penType.toString(); }

    public PenType getPenType() { return penType; }

    protected boolean isAnimalOfSamePredatoryStatus(Animal animal){
        return animal.isPredator() == doesPenContainPredator; }


    public abstract void addNewAnimal(Animal animal) throws UIException;

    protected boolean isAnimalSuitableForPen(Animal animal) {
        Boolean isSuitable = false;
        try {
            for (PenType penType : animal.getSuitablePens()) {
                if (isPenTypeSuitableForAnimal(penType)) {
                    isSuitable = true;
                    break;
                } else {
                    isSuitable = false;
                }
            }
            if (!isSuitable) {
                throw new Throwable();
            }
        } catch (Throwable throwable) {
            System.out.println("not suitable");
        }
        return isSuitable;
    }

    private boolean isPenTypeSuitableForAnimal(PenType penType) { return this.penType.equals(penType); }

    public int getTemp() { return temp; }

    public ArrayList<Animal> getCurrentAnimals() { return currentAnimals; }

    public int getWidth() { return this.width; }

    public int getLength() { return this.length; }

    public int getNoOfAnimals() { return noOfAnimals; }

    public String getPenName() { return penName; }

    public void setCurrentZookeeper(ZooKeeper currentZookeeper) { this.currentZookeeper = currentZookeeper; }

    public ZooKeeper getCurrentZookeeper() { return currentZookeeper; }
}


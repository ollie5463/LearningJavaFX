package zoo.Pens;

import zoo.Animals.Animal;
import zoo.PenType;

import java.util.ArrayList;

public class  Pen {

    protected PenType penType;
    protected ArrayList<Animal> currentAnimals = new ArrayList<>();
    protected String animalType;
    protected int noOfAnimals = 0;
    protected int length, width, temp;
    protected int availableSpace;
//    protected String penName;


    public Pen(PenType penType, int length, int width, int temp) {
//        this.penName = penName;
        this.length = length;
        this.width = width;
        this.temp = temp;
        this.penType = penType;
    }

    public Pen(PenType penType) {
        this.penType = penType;
    }

    @Override
    public String toString() {
        return this.penType.toString();
    }

    public PenType getPenType() {
        return penType;
    }

    public void addNewAnimal(Animal animal) {
        if (isAnimalSuitableForPen(animal) && isEnoughSpace(animal)) {
            System.out.println("its suitable and there is enough space");
        }
    }

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

    public void removeAnimal(Animal animal) {

        try {
            if (noOfAnimals == 0) {
                throw new Throwable();
            } else {
                noOfAnimals--;
                availableSpace += animal.getSpaceNeeded();
            }
        } catch (Throwable throwable) {
            System.out.println("There are no animals to remove");
        }
    }

    private boolean isPenTypeSuitableForAnimal(PenType penType) {
        if (this.penType.equals(penType)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isEnoughSpace(Animal animal) {
        try {
            if ((availableSpace - animal.getSpaceNeeded()) < 0) {
                throw new Throwable();
            } else {
                availableSpace -= animal.getSpaceNeeded();
                this.currentAnimals.add(animal);
                noOfAnimals++;
                return true;
            }
        } catch (Throwable throwable) {
            System.out.println("there is not enough space to hold this animal, there is only " + availableSpace + " meters squared left");
            return false;
        }
    }

    public int getTemp() {
        return temp;
    }

    public int getAvailableSpace() {
        return this.availableSpace;
    }

    public ArrayList<Animal> getCurrentAnimals() {
        return currentAnimals;
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    public int getNoOfAnimals() {
        return noOfAnimals;
    }
}


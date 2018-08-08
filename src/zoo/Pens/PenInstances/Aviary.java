package zoo.Pens.PenInstances;

import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.AvesAnimal;
import zoo.Exceptions.NotSuitableForPenException;
import zoo.Exceptions.NotEnoughSpaceException;
import zoo.Exceptions.NotOfSamePredtoryStatusException;
import zoo.Exceptions.UIException;
import zoo.PenType;
import zoo.Pens.Pen;

public class Aviary extends Pen {
    private int height;
    private int availableAirSpace;


    public int getHeight() {
        return height;
    }

    public Aviary(int length, int width, int height, int temp, String penName) {
        super(PenType.AVIARY, length, width, temp, penName);
        this.height = height;
        this.availableAirSpace = length * width * height;
    }

    @Override
    public void addNewAnimal(Animal animal) throws UIException{
        AvesAnimal avesAnimal = (AvesAnimal) animal;
        if(!isEnoughSpace(avesAnimal)) {
            throw new NotEnoughSpaceException();
        }
        else if(!isAnimalSuitableForPen(avesAnimal)){
            throw new NotSuitableForPenException();
        }
        else if(!isAnimalOfSamePredatoryStatus(avesAnimal)){
            throw new NotOfSamePredtoryStatusException();
        }
        else{
            updatePenForNewAnimal(avesAnimal);
        }
    }

    private void updatePenForNewAnimal(AvesAnimal avesAnimal){
        availableAirSpace -= avesAnimal.getAirNeeded();
        this.currentAnimals.add(avesAnimal);
        noOfAnimals++;
    }

    private boolean isEnoughSpace(AvesAnimal animal){
        return((availableAirSpace - animal.getAirNeeded()) >= 0);
    }
}

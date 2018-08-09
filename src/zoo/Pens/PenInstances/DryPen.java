package zoo.Pens.PenInstances;

import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.LandAnimal;
import zoo.Exceptions.NotSuitableForPenException;
import zoo.Exceptions.NotEnoughSpaceException;
import zoo.Exceptions.NotOfSamePredtoryStatusException;
import zoo.Exceptions.UIException;
import zoo.Pens.PenType;
import zoo.Pens.Pen;

public class DryPen extends Pen {
    private int availableDrySpace;

    public DryPen(int length, int width, int temp, String penName) {
        super(PenType.DRY, length, width, temp, penName);
        this.availableDrySpace = length * width;
    }
    @Override
    public void addNewAnimal(Animal animal) throws UIException {
        LandAnimal landAnimal = (LandAnimal) animal;
        if(!isEnoughSpace(landAnimal)) throw new NotEnoughSpaceException();

        else if(!isAnimalSuitableForPen(landAnimal)) throw new NotSuitableForPenException();

        else if(!isAnimalOfSamePredatoryStatus(landAnimal)) throw new NotOfSamePredtoryStatusException();

        else updatePenForNewAnimal(landAnimal);
    }

    private void updatePenForNewAnimal(LandAnimal landAnimal){
        availableDrySpace -= landAnimal.getLandNeeded();
        this.currentAnimals.add(landAnimal);
        noOfAnimals++;
    }

    private boolean isEnoughSpace(LandAnimal animal){ return ((availableDrySpace - animal.getLandNeeded()) > 0); }
}

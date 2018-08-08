package zoo.Pens.PenInstances;

import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.AquaticAnimal;
import zoo.Exceptions.NotOfSamePredtoryStatusException;
import zoo.Exceptions.NotSuitableForPenException;
import zoo.Exceptions.NotEnoughSpaceException;
import zoo.Exceptions.UIException;
import zoo.PenType;
import zoo.Pens.Pen;

public class Aquarium extends Pen {
    private int waterVolumeAvailable;
    private int height;

    public int getWaterVolumeAvailable() {
        return waterVolumeAvailable;
    }

    public int getHeight() {
        return height;
    }

    public Aquarium(int length, int width, int height, int temp, String penName){
        super(PenType.AQUARIUM, length, width, temp, penName);
        this.waterVolumeAvailable = length * width * height;
        this.height = height;
    }
    @Override
    public void addNewAnimal(Animal animal) throws UIException{
        AquaticAnimal aquaticAnimal = (AquaticAnimal) animal;
        if(!isEnoughSpace(aquaticAnimal)) {
            throw new NotEnoughSpaceException();
        }
        else if(!isAnimalSuitableForPen(aquaticAnimal)){
            throw new NotSuitableForPenException();
        }
        else if(!isAnimalOfSamePredatoryStatus(aquaticAnimal)){
            throw new NotOfSamePredtoryStatusException();
        }
        else{
            updatePenForNewAnimal(aquaticAnimal);
        }
    }


    private void updatePenForNewAnimal(AquaticAnimal aquaticAnimal) {
        waterVolumeAvailable -= aquaticAnimal.getWaterNeeded();
        this.currentAnimals.add(aquaticAnimal);
        noOfAnimals++;
    }

    protected boolean isEnoughSpace(AquaticAnimal aquaticAnimal){
        return((waterVolumeAvailable - aquaticAnimal.getWaterNeeded() >= 0));
    }
}
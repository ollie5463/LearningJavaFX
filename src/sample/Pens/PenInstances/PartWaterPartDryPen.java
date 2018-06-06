package sample.Pens.PenInstances;

import sample.Animals.Animal;
import sample.PenType;
import sample.Pens.Pen;

public class PartWaterPartDryPen extends Pen {
    private int availableWaterVolume;

    public PartWaterPartDryPen(int length, int width, int waterVolume ,int temp){
        super(PenType.DRY, length, width, temp);
        this.availableSpace = length * width;
        this.availableWaterVolume = waterVolume;
    }

    @Override
    public void addNewAnimal(Animal animal) {
        if (isAnimalSuitableForPen(animal) && isEnoughSpace(animal)) {
            System.out.println("its suitable and there is enough space");
        }
    }
    @Override
    protected boolean isEnoughSpace(Animal animal){
        try {
            if ((availableSpace - animal.getSpaceNeeded()) < 0 || (availableWaterVolume - animal.getWaterVolumeNeeded()) < 0 ) {
                throw new Throwable();
            } else {
                availableSpace -= animal.getSpaceNeeded();
                availableWaterVolume -= animal.getWaterVolumeNeeded();
                this.currentAnimals.add(animal);
                noOfAnimals++;
                return true;
            }
        }
        catch (Throwable throwable){
            System.out.println("there is not enough space to hold this animal, there is "+ availableSpace + " meters squared left of land and "+availableWaterVolume+ " water volume available");
            return false;
        }
    }
    @Override
    public void removeAnimal(Animal animal) {

        try {
            if (noOfAnimals == 0) {
                throw new Throwable();
            } else {
                System.out.println("animal has been removed");
                noOfAnimals--;
                availableSpace += animal.getSpaceNeeded();
                availableWaterVolume += animal.getWaterVolumeNeeded();
            }
        } catch (Throwable throwable) {
            System.out.println("There are no animals to remove");
        }
    }

    public int getAvailableWaterVolume(){
        return this.availableWaterVolume;
    }

}

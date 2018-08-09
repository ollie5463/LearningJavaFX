package zoo.Pens.PenInstances;

import zoo.Animals.Animal;
import zoo.Animals.AnimalClassifications.AmphibiousAnimal;
import zoo.Exceptions.NotEnoughSpaceException;
import zoo.Exceptions.NotOfSamePredtoryStatusException;
import zoo.Exceptions.NotSuitableForPenException;
import zoo.Exceptions.UIException;
import zoo.Pens.PenType;
import zoo.Pens.Pen;

public class PartWaterPartDryPen extends Pen {
    private int availableWaterVolume;
    private int availableDrySpace;

    public PartWaterPartDryPen(int length, int width, int waterVolume ,int temp, String penName){
        super(PenType.PARTWATERPARTDRY, length, width, temp, penName);
        this.availableDrySpace = length * width;
        this.availableWaterVolume = waterVolume;
    }

    @Override
    public void addNewAnimal(Animal animal) throws UIException {
        AmphibiousAnimal amphibiousAnimal = (AmphibiousAnimal) animal;
        if (!isAnimalSuitableForPen(amphibiousAnimal)){
            throw new NotSuitableForPenException();
        }
        else if(!isEnoughSpace(amphibiousAnimal)){
            throw new NotEnoughSpaceException();
        }
        else if(!isAnimalOfSamePredatoryStatus(amphibiousAnimal)){
            throw new NotOfSamePredtoryStatusException();
        }
        else{
            updatePenForNewAnimal(amphibiousAnimal);
        }
    }
    protected boolean isEnoughSpace(AmphibiousAnimal animal){
        return ((availableDrySpace - animal.getLandNeeded()) >= 0 || (availableWaterVolume - animal.getWaterNeeded()) >= 0 );
    }

    private void updatePenForNewAnimal(AmphibiousAnimal amphibiousAnimal){
        availableWaterVolume -= amphibiousAnimal.getWaterNeeded();
        availableDrySpace -= amphibiousAnimal.getLandNeeded();
    }
    public int getAvailableWaterVolume(){
        return this.availableWaterVolume;
    }

}

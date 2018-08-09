package zoo.Animals.AnimalClassifications;

import zoo.Animals.Animal;
import zoo.Pens.PenType;

import java.util.ArrayList;
import java.util.HashMap;

public class AmphibiousAnimal extends Animal{
    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getLandNeeded() {
        return landNeeded;
    }
    private int waterNeeded, landNeeded;

    public AmphibiousAnimal(ArrayList<PenType> suitablePens, String animalName, boolean isPredator, int waterNeeded, int landNeeded) {
        super(suitablePens, animalName, isPredator);
        this.waterNeeded = waterNeeded;
        this.landNeeded = landNeeded;
        this.classification = Classification.AMPHIBIOUS;
    }

    @Override
    public HashMap<String, Integer> getAvailableSpace() {
        return new HashMap<>(){{put("WATER", waterNeeded);put("LAND", landNeeded);}};
    }
}

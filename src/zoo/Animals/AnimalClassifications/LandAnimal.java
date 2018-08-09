package zoo.Animals.AnimalClassifications;

import zoo.Animals.Animal;
import zoo.Pens.PenType;

import java.util.ArrayList;
import java.util.HashMap;

public class LandAnimal extends Animal{
    public int getLandNeeded() {
        return landNeeded;
    }
    private int landNeeded;

    public LandAnimal(ArrayList<PenType> suitablePens, String animalName, boolean isPredator, int landNeeded) {
        super(suitablePens, animalName, isPredator);
        this.landNeeded = landNeeded;
        this.classification = Classification.LAND;
    }
    @Override
    public HashMap<String, Integer> getAvailableSpace() {
        return new HashMap<>(){{put("LAND", landNeeded);}};
    }
}

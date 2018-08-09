package zoo.Animals.AnimalClassifications;

import zoo.Animals.Animal;
import zoo.Pens.PenType;

import java.util.ArrayList;
import java.util.HashMap;

public class AquaticAnimal extends Animal {
    public int getWaterNeeded() {
        return waterNeeded;
    }

    private int waterNeeded;
    public AquaticAnimal(ArrayList<PenType> suitablePens, String animalName, boolean isPredator, int waterNeeded) {
        super(suitablePens, animalName, isPredator);
        this.waterNeeded = waterNeeded;
        this.classification = Classification.AQUATIC;
    }

    @Override
    public HashMap<String, Integer> getAvailableSpace() {
        return new HashMap<>(){{put("WATER", waterNeeded);}};
    }
}

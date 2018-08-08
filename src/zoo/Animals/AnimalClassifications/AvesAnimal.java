package zoo.Animals.AnimalClassifications;

import zoo.Animals.Animal;
import zoo.PenType;

import java.util.ArrayList;
import java.util.HashMap;

public class AvesAnimal  extends Animal{
    public int getAirNeeded() {
        return airNeeded;
    }

    private int airNeeded;
    public AvesAnimal(ArrayList<PenType> suitablePens, String animalName, boolean isPredator, int airNeeded) {
        super(suitablePens, animalName, isPredator);
        this.airNeeded = airNeeded;
    }

    @Override
    public HashMap<String, Integer> getAvailableSpace() {
        return new HashMap<>(){{put("AIR", airNeeded);}};
    }
}

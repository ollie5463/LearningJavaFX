package zoo.Animals;

import zoo.Animals.AnimalClassifications.Classification;
import zoo.Pens.PenType;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Animal {
    private String currentPen = null;
    private String animalName;
    private ArrayList<PenType> suitablePens;
    private boolean isPredator;
    protected Classification classification;

    public Animal(ArrayList<PenType> suitablePens, String animalName, boolean isPredator){
        this.suitablePens = suitablePens;
        this.animalName = animalName;
        this.isPredator = isPredator;
    }

    @Override
    public String toString() { return this.animalName; }

    public ArrayList<PenType> getSuitablePens(){ return this.suitablePens; }

    public String getName() { return animalName; }

    public String getCurrentPen() { return currentPen; }

    public void setCurrentPen(String currentPen) { this.currentPen = currentPen; }

    abstract public HashMap<String, Integer> getAvailableSpace();

    public boolean isPredator() { return isPredator; }

    public Classification getClassification() {
        return classification;
    }
}
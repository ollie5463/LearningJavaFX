package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import zoo.Animals.Animal;
import zoo.PenType;
import zoo.ZooManager;

import java.util.ArrayList;

import static zoo.PenType.*;

public class AnimalsController {

    public void displayChoicesForPenType(MouseEvent mouseEvent, ChoiceBox<PenType> zooKeeperPenType, ChoiceBox<PenType> zooKeeperPenType2) {
        String source = ((ChoiceBox) mouseEvent.getSource()).idProperty().getValue();
        switch (source) {
            case "PenType":
                zooKeeperPenType.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
            case "PenType2":
                zooKeeperPenType2.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
        }
    }

    public void displayChoicesForAnimals(ChoiceBox<Animal> Animals) {
        ArrayList<Animal> animals = ZooManager.getAnimals();
        ObservableList<Animal> listOfAnimals =FXCollections.observableArrayList();
        listOfAnimals.addAll(animals);
        Animals.setItems(listOfAnimals);
    }

    public void displayValuesForAnimals(ChoiceBox<Animal> Animals, TextArea screenForAnimals, int rowCount) {
        Animal chosenAnimal = Animals.getValue();
        try {
            Animal animal = ZooManager.getAnimal(chosenAnimal);
            screenForAnimals.setPrefRowCount(rowCount);
            screenForAnimals.setText("Animal info for: " + animal + "\n\n"
                    +"Space needed for animal: " + animal.getSpaceNeeded() + "\n\n"
                    +"Suitable pens: " + animal.getSuitablePens() + "\n\n"
                    +"Water volume needed: " + animal.getWaterVolumeNeeded());
        }
        catch(Throwable animalException){
            screenForAnimals.setText("Please choose an animal from the list at the top of the screen !!!");
        }
    }

    public void addAnimal(ChoiceBox<PenType> penType, ChoiceBox<PenType> penType2, TextArea waterSpaceNeeded, TextArea spaceNeeded, TextArea screenForAnimals, TextArea animalType) {

        //@todo when adding an animal the water volume needed is always set to 0 even though it shoudlnt be, need to fix

        ArrayList<PenType> penTypes = getChosenPenTypes(penType, penType2, screenForAnimals);
        if( waterSpaceNeeded != null && spaceNeeded != null) {
            ZooManager.addAnimal(getAnimalType(animalType, screenForAnimals), penTypes, getSpaceNeeded(spaceNeeded, screenForAnimals));
        }
        else if(waterSpaceNeeded != null){
            ZooManager.addAnimal(getAnimalType(animalType, screenForAnimals), penTypes, getSpaceNeeded(spaceNeeded, screenForAnimals),getWaterSpacedNeeded(waterSpaceNeeded, screenForAnimals));
        }
        animalType.clear();
        spaceNeeded.clear();
        waterSpaceNeeded.clear();
    }

    private ArrayList<PenType> getChosenPenTypes(ChoiceBox<PenType> penType, ChoiceBox<PenType> penType2, TextArea screenForAnimals){
        try {
            if (penType.getValue() == null && penType2.getValue() == null) {
                throw new Throwable();
            }
        }
        catch(Throwable PenTypeNotSpecified){
            screenForAnimals.setText("Pen type not specified");
        }
        return new ArrayList<>(){{add(penType.getValue());add(penType2.getValue());}};
    }


    private String getAnimalType(TextArea animalType, TextArea screenForAnimals){
        try{
            if (animalType.getText() == null){
                throw new Throwable();
            }
        }
        catch(Throwable AnimalTypeNotSpecified){
            screenForAnimals.setText("animal type not specified");
        }
        return animalType.getText();
    }

    private int getSpaceNeeded(TextArea spaceNeeded, TextArea screenForAnimals){
        try{
            if(spaceNeeded.getText() == null){
                throw new Throwable();
            }
        }
        catch(Throwable SpaceNeededNotSpecified){
            screenForAnimals.setText("Please specify space needed for the animal");
        }
        return Integer.parseInt(spaceNeeded.getText());
    }

    private int getWaterSpacedNeeded(TextArea waterSpaceNeeded, TextArea screenForAnimals){
        try{
            if(waterSpaceNeeded.getText() == null){
                throw new Throwable();
            }
        }
        catch(Throwable waterSpaceNotSpecified){
            screenForAnimals.setText("please specify a water volume");
        }

        return Integer.parseInt(waterSpaceNeeded.getText());
    }
}

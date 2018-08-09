package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import zoo.Animals.Animal;
import zoo.Pens.PenType;
import zoo.Pens.Pen;
import zoo.ZooManager;

import java.util.ArrayList;

import static zoo.Pens.PenType.*;
import static zoo.ZooManager.getAnimalsNotAssignedToAPen;

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
            screenForAnimals.setText(animal.getAvailableSpace().toString());
            screenForAnimals.setText("Animal info for: " + animal + "\n\n" +
                    animal.getAvailableSpace().toString() + "\n\n"+
                    "Suitable pens: " + animal.getSuitablePens() + "\n\n"
                    +"Current pen: " + animal.getCurrentPen() + "\n\n"
                    +"Predator or prey: " + animal.isPredator());
        }
        catch(Throwable animalException){
            screenForAnimals.setText("Please choose an animal from the list at the top of the screen !!!");
        }
    }


    public void addAnimal(ChoiceBox<PenType> penType, ChoiceBox<PenType> penType2, TextArea waterSpaceOrAirSpaceNeeded, TextArea spaceNeeded, TextArea screenForAnimals, TextArea animalType, ChoiceBox predatorOrPrey) {
        ArrayList<PenType> penTypes = getChosenPenTypes(penType, penType2, screenForAnimals);
        boolean isPredator = (predatorOrPrey.getValue()) == "Predator";
        if(penTypes.contains(PETTING) && penTypes.contains(DRY)){
            ZooManager.addLandAnimal(new ArrayList<>(){{add(PETTING);add(DRY);}}, animalType.getText(), isPredator, Integer.parseInt(spaceNeeded.getText()));
        }
        else if(penTypes.contains(PETTING)){
            ZooManager.addLandAnimal(new ArrayList<>(){{add(PETTING);}}, animalType.getText(), isPredator, Integer.parseInt(spaceNeeded.getText()));
        }
        else if(penTypes.contains(DRY)){
            ZooManager.addLandAnimal(new ArrayList<>(){{add(DRY);}}, animalType.getText(), isPredator, Integer.parseInt(spaceNeeded.getText()));
        }
        else if(penTypes.contains(PARTWATERPARTDRY)) {
            ZooManager.addAmphibiousAnimal(new ArrayList<>(){{add(PARTWATERPARTDRY);}}, animalType.getText(), isPredator, Integer.parseInt(waterSpaceOrAirSpaceNeeded.getText()), Integer.parseInt(spaceNeeded.getText()));
        }
        else if(penTypes.contains(AQUARIUM)){
            ZooManager.addAquaticAnimal(new ArrayList<>(){{add(AQUARIUM);}}, animalType.getText(), isPredator, Integer.parseInt(waterSpaceOrAirSpaceNeeded.getText()));
        }
        else if(penTypes.contains(AVIARY)){
            ZooManager.addAvesAnimal(new ArrayList<>(){{add(AVIARY);}}, animalType.getText(), isPredator, Integer.parseInt(waterSpaceOrAirSpaceNeeded.getText()));
        }
        clearAnimalRequirements(new ArrayList<>(){{add(animalType);add(spaceNeeded);add(waterSpaceOrAirSpaceNeeded);}});
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


    private void clearAnimalRequirements(ArrayList<TextArea> animalChoices){
        for(TextArea textArea :  animalChoices){
            textArea.clear();
        }

    }

    public void displayChoicesForPens(ChoiceBox<Animal> animalChoices, TextArea screenForAssigningAnimals, ChoiceBox<String> penChoices) {
        if(animalChoices.getValue() == null){
            screenForAssigningAnimals.setPrefRowCount(2);
            screenForAssigningAnimals.setText("please choose an animal before you \n pick a possible pen to assign it to");
        }
        else{
            penChoices.setItems(getListOfPensForAnimal(animalChoices.getValue().getSuitablePens(),ZooManager.getPens()));
        }
    }

    private ObservableList<String> getListOfPensForAnimal(ArrayList<PenType> suitablePens, ArrayList<Pen> pens){
        ObservableList<String> listOfPens = FXCollections.observableArrayList();
        for(Pen pen : pens){
            if(suitablePens.contains(pen.getPenType())){
                listOfPens.add(pen.getPenName());
            }
        }
         return listOfPens;
    }

    public void assignAnimalToPen(ChoiceBox<Animal> animalChoices, ChoiceBox<String> penChoices, TextArea screenForAssigningAnimals) {
        if(animalChoices.getValue().getCurrentPen() == null || !animalChoices.getValue().getCurrentPen().equals(penChoices.getValue())){
            try{
                assignAnimal(animalChoices.getValue(), penChoices.getValue());
                animalChoices.getValue().setCurrentPen(penChoices.getValue());
                screenForAssigningAnimals.setText("You just set " + animalChoices.getValue().getName() + " to pen " + penChoices.getValue());
            }
            catch (Throwable throwable){
                screenForAssigningAnimals.setText(throwable.toString());
            }
        }
        else{
            screenForAssigningAnimals.setText("Your trying to assign " + animalChoices.getValue().getName() + " to a pen its currently in " + penChoices.getValue());
         }
    }


    private void assignAnimal(Animal animalChoice, String penName) throws Throwable{
            ZooManager.assignAnimalToPen(animalChoice, penName);
    }

    public void checkAnimalsAreAssignedToPens(TextArea screenForHome) {
        ArrayList<Animal> animalsNotAssignedToPens = getAnimalsNotAssignedToAPen();
        if(!animalsNotAssignedToPens.isEmpty()){
            screenForHome.setText(animalsNotAssignedToPens.toString());
        }
        else{
            screenForHome.setText("All animals are assigned to pens");
        }
    }

    public void autoAllocateAnimals(TextArea screenForAssigningAnimals) {
        ArrayList<Animal> animalsNotAssignedToAPen = ZooManager.getAnimalsNotAssignedToAPen();
        if(!animalsNotAssignedToAPen.isEmpty()){
            for(Animal animal : animalsNotAssignedToAPen){
                ZooManager.autoAssignAnimalToPen(animal);
            }
        }
        else{
            screenForAssigningAnimals.setText("There are no animals to auto allocate");
        }
    }

    public void displayChoicesForAnimalOrPrey(ChoiceBox predatorOrPrey) {
        predatorOrPrey.setItems(FXCollections.observableArrayList("Predator", "Prey"));
    }

    public void setTable(TextArea tableForAnimal) {
        tableForAnimal.setPrefRowCount(5);
        tableForAnimal.setText("Different animal specs when adding an animal" + "\n\n"+
        "For Dry or Petting pens you specify the landm2" + "\n\n" +
        "For Part Water Part Dry pens you specify the landm2 and water m3" + "\n\n" +
        "For an Aviary you specify the air m3" + "\n\n" +
        "For an Aquarium you need to specify the waterm3" + "\n\n"
        );
    }
}
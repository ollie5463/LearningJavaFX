package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Animals.Animal;
import sample.PenType;
import sample.Pens.Pen;
import sample.ZooKeeper;
import sample.ZooManager;

import java.util.ArrayList;

import static sample.PenType.*;


public class Controller{

    @FXML
    private TextArea waterSpaceNeeded;

    @FXML
    private TextArea animalType;

    @FXML
    private TextArea spaceNeeded;

    @FXML
    private TextArea screenForAnimals;

    @FXML
    private ChoiceBox<Animal> Animals;

    @FXML
    private ChoiceBox<ZooKeeper> ZooKeepers;

    @FXML
    private ChoiceBox<Pen> Pens;

    @FXML
    private ChoiceBox<sample.PenType> PenType;

    @FXML
    private ChoiceBox<PenType> PenType2;

    @FXML
    private ChoiceBox<PenType> ZooKeeperPenType;

    @FXML
    private ChoiceBox<PenType> ZooKeeperPenType2;

    @FXML
    private TextArea screen;

    @FXML
    private TextArea screenForPens;

    @FXML
    private void displayChoicesForZooKeepers(MouseEvent mouseEvent) {

        ArrayList<ZooKeeper> zooKeepers = ZooManager.getZooKeepers();

        ObservableList listOfZookeepers = FXCollections.observableArrayList();

        for(ZooKeeper zooKeeper : zooKeepers){
            listOfZookeepers.add(zooKeeper);
        }
        ZooKeepers.setItems(listOfZookeepers);
    }

    @FXML
    private void displayValuesForZooKeepers(ActionEvent actionEvent) {
        ZooKeeper chosenZooKeeper = ZooKeepers.getValue();

        try{
            ZooKeeper zooKeeper = ZooManager.getZooKeeper(chosenZooKeeper);
            screen.setPrefRowCount(2);
            screen.setText("Zoo keeper info for: "+ zooKeeper + "\n\n"
                    + "Pens responsible for: " + zooKeeper.getPensResponsibleFor().toString()
            );
        }
        catch(Throwable zooKeeperException){
            System.out.println("That isn't a Zoo Keeper");
        }
    }

    @FXML
    private void displayChoicesForAnimals(MouseEvent mouseEvent) {
        ArrayList<Animal> animals = ZooManager.getAnimals();
        ObservableList listOfAnimals =FXCollections.observableArrayList();

        for(Animal animal : animals){
            listOfAnimals.add(animal);
        }
        Animals.setItems(listOfAnimals);
    }

    @FXML
    private void displayValuesForAnimals(ActionEvent actionEvent){
        Animal chosenAnimal = Animals.getValue();
        try {
            Animal animal = ZooManager.getAnimal(chosenAnimal);
            screenForAnimals.setPrefRowCount(4);
            screenForAnimals.setText("Animal info for: " + animal + "\n\n"
                    +"Space needed for animal: " + animal.getSpaceNeeded() + "\n\n"
                    +"Suitable pens: " + animal.getSuitablePens() + "\n\n"
                    +"Water volume needed: " + animal.getWaterVolumeNeeded());
        }
        catch(Throwable animalException){
            screenForAnimals.setText("Please choose an animal from the list at the top of the screen !!!");
        }
    }

    @FXML
    private void addAnimal(ActionEvent actionEvent){
        ArrayList<PenType> penTypes = getChosenPenTypes();
        if( waterSpaceNeeded != null && spaceNeeded != null) {
            ZooManager.addAnimal(getAnimalType(), penTypes, getSpaceNeeded());
        }
        else if(waterSpaceNeeded != null){
            ZooManager.addAnimal(getAnimalType(), penTypes, getSpaceNeeded(),getWaterSpacedNeeded());
        }
        animalType.clear();
        spaceNeeded.clear();
        waterSpaceNeeded.clear();
    }

    @FXML
    private void displayChoicesForPenType(MouseEvent mouseEvent) {
        String source = ((ChoiceBox) mouseEvent.getSource()).idProperty().getValue();
        switch (source) {
            case "ZooKeeperPenType":
                ZooKeeperPenType.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
            case "ZooKeeperPenType2":
                ZooKeeperPenType2.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
            case "PenType":
                PenType.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
            case "PenType2":
                PenType2.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
        }
    }

    private ArrayList<PenType> getChosenPenTypes(){
        try {
            if (PenType.getValue() == null && PenType2.getValue() == null) {
                throw new Throwable();
            }
        }
        catch(Throwable PenTypeNotSpecified){
            screenForAnimals.setText("Pen type not specified");
        }
        return new ArrayList<>(){{add(PenType.getValue());add(PenType2.getValue());}};
    }

    private int getSpaceNeeded(){
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

    private int getWaterSpacedNeeded(){
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

    private String getAnimalType(){
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

    @FXML
    private void changePenType(ActionEvent actionEvent) {

        PenType chosenPenType = ZooKeeperPenType.getValue();
        PenType chosenPenType2 = ZooKeeperPenType2.getValue();
        ArrayList<PenType> penTypes = ZooKeepers.getValue().getPensResponsibleFor();

        ZooKeeper zooKeeper = ZooKeepers.getValue();
        if(!chosenPenType.equals(penTypes.get(0))){
            zooKeeper.setPensResponsibleFor(0, chosenPenType);
        }
        if(!chosenPenType2.equals(penTypes.get(1))){
            zooKeeper.setPensResponsibleFor( 1, chosenPenType2);
        }
        this.displayValuesForZooKeepers(actionEvent);
    }

    @FXML
    private void displayChoicesForPens(MouseEvent mouseEvent) {
        ArrayList<Pen> pens = ZooManager.getPens();
        ObservableList listOfPens = FXCollections.observableArrayList();

        for(Pen pen : pens){
            listOfPens.add(pen);
        }
        Pens.setItems(listOfPens);
    }

    @FXML
    private void displayValuesForPens(ActionEvent actionEvent){
        Pen chosenPen = Pens.getValue();
        try{
            Pen pen = ZooManager.getPen(chosenPen);
            screenForPens.setPrefRowCount(4);
            screenForPens.setText("Pen info for: " + pen + "\n\n"
                    + "Width: " + pen.getWidth() + "\n\n"
                    + "Length: " + pen.getLength() + "\n\n"
                    + "Temp: " + pen.getTemp()
            );
        }
        catch(Throwable zooKeeperException){

        }
    }
}

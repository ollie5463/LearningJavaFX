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
    private TextArea screenForZooKeepers;

    @FXML
    private ChoiceBox<Animal> Animals;

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
    private TextArea screenForPens;

    @FXML
    private ChoiceBox<ZooKeeper> ZooKeepers;

    private ZooKeeperController zooKeeperController;
    private AnimalsController animalsController;
    private PenController penController;

    public Controller(){
        zooKeeperController = new ZooKeeperController();
        animalsController = new AnimalsController();
        penController = new PenController();
    }

    @FXML
    private void displayChoicesForZooKeepers(MouseEvent mouseEvent) {
        zooKeeperController.displayChoicesForKeepers(ZooManager.getZooKeepers(), ZooKeepers);
    }

    @FXML
    private void displayValuesForZooKeepers(ActionEvent actionEvent){
        ZooKeeper chosenZooKeeper = ZooKeepers.getValue();
        zooKeeperController.displayValuesForZooKeepers(2, getZooKeeper(chosenZooKeeper), screenForZooKeepers);
    }

    //    @Todo
    @FXML
    private void displayChoicesForAnimals(MouseEvent mouseEvent) {
        ArrayList<Animal> animals = ZooManager.getAnimals();
        ObservableList listOfAnimals =FXCollections.observableArrayList();

        for(Animal animal : animals){
            listOfAnimals.add(animal);
        }
        Animals.setItems(listOfAnimals);
    }

    //    @Todo
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

    //    @Todo
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
    private void displayChoicesForPenTypeForZooKeepers(MouseEvent mouseEvent){
        zooKeeperController.displayChoicesForPenType(mouseEvent, ZooKeeperPenType, ZooKeeperPenType2);
    }
    //    @Todo Pen type controller
    @FXML
    private void displayChoicesForPenType(MouseEvent mouseEvent) {
        String source = ((ChoiceBox) mouseEvent.getSource()).idProperty().getValue();
        switch (source) {
            case "PenType":
                PenType.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
            case "PenType2":
                PenType2.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
        }
    }

    //    @Todo
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

    //    @Todo
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

    //    @Todo
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

    //    @Todo
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
        zooKeeperController.changePenTypes(ZooKeeperPenType, ZooKeeperPenType2, ZooKeepers);
        this.displayValuesForZooKeepers(actionEvent);
    }

    //    @Todo
    @FXML
    private void displayChoicesForPens(MouseEvent mouseEvent) {
        ArrayList<Pen> pens = ZooManager.getPens();
        ObservableList listOfPens = FXCollections.observableArrayList();

        for(Pen pen : pens){
            listOfPens.add(pen);
        }
        Pens.setItems(listOfPens);
    }

    //    @Todo
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

    //    @Todo
    private ZooKeeper getZooKeeper(ZooKeeper zooKeeper){
        try{
            zooKeeper = ZooManager.getZooKeeper(zooKeeper);

        }
        catch(Throwable zooKeeperException){
            System.out.println("That isn't a Zoo Keeper");
        }
        return zooKeeper;
    }
}

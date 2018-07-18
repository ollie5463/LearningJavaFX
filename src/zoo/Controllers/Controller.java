package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import zoo.Animals.Animal;
import zoo.PenType;
import zoo.ZooKeeper;
import zoo.ZooManager;

import java.util.ArrayList;


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
    private ChoiceBox<String> Pens;

    @FXML
    private ChoiceBox<zoo.PenType> PenType;

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
    public void initialize(){

    }

    // Zoo keeper controller stuff

    @FXML
    private void displayChoicesForZooKeepers(MouseEvent mouseEvent) {
        zooKeeperController.displayChoicesForKeepers(ZooKeepers);
    }

    @FXML
    private void displayValuesForZooKeepers(ActionEvent actionEvent){
        zooKeeperController.displayValuesForZooKeepers(2, ZooKeepers, screenForZooKeepers);
    }

    @FXML
    private void displayChoicesForPenTypeForZooKeepers(MouseEvent mouseEvent){
        zooKeeperController.displayChoicesForPenType(mouseEvent, ZooKeeperPenType, ZooKeeperPenType2);
    }

    @FXML
    private void changePenType(ActionEvent actionEvent) {
        zooKeeperController.changePenTypes(ZooKeeperPenType, ZooKeeperPenType2, ZooKeepers);
        this.displayValuesForZooKeepers(actionEvent);
    }
    // end
    // Pen controller stuff

    //    @Todo -- displays the options for which pen you want to pick
    @FXML
    private void displayChoicesForPens(MouseEvent mouseEvent) {
        penController.displayChoicesForPens(Pens);
    }

    //    @Todo
    @FXML
    private void displayValuesForPens(ActionEvent actionEvent){
//        penController.displayChoicesForPens(Pens);
//        String chosenPen = Pens.getValue();
//        try
//        ZooManager.getPen(chosenPen);

//        Pen chosenPen = Pens.getValue();
//        try{
//            Pen pen = ZooManager.getPen(chosenPen);
//            screenForPens.setPrefRowCount(4);
//            screenForPens.setText("Pen info for: " + pen + "\n\n"
//                    + "Width: " + pen.getWidth() + "\n\n"
//                    + "Length: " + pen.getLength() + "\n\n"
//                    + "Temp: " + pen.getTemp()
//            );
//        }
//        catch(Throwable zooKeeperException){
//
//        }
    }

    // end
    // Animals controller stuff

    @FXML
    private void displayChoicesForPenType(MouseEvent mouseEvent) {
        penController.displayChoicesForPenType(mouseEvent, ZooKeeperPenType, ZooKeeperPenType2);
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






}

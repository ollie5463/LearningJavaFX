package zoo.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import zoo.*;
import zoo.Animals.Animal;
import zoo.FileUtilities.FileUtilities;
import zoo.Zoo.Zoo;
import java.io.IOException;
import java.util.ArrayList;


public class Controller{

    public TextArea ScreenForAssigningAnimals;
    public TextArea screenForWeather;
    public TextArea screenForUnassignedAnimals;
    public Button displayWeather;
    public TextArea screenForUnassignedPens;
    public ChoiceBox PredatorOrPrey;
    public ChoiceBox<Animal> AnimalChoices;
    public TextArea width;
    public TextArea length;
    public TextArea penName;
    public TextArea heightOrVolume;
    public TextArea temp;
    public Button submitAnimal;
    public ChoiceBox<String> PenChoices;
    public Button AddPen;
    public ChoiceBox<PenType> PenTypeForPen;
    public TextArea waterSpaceOrAirVolumeNeeded;
    public TextArea animalType;
    public TextArea spaceNeeded;
    public TextArea screenForAnimals;
    public TextArea screenForZooKeepers;
    public ChoiceBox<Animal> Animals;
    public ChoiceBox<String> Pens;
    public ChoiceBox<zoo.PenType> PenType;
    public ChoiceBox<PenType> PenType2;
    public ChoiceBox<String> PossiblePensForZooKeepers;
    public ChoiceBox<PenType> ZooKeeperPenType2;
    public TextArea screenForPens;
    public ChoiceBox<ZooKeeper> ZooKeepers;

    public ZooKeeperController getZooKeeperController() {
        return zooKeeperController;
    }

    private ZooKeeperController zooKeeperController;

    public AnimalsController getAnimalsController() {
        return animalsController;
    }

    public PenController getPenController() {
        return penController;
    }

    private AnimalsController animalsController;
    private PenController penController;

    public Controller(){
        zooKeeperController = new ZooKeeperController();
        animalsController = new AnimalsController();
        penController = new PenController();
    }

    @FXML
    public void initialize(){
        // @todo, you need to find a way to only initialise the zoo once because it now does it twice
        // one when you load the first screen and once when you load the second
        Zoo zoo = FileUtilities.readFromFile();
            if(zoo == null){
                ZooManager.createDefaultZoo();
            }
            else{
                ZooManager.loadUpZoo(zoo);
            }

    }

    @FXML
    public void getWeather(){
        WeatherManager weatherManager = new WeatherManager();
        try {
            weatherManager.run(this);
        }
        catch (Exception e){

        }
    }

    @FXML
    public void setWeather(ArrayList<Object> weather){
        screenForWeather.setText("The current temperature is: " + weather.get(0) + " This was accessed at " + weather.get(1));
    }

    // Zoo keeper controller stuff

    @FXML
    public void displayChoicesForZooKeepers() {
        zooKeeperController.displayChoicesForKeepers(ZooKeepers);
    }

    @FXML
    public void displayValuesForZooKeepers(ActionEvent actionEvent){
        zooKeeperController.displayValuesForZooKeepers(2, ZooKeepers, screenForZooKeepers);
    }


    // end
    // Pen controller stuff

    @FXML
    public void displayChoicesForPens() {
        penController.displayChoicesForPens(Pens);
    }

    @FXML
    public void displayChoicesForPens2(){
        penController.displayChoicesForPenType(PenTypeForPen);
    }
    @FXML
    public void displayValuesForPens(){
        penController.displayValuesForPen(Pens, screenForPens, 4);
    }

    public void addPen() {
        penController.addPen(PenTypeForPen, width, heightOrVolume, length, penName, temp);
    }

    // end
    // Animals controller stuff

    @FXML
    public void displayChoicesForPenType(MouseEvent mouseEvent) {
        animalsController.displayChoicesForPenType(mouseEvent, PenType, PenType2);
    }

    @FXML
    public void displayChoicesForAnimals() {
        animalsController.displayChoicesForAnimals(Animals);
    }

    @FXML
    public void displayValuesForAnimals(){
        animalsController.displayValuesForAnimals(Animals , screenForAnimals, 4);
    }

    //    @Todo  BUG
    @FXML
    public void addAnimal(){
        animalsController.addAnimal(PenType, PenType2, waterSpaceOrAirVolumeNeeded, spaceNeeded, screenForAnimals, animalType, PredatorOrPrey);
    }

    // assign animal to pen stuff
    public void assignAnimalToPenPopUpWindowCreation(ActionEvent actionEvent) {
        // break down into create popUp
        Stage secondaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../FXML/AssignAnimalToPenPopUp.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondaryStage.setTitle("Assign animal to Pen");
        secondaryStage.setScene(new Scene(root, 900, 600));
        secondaryStage.show();
    }

    public void displayChoicesForPenPopUpWindow() {
        animalsController.displayChoicesForPens(AnimalChoices, ScreenForAssigningAnimals, PenChoices);
    }

    public void displayChoicesForAnimalsPopUpWindow(){
        animalsController.displayChoicesForAnimals(AnimalChoices);
    }

    public void assignAnimalToPen() {
        animalsController.assignAnimalToPen(AnimalChoices, PenChoices, ScreenForAssigningAnimals);
    }

    // zookeeper stuff
    public void pensZooKeepersAreResponsibleFor() {
        try {
            zooKeeperController.displayChoicesForPens(PossiblePensForZooKeepers, ZooKeepers);
        }
        catch(NullPointerException zooKeeperNotChosenException){
            screenForZooKeepers.setText("Please choose a zookeeper before chosing a pen");
        }
    }

    public void assignPenToZooKeeper(ActionEvent actionEvent) {
        try {
            zooKeeperController.setPensResponsibleFor(ZooKeepers, PossiblePensForZooKeepers);
        }
        catch(Throwable throwable){
// @todo
        }
        this.displayValuesForZooKeepers(actionEvent);
    }

    public void checkAnimalsAreAssignedToPens() {
        animalsController.checkAnimalsAreAssignedToPens(screenForUnassignedAnimals);
    }

    public void checkPensAreAssignedToStaff() {
        penController.checkPensAreAssignedToStaff(screenForUnassignedPens);
    }

    public void autoAllocatePens() {
        penController.autoAllocatePens(screenForUnassignedPens);
    }

    public void autoAllocateAnimals() {
        animalsController.autoAllocateAnimals(screenForUnassignedAnimals);
    }

    public void displayChoicesForPredatorOrPrey() {
        animalsController.displayChoicesForAnimalOrPrey(PredatorOrPrey);
    }
}
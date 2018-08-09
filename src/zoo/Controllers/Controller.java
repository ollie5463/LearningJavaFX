package zoo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import zoo.*;
import zoo.Animals.Animal;
import zoo.FileUtilities.FileManager;
import zoo.Pens.PenType;
import zoo.Zoo.Zoo;
import java.io.IOException;
import java.util.ArrayList;


public class Controller{

    public TextArea ScreenForAssigningAnimals;
    public TextArea ScreenForWeather;
    public TextArea ScreenForUnassignedAnimals;
    public Button DisplayWeather;
    public TextArea ScreenForUnassignedPens;
    public ChoiceBox PredatorOrPrey;
    public ChoiceBox<Animal> AnimalChoices;
    public TextArea Width;
    public TextArea Length;
    public TextArea PenName;
    public TextArea HeightOrVolume;
    public TextArea Temp;
    public Button SubmitAnimal;
    public ChoiceBox<String> PenChoices;
    public Button AddPen;
    public ChoiceBox<PenType> PenTypeForPen;
    public TextArea WaterSpaceOrAirVolumeNeeded;
    public TextArea AnimalType;
    public TextArea SpaceNeeded;
    public TextArea ScreenForAnimals;
    public TextArea ScreenForZooKeepers;
    public ChoiceBox<Animal> Animals;
    public ChoiceBox<String> Pens;
    public ChoiceBox<zoo.Pens.PenType> PenType;
    public ChoiceBox<PenType> PenType2;
    public ChoiceBox<String> PossiblePensForZooKeepers;
    public TextArea ScreenForPens;
    public ChoiceBox<ZooKeeper> ZooKeepers;
    public TextArea TableForAnimal;
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
        // @todo, you need to find a way to only initialise the zoo once because it now does it twice
        // one when you load the first screen and once when you load the second
        Zoo zoo = FileManager.readFromFile();
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
            //@todo
        }
    }

    @FXML
    public void setWeather(ArrayList<Object> weather){
        ScreenForWeather.setText("The current temperature is: " + weather.get(0) + " This was accessed at " + weather.get(1));
    }

    // Zoo keeper controller stuff

    @FXML
    public void displayChoicesForZooKeepers() {
        zooKeeperController.displayChoicesForKeepers(ZooKeepers);
    }

    @FXML
    public void displayValuesForZooKeepers(){
        zooKeeperController.displayValuesForZooKeepers(2, ZooKeepers, ScreenForZooKeepers);
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
        penController.displayValuesForPen(Pens, ScreenForPens, 4);
    }

    public void addPen() {
        penController.addPen(PenTypeForPen, Width, HeightOrVolume, Length, PenName, Temp);
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
        animalsController.displayValuesForAnimals(Animals , ScreenForAnimals, 4);
    }

    //    @Todo  BUG
    @FXML
    public void AddAnimal(){
        animalsController.addAnimal(PenType, PenType2, WaterSpaceOrAirVolumeNeeded, SpaceNeeded, ScreenForAnimals, AnimalType, PredatorOrPrey);
    }

    // assign animal to pen stuff
    public void assignAnimalToPenPopUpWindowCreation() {
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
            ScreenForZooKeepers.setText("Please choose a zookeeper before chosing a pen");
        }
    }

    public void assignPenToZooKeeper() {
        try {
            zooKeeperController.setPensResponsibleFor(ZooKeepers, PossiblePensForZooKeepers);
        }
        catch(Throwable throwable){

        }
        this.displayValuesForZooKeepers();
    }

    public void checkAnimalsAreAssignedToPens() {
        animalsController.checkAnimalsAreAssignedToPens(ScreenForUnassignedAnimals);
    }

    public void checkPensAreAssignedToStaff() {
        penController.checkPensAreAssignedToStaff(ScreenForUnassignedPens);
    }

    public void autoAllocatePens() {
        penController.autoAllocatePens(ScreenForUnassignedPens);
//        checkPensAreAssignedToStaff();
    }

    public void autoAllocateAnimals() {
        animalsController.autoAllocateAnimals(ScreenForUnassignedAnimals);
//        checkAnimalsAreAssignedToPens();
    }

    public void displayChoicesForPredatorOrPrey() {
        animalsController.displayChoicesForAnimalOrPrey(PredatorOrPrey);
    }

    public void resetPensForZooKeeper() {
        try {
            zooKeeperController.resetPensForZooKeeper(ZooKeepers);
            this.displayValuesForZooKeepers();
        }
        catch(Throwable uiException){
            ScreenForZooKeepers.setText(uiException.toString());
        }
    }

    public void displayTableForAnimal() {
        animalsController.setTable(TableForAnimal);
    }
}
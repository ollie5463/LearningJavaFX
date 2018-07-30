package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import zoo.Animals.Animal;
import zoo.FileUtilities.FileUtilities;
import zoo.PenType;
import zoo.WeatherManager;
import zoo.Zoo.Zoo;
import zoo.ZooKeeper;
import zoo.ZooManager;


public class Controller{

    public TextArea width;
    public TextArea length;
    public TextArea penName;
    public TextArea heightOrVolume;
    public TextArea temp;
    @FXML
    private Button AddPen;

    @FXML
    private ChoiceBox<PenType> PenTypeForPen;

    @FXML
    private TextArea screenForWeather;

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
        Zoo zoo = FileUtilities.readFromFile();
            if(zoo == null){
                ZooManager.createDefaultZoo();
            }
            else{
                ZooManager.loadUpZoo(zoo);
            }

    }

    @FXML
    private void getWeather(){
        WeatherManager weatherManager = new WeatherManager();
        try {
            weatherManager.run(this);
        }
        catch (Exception e){

        }
    }

    @FXML
    public void setWeather(double temperature){
            screenForWeather.setText("The current temperature is: " + temperature);
    }

    // Zoo keeper controller stuff

    @FXML
    private void displayChoicesForZooKeepers() {
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

    @FXML
    private void displayChoicesForPens() {
        penController.displayChoicesForPens(Pens);
    }

    @FXML
    private void displayChoicesForPens2(){
        penController.displayChoicesForPenType(PenTypeForPen);
    }
    //    @Todo
    @FXML
    private void displayValuesForPens(){
        penController.displayValuesForPen(Pens, screenForPens, 4);
    }

    public void addPen() {
        penController.addPen(PenTypeForPen, width, heightOrVolume, length, penName, temp);
    }

    // end
    // Animals controller stuff

    @FXML
    private void displayChoicesForPenType(MouseEvent mouseEvent) {
        animalsController.displayChoicesForPenType(mouseEvent, PenType, PenType2);
    }

    @FXML
    private void displayChoicesForAnimals() {
        animalsController.displayChoicesForAnimals(Animals);
    }

    @FXML
    private void displayValuesForAnimals(){
        animalsController.displayValuesForAnimals(Animals , screenForAnimals, 4);
    }

    //    @Todo  BUGGG
    @FXML
    private void addAnimal(){
        animalsController.addAnimal(PenType, PenType2, waterSpaceNeeded, spaceNeeded, screenForAnimals, animalType);
    }
}

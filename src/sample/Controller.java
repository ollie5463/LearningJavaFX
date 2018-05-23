package sample;

import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller{
//    private ObservableArrayList<String> choiceBoxOptions = new ObservableList<>();


    @FXML
    private ChoiceBox<String> ZooKeeper;

    @FXML
    private TextField screen;

    @FXML
    private void displayChoices(ActionEvent actionEvent){
        System.out.println("clicked");
        ZooKeeper.setValue("farhad");
        ZooKeeper.setValue("hardip");
        ZooKeeper.setValue("alex");
        ZooKeeper.setValue("alan");
    }

    @FXML
    private void displayValue(ActionEvent actionEvent){
        String zookeeper= ZooKeeper.getValue();



        switch (zookeeper) {
            case "farhad":
                screen.setText("zoo keeper info for: " + ZooManager.getZooKeeper("farhad"));
                break;
            case "hardip":
                screen.setText("zoo keeper info for: " + ZooManager.getZooKeeper("hardip"));
                break;
            case "alex":
                screen.setText("zoo keeper info for: " + ZooManager.getZooKeeper("alex"));
                break;
            case "alan":
                screen.setText("zoo keeper info for: " + ZooManager.getZooKeeper("alan"));
                break;
        }
    }
}

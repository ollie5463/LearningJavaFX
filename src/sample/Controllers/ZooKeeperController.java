package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import sample.PenType;
import sample.ZooKeeper;

import java.util.ArrayList;

import static sample.PenType.*;
import static sample.PenType.PARTWATERPARTDRY;

public class ZooKeeperController {

    public void displayChoicesForKeepers(ArrayList<ZooKeeper> zooKeepers, ChoiceBox<ZooKeeper> zooKeeperChoiceBox){
        ObservableList listOfZookeepers = FXCollections.observableArrayList();
        for(ZooKeeper zooKeeper : zooKeepers){
            listOfZookeepers.add(zooKeeper);
        }
        zooKeeperChoiceBox.setItems(listOfZookeepers);
    }

    public void displayValuesForZooKeepers(int rowCount, ZooKeeper zooKeeper, TextArea screenForZooKeepers) {
        screenForZooKeepers.setPrefRowCount(rowCount);
        screenForZooKeepers.setText("Zoo keeper info for: "+ zooKeeper + "\n\n"
                    + "Pens responsible for: " + zooKeeper.getPensResponsibleFor().toString()
            );
    }

    public void changePenTypes(ChoiceBox<PenType> zooKeeperPenType, ChoiceBox<PenType> zooKeeperPenType2, ChoiceBox<ZooKeeper> zooKeepersChoiceBox){
        PenType chosenPenType = zooKeeperPenType.getValue();
        PenType chosenPenType2 = zooKeeperPenType2.getValue();
        ArrayList<PenType> penTypes = zooKeepersChoiceBox.getValue().getPensResponsibleFor();
        ZooKeeper zooKeeper = zooKeepersChoiceBox.getValue();
        if(!chosenPenType.equals(penTypes.get(0))){
            zooKeeper.setPensResponsibleFor(0, chosenPenType);
        }
        if(!chosenPenType2.equals(penTypes.get(1))){
            zooKeeper.setPensResponsibleFor( 1, chosenPenType2);
        }
    }

    public void displayChoicesForPenType(javafx.scene.input.MouseEvent mouseEvent, ChoiceBox<PenType> zooKeeperPenType, ChoiceBox<PenType> zooKeeperPenType2){
        String source = ((ChoiceBox) mouseEvent.getSource()).idProperty().getValue();

        switch(source) {
            case "ZooKeeperPenType":
                zooKeeperPenType.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
            case "ZooKeeperPenType2":
                zooKeeperPenType2.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
        }
    }
}

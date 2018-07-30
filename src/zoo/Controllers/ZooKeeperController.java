package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import zoo.PenType;
import zoo.ZooKeeper;
import zoo.ZooManager;
import java.util.ArrayList;
import static zoo.PenType.*;
import static zoo.PenType.PARTWATERPARTDRY;

public class ZooKeeperController {

    public void displayChoicesForKeepers(ChoiceBox<ZooKeeper> zooKeeperChoiceBox){
        ArrayList<ZooKeeper> zookeepers = ZooManager.getZooKeepers();
        ObservableList<ZooKeeper> listOfZookeepers = FXCollections.observableArrayList();
        listOfZookeepers.addAll(zookeepers);
        zooKeeperChoiceBox.setItems(listOfZookeepers);
    }

    public void displayValuesForZooKeepers(int rowCount, ChoiceBox<ZooKeeper> zooKeepers, TextArea screenForZooKeepers) {
        ZooKeeper chosenZooKeeper = zooKeepers.getValue();
        ZooKeeper zooKeeper = getZooKeeper(chosenZooKeeper);
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

    public void displayChoicesForPenType(MouseEvent mouseEvent, ChoiceBox<PenType> zooKeeperPenType, ChoiceBox<PenType> zooKeeperPenType2){
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

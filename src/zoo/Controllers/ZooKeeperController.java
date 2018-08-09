package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import zoo.Exceptions.ZooKeeperNotChosenException;
import zoo.Pens.PenType;
import zoo.Pens.Pen;
import zoo.ZooKeeper;
import zoo.ZooManager;
import java.util.ArrayList;
import static zoo.Pens.PenType.*;
import static zoo.Pens.PenType.PARTWATERPARTDRY;

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
                    + "Pens responsible for: " + zooKeeper.getPensResponsibleFor()
            );
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

    public void setPensResponsibleFor(ChoiceBox<ZooKeeper> zooKeepers, ChoiceBox<String> possiblePensForZooKeepers) throws Throwable {
        ZooManager.assignZooKeeperToPen(zooKeepers, possiblePensForZooKeepers);
    }

    public void displayChoicesForPens(ChoiceBox<String> possiblePensForZooKeepers, ChoiceBox<ZooKeeper> zooKeepers)throws NullPointerException{
        ObservableList<String> listOfPens = FXCollections.observableArrayList();
        ArrayList<PenType> possiblePenTypes = getZooKeeper(zooKeepers).getPenTypesResponsibleFor();
        ArrayList<Pen> pens =ZooManager.getPens();
        for(PenType penType : possiblePenTypes){
            for(Pen pen : pens){
                if(penType.equals(pen.getPenType())){
                    listOfPens.add(pen.getPenName());
                }
            }
        }
        possiblePensForZooKeepers.setItems(listOfPens);
    }

    private ZooKeeper getZooKeeper(ChoiceBox<ZooKeeper> zooKeeperChoiceBox) throws NullPointerException{
            return zooKeeperChoiceBox.getValue();
    }

    public void resetPensForZooKeeper(ChoiceBox<ZooKeeper> zooKeepers) throws Throwable {
        if(zooKeepers.getValue() == null) {
            throw new ZooKeeperNotChosenException();
        }
        else{
            ZooManager.resetPensForZooKeeper(zooKeepers.getValue());

        }
    }


}

package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import zoo.PenType;
import zoo.Pens.Pen;
import zoo.ZooManager;

import java.util.ArrayList;

import static zoo.PenType.*;

public class PenController {

    public void displayChoicesForPens(ChoiceBox<String> penChoiceBox){
        ArrayList<Pen> pens = ZooManager.getPens();
        ObservableList<String> listOfPens = FXCollections.observableArrayList();
        for(Pen pen : pens){
            listOfPens.add(pen.getPenName());
        }
            penChoiceBox.setItems(listOfPens);

    }

    public void displayChoicesForPenType(MouseEvent mouseEvent, ChoiceBox<PenType> zooKeeperPenType, ChoiceBox<PenType> zooKeeperPenType2) {
        String source = ((ChoiceBox) mouseEvent.getSource()).idProperty().getValue();
        switch (source) {
            case "PenType":
                zooKeeperPenType.setItems(FXCollections.observableArrayList(
                        DRY,
                        PETTING,
                        AQUARIUM,
                        AVIARY,
                        PARTWATERPARTDRY));
                break;
            case "PenType2":
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

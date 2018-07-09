package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import sample.PenType;

import static sample.PenType.*;

public class PenController {

    public void displayChoicesForPenType(javafx.scene.input.MouseEvent mouseEvent, ChoiceBox<PenType> zooKeeperPenType, ChoiceBox<PenType> zooKeeperPenType2) {
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

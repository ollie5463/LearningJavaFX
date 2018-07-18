package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import zoo.PenType;

import static zoo.PenType.*;

public class PenController {

    public void displayChoicesForPens(ChoiceBox<String> pens){
        pens.setItems(FXCollections.observableArrayList(
                "Dry",
                "Part water part dry ",
                "Aquarium",
                "Petting pen",
                "Aviary"
        ));
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

package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import zoo.PenType;
import zoo.Pens.Pen;
import zoo.Pens.PenInstances.Aquarium;
import zoo.Pens.PenInstances.Aviary;
import zoo.Pens.PenInstances.PartWaterPartDryPen;
import zoo.ZooManager;

import java.util.ArrayList;

import static zoo.PenType.*;

public class PenController {

    public void displayChoicesForPens(ChoiceBox<String> penChoiceBox) {
        ArrayList<Pen> pens = ZooManager.getPens();
        ObservableList<String> listOfPens = FXCollections.observableArrayList();
        for (Pen pen : pens) {
            listOfPens.add(pen.getPenName());
        }
        penChoiceBox.setItems(listOfPens);

    }

    public void displayChoicesForPenType(ChoiceBox<PenType> zooKeeperPenType) {
        zooKeeperPenType.setItems(FXCollections.observableArrayList(AQUARIUM, PETTING, AVIARY, PARTWATERPARTDRY, DRY));
    }

    public void displayValuesForPen(ChoiceBox<String> pens, TextArea screenForPens, int rowCount) {
        String chosenPen = pens.getValue();
        try {
            Pen pen = ZooManager.getPen(chosenPen);


            switch(pen.getPenType()){
                case DRY:
                    // lenght + width
                    screenForPens.setPrefRowCount(rowCount);
                    screenForPens.setText("Pen info for: " + pen + "\n\n"
                            + "Width: " + pen.getWidth() + "\n\n"
                            + "Length: " + pen.getLength() + "\n\n"
                            + "Temp: " + pen.getTemp()
                    );
                    break;
                case PETTING:
                    // length + width
                    screenForPens.setPrefRowCount(rowCount);
                    screenForPens.setText("Pen info for: " + pen + "\n\n"
                            + "Width: " + pen.getWidth() + "\n\n"
                            + "Length: " + pen.getLength() + "\n\n"
                            + "Temp: " + pen.getTemp()
                    );
                    break;
                case PARTWATERPARTDRY:
//                    length + width + volume
                    PartWaterPartDryPen partWaterPartDryPen = (PartWaterPartDryPen)pen;
                    screenForPens.setPrefRowCount(rowCount);
                    screenForPens.setText("Pen info for: " + pen + "\n\n"
                            + "Width: " + partWaterPartDryPen.getWidth() + "\n\n"
                            + "Length: " + partWaterPartDryPen.getLength() + "\n\n"
                            + "Water volume: " + partWaterPartDryPen.getAvailableWaterVolume() + "\n\n"
                            + "Temp: " + pen.getTemp()
                    );
                    break;
                case AVIARY:
                    Aviary aviary = (Aviary) pen;
                    screenForPens.setPrefRowCount(rowCount);
                    screenForPens.setText("Pen info for: " + pen + "\n\n"
                            + "Width: " + aviary.getWidth() + "\n\n"
                            + "Length: " + aviary.getLength() + "\n\n"
                            + "Height: " + aviary.getHeight() + "\n\n"
                            + "Temp: " + pen.getTemp()
                    );
//                    length + width + height
                    break;
                case AQUARIUM:
//                    length + width +height + volume
                    Aquarium aquarium = (Aquarium) pen;
                    screenForPens.setPrefRowCount(rowCount);
                    screenForPens.setText("Pen info for: " + pen + "\n\n"
                            + "Width: " + aquarium.getWidth() + "\n\n"
                            + "Length: " + aquarium.getLength() + "\n\n"
                            + "Height: " + aquarium.getHeight() + "\n\n"
                            + "Water volume: " + aquarium.getWaterVolume() + "\n\n"
                            + "Temp: " + pen.getTemp()
                    );
                    break;
            }

        } catch (Throwable zooKeeperException) {

        }
    }
    public void addPen(ChoiceBox<PenType> penTypeForPen, TextArea width, TextArea heightOrVolume, TextArea length, TextArea name, TextArea temp) {
        switch(penTypeForPen.getValue()){
            case DRY:
                // length and width
                ZooManager.createPen(DRY, getIntFromString(length.getText()), getIntFromString(width.getText()),  getIntFromString(temp.getText()), name.getText());
                break;
            case PETTING:
                // length and width
                ZooManager.createPen(PETTING, getIntFromString(length.getText()), getIntFromString(width.getText()), getIntFromString(temp.getText()), name.getText());
                break;
            case PARTWATERPARTDRY:
                // length width and volume
                int volume = getIntFromString(heightOrVolume.getText());
                ZooManager.createPen(PARTWATERPARTDRY, getIntFromString(length.getText()), getIntFromString(width.getText()), getIntFromString(temp.getText()), volume , name.getText());
                break;
            case AVIARY:
                // length width and height
                ZooManager.createPen(AVIARY, getIntFromString(length.getText()), getIntFromString(width.getText()), name.getText(), getIntFromString(heightOrVolume.getText()) , getIntFromString(temp.getText()));
                break;
            case AQUARIUM:
                //length width height and volume(calculated)
                ZooManager.createPen(AQUARIUM, getIntFromString(length.getText()), getIntFromString(width.getText()), name.getText(), getIntFromString(heightOrVolume.getText()) , getIntFromString(temp.getText()));
                break;
        }
        clearPenRequirements(new ArrayList<>(){{add(width);add(heightOrVolume);add(length);add(name);add(temp);}});
    }

    private void clearPenRequirements(ArrayList<TextArea> textAreas){
        for(TextArea textArea : textAreas){
            textArea.clear();
        }
    }

    private int getIntFromString(String stringToBeCasted){
        return Integer.parseInt(stringToBeCasted);
    }
}

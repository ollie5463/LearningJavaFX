package zoo.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import zoo.Pens.PenType;
import zoo.Pens.Pen;
import zoo.Pens.PenInstances.Aquarium;
import zoo.Pens.PenInstances.Aviary;
import zoo.Pens.PenInstances.PartWaterPartDryPen;
import zoo.ZooManager;

import java.util.ArrayList;

import static zoo.Pens.PenType.*;

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
                            + "Water volume: " + aquarium.getWaterVolumeAvailable() + "\n\n"
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
                ZooManager.createDryPen(getIntFromString(length.getText()), getIntFromString(width.getText()),  getIntFromString(temp.getText()), name.getText());
                break;
            case PETTING:
                // length and width
                ZooManager.createPettingPen(getIntFromString(length.getText()), getIntFromString(width.getText()),  getIntFromString(temp.getText()), name.getText());
                break;
            case PARTWATERPARTDRY:
                // length width and volume
                ZooManager.createPartWaterPartDryPen(getIntFromString(length.getText()), getIntFromString(width.getText()), getIntFromString(temp.getText()), Integer.parseInt(heightOrVolume.getText()), name.getText());
                break;
            case AVIARY:
                // length width and height
                ZooManager.createAviary(getIntFromString(length.getText()), getIntFromString(width.getText()), name.getText(), getIntFromString(heightOrVolume.getText()) , getIntFromString(temp.getText()));
                break;
            case AQUARIUM:
                //length width height and volume(calculated)
                ZooManager.createAquarium(getIntFromString(length.getText()), getIntFromString(width.getText()), name.getText(), getIntFromString(heightOrVolume.getText()) , getIntFromString(temp.getText()));
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

    public void checkPensAreAssignedToStaff(TextArea screenForUnassignedPens) {
        ArrayList<Pen> pensNotAssignedToStaff = ZooManager.checkIfPensAreAssignedToStaff();
        if(!pensNotAssignedToStaff.isEmpty()){
            screenForUnassignedPens.setText(pensNotAssignedToStaff.toString());
        }
        else{
            screenForUnassignedPens.setText("All pens are assigned to zoo keepers");
        }
    }

    public void autoAllocatePens(TextArea screenForUnassignedPens) {
        ArrayList<Pen> pensNotAssignedToAZooKeeper = ZooManager.getPensNotAssignedToAZooKeeper();
        if(!pensNotAssignedToAZooKeeper.isEmpty()){
            for(Pen pen : pensNotAssignedToAZooKeeper){
                ZooManager.autoAssignPenToZooKeeper(pen);
            }
        }
        else{
            screenForUnassignedPens.setText("There are no pens to auto allocate");
        }
    }
}

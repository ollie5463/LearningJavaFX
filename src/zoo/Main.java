package zoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zoo.FileUtilities.FileUtilities;
import zoo.Zoo.Zoo;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Zoo zoo = FileUtilities.readFromFile();
        if(zoo == null){
            ZooManager.createDefaultZoo();
        }
        else{
            ZooManager.loadUpZoo(zoo);
        }
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo Management Software");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    @Override
    public void stop(){
        FileUtilities.writeToFile(ZooManager.getZoo());
        System.out.println("saved");
    }

    public static void main(String[] args) {
          launch(args);
    }
}

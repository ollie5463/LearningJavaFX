package zoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zoo.FileUtilities.FileManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/ZooKeeperSoftware.fxml"));
        primaryStage.setTitle("Zoo Management Software");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    @Override
    public void stop(){
        FileManager.writeToFile(ZooManager.getZoo());
    }

    public static void main(String[] args) {
          launch(args);
    }
}

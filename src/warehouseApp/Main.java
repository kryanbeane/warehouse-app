package warehouseApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // List that stores floors
    static MyList<Floor> floorList = new MyList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Gets the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("warehouseApp.fxml"));
        // Sets the title of the window
        primaryStage.setTitle("Warehouse Application");
        // Sets the size of the window
        primaryStage.setScene(new Scene(root, 700, 752));
        // Makes the window non resizeable
        primaryStage.setResizable(false);
        // Displays window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

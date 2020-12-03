package warehouseApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     *
     */
    static MyList<Floor> floorList = new MyList<>();

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("warehouseApp.fxml"));
        primaryStage.setTitle("Warehouse Application");
        primaryStage.setScene(new Scene(root, 700, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

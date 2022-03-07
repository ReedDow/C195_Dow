package main;
import javafx.application.Application;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**This method loads the initial page and makes the FXML stage.*/
    @Override
    public void start(Stage stage) throws IOException {
        //addTestData();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_form.fxml"));
        stage.setTitle("Main Form");
        stage.setScene(new Scene(root, 987, 446 ));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
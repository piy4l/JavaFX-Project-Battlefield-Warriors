package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MissionFailed {
    @FXML
    private AnchorPane rootpane;
    Parent rootHomepsge;

    {
        try {
            rootHomepsge = FXMLLoader.load(getClass().getResource("home1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void setMainMenu(){
        Stage stage = (Stage) rootpane.getScene().getWindow();
        Scene scene = rootpane.getScene();
        scene = new Scene(rootHomepsge, 1920, 990);
        stage.setScene(scene);
        stage.show();

    }
}

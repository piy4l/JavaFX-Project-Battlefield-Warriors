package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MissionSuccessful {
    @FXML
    private AnchorPane rootpane;
    Parent rootHomepage;

    {
        try {
            rootHomepage = FXMLLoader.load(getClass().getResource("home1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void setMainMenu(){
        System.out.println("main menu pressed");
        Stage stage = (Stage) rootpane.getScene().getWindow();
        Scene scene = rootpane.getScene();
        scene = new Scene(rootHomepage, 1920, 990);
        stage.setScene(scene);
        stage.show();

    }

}

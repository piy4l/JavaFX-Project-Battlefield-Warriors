package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Credits {
    @FXML
    private AnchorPane rootpane;



    @FXML public void setMainMenu(){
        ;
            try {
                Parent root = FXMLLoader.load(getClass().getResource("home1.fxml"));
                Stage stage = (Stage) rootpane.getScene().getWindow();
                Scene scene = rootpane.getScene();
                scene = new Scene(root, 1920, 1000);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}

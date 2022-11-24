package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class LoginPage {
    @FXML
    TextField usernameText;

    @FXML
    private AnchorPane rootpane;
    Parent root = FXMLLoader.load(getClass().getResource("home1.fxml"));

    public LoginPage() throws IOException {
    }

    @FXML
    public void setLogin() {

        String string = usernameText.getText();
        //putNameInFile(string);
        Example1.outToServer.println("USERNAME#"+string);
        //Stage stage = new Stage();

        Stage stage = (Stage) rootpane.getScene().getWindow();
        Scene scene = rootpane.getScene();
        scene = new Scene(root, 1920, 990);
        stage.setScene(scene);
        //stage.setScene(example1.getScene());
        //stage.close();
        stage.show();
    }


}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

public class homeController {
    @FXML
    Button newGame;
    @FXML
            private AnchorPane rootpane;

    Parent rootCredits;

    {
        try {
            rootCredits = FXMLLoader.load(getClass().getResource("credits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Example1 example1 = new Example1();
    @FXML
    public void setNewGame(ActionEvent event)throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("gamestage2.fxml"));
        Scene scene = new Scene(root, 1920, 990);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        System.out.println("new game");*/
        Stage stage=(Stage)rootpane.getScene().getWindow();
        //stage.setScene(example1.getScene());
        stage.close();
        example1.play();
    }

    @FXML
    Button exit;
    @FXML
    public void setExit(){
        System.out.println("exit");
        Stage stage=(Stage)rootpane.getScene().getWindow();
        //stage.setScene(example1.getScene());
        stage.close();
        //example1.close();

    }

    @FXML
    Button scoreBoard;
    @FXML
    public  void setScoreBoard(ActionEvent event)throws IOException{
        System.out.println("scoreboard");
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("score.fxml"));
            Parent root=(Parent)loader.load();
            Scene scene=new Scene(root);
            Stage stage=(Stage)rootpane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("SCORE");
            stage.show();
        }catch (Exception e)
        {

        }
    }

    @FXML
    Button settings;
    @FXML
    public void setCredits(){
        System.out.println("credits");
        Stage stage=(Stage)rootpane.getScene().getWindow();
        Scene scene = rootpane.getScene();
        scene = new Scene(rootCredits, 1920, 1000);
        stage.setScene(scene);
        stage.show();
    }

    @FXML public void setInstruction(){

        try {
            Parent rootInstructions = FXMLLoader.load(getClass().getResource("instructions.fxml"));
            Stage stage=(Stage)rootpane.getScene().getWindow();
            Scene scene = rootpane.getScene();
            scene = new Scene(rootInstructions, 1920, 1000);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
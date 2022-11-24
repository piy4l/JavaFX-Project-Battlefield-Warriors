package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;

public class Example1 extends Application {
    public static ImageView imageView, imageView2;
    public static String sentenceToServer = "";
    public static String sentenceFromServer;
    public static double t1, t2;
    ClientThread clientThread;
    static BufferedReader inFromServer;
    static PrintWriter outToServer;
    static Sprite bullet = new Sprite();
    static Sprite bullet2 = new Sprite();
    public static double lifeplayer1 = 1;
    public static double lifeplayer2 = 1;
    static boolean gameover = false;

    public boolean collectgift1 = false;
    public boolean collectgift2 = false;
    public boolean collectgift3 = false;
    public static boolean assign = false;
    private MediaPlayer audioPlayer;

    public static void main(String[] args) throws Exception {
        launch(args);
    }


    public void play() {
        Stage window = new Stage();
        Scene theScene;

        window.setTitle("Shooting Game");
        //root2.getChildren().add(root1);
        Group root = new Group();
        theScene = new Scene(root);
        window.setScene(theScene);
        try {

            window.setScene(theScene);
            window.show();

            Canvas canvas = new Canvas(1920, 990);
            root.getChildren().add(canvas);

            ArrayList<String> input = new ArrayList<String>();

            Image bg = new Image("file:gamestage2.png");
            Sprite bgview = new Sprite();
            bgview.setImage(bg);
            bgview.setPosition(0, 0);

            ProgressBar progressBar1 = new ProgressBar();
            progressBar1.setMinSize(150, 30);
            progressBar1.setLayoutX(20);
            progressBar1.setLayoutY(20);
            progressBar1.progressProperty().setValue(lifeplayer1);
            root.getChildren().add(progressBar1);
            Label player1 = new Label();
            player1.setText("PLAYER 1");
            player1.setLayoutX(200);
            player1.setLayoutY(20);
            player1.setFont(new Font("Comic Sans Ms", 20));
            player1.setTextFill(Color.RED);
            root.getChildren().add(player1);


            //Player2 Progressbar
            ProgressBar progressBar2 = new ProgressBar();
            progressBar2.setMinSize(150, 30);
            progressBar2.setLayoutX(1920 - 150 - 20);
            progressBar2.setLayoutY(20);
            progressBar2.progressProperty().setValue(lifeplayer2);
            root.getChildren().add(progressBar2);
            Label player2 = new Label();
            player2.setText("PLAYER 2");
            player2.setLayoutX(1920 - 20 - 150 - 120);
            player2.setLayoutY(20);
            player2.setFont(new Font("Comic Sans Ms", 20));
            player2.setTextFill(Color.RED);
            root.getChildren().add(player2);


            GraphicsContext gc = canvas.getGraphicsContext2D();

            Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
            gc.setFont(theFont);
            gc.setFill(Color.GREEN);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            //Image gift1 = new Image("file:gift1.png");
            //ImageView gift1view = new ImageView(gift1);
            //gift1view.setX(150);
            //gift1view.setY(150);
            //root.getChildren().add(gift1view);
            Sprite gift1 = new Sprite();
            gift1.setImage("file:gift1.png");
            gift1.setPosition(100, 100);
            Sprite gift2 = new Sprite();
            gift2.setImage("file:gift2.png");
            gift2.setPosition(1800, 100);
            Sprite gift3 = new Sprite();
            gift3.setImage("file:gift3.png");
            gift3.setPosition(400, 900);
            //door1 from stage1
            Image door1 = new Image("file:door2.png");
            ImageView door1view = new ImageView(door1);
            door1view.setX(1300);
            door1view.setY(890);
            root.getChildren().add(door1view);
            final ImageView blood = new ImageView("file:blood1.png");
            blood.setX(1000000);
            blood.setY(1000000);
            root.getChildren().add(blood);
            Image img2 = new Image("army2.png");
            Image img = new Image("army1.png");
            imageView = new ImageView(img);
            imageView2 = new ImageView(img2);
            imageView.setX(950);
            imageView.setY(100);
            root.getChildren().add(imageView);
            imageView2.setX(950);
            imageView2.setY(500);
            root.getChildren().add(imageView2);

            theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                double t1 = imageView.getX();
                double t2 = imageView.getY();
                double tt1 = imageView2.getX();
                double tt2 = imageView2.getY();

                @Override
                public void handle(KeyEvent event) {
                    String code = event.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);

                    int block = 0;
                    if (imageView.getX() >= 350 && imageView.getX() <= 890 && imageView.getY() >= 550)
                        block = 1;
                    else if (imageView.getX() >= 1000 && imageView.getX() <= 1550 && imageView.getY() >= 550)
                        block = 2;
                    else if (imageView.getX() >= 0 && imageView.getX() <= 890 && imageView.getY() <= 280)
                        block = 3;
                    else if (imageView.getX() >= 890 && imageView.getX() <= 1000 && imageView.getY() >= 280 && imageView.getY() <= 550)
                        block = 4;
                    else if (imageView.getX() >= 1000 && imageView.getX() <= 1920 && imageView.getY() <= 280)
                        block = 5;
                    else
                        block = 0;

                    int block2 = 0;
                    if (imageView2.getX() >= 350 && imageView2.getX() <= 890 && imageView2.getY() >= 550)
                        block2 = 1;
                    else if (imageView2.getX() >= 1000 && imageView2.getX() <= 1550 && imageView2.getY() >= 550)
                        block2 = 2;
                    else if (imageView2.getX() >= 0 && imageView2.getX() <= 890 && imageView2.getY() <= 280)
                        block2 = 3;
                    else if (imageView2.getX() >= 890 && imageView2.getX() <= 1000 && imageView2.getY() >= 280 && imageView2.getY() <= 550)
                        block2 = 4;
                    else if (imageView2.getX() >= 1000 && imageView2.getX() <= 1920 && imageView2.getY() <= 280)
                        block2 = 5;
                    else
                        block2 = 0;
                    KeyCode inp = event.getCode();
                    if (assign == true) {
                        switch (inp) {

                            case RIGHT:
                                if (lifeplayer1 > 0) {
                                    System.out.println("TRUE right e dhukse");
                                    t1 += 10;
                                    imageView.setRotate(90);
                                    outToServer.println("RIGHT");

                                }
                                break;
                            case LEFT:
                                System.out.println(imageView.getX() + " " + imageView.getY() + " " + imageView2.getX() + " " + imageView2.getY());
                                if (lifeplayer1 > 0) {
                                    t1 -= 10;
                                    imageView.setRotate(-90);
                                    outToServer.println("LEFT");

                                }
                                break;
                            case UP:
                                if (lifeplayer1 > 0) {
                                    t2 -= 10;
                                    imageView.setRotate(0);
                                    outToServer.println("UP");

                                }
                                break;
                            case DOWN:
                                if (lifeplayer1 > 0) {
                                    t2 += 10;
                                    imageView.setRotate(-180);
                                    outToServer.println("DOWN");

                                }
                                break;
                        }
                    } else {
                        switch (inp) {
                            case RIGHT:
                                if (lifeplayer2 > 0) {
                                    outToServer.println("RIGHT");
                                    tt1 += 10;
                                    imageView2.setRotate(90);
                                }
                                break;

                            case LEFT:
                                System.out.println(imageView.getX() + " " + imageView.getY() + " " + imageView2.getX() + " " + imageView2.getY());

                                if (lifeplayer2 > 0) {
                                    outToServer.println("LEFT");
                                    tt1 -= 10;
                                    imageView2.setRotate(-90);
                                }
                                break;
                            case UP:
                                if (lifeplayer2 > 0) {
                                    outToServer.println("UP");
                                    tt2 -= 10;
                                    imageView2.setRotate(0);
                                }
                                break;
                            case DOWN:
                                if (lifeplayer2 > 0) {
                                    outToServer.println("DOWN");
                                    tt2 += 10;
                                    imageView2.setRotate(-180);
                                }
                                break;
                        }
                    }
                    if (block == 1) {
                        if (t1 < 350)
                            t1 += 15;

                        if (t2 < 550)
                            t2 += 15;
                    }
                    if (block == 2) {
                        if (t2 < 550)
                            t2 += 15;
                        if (t1 > 1550)
                            t1 -= 15;
                    }
                    if (block == 3) {
                        if (t2 > 280)
                            t2 -= 15;
                    }
                    if (block == 4) {
                        if (t1 < 890)
                            t1 += 15;
                        if (t1 > 1000)
                            t1 -= 15;
                    }
                    if (block == 5)
                        if (t2 > 280)
                            t2 -= 15;


                    if (block2 == 1) {
                        if (tt1 < 350)
                            tt1 += 15;

                        if (tt2 < 550)
                            tt2 += 15;
                    }
                    if (block2 == 2) {
                        if (tt2 < 550)
                            tt2 += 15;
                        if (tt1 > 1550)
                            tt1 -= 15;
                    }
                    if (block2 == 3) {
                        if (tt2 > 280)
                            tt2 -= 15;
                    }
                    if (block2 == 4) {
                        if (tt1 < 890)
                            tt1 += 15;
                        if (tt1 > 1000)
                            tt1 -= 15;
                    }
                    if (block2 == 5)
                        if (tt2 > 280)
                            tt2 -= 15;

                    imageView.setX(t1);
                    imageView.setY(t2);
                    imageView2.setX(tt1);
                    imageView2.setY(tt2);
                }
            });

            theScene.setOnKeyReleased(
                    new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent e) {
                            String code = e.getCode().toString();
                            input.remove(code);
                        }
                    });




            //gift1 from stage1


            Sprite enemy2 = new Sprite();
            enemy2.setImage("file:enemy2.png");
            enemy2.setPosition(800, 800);


            Sprite enemy3 = new Sprite();
            enemy3.setImage("file:enemy3.png");
            enemy3.setPosition(200, 200);


            Sprite enemy4 = new Sprite();
            enemy4.setImage("file:enemy4.png");
            enemy4.setPosition(1800, 200);

            Sprite enemy22 = new Sprite();
            enemy22.setImage("file:enemy2.png");
            enemy22.setPosition(500, 800);


            Sprite enemy33 = new Sprite();
            enemy33.setImage("file:enemy3.png");
            enemy33.setPosition(400, 200);


            Sprite enemy44 = new Sprite();
            enemy44.setImage("file:enemy4.png");
            enemy44.setPosition(1500, 200);


            LongValue lastNanoTime = new LongValue(System.nanoTime());

            IntValue score = new IntValue(0);

            enemy3.setVelocity(imageView.getX() / 10, imageView.getY() / 10);


            new AnimationTimer() {

                public void handle(long currentNanoTime) {

                    // calculate time since last update.
                    double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                    lastNanoTime.value = currentNanoTime;

                    // game logic

                    //enemy will come close to me
                    if (imageView.getX() >= 0 && imageView.getX() <= 900 && imageView.getY() >= 0 && imageView.getY() <= 350)
                        enemy3.setVelocity((imageView.getX() - enemy3.positionX) / 3, (imageView.getY() - enemy3.positionY) / 3);
                    else
                        enemy3.setVelocity(0, 0);
                    if (imageView.getX() >= 350 && imageView.getX() <= 1600 && imageView.getY() >= 550 && imageView.getY() <= 990)
                        enemy2.setVelocity((imageView.getX() - enemy2.positionX) / 2, (imageView.getY() - enemy2.positionY) / 2);
                    else
                        enemy2.setVelocity(0, 0);
                    if (imageView.getY() >= 0 && imageView.getY() <= 350 && imageView.getX() >= 1000 && imageView.getX() <= 1920)
                        enemy4.setVelocity((imageView.getX() - enemy4.positionX) / 4, (imageView.getY() - enemy4.positionY) / 4);
                    else
                        enemy4.setVelocity(0, 0);


                    if (imageView2.getX() >= 0 && imageView2.getX() <= 900 && imageView2.getY() >= 0 && imageView2.getY() <= 350)
                        enemy33.setVelocity((imageView2.getX() - enemy33.positionX) / 3, (imageView2.getY() - enemy33.positionY) / 3);
                    else
                        enemy33.setVelocity(0, 0);
                    if (imageView2.getX() >= 350 && imageView2.getX() <= 1600 && imageView2.getY() >= 550 && imageView2.getY() <= 990)
                        enemy22.setVelocity((imageView2.getX() - enemy22.positionX) / 2, (imageView2.getY() - enemy22.positionY) / 2);
                    else
                        enemy22.setVelocity(0, 0);
                    if (imageView2.getY() >= 0 && imageView2.getY() <= 350 && imageView2.getX() >= 1000 && imageView2.getX() <= 1920)
                        enemy44.setVelocity((imageView2.getX() - enemy44.positionX) / 4, (imageView2.getY() - enemy44.positionY) / 4);
                    else
                        enemy44.setVelocity(0, 0);

                    //generating bullet
                    if (input.contains("SPACE")) {
                        File audioFile = new File("src//shoot.mp3");
                        Media audio = new Media(audioFile.toURI().toString());
                        audioPlayer = new MediaPlayer(audio);
                        audioPlayer.play();
                        if (lifeplayer1 > 0 && assign == true) {
                            outToServer.println("SPACE");
                            bullet.setImage("file:bullet1.png");

                            if (imageView.getRotate() == -90) {
                                bullet.setPosition(imageView.getX() - 100, imageView.getY() + 30);
                                bullet.setVelocity(-3000, 0);
                            }
                            if (imageView.getRotate() == 90) {
                                bullet.setPosition(imageView.getX() + 100, imageView.getY() + 60);
                                bullet.setVelocity(3000, 0);
                            }
                            if (imageView.getRotate() == 0) {
                                bullet.setPosition(imageView.getX() + 40, imageView.getY());
                                bullet.setVelocity(0, -3000);
                            }
                            if (imageView.getRotate() == -180) {
                                bullet.setPosition(imageView.getX(), imageView.getY() + 50);
                                bullet.setVelocity(0, 3000);
                            }
                        }

                        if (lifeplayer2 > 0 && (!assign)) {
                            outToServer.println("SPACE");
                            bullet2.setImage("file:bullet1.png");

                            if (imageView2.getRotate() == -90) {
                                bullet2.setPosition(imageView2.getX() - 100, imageView2.getY() + 30);
                                bullet2.setVelocity(-3000, 0);
                            }
                            if (imageView2.getRotate() == 90) {
                                bullet2.setPosition(imageView2.getX() + 100, imageView2.getY() + 60);
                                bullet2.setVelocity(3000, 0);
                            }
                            if (imageView2.getRotate() == 0) {
                                bullet2.setPosition(imageView2.getX() + 40, imageView2.getY());
                                bullet2.setVelocity(0, -3000);
                            }
                            if (imageView2.getRotate() == -180) {
                                bullet2.setPosition(imageView2.getX(), imageView2.getY() + 50);
                                bullet2.setVelocity(0, 3000);
                            }
                        }
                    }

                    if (lifeplayer1 <= 0 && lifeplayer2 <= 0 && gameover == false) {
                        int b = 100 * score.value;
                        outToServer.println("GAMEOVER#" + b);
                        gameover = true;
                        Parent rootFailed = null;
                        try {
                            rootFailed = FXMLLoader.load(getClass().getResource("missionFailed.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene rootFailedScene = new Scene(rootFailed);
                        window.setScene(rootFailedScene);
                        window.show();
                    }
                    if (collectgift1 && collectgift2 && collectgift3 && gameover == false)
                        if ((imageView.intersects(door1view.getX(), door1view.getY(), 50, 50)) || (imageView2.intersects(door1view.getX(), door1view.getY(), 50, 50))) {
                            {
                                System.out.println("done");
                                int b = 100 * score.value;
                                outToServer.println("GAMEOVER#" + b);
                                gameover = true;
                                Parent rootSuccessful = null;
                                try {
                                    rootSuccessful = FXMLLoader.load(getClass().getResource("missionSuccessful.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Scene rootSuccessScene = new Scene(rootSuccessful);
                                window.setScene(rootSuccessScene);
                                window.show();
                            }
                        }


                    enemy3.update(elapsedTime);
                    enemy2.update(elapsedTime);
                    enemy4.update(elapsedTime);
                    enemy33.update(elapsedTime);
                    enemy22.update(elapsedTime);
                    enemy44.update(elapsedTime);
                    bullet.update(elapsedTime);
                    bullet2.update(elapsedTime);
                    gift1.update(elapsedTime);
                    gift2.update(elapsedTime);
                    gift3.update(elapsedTime);
                    // collision detection

                    if (bullet.intersects(enemy3)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView.getRotate() == 0)
                            enemy3.setPosition(enemy3.positionX, enemy3.positionY - 200);
                        if (imageView.getRotate() == 90)
                            enemy3.setPosition(enemy3.positionX + 200, enemy3.positionY);
                        if (imageView.getRotate() == -90)
                            enemy3.setPosition(enemy3.positionX - 200, enemy3.positionY);
                        if (imageView.getRotate() == -180)
                            enemy3.setPosition(enemy3.positionX, enemy3.positionY + 200);
                        score.value++;
                    }


                    if (bullet.intersects(enemy2)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView.getRotate() == 0)
                            enemy2.setPosition(enemy2.positionX, enemy2.positionY - 200);
                        if (imageView.getRotate() == 90)
                            enemy2.setPosition(enemy2.positionX + 200, enemy2.positionY);
                        if (imageView.getRotate() == -90)
                            enemy2.setPosition(enemy2.positionX - 200, enemy2.positionY);
                        if (imageView.getRotate() == -180)
                            enemy2.setPosition(enemy2.positionX, enemy2.positionY + 200);
                        score.value++;
                    }

                    if (bullet.intersects(enemy4)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView.getRotate() == 0)
                            enemy4.setPosition(enemy4.positionX, enemy4.positionY - 200);
                        if (imageView.getRotate() == 90)
                            enemy4.setPosition(enemy4.positionX + 200, enemy4.positionY);
                        if (imageView.getRotate() == -90)
                            enemy4.setPosition(enemy4.positionX - 200, enemy4.positionY);
                        if (imageView.getRotate() == -180)
                            enemy4.setPosition(enemy4.positionX, enemy4.positionY + 200);
                        score.value++;
                    }


                    if (bullet2.intersects(enemy3)) {
                        bullet2.setPosition(1000000, 1000000);
                        if (imageView2.getRotate() == 0)
                            enemy3.setPosition(enemy3.positionX, enemy3.positionY - 200);
                        if (imageView2.getRotate() == 90)
                            enemy3.setPosition(enemy3.positionX + 200, enemy3.positionY);
                        if (imageView2.getRotate() == -90)
                            enemy3.setPosition(enemy3.positionX - 200, enemy3.positionY);
                        if (imageView2.getRotate() == -180)
                            enemy3.setPosition(enemy3.positionX, enemy3.positionY + 200);
                        score.value++;
                    }


                    if (bullet2.intersects(enemy2)) {
                        bullet2.setPosition(1000000, 1000000);
                        if (imageView2.getRotate() == 0)
                            enemy2.setPosition(enemy2.positionX, enemy2.positionY - 200);
                        if (imageView2.getRotate() == 90)
                            enemy2.setPosition(enemy2.positionX + 200, enemy2.positionY);
                        if (imageView2.getRotate() == -90)
                            enemy2.setPosition(enemy2.positionX - 200, enemy2.positionY);
                        if (imageView2.getRotate() == -180)
                            enemy2.setPosition(enemy2.positionX, enemy2.positionY + 200);
                        score.value++;
                    }

                    if (bullet2.intersects(enemy4)) {
                        bullet2.setPosition(1000000, 1000000);
                        if (imageView2.getRotate() == 0)
                            enemy4.setPosition(enemy4.positionX, enemy4.positionY - 200);
                        if (imageView2.getRotate() == 90)
                            enemy4.setPosition(enemy4.positionX + 200, enemy4.positionY);
                        if (imageView2.getRotate() == -90)
                            enemy4.setPosition(enemy4.positionX - 200, enemy4.positionY);
                        if (imageView2.getRotate() == -180)
                            enemy4.setPosition(enemy4.positionX, enemy4.positionY + 200);
                        score.value++;
                    }


                    if (bullet.intersects(enemy33)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView.getRotate() == 0)
                            enemy33.setPosition(enemy33.positionX, enemy33.positionY - 200);
                        if (imageView.getRotate() == 90)
                            enemy33.setPosition(enemy33.positionX + 200, enemy33.positionY);
                        if (imageView.getRotate() == -90)
                            enemy33.setPosition(enemy33.positionX - 200, enemy33.positionY);
                        if (imageView.getRotate() == -180)
                            enemy33.setPosition(enemy33.positionX, enemy33.positionY + 200);
                        score.value++;
                    }


                    if (bullet.intersects(enemy22)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView.getRotate() == 0)
                            enemy22.setPosition(enemy22.positionX, enemy22.positionY - 200);
                        if (imageView.getRotate() == 90)
                            enemy22.setPosition(enemy22.positionX + 200, enemy22.positionY);
                        if (imageView.getRotate() == -90)
                            enemy22.setPosition(enemy22.positionX - 200, enemy22.positionY);
                        if (imageView.getRotate() == -180)
                            enemy22.setPosition(enemy22.positionX, enemy22.positionY + 200);
                        score.value++;
                    }

                    if (bullet.intersects(enemy44)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView.getRotate() == 0)
                            enemy44.setPosition(enemy44.positionX, enemy44.positionY - 200);
                        if (imageView.getRotate() == 90)
                            enemy44.setPosition(enemy44.positionX + 200, enemy44.positionY);
                        if (imageView.getRotate() == -90)
                            enemy44.setPosition(enemy44.positionX - 200, enemy44.positionY);
                        if (imageView.getRotate() == -180)
                            enemy44.setPosition(enemy44.positionX, enemy44.positionY + 200);
                        score.value++;
                    }


                    if (bullet2.intersects(enemy33)) {
                        bullet.setPosition(1000000, 1000000);
                        if (imageView2.getRotate() == 0)
                            enemy33.setPosition(enemy33.positionX, enemy33.positionY - 200);
                        if (imageView2.getRotate() == 90)
                            enemy33.setPosition(enemy33.positionX + 200, enemy33.positionY);
                        if (imageView2.getRotate() == -90)
                            enemy33.setPosition(enemy33.positionX - 200, enemy33.positionY);
                        if (imageView2.getRotate() == -180)
                            enemy33.setPosition(enemy33.positionX, enemy33.positionY + 200);
                        score.value++;
                    }


                    if (bullet2.intersects(enemy22)) {
                        bullet2.setPosition(1000000, 1000000);
                        if (imageView2.getRotate() == 0)
                            enemy22.setPosition(enemy22.positionX, enemy22.positionY - 200);
                        if (imageView2.getRotate() == 90)
                            enemy22.setPosition(enemy22.positionX + 200, enemy22.positionY);
                        if (imageView2.getRotate() == -90)
                            enemy22.setPosition(enemy22.positionX - 200, enemy22.positionY);
                        if (imageView2.getRotate() == -180)
                            enemy22.setPosition(enemy22.positionX, enemy22.positionY + 200);
                        score.value++;
                    }

                    if (bullet2.intersects(enemy44)) {
                        bullet2.setPosition(1000000, 1000000);
                        if (imageView2.getRotate() == 0)
                            enemy44.setPosition(enemy44.positionX, enemy44.positionY - 200);
                        if (imageView2.getRotate() == 90)
                            enemy44.setPosition(enemy44.positionX + 200, enemy44.positionY);
                        if (imageView2.getRotate() == -90)
                            enemy44.setPosition(enemy44.positionX - 200, enemy44.positionY);
                        if (imageView2.getRotate() == -180)
                            enemy44.setPosition(enemy44.positionX, enemy44.positionY + 200);
                        score.value++;
                    }

                    if (imageView.intersects(gift1.positionX, gift1.positionY, 50, 50)) {
                        collectgift1 = true;
                        gift1.setVelocity(0, 200);
                        //score.value+=100;
                    }
                    if (imageView2.intersects(gift1.positionX, gift1.positionY, 50, 50)) {
                        collectgift1 = true;
                        gift1.setVelocity(0, 200);
                        //score.value+=100;
                    }
                    if (imageView.intersects(gift2.positionX, gift2.positionY, 50, 50)) {
                        collectgift2 = true;
                        gift2.setVelocity(0, 200);
                        //score.value+=100;
                    }
                    if (imageView2.intersects(gift2.positionX, gift2.positionY, 50, 50)) {
                        collectgift2 = true;
                        gift2.setVelocity(0, 200);
                        //score.value+=100;
                    }
                    if (imageView.intersects(gift3.positionX, gift3.positionY, 50, 50)) {
                        collectgift3 = true;
                        gift3.setVelocity(0, -200);
                        //score.value+=100;
                    }
                    if (imageView2.intersects(gift3.positionX, gift3.positionY, 50, 50)) {
                        collectgift3 = true;
                        gift3.setVelocity(0, 200);
                        //score.value+=100;
                    }

                    // render
                    if (imageView.intersects(enemy2.positionX, enemy2.positionY, 100, 100) || imageView.intersects(enemy3.positionX, enemy3.positionY, 100, 100) || imageView.intersects(enemy4.positionX, enemy4.positionY, 100, 100)) {
                        lifeplayer1 -= 0.005;
                        progressBar1.setProgress(lifeplayer1);
                        blood.setX(imageView.getX());
                        blood.setY(imageView.getY());
                    }

                    if (imageView2.intersects(enemy2.positionX, enemy2.positionY, 100, 100) || imageView2.intersects(enemy3.positionX, enemy3.positionY, 100, 100) || imageView2.intersects(enemy4.positionX, enemy4.positionY, 100, 100)) {
                        lifeplayer2 -= 0.005;
                        progressBar2.setProgress(lifeplayer2);
                        blood.setX(imageView2.getX());
                        blood.setY(imageView2.getY());
                    }

                    if (imageView.intersects(enemy22.positionX, enemy22.positionY, 100, 100) || imageView.intersects(enemy33.positionX, enemy33.positionY, 100, 100) || imageView.intersects(enemy44.positionX, enemy44.positionY, 100, 100)) {
                        lifeplayer1 -= 0.005;
                        progressBar1.setProgress(lifeplayer1);
                        blood.setX(imageView.getX());
                        blood.setY(imageView.getY());
                    }

                    if (imageView2.intersects(enemy22.positionX, enemy22.positionY, 100, 100) || imageView2.intersects(enemy33.positionX, enemy33.positionY, 100, 100) || imageView2.intersects(enemy44.positionX, enemy44.positionY, 100, 100)) {
                        lifeplayer2 -= 0.005;
                        progressBar2.setProgress(lifeplayer2);
                        blood.setX(imageView2.getX());
                        blood.setY(imageView2.getY());
                    }

                    gc.clearRect(0, 0, 1920, 990);
                    bgview.render(gc);
                    enemy3.render(gc);
                    enemy2.render(gc);
                    enemy4.render(gc);
                    enemy33.render(gc);
                    enemy22.render(gc);
                    enemy44.render(gc);
                    bullet.render(gc);
                    bullet2.render(gc);
                    gift1.render(gc);
                    gift2.render(gc);
                    gift3.render(gc);
                    //String pointsText = "Score: " + (100 * score.value);
                    //gc.fillText(pointsText, 360, 36);
                    //gc.strokeText(pointsText, 360, 36);
                }

            }.start();

            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage theStage) throws Exception {
        try {
            Socket clientSocket = new Socket("localhost", 6789);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            ClientThread ct = new ClientThread(inFromServer, outToServer);
            Thread t = new Thread(ct);
            t.start();

            String musicFile = "don_theme.mp3";     // For example

            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.play();
                }
            });

            Stage window = theStage;
            Scene theScene;
            Group root2 = new Group();
            Parent root1 = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            window.setTitle("Shooting Game");
            root2.getChildren().add(root1);
            theScene = new Scene(root2);
            window.setScene(theScene);
            window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

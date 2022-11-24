package sample;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ClientThread implements Runnable {

    BufferedReader inFromServer;
    PrintWriter outToServer;
    private MediaPlayer audioPlayer;

    ClientThread(BufferedReader inFromServer, PrintWriter outToServer) {
        this.outToServer = outToServer;
        this.inFromServer = inFromServer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int block = 0, block2 = 0;
                String string = inFromServer.readLine();
                String[] parts = string.split("#");
                try {
                    block = 0;

                    if (Example1.imageView.getX() >= 350 && Example1.imageView.getX() <= 890 && Example1.imageView.getY() >= 550)
                        block = 1;
                    else if (Example1.imageView.getX() >= 1000 && Example1.imageView.getX() <= 1550 && Example1.imageView.getY() >= 550)
                        block = 2;
                    else if (Example1.imageView.getX() >= 0 && Example1.imageView.getX() <= 890 && Example1.imageView.getY() <= 280)
                        block = 3;
                    else if (Example1.imageView.getX() >= 890 && Example1.imageView.getX() <= 1000 && Example1.imageView.getY() >= 280 && Example1.imageView.getY() <= 550)
                        block = 4;
                    else if (Example1.imageView.getX() >= 1000 && Example1.imageView.getX() <= 1920 && Example1.imageView.getY() <= 280)
                        block = 5;

                    block2 = 0;
                    if (Example1.imageView2.getX() >= 350 && Example1.imageView2.getX() <= 890 && Example1.imageView2.getY() >= 550)
                        block2 = 1;
                    else if (Example1.imageView2.getX() >= 1000 && Example1.imageView2.getX() <= 1550 && Example1.imageView2.getY() >= 550)
                        block2 = 2;
                    else if (Example1.imageView2.getX() >= 0 && Example1.imageView2.getX() <= 890 && Example1.imageView2.getY() <= 280)
                        block2 = 3;
                    else if (Example1.imageView2.getX() >= 890 && Example1.imageView2.getX() <= 1000 && Example1.imageView2.getY() >= 280 && Example1.imageView.getY() <= 550)
                        block2 = 4;
                    else if (Example1.imageView2.getX() >= 1000 && Example1.imageView2.getX() <= 1920 && Example1.imageView2.getY() <= 280)
                        block2 = 5;
                    else
                        block2 = 0;
                } catch (Exception ee) {

                }
                if (string != null) {
                    if (string.equals("USERNAME")) {
                        System.out.println("username aslo");
                        Example1.assign = true;
                    }
                    System.out.println(Example1.assign);
                    double t1 = Example1.imageView.getX();
                    double t2 = Example1.imageView.getY();
                    double tt1 = Example1.imageView2.getX();
                    double tt2 = Example1.imageView2.getY();

                    if (string.compareTo("UP") == 0) {
                        if (Example1.assign == true) {
                            tt2 -= 10;
                            Example1.imageView2.setRotate(0);
                        } else {
                            t2 -= 10;
                            Example1.imageView.setRotate(0);
                        }
                    }
                    if (string.compareTo("DOWN") == 0) {
                        if (Example1.assign == true) {
                            tt2 += 10;
                            Example1.imageView2.setRotate(-180);
                        } else {
                            t2 += 10;
                            Example1.imageView.setRotate(-180);
                        }
                    }
                    if (string.compareTo("RIGHT") == 0) {
                        if (Example1.assign == true) {
                            tt1 += 10;
                            Example1.imageView2.setRotate(90);
                        } else {
                            t1 += 10;
                            Example1.imageView.setRotate(90);
                        }
                    }
                    if (string.compareTo("LEFT") == 0) {
                        System.out.println(Example1.imageView.getX() + " " + Example1.imageView.getY() + " " + Example1.imageView2.getX() + " " + Example1.imageView2.getY());
                        if (Example1.assign == true) {
                            tt1 -= 10;
                            Example1.imageView2.setRotate(-90);
                        } else {
                            t1 -= 10;
                            Example1.imageView.setRotate(-90);
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
                    Example1.imageView.setX(t1);
                    Example1.imageView.setY(t2);
                    Example1.imageView2.setX(tt1);
                    Example1.imageView2.setY(tt2);

                    //shooting
                    if (string.compareTo("SPACE") == 0) {
                        File audioFile = new File("src//shoot.mp3");
                        Media audio = new Media(audioFile.toURI().toString());
                        audioPlayer = new MediaPlayer(audio);
                        audioPlayer.play();
                        if (!Example1.assign) {
                            Example1.bullet.setImage("file:bullet1.png");

                            if (Example1.imageView.getRotate() == -90) {
                                Example1.bullet.setPosition(Example1.imageView.getX() - 100, Example1.imageView.getY() + 30);
                                Example1.bullet.setVelocity(-3000, 0);
                            }
                            if (Example1.imageView.getRotate() == 90) {
                                Example1.bullet.setPosition(Example1.imageView.getX() + 100, Example1.imageView.getY() + 60);
                                Example1.bullet.setVelocity(3000, 0);
                            }
                            if (Example1.imageView.getRotate() == 0) {
                                Example1.bullet.setPosition(Example1.imageView.getX() + 40, Example1.imageView.getY());
                                Example1.bullet.setVelocity(0, -3000);
                            }
                            if (Example1.imageView.getRotate() == -180) {
                                Example1.bullet.setPosition(Example1.imageView.getX(), Example1.imageView.getY() + 50);
                                Example1.bullet.setVelocity(0, 3000);
                            }
                        } else {
                            Example1.bullet2.setImage("file:bullet1.png");

                            if (Example1.imageView2.getRotate() == -90) {
                                Example1.bullet2.setPosition(Example1.imageView2.getX() - 100, Example1.imageView2.getY() + 30);
                                Example1.bullet2.setVelocity(-3000, 0);
                            }
                            if (Example1.imageView2.getRotate() == 90) {
                                Example1.bullet2.setPosition(Example1.imageView2.getX() + 100, Example1.imageView2.getY() + 60);
                                Example1.bullet2.setVelocity(3000, 0);
                            }
                            if (Example1.imageView2.getRotate() == 0) {
                                Example1.bullet2.setPosition(Example1.imageView2.getX() + 40, Example1.imageView2.getY());
                                Example1.bullet2.setVelocity(0, -3000);
                            }
                            if (Example1.imageView2.getRotate() == -180) {
                                Example1.bullet2.setPosition(Example1.imageView2.getX(), Example1.imageView2.getY() + 50);
                                Example1.bullet2.setVelocity(0, 3000);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
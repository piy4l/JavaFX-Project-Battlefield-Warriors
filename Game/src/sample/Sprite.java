package sample;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.RotateEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {
    private Image image;
    double positionX;
    double positionY;    
    double velocityX;
    double velocityY;
    private double width;
    private double height;
     //private final Node view;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }


    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public Image getImage(){
        return image;
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }


    /*public void rotateImage(double degrees, String filename) throws IOException {
           ImageView imageView = new ImageView(image);
           imageView.setOnRotate(new EventHandler<RotateEvent>() {
               @Override
               public void handle(KeyEvent event) {

               }
           });
       }

        public double getRotate() {
              return .getRotate();
          }

          public void rotateRight() {
              view.setRotate(90);
              //setVelocity(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate())));
          }

          public void rotateLeft() {
              view.setRotate(-90);
              //setVelocity(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate())));
          }
      */
    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }

    //void setRotate(int i) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    //Object getTransforms() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

   // void setRotate(int i) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
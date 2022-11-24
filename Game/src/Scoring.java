import javafx.fxml.FXML;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Scoring {
    @FXML
    private Label scr;
    public void setScr()throws IOException
    {
        File file=new File("E:\\list of name.txt");
        String string=new String();
        Scanner x=new Scanner(file);
        while (x.hasNextLine())
        {
            string=string+"#"+x.nextLine();
        }
        System.out.println(string);
        scr.setText(string);
    }
}

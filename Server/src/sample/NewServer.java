package sample;

import javafx.scene.Scene;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class NewServer {
    public static Socket[] skt = new Socket[2];

    public static void main(String argv[]) throws Exception {
        int workerThreadCount = 0;
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        int id = 1;
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            Socket connectionSocket2 = welcomeSocket.accept();
            ArrayList<String> u=new ArrayList<>();
            Integer cnt=0;
            //skt[id-1]=connectionSocket;
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(connectionSocket2.getInputStream()));
            PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream(), true);
            PrintWriter outToClient2 = new PrintWriter(connectionSocket2.getOutputStream(), true);
            WorkerThread wt1 = new WorkerThread(connectionSocket, id, outToClient2, outToClient, inFromClient,u, cnt);
            Thread t1 = new Thread(wt1);
            t1.start();
            WorkerThread wt2 = new WorkerThread(connectionSocket, id, outToClient, outToClient2, inFromClient2,u, cnt);
            Thread t2 = new Thread(wt2);
            t2.start();
            workerThreadCount++;
            System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);
            id++;
        }
    }
}

class WorkerThread implements Runnable {
    private Socket connectionSocket;
    private int id = 0;
    private PrintWriter outToClient;
    private PrintWriter outToMyClient;
    private BufferedReader inFromClient;
    private ArrayList<String>u;
    private Integer cnt;

    public WorkerThread(Socket s, int id, PrintWriter outToClient, PrintWriter outToMyClient, BufferedReader inFromClient,ArrayList<String>u, Integer cnt) {
        this.connectionSocket = s;
        this.id = id;
        this.outToClient = outToClient;
        this.outToMyClient = outToMyClient;
        this.inFromClient = inFromClient;
        this.u= u;
        this.cnt= cnt;

    }


    private void putNameInFile(String string1, String string2, String score) {
        System.out.println("CALLED");
        try {
            //System.out.println(string1+" "+string2+" "+score);
            File file = new File("E:\\list of name.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(string1 + " " + string2 + " " + score);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }

    public void run() {
        while (true) {
            try {
                String sentence = inFromClient.readLine ();
                if (sentence != null) {
                    //venge felo
                    ArrayList<String> str = new ArrayList<> ();
                    for (String val : sentence.split ("[#]")) {
                        if (!val.isEmpty ()) {
                            str.add (val);
                        }
                    }
                    //done
                    //now check cases
                    //first case if the game is over
                    if(str.get(0).compareTo("USERNAME")==0){
                        u.add(str.get(1));
                        for(int i=0;i<u.size();i++){
                            System.out.println(u.get(i));
                        }
                        if(u.size()==2) {
                            System.out.println("Pathalam");
                            outToClient.println("USERNAME");
                        }
                    }
                    else if(str.get(0).compareTo("GAMEOVER")==0){
                        System.out.println("SESH KHELA users are :");
                        for(int i=0;i<u.size();i++){
                            System.out.println(u.get(i));
                        }
                        if(u.size()==2){
                            putNameInFile(u.get(0),u.get(1),str.get(1));
                        }

                    }
//                    else if(str.get(0).compareTo("kheltecai")==0)
//                    {
//                        cnt++;
//                        if(cnt==2)
//                        {
//                            outToClient.println("khelo");
//                            outToMyClient.println("khelo");
//                        }
//                    }
                    //othoba khela colche onno joner kache pathaw
                    else {
                        outToClient.println (sentence);
                    }


                }

            } catch (Exception e) {

            }
        }
    }
}

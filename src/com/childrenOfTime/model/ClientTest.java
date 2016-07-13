package com.childrenOfTime.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by SaeedHD on 07/11/2016.
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket2 = new Socket(InetAddress.getLocalHost(), 2050);
                        ObjectOutputStream objOut = new ObjectOutputStream(socket2.getOutputStream());
                        objOut.writeObject(new String("salam Port 2 !"));
                        objOut.flush();


                    } catch (Exception e) {
                    }

                }
            }
        });
        //thread.start();
        startAsClient(2051);

    }

    public static void startAsClient(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(3003);
        while (true) {
            System.out.println("Port Opoened");
            Socket client = serverSocket.accept();
            //    Socket client = new Socket(InetAddress.getLocalHost(), port);
            //InputStreamReader cin = new InputStreamReader(client.getInputStream());
            //BufferedReader buf = new BufferedReader(cin);
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // System.out.println("Type a message:");
//            ObjectOutputStream ObOut = new ObjectOutputStream(client.getOutputStream());
//            ObOut.writeObject(new Object("Team A"));
//            ObOut.flush();

            //ObjectInputStream ObIn = new ObjectInputStream(client.getInputStream());
            //Object objIn = ObIn.readObject();
            System.out.println(new Scanner(client.getInputStream()).nextLine());

            // pw.println(br.readLine());
            // System.out.println("Server : " + buf.readLine());
            //client.close();
        }
        //buf.close();
        //client.close();
    }

}

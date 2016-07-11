package com.childrenOfTime.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

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
                        Socket socket2 = new Socket(InetAddress.getLocalHost(), 2052);
                        ObjectOutputStream objOut = new ObjectOutputStream(socket2.getOutputStream());
                        objOut.writeObject(new String("salam Port 2 !"));
                        objOut.flush();


                    } catch (Exception e) {
                    }

                }
            }
        });
        thread.start();
        startAsClient(2050);

    }

    public static void startAsClient(int port) throws IOException, ClassNotFoundException {

        while (true) {
            Socket client = new Socket(InetAddress.getLocalHost(), port);
            //InputStreamReader cin = new InputStreamReader(client.getInputStream());
            //BufferedReader buf = new BufferedReader(cin);
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // System.out.println("Type a message:");
            ObjectOutputStream ObOut = new ObjectOutputStream(client.getOutputStream());
            ObOut.writeObject(new Player("Team A"));
            ObOut.flush();

            ObjectInputStream ObIn = new ObjectInputStream(client.getInputStream());
            Object objIn = ObIn.readObject();
            System.out.println(objIn);

            // pw.println(br.readLine());
            // System.out.println("Server : " + buf.readLine());
            client.close();
        }
        //buf.close();
        //client.close();
    }

}

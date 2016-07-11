package com.childrenOfTime.model;

import com.childrenOfTime.model.Equip.AbilComps.Ability;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by SaeedHD on 07/11/2016.
 */


public class MultiPlayer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        startHost();
    }

    public static void startHost() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(2050);
        ServerSocket serverSocket2 = new ServerSocket(2052);


        Thread thread = new Thread(new salam(serverSocket2));
        thread.start();

        while (true) {
            //System.out.println("Port 1 Opened");
            Socket client = serverSocket.accept();
            //System.out.println("Port 1 Connected");
            // OutputStream cout = client.getOutputStream();
            //   InputStream cin = client.getInputStream();
            //PrintWriter printStream = new PrintWriter(cout, true);
            //PrintWriter printStream = new PrintWriter(cout, true);
            ObjectInputStream ObIn = new ObjectInputStream(client.getInputStream());
            Object obj = ObIn.readObject();
            System.out.println((obj));

            ObjectOutputStream ObOut = new ObjectOutputStream(client.getOutputStream());
            ObOut.writeObject(new Ability("Swirling Attack"));
            //ObOut.flush();

            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //printStream.println(br.readLine());


        }


    }


    static class salam implements Runnable {
        ServerSocket serverSocket2;

        public salam(ServerSocket serverSocket) {
            this.serverSocket2 = serverSocket;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    //System.out.println("port 2 Opened ");
                    Socket client2 = serverSocket2.accept();
                    // System.out.println("port 2 Connected ");

                    // OutputStream cout = client.getOutputStream();
                    //   InputStream cin = client.getInputStream();
                    //PrintWriter printStream = new PrintWriter(cout, true);
                    //PrintWriter printStream = new PrintWriter(cout, true);
                    ObjectInputStream ObIn = new ObjectInputStream(client2.getInputStream());
                    Object obj = ObIn.readObject();
                    System.out.println("chat : " + obj);
                    //
                    //                        ObjectOutputStream ObOut = new ObjectOutputStream(client.getOutputStream());
                    //                        ObOut.writeObject(new AbilityMaker());
                } catch (Exception e) {
                }
            }
        }
    }
}
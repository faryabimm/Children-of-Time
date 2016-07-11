package com.childrenOfTime.model;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by SaeedHD on 07/11/2016.
 */


public class MultiPlayer {
    public static final int DEFAULT_PORT = 2050;

    private String recivedMessage = "";
    private String toSendMessage = "";
    private Player yourPlayer;
    private Player enemyPlayer;
    private Battle battle;


    public void setRecievedMesssage(String recievedMesssage) {
        synchronized (this.recivedMessage) {
            this.recivedMessage = recievedMesssage;
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MultiPlayer multiPlayer = new MultiPlayer();
        multiPlayer.startAsHost();
    }


    public void startAsHost() throws IOException, ClassNotFoundException {
        int port = DEFAULT_PORT;
        boolean portIsFree;
        ServerSocket SS1 = null;
        ServerSocket SS2 = null;
        ServerSocket SS3 = null;
        ServerSocket SS4 = null;

        do {
            try {
                SS1 = new ServerSocket(port);
                SS2 = new ServerSocket(port + 1);
                SS3 = new ServerSocket(port + 2);
                SS4 = new ServerSocket(port + 3);
                portIsFree = true;
                printOutput("IP Address : " + SS1.getInetAddress() + "    Port : " + port);
            } catch (BindException be) {
                portIsFree = false;
                port += 100;
            }
        } while (!portIsFree);

        Thread dataReceiver = new Thread(new Communicator<Player>(SS1, this, ConnectionType.Host, Job.Recieve));
        Thread dataSender = new Thread(new Communicator<Player>(SS2, this, ConnectionType.Host, Job.Send));
        Thread messageReceiver = new Thread(new Communicator<String>(SS3, this, ConnectionType.Host, Job.Recieve));
        Thread messageSender = new Thread(new Communicator<String>(SS4, this, ConnectionType.Host, Job.Send));

        dataReceiver.start();
        dataSender.start();
        messageReceiver.start();
        messageSender.start();





    }

    public void startJoin(@NotNull InetAddress address, @Nullable Integer port) {
        if (port == null) port = MultiPlayer.DEFAULT_PORT;

        Thread dataReceiver = new Thread(new Communicator<Player>(this, ConnectionType.Join, port, address, Job.Recieve));
        Thread dataSender = new Thread(new Communicator<Player>(this, ConnectionType.Join, port, address, Job.Send));
        Thread messageReceiver = new Thread(new Communicator<String>(this, ConnectionType.Join, port, address, Job.Recieve));
        Thread messageSender = new Thread(new Communicator<String>(this, ConnectionType.Join, port, address, Job.Send));

        dataReceiver.start();
        dataSender.start();
        messageReceiver.start();
        messageSender.start();


    }

    public Player getYourPlayer() {
        return yourPlayer;
    }

    public void setEnemyPlayer(Player enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
        battle.setPlayers(this.yourPlayer, enemyPlayer);
    }

    public String getToSendMessage() {
        return toSendMessage;
    }

    public void setRecivedMessage(String recivedMessage) {
        this.recivedMessage = recivedMessage;
    }
}


//class salam implements Runnable {
//    ServerSocket serverSocket2;
//
//    public salam(ServerSocket performer) {
//        this.serverSocket2 = performer;
//    }
//
//    @Override
//    public void run() {
//
//        while (true) {
//            try {
//                //System.out.println("port 2 Opened ");
//                Socket client2 = serverSocket2.accept();
//                // System.out.println("port 2 Connected ");
//
//                // OutputStream cout = client.getOutputStream();
//                //   InputStream cin = client.getInputStream();
//                //PrintWriter printStream = new PrintWriter(cout, true);
//                //PrintWriter printStream = new PrintWriter(cout, true);
//                ObjectInputStream ObIn = new ObjectInputStream(client2.getInputStream());
//                Object obj = ObIn.readObject();
//                System.out.println("chat : " + obj);
//                //
//                //                        ObjectOutputStream ObOut = new ObjectOutputStream(client.getOutputStream());
//                //                        ObOut.writeObject(new AbilityMaker());
//            } catch (Exception e) {
//            }
//        }
//    }
//}


enum ConnectionType {Join, Host}

enum Job {Send, Recieve}

class Communicator<T> implements Runnable {
    Closeable performer;
    MultiPlayer multiPlayer;
    ConnectionType connectionType;
    Job job;
    int port;
    InetAddress IPAddress;


    public Communicator(ServerSocket performer, MultiPlayer multiPlayer, ConnectionType connectionType, Job job) {
        this.performer = performer;
        this.multiPlayer = multiPlayer;
        this.connectionType = connectionType;
        this.job = job;
        System.out.println("Const");
    }

    public Communicator(MultiPlayer multiPlayer, ConnectionType connectionType, int port, InetAddress IPAddress, Job job) {
        this.multiPlayer = multiPlayer;
        this.connectionType = connectionType;
        this.IPAddress = IPAddress;
        this.port = port;
        this.job = job;
    }

    @Override
    public void run() {
        try {
            Socket socket = null;
            while (true) {
                switch (connectionType) {
                    case Host:
                        System.out.println("Port Opened");
                        socket = ((ServerSocket) this.performer).accept();
                        System.out.println("Connected");
                        break;
                    case Join:
                        socket = new Socket(this.IPAddress, this.port);
                        break;
                }
                switch (job) {
                    case Send:
                        sendData(socket, (T) multiPlayer.getToSendMessage());
                        break;
                    case Recieve:
                        recieveData(socket);
                        break;
                }
                if (connectionType == ConnectionType.Join) socket.close();
            }

        } catch (Exception e) {
        }
    }

    private T recieveData(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream oips = new ObjectInputStream(socket.getInputStream());
        return (T) oips.readObject();
    }


    private void sendData(Socket socket, T data) throws IOException {
        ObjectOutputStream oops = new ObjectOutputStream(socket.getOutputStream());
        oops.writeObject(data);
        oops.flush();
    }
}
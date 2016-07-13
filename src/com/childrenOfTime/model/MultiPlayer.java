package com.childrenOfTime.model;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.childrenOfTime.view.IOHandler.printOutput;

/**
 * Created by SaeedHD on 07/11/2016.
 */


public class MultiPlayer {

    public static final int DEFAULT_PORT = 3050;

    public static MultiPlayer Instacne;

    public MultiPlayer(Player yourPlayer, Player enemyPlayer, Battle battle) {
        this.yourPlayer = yourPlayer;
        this.enemyPlayer = enemyPlayer;
        this.battle = battle;
        Instacne = this;
    }

    private String recivedMessage = "";
    private String toSendMessage = "This is Ali Talking To you";
    private Player yourPlayer;
    private Player enemyPlayer;
    private Battle battle;


    public void setRecievedMesssage(String recievedMesssage) {
//        synchronized (this.recivedMessage) {
        System.out.println(recievedMesssage);
            this.recivedMessage = recievedMesssage;
//        }
    }


    public String getRecivedMessage() {
        synchronized (this.recivedMessage) {
            return recivedMessage;
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MultiPlayer multiPlayer = new MultiPlayer(null, null, null);
        //multiPlayer.startAsHost();
        // multiPlayer.startJoin(InetAddress.getByName("81.31.172.145") , DEFAULT_PORT);
        multiPlayer.findIPAddress();
    }


    public void findIPAddress() {
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            DatagramSocket c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = "DISCOVER_FUIFSERVER_REQUEST".getBytes();

            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.1.255"), 8888);
                c.send(sendPacket);
                System.out.println(getClass().getName() + ">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
            } catch (Exception e) {
            }

            // Broadcast the message over all the network interfaces
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }

                    // Send the broadcast package!
                    try {
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
                        c.send(sendPacket);
                    } catch (Exception e) {
                    }

                    System.out.println(getClass().getName() + ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
                }
            }

            System.out.println(getClass().getName() + ">>> Done looping over all network interfaces. Now waiting for a reply!");

            //Wait for a response
            byte[] recvBuf = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
            c.receive(receivePacket);

            //We have a response
            System.out.println(getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

            //Check if the message is correct
            String message = new String(receivePacket.getData()).trim();
            if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
                //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
                InetAddress inetAddress = receivePacket.getAddress();
                //InetAddress.getByName("81.31.172.145") ;
                System.out.println(inetAddress.getHostName());
                startJoin(inetAddress, MultiPlayer.DEFAULT_PORT);
            }

            //Close the port!
            c.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return;
    }


    public void quitMultiPlayer() {
        Instacne = null;
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

                printOutput("IP Address : " + "    Port : " + port);

            } catch (BindException be) {
                portIsFree = false;
                port += 100;
            }
        } while (!portIsFree);

        Thread dataReceiver = new Thread(new Communicator(SS1, this, ConnectionType.Host, Job.Recieve, ObjectType.Player));
        Thread dataSender = new Thread(new Communicator(SS2, this, ConnectionType.Host, Job.Send, ObjectType.Player));
        Thread messageReceiver = new Thread(new Communicator(SS3, this, ConnectionType.Host, Job.Recieve, ObjectType.Message));
        Thread messageSender = new Thread(new Communicator(SS4, this, ConnectionType.Host, Job.Send, ObjectType.Message));

        dataReceiver.start();
        dataSender.start();
        messageReceiver.start();
        messageSender.start();
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
        }


    }

    public void startJoin(@NotNull InetAddress address, @Nullable Integer port) {
        if (port == null) port = MultiPlayer.DEFAULT_PORT;

//        Thread dataReceiver = new Thread(new Communicator(this, ConnectionType.Join, port, address, Job.Recieve, ObjectType.Player));
//        Thread dataSender = new Thread(new Communicator(this, ConnectionType.Join, port + 1, address, Job.Send, ObjectType.Player));
        Thread messageReceiver = new Thread(new Communicator(this, ConnectionType.Join, port + 2, address, Job.Recieve, ObjectType.Message));
        Thread messageSender = new Thread(new Communicator(this, ConnectionType.Join, port + 3, address, Job.Send, ObjectType.Message));

//        dataReceiver.start();
//        dataSender.start();
        messageReceiver.start();
        messageSender.start();


    }

    public void sendPlayerChanges() {
        synchronized (this.yourPlayer) {
            this.yourPlayer.notify();
        }
    }

    public Player getYourPlayer() {
        synchronized (this.yourPlayer) {
            try {
                this.yourPlayer.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return yourPlayer;
        }
    }


    public Player getEnemyPlayer() {
        synchronized (enemyPlayer) {
            return enemyPlayer;
        }
    }

    public void setEnemyPlayer(Player enemyPlayer) {
        synchronized (this.enemyPlayer) {
            this.enemyPlayer = enemyPlayer;
            battle.startPvP(this.yourPlayer, enemyPlayer);
        }
    }

    public String getToSendMessage() {
//        synchronized (this.toSendMessage) {
//            try {
//                this.toSendMessage.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        return new Scanner(System.in).nextLine();
//        }
    }

    public void setToSendMessage(String toSendMessage) {
        synchronized (this.toSendMessage) {
            this.toSendMessage.notify();
            this.toSendMessage = toSendMessage;
        }
    }

    //    public void setRecivedMessage(String recivedMessage) {
//        this.recivedMessage = recivedMessage;
//    }
//
//    public String getRecivedMessage() {
//        return recivedMessage;
//    }
}


enum ConnectionType {Join, Host}

enum Job {Send, Recieve}

enum ObjectType {Message, Player}

class Communicator implements Runnable {
    Closeable performer;
    MultiPlayer multiPlayer;
    ConnectionType connectionType;
    Job job;
    int port;
    InetAddress IPAddress;
    ObjectType transformingObjectType;

    public Communicator(ServerSocket performer, MultiPlayer multiPlayer, ConnectionType connectionType, Job job, ObjectType transformingObjectType) {
        this.performer = performer;
        this.multiPlayer = multiPlayer;
        this.connectionType = connectionType;
        this.job = job;
        this.transformingObjectType = transformingObjectType;
        System.out.println("Const");
    }

    public Communicator(MultiPlayer multiPlayer, ConnectionType connectionType, int port, InetAddress IPAddress, Job job, ObjectType transformingObjectType) {
        this.multiPlayer = multiPlayer;
        this.connectionType = connectionType;
        this.IPAddress = IPAddress;
        this.port = port;
        this.job = job;
        this.transformingObjectType = transformingObjectType;

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
                        System.out.println("Befor new Socket");
                        socket = new Socket(this.IPAddress, this.port);
                        System.out.println("Befor new Socket");

                        break;
                }

                socket.setSoTimeout(10000);



                Object tosend = null;
                if (job == Job.Send) {
                    switch (transformingObjectType) {
                        case Message:
                            tosend = multiPlayer.getToSendMessage();
                            break;
                        case Player:
                            tosend = multiPlayer.getYourPlayer();
                            break;
                    }
                }

                Object recObj = null;

                try {

                    switch (job) {
                        case Send:
                            sendData(socket, tosend);
                            printOutput("RESID INJA");

                            break;
                        case Recieve:
                            recObj = recieveData(socket);
                            break;
                    }
                } catch (Exception e) {

                    e.printStackTrace();

                }

                if (job == Job.Recieve) {
                    switch (transformingObjectType) {
                        case Message:
                            multiPlayer.setRecievedMesssage((String) recObj);
                            break;
                        case Player:
                            multiPlayer.setEnemyPlayer((Player) recObj);
                            break;
                    }
                }

                if (connectionType == ConnectionType.Join) socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object recieveData(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream oips = new ObjectInputStream(socket.getInputStream());
        return oips.readObject();
    }


    private void sendData(Socket socket, Object data) throws IOException {
        ObjectOutputStream oops = new ObjectOutputStream(socket.getOutputStream());
        oops.writeObject(data);
        oops.flush();
    }
}


class DiscoveryThread implements Runnable {


    DatagramSocket socket;

    @Override
    public void run() {
        try {
            //Keep a socket open to listen to all the UDP trafic that is destined for this port
            socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);

            while (true) {
                System.out.println(getClass().getName() + ">>>Ready to receive broadcast packets!");

                //Receive a packet
                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                socket.receive(packet);

                //Packet received
                System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
                System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

                //See if the packet holds the right command (message)
                String message = new String(packet.getData()).trim();
                if (message.equals("DISCOVER_FUIFSERVER_REQUEST")) {
                    byte[] sendData = "DISCOVER_FUIFSERVER_RESPONSE".getBytes();

                    //Send a response
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                    socket.send(sendPacket);

                    System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DiscoveryThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static DiscoveryThread getInstance() {
        return DiscoveryThreadHolder.INSTANCE;
    }

    private static class DiscoveryThreadHolder {

        private static final DiscoveryThread INSTANCE = new DiscoveryThread();
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


package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.utilities.GUIUtils;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by SaeedHD on 07/11/2016.
 */


public class MultiPlayer {

    public static final int DEFAULT_PORT = 3050;

    private static MultiPlayer Instacne;

    private Player thiss;
    private Player oponent;


    public static MultiPlayer getInstacne() {
        if (Instacne == null) {
            Instacne = new MultiPlayer();
        }
        return Instacne;
    }

    public void setThisPlayer(Player thiss) {
        this.thiss = thiss;
    }

    private String receivedMessage;

    private String toSendMessage = "";
    private ArrayBlockingQueue inbox = new ArrayBlockingQueue(20);
    private ArrayBlockingQueue outbox = new ArrayBlockingQueue(20);
    private Battle battle;


//
//    static HeroClass HC1;
//    static Warrior W1;
//    static Warrior W2;
//    static Warrior W3;
//    static Effect E2;
//    static Ability AB1;
//    static private Ability AB2;
//    static Item I1;
//

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            //multiPlayer.startJoin(InetAddress.getLocalHost(), 3000);


//        Instacne.autoJoin();


        } catch (Exception e) {
        }
    }


    public void initializeBattle() {
        Instacne.addToSendObjects(Instacne.thiss);
    }

    public void autoJoin() {
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
            System.out.println("Here");
            c.receive(receivePacket);

            //We have a response
            System.out.println(getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

            //Check if the message is correct
            String message = new String(receivePacket.getData()).trim();
            if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
                //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
                InetAddress inetAddress = receivePacket.getAddress();
                //InetAddress.getByName("81.31.172.145") ;
                GUIUtils.showNotification(thiss.getName(), NotificationType.MESSAGE);
                startJoin(inetAddress, MultiPlayer.DEFAULT_PORT);
                return;

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

    private DiscoveryThread discoveryThread;
    private Thread dataReceiver;
    private Thread dataSender;
    private Thread messageSender;
    private Thread messageReceiver;

    public void startAsHost() throws IOException, ClassNotFoundException {
        int port = DEFAULT_PORT;

        Instacne.discoveryThread = new DiscoveryThread();
        Instacne.discoveryThread.start();


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

//                printOutput("IP Address : " + "    Port : " + port);

            } catch (BindException be) {
                portIsFree = false;
                port += 100;
            }
        } while (!portIsFree);

        dataReceiver = new Communicator(SS2, this, ConnectionType.Host, Job.Recieve, ObjectType.Object);
        dataSender = new Communicator(SS1, this, ConnectionType.Host, Job.Send, ObjectType.Object);
        messageReceiver = new Communicator(SS4, this, ConnectionType.Host, Job.Recieve, ObjectType.Message);
        messageSender = new Communicator(SS3, this, ConnectionType.Host, Job.Send, ObjectType.Message);

        dataReceiver.start();
        dataSender.start();
        messageReceiver.start();
        messageSender.start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        forceStopConnection();
    }


    public void forceStopConnection() {
        if (this.discoveryThread != null) (discoveryThread).stopp();
        if (this.dataReceiver != null) ((Communicator) dataReceiver).stopp();
        if (this.dataSender != null) ((Communicator) dataReceiver).stopp();
        if (this.messageReceiver != null) ((Communicator) dataReceiver).stopp();
        if (this.messageSender != null) ((Communicator) dataReceiver).stopp();
        GUIUtils.showNotification("Successfully Disconnected !", NotificationType.NORMAL);

    }


    public void startJoin(@NotNull InetAddress address, @Nullable Integer port) {
        if (port == null) port = MultiPlayer.DEFAULT_PORT;

        Thread dataReceiver = new Communicator(this, ConnectionType.Join, port, address, Job.Recieve, ObjectType.Object);
        Thread dataSender = new Communicator(this, ConnectionType.Join, port + 1, address, Job.Send, ObjectType.Object);
        Thread messageReceiver = new Communicator(this, ConnectionType.Join, port + 2, address, Job.Recieve, ObjectType.Message);
        Thread messageSender = new Communicator(this, ConnectionType.Join, port + 3, address, Job.Send, ObjectType.Message);

        dataReceiver.start();
        dataSender.start();
        messageReceiver.start();
        messageSender.start();


    }

    public void addToSendObjects(Object toSendObject) {
        if (toSendObject == null) return;
        try {
            this.outbox.put(toSendObject);
            System.out.println(" Object Sent    :    " + toSendObject.getClass().getSimpleName());
            if (toSendObject instanceof Player) System.out.println("Sent : " + ((Player) toSendObject).getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object getToSendObject() {
        Object toSendObject = new Object();
        try {
            toSendObject = this.outbox.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return toSendObject;
    }


    public void addReceivedObject(Object receivedObject) {
        if (receivedObject == null) return;
//        this.receivedObject = receivedObject;

        System.out.println(" Object Received    :    " + receivedObject.getClass().getSimpleName());

        if (receivedObject instanceof Player) {
            this.oponent = (Player) receivedObject;

            System.out.println("Player   :    " + ((Player) receivedObject).getName());

            return;
        } else {
            inbox.add(receivedObject);
        }

    }


    public void setRecievedMesssage(String recievedMesssage) {
        GUIUtils.showNotification(recievedMesssage, NotificationType.BAD);
        this.receivedMessage = recievedMesssage;
    }


    public String getToSendMessage() {
        synchronized (this.toSendMessage) {
//            try {
//                this.toSendMessage.wait();
//            } catch (InterruptedException e) {
//            }
//            return this.toSendMessage;
            return new Scanner(System.in).nextLine();
        }
    }

    public void setToSendMessage(String toSendMessage) {
        if (toSendMessage == null) return;
        if (toSendMessage.length() == 0) return;
        synchronized (this.toSendMessage) {
            this.toSendMessage = toSendMessage;
            this.toSendMessage.notify();
        }
    }


}
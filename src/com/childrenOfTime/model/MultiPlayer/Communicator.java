package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.utilities.GUIUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by SaeedHD on 07/13/2016.
 */
public class Communicator extends Thread {
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
    }

    public Communicator(MultiPlayer multiPlayer, ConnectionType connectionType, int port, InetAddress IPAddress, Job job, ObjectType transformingObjectType) {
        this.multiPlayer = multiPlayer;
        this.connectionType = connectionType;
        this.IPAddress = IPAddress;
        this.port = port;
        this.job = job;
        this.transformingObjectType = transformingObjectType;

    }

    boolean sho = true;

    public void stopp() {
        System.out.println("Fucked");
        sho = false;
    }

    @Override
    public void run() {
        try {
            Socket socket = null;
            long t1 = System.currentTimeMillis();
            boolean errorHasPrinted = false;
            while (true) {
                if (!sho) Thread.currentThread().interrupt();
                try {
                    switch (connectionType) {
                        case Host:
                            socket = ((ServerSocket) this.performer).accept();
                            break;
                        case Join:
                            socket = new Socket(this.IPAddress, this.port);
                            break;
                    }
                    Object toSend = null;
                    if (job == Job.Send) {
                        switch (transformingObjectType) {
                            case Message:
                                toSend = multiPlayer.getToSendMessage();
                                break;
                            case Object:
                                toSend = multiPlayer.getToSendObject();
                                break;
                        }
                    }

                    Object recObj = null;
                    switch (job) {
                        case Send:
                            sendData(socket, toSend);
                            break;
                        case Recieve:
                            recObj = recieveData(socket);
                            break;
                    }
                    if (job == Job.Recieve) {
                        switch (transformingObjectType) {
                            case Message:
                                multiPlayer.setRecievedMesssage((String) recObj);
                                break;
                            case Object:
                                multiPlayer.addReceivedObject(recObj);
                                break;
                        }
                    }

                    if (connectionType == ConnectionType.Join) socket.close();
                } catch (IOException e) {
                    if (connectionType == ConnectionType.Join) {
                        if (!errorHasPrinted) {
                            GUIUtils.showNotification("Reconnecting...", NotificationType.BAD);
                            errorHasPrinted = true;
                            t1 = System.currentTimeMillis();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        if (System.currentTimeMillis() - t1 > 8000) {
                            throw new Exception();
                        }
                    }
                } catch (ClassNotFoundException e) {
                }
            }
//            System.out.println("YES");
////            if (transformingObjectType == ObjectType.Object && job == Job.Recieve)
//            GUIUtils.showNotification("Connection Lost... ! ", NotificationType.BAD);

        } catch (Exception io) {
            if (transformingObjectType == ObjectType.Object && job == Job.Recieve)
                GUIUtils.showNotification("Connection Lost... ! ", NotificationType.BAD);
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


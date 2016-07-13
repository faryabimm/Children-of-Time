package com.childrenOfTime.model.MultiPlayer;

import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.utilities.GUIUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by SaeedHD on 07/13/2016.
 */
class DiscoveryThread extends Thread {



    DatagramSocket socket;

    Thread blinker;

    public void stopp() {
        blinker = null;
    }


    @Override
    public void run() {
        blinker = Thread.currentThread();
        try {

            //Keep a socket open to listen to all the UDP trafic that is destined for this port
            socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);

            while (Thread.currentThread() == blinker) {

                GUIUtils.showNotification("Ready to receive client requests!", NotificationType.GOOD);

                //Receive a packet
                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                socket.receive(packet);

                //Packet received

                //See if the packet holds the right command (message)
                String message = new String(packet.getData()).trim();
                if (message.equals("DISCOVER_FUIFSERVER_REQUEST")) {
                    GUIUtils.showNotification(packet.getAddress().getHostAddress() + " has joint to Server ;", NotificationType.GOOD);
                    byte[] sendData = "DISCOVER_FUIFSERVER_RESPONSE".getBytes();

                    //Send a response
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                    socket.send(sendPacket);
                    return;
                }
            }
            GUIUtils.showNotification("Server is Closed ! ", NotificationType.BAD);

        } catch (IOException ex) {
            GUIUtils.showNotification("Unknown Error; Try Again ! ", NotificationType.BAD);

        }
    }


    public static DiscoveryThread getInstance() {
        return DiscoveryThreadHolder.INSTANCE;
    }

    private static class DiscoveryThreadHolder {

        private static final DiscoveryThread INSTANCE = new DiscoveryThread();
    }

}

package io.pivotal.pde.UDPtester;

import org.springframework.context.annotation.Configuration;
import java.net.*;
import java.io.*;

@Configuration
public class UDPClient {

    private int UDPPort;

    public void sendPacket (String packet, int UDPPort) throws Exception {

        byte[] sendData = new byte[64];
        InetAddress IPAddress = InetAddress.getByName("localhost");

        System.out.println("SENDING  --> " + packet);

        DatagramSocket senderSocket = new DatagramSocket();
        sendData = packet.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, UDPPort);
        senderSocket.send(sendPacket);
        senderSocket.close();
    }

    public void setUDPPort(int UDPPort) {
        this.UDPPort = UDPPort;
    }

    public int getUDPPort() {
        System.out.println("Client UDP post is : " + UDPPort);
        return UDPPort;
    }
}

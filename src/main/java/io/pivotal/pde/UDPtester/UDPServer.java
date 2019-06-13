package io.pivotal.pde.UDPtester;

import org.springframework.context.annotation.Configuration;

import java.net.*;

@Configuration
public class UDPServer {

    private int UDPPort;

    public DatagramSocket initializeServe (int UDPPort) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(UDPPort);
        System.out.println("Server has being initialized on UDP port : " + UDPPort);
        return serverSocket;
    }

    public void receivePacket (DatagramSocket serverSocket) throws Exception {
        byte[] receiveData = new byte[64];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            System.out.println("RECEIVED <-- " + sentence.toUpperCase());
    }

    public void setUDPPort(int UDPPort) {
        this.UDPPort = UDPPort;
    }

    public int getUDPPort() {
        System.out.println("Server UDP post is : " + UDPPort);
        return UDPPort;
    }

}

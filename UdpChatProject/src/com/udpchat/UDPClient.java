package com.udpchat;

import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter message (or 'exit' to quit): ");
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) break;
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String echoedMessage = new String(receivePacket.getData()).trim();
                System.out.println("Echo from server: " + echoedMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}
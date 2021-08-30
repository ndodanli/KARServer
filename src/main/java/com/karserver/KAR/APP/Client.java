package com.karserver.KAR.APP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    static private String msg;

    public String getMsg() {
        return msg;
    }

    public static void main(String[] args) {
        final Socket clientSocket;
        final BufferedReader in;
        try {
            String host = "spring-boot-complete-1630332816013.azurewebsites.net",
                    hostLocal = "localhost";
            clientSocket = new Socket(host, 5000);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            try {
                msg = in.readLine();
                while (msg != null) {
                    System.out.println("Server : " + msg);
                    msg = in.readLine();
                }
                System.out.println("Server out of service");
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

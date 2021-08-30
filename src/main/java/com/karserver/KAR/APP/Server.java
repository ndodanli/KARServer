package com.karserver.KAR.APP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static final int PORT = 80;
    public static ArrayList<EchoThread> threads;
    public static int index;

    public static void runServer() {
        System.out.println("WORKED");
        ServerSocket serverSocket = null;
        Socket socket = null;
        threads = new ArrayList<>();
        index = 0;
        try {
            serverSocket = new ServerSocket();
            System.out.println(serverSocket);
        } catch (IOException e) {
            //System.out.println("Error: " + e);
        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            threads.add(new EchoThread(socket, index));
            threads.get(index).start();

            Server.index++;
        }
    }
}

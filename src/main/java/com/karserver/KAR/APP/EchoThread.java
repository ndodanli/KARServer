package com.karserver.KAR.APP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoThread extends Thread {

    protected Socket socket;
    public DataOutputStream out;
    private final int id;

    public EchoThread(Socket clientSocket, int id) {
        this.out = null;
        this.id = id;
        this.socket = clientSocket;
        //System.out.println("Client connected with id: " + id);
    }

    @Override
    public void run() {
        InputStream inp;
        BufferedReader brinp;

        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    Server.threads.remove(this.id);
                    Server.index--;
                    return;
                } else {
                    if (Server.threads.size() > 1) {
                        Server.threads.get(getOtherClientsId()).out.writeBytes(line + "\n");
                        Server.threads.get(getOtherClientsId()).out.flush();
                    }else{
                        System.out.println("No client to retrieve data");
                    }
                }
            } catch (IOException e) {
                return;
            }
        }
    }

    private int getOtherClientsId() {
        int otherClientsId = -1;
        for (int i = 0; i < Server.index; i++) {
            if (this.id != i) {
                otherClientsId = i;
            }
        }
        return otherClientsId;
    }
}

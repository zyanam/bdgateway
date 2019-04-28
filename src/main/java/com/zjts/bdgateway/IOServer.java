package com.zjts.bdgateway;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);

        new Thread(()-> {
            while (true) {
                Socket socket = serverSocket.accept();
            }
        }).start();
    }
}

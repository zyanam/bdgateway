package com.zjts.bdgateway;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);

        new Thread(() -> {

            try {
                while (true) {
                    Socket socket = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            byte[] buffer = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while (true) {
                                int len;
                                while ((len = inputStream.read(buffer)) != -1) {
                                    for (int i = 0; i < len; i++) {
                                        byte b = buffer[i];
                                        int it = b & 0xff;
                                        System.out.println(Integer.toHexString(it));
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                    ).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }).start();
    }
}

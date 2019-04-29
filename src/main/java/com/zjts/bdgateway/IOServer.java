package com.zjts.bdgateway;

import com.zjts.protocol808.Message808;

import java.io.ByteArrayOutputStream;
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

                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            while (true) {
                                int len;
                                while ((len = inputStream.read(buffer)) != -1) {
                                    baos.write(buffer, 0, len);
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

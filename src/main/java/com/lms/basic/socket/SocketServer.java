package com.lms.basic.socket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 10:32 2018/11/6
 **/
public class SocketServer {
    private static final int port = 55543;
    private static ServerSocket server ;
    private static Socket socket;
    private static InputStream inputStream ;
    private void init() throws IOException {
        if(server==null)
        server = new ServerSocket(port);
        if(socket==null)
            socket=server.accept();
        if(inputStream==null)
            inputStream=socket.getInputStream();
    }
    private String readString(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len=inputStream.read(bytes))!=-1){
            sb.append(new String(bytes,0,len,"UTF-8"));
        }
        return sb.toString();
    }
    private void reply(String message) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(message.getBytes("UTF-8"));
    }
    public void read() throws IOException {
        init();
        System.out.println("get message from client : "+readString(inputStream));
        close();
    }
    public void readAndReply() throws IOException {
        init();
        String message = readString(inputStream);
        System.out.println("get message from client : "+message);
        reply("I have get your message, have a good day!");
        reply("\n I have get your message, have a good day!");

        System.out.println();
        // 一端退出，但退出时并未关闭该连接，另一端如果在从连接中读数据则抛出该异常（Connection reset）。简单的说就是在连接断开后的读和写操作引起的。
//        close();
    }
    private void close() throws IOException {
        inputStream.close();
        socket.close();
        server.close();
    }
    public static void main(String[] args) throws Exception{
        SocketServer socketServer = new SocketServer();
        socketServer.readAndReply();
    }
}

package com.lms.basic.socket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author MissionLee
 * @Description
 * @Date Created in 10:39 2018/11/6
 **/
public class SocketClient {
    public static void main(String[] args) throws Exception{
        String host = "127.0.0.1";
        int serverPort = 55543;
        Socket socket = new Socket(host,serverPort);
        OutputStream outputStream = socket.getOutputStream();
        String message = "Learn how to use java Socket";
        outputStream.write(message.getBytes("UTF-8"));
        socket.shutdownOutput();
        InputStream inputStream =socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len=inputStream.read(bytes))!=-1){
            sb.append(new String(bytes,0,len,"UTF-8"));
        }
        System.out.println("get return message from server :"+sb);
        outputStream.close();
        socket.close();
    }
}

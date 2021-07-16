package com.bytedance.practice5.socket;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class ClientSocketThread extends Thread {
    public ClientSocketThread(SocketActivity.SocketCallback callback) {
        this.callback = callback;
    }

    private SocketActivity.SocketCallback callback;

    //head请求内容
    private static String content = "HEAD / HTTP/1.1\r\nHost:www.zju.edu.cn\r\n\r\n";

    private boolean stopFlag = false;

    private static final String TAG = "MYSOCKET";


    @Override
    public void run() {
        // TODO 6 用socket实现简单的HEAD请求（发送content）
        //  将返回结果用callback.onresponse(result)进行展示

        String message = content;

        try{
            Socket socket = new Socket("115.238.192.248",80);
            BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream is = new BufferedInputStream(socket.getInputStream());

            double n = 1;
            byte[] data = new byte[1024*5];
            int len = -1;
            while(!stopFlag&&socket.isConnected()){
                if(!message.isEmpty()){
                    os.write(message.getBytes());
                    os.flush();
                    message = "";
                    int receiveLen = is.read(data);
                    String receive = new String(data,0,receiveLen);
                    Log.d(TAG, "receive "+ receive);
                    callback.onResponse(receive);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "error "+e.toString());
        }

    }
}
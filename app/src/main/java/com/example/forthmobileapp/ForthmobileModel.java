package com.example.forthmobileapp;
import android.widget.MultiAutoCompleteTextView;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ForthmobileModel {
    TelnetClient client;
    Runnable receiver;
    ForthmobileModel() {
        client = new TelnetClient();
    }
    public void connect(String host, int port){
        try {
            client.connect(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String text, MultiAutoCompleteTextView receiverWidget, int whereToReceiveReply) throws NullPointerException {
        OutputStream out = client.getOutputStream();
        if (out == null){
            throw new NullPointerException("client.getOutputStream() returned null");
        }
        PrintWriter pout = new PrintWriter(out);
        pout.println(text);
        Runnable receiver = new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[2048];
                InputStream is = client.getInputStream();
                try {
                    is.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                receiverWidget.append(new String(buffer));
            }
        };
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void disconnect() {
        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

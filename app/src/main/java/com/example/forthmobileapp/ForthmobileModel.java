package com.example.forthmobileapp;

import android.util.Log;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ForthmobileModel {
    static Socket socket = new Socket();
    MultiAutoCompleteTextView _view;
    static Thread receiver;
    static Thread sender;
    static Thread connector;
    static PrintWriter out = null;
    static  BufferedReader br = null;
    final static String[] reply = {""};

    public static String send(String host, int port, String text, EditText view) {

        connector = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket.connect(new InetSocketAddress(host, port));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OutputStream os = null;
                try {
                    os = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    view.setText("Error:" + e.getMessage());
                    return;
                }
                out = new PrintWriter(os);

                InputStreamReader ir = null;
                try {
                    ir = new InputStreamReader(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br = new BufferedReader(ir);
            }
        });

        sender = new Thread(new Runnable() {
            @Override
            public void run() {
                    out.println(text);
                    out.flush();
            }
        });

        receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                String s = "";
                reply[0] = "";
                while (s != null) {
                    try {
                        s = br.readLine();
                    } catch (IOException e) {
                        System.out.println(e.getCause());
                    }
                   if (s != null) {
                       reply[0] += s + "\n";
                       Log.i("RECV", s);
                   }
                }
            }
        });
        if ( ! socket.isConnected()) {
            connector.start();
            try {
                connector.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        receiver.start();
        sender.start();

        try {
            sender.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            receiver.join(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        view.setText(reply[0] + view.getText());
        return reply[0];
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

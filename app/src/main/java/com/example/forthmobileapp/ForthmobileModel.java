package com.example.forthmobileapp;
import android.util.Log;
import android.widget.MultiAutoCompleteTextView;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ForthmobileModel {
    static TelnetClient _client = new TelnetClient();
    String _host;
    int _port;
    char[] buffer = new char[32768];

    Runnable receiver;

    public void connect(String host, int port){
        _host = host;
        _port = port;
        Runnable sender = new Runnable() {
            @Override
            public void run() {
                try {
                    _client.connect(host, port);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Connect failed", e.toString());
                }
            }
        };
        new Thread(sender).start();
    }

    public void send(String text, MultiAutoCompleteTextView receiverWidget, int whereToReceiveReply) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (_client.getOutputStream() == null){
                    try {
                        _client.connect(_host, _port);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                OutputStream out = _client.getOutputStream();
                if (out == null){
                    throw new NullPointerException("client.getOutputStream() returned null");
                }
                PrintWriter pout = new PrintWriter(out);
                pout.println(text);

            }
        }).start();

        Runnable receiver = new Runnable() {
            @Override
            public void run() {

                OutputStream out = _client.getOutputStream();
                if (out == null){
                    throw new NullPointerException("client.getOutputStream() returned null");
                }
                PrintWriter pout = new PrintWriter(out);
                pout.println(text);

                InputStream is = _client.getInputStream();
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(ir);
                try {
                   while (br.read(buffer) != -1){
                       receiverWidget.append(new String(buffer));
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(receiver).start();
    }

    public boolean isConnected() {
        return _client.isConnected();
    }

    public void disconnect() {
        try {
            _client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

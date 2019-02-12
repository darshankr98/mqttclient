package com.example.darshanramesh.mqttclient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class navigationActivity extends AppCompatActivity {

    private Button forward,backward,left,right,stop,recv;
    TextView tvRecvMsg;
    MqttAndroidClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        forward = findViewById(R.id.button1);
        backward = findViewById(R.id.button2);
        left = findViewById(R.id.button3);
        right = findViewById(R.id.button4);
        stop = findViewById(R.id.button5);
        tvRecvMsg = findViewById(R.id.textViewRecdMsg);
        Toast.makeText(navigationActivity.this,"Make sure your phone is connected to internet",Toast.LENGTH_SHORT).show();

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(getApplicationContext(), "tcp://iot.eclipse.org:1883",
                clientId);

        try {

            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("MQTT", "onSuccess");
                    Toast.makeText(navigationActivity.this, "Successfull", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d("MQTT", "onFailure");

                    Toast.makeText(navigationActivity.this, "Not Successfull", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            Toast.makeText(navigationActivity.this,"Turn on Internet",Toast.LENGTH_SHORT).show();
        }


        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = "Robot";
                String payload = "u";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                    Toast.makeText(navigationActivity.this, "Moving forward", Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = "Robot";
                String payload = "d";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                    Toast.makeText(navigationActivity.this, "Moving backward", Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = "Robot";
                String payload = "l";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                    Toast.makeText(navigationActivity.this, "Moving left", Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = "Robot";
                String payload = "r";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                    Toast.makeText(navigationActivity.this, "Moving right", Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = "Robot";
                String payload = "s";
                byte[] encodedPayload = new byte[0];
                try {
                    encodedPayload = payload.getBytes("UTF-8");
                    MqttMessage message = new MqttMessage(encodedPayload);
                    client.publish(topic, message);
                    Toast.makeText(navigationActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException | MqttException e) {
                    e.printStackTrace();
                }

            }
        });

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

                topic = "Robot";

                tvRecvMsg.setText(new String(message.getPayload()));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }




}


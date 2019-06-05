
package com.example.darshanramesh.mqttclient;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.darshanramesh.mqttclient.R;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isNetworkAvailable()){
            startActivity(new Intent(MainActivity.this,navigationActivity.class));    
        } else{
            Toast.makeText(navigationActivity.this,"Make sure your phone is connected to internet",Toast.LENGTH_SHORT).show();
        }
    
    }
}

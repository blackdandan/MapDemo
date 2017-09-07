package com.example.blackdandan.mapdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.example.blackdandan.mapdemo.im.ChatActivity;
import com.example.blackdandan.mapdemo.im.IMActivity;
import com.example.blackdandan.mapdemo.map.MapActivity;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openMap(View view){
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }
    public void im(View view){
        Intent intent = new Intent(this,IMActivity.class);
        startActivity(intent);
    }
}

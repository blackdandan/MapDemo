package com.example.blackdandan.mapdemo.im;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blackdandan.mapdemo.R;
import com.hyphenate.EMCallBack;

import java.util.ArrayList;
import java.util.List;

public class IMActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private ListView userListView;
    private List<String> userList = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        initView();


        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IMActivity.this,ChatActivity.class);
                intent.putExtra("to_username",userList.get(position));
                startActivity(intent);
            }
        });

    }

    private void initView(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        userListView = (ListView) findViewById(R.id.user_list);
    }
    @SuppressLint("HandlerLeak")
    public void register(View view){
        IM.createAccount(username.getText().toString(),password.getText().toString(),new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1){
                    Toast.makeText(IMActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
                }
                if (msg.what == 2){
                    Toast.makeText(IMActivity.this,"创建失败",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    @SuppressLint("HandlerLeak")
    public void getUserList(View view){
        IM.getUserList(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj!=null)
                    userList = (List<String>)msg.obj;
                System.out.println("do====userList.size:"+userList);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(IMActivity.this,android.R.layout.simple_list_item_1,
                        android.R.id.text1,userList);
                userListView.setAdapter(arrayAdapter);
                super.handleMessage(msg);
            }
        });
    }
    public void addFriend(View view){
        IM.addFriend(username.getText().toString());
    }
    public void logout(View view){
        IM.logout();
    }
    public void login(View view){
        IM.login(username.getText().toString(), password.getText().toString(), new EMCallBack() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(IMActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onError(int i, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(IMActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onProgress(int i, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(IMActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

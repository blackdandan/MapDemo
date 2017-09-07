package com.example.blackdandan.mapdemo.im;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.blackdandan.mapdemo.R;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

public class ChatActivity extends AppCompatActivity implements EMMessageListener {
    private TextView record;
    private EditText message;
    private String toUsername;
    private StringBuffer recordBuffer = new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        EMClient.getInstance().chatManager().addMessageListener(this);
        initView();
        toUsername = getIntent().getStringExtra("to_username");
        setRecord("与 :"+toUsername +"的聊天记录");
    }
    private void setRecord(String record){
        recordBuffer.append(record+"\n");
        this.record.setText(recordBuffer.toString());
    }
    private void initView(){
        record = (TextView) findViewById(R.id.record);
        message = (EditText) findViewById(R.id.message);
    }
    public void send(View view){
        IM.sendMessage(message.getText().toString(),toUsername);
        setRecord("我说   :"+message.getText().toString());
    }

    @Override
    public void onMessageReceived(final List<EMMessage> list) {
        System.out.println("do====onMessageReceived");
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getFrom().equals(toUsername)){
                final int finalI = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setRecord(toUsername+"说:    "+list.get(finalI).getBody());
                    }
                });

            }
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
}

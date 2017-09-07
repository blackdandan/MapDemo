package com.example.blackdandan.mapdemo.im;

import android.os.Handler;
import android.os.Message;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by blackdandan on 2017/9/7.
 */

public class IM {
    public static void createAccount(final String username, final String password, final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username,password);
                    handler.sendEmptyMessage(1);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(2);
                }
            }
        }).start();

    }
    public static void login(final String username, final String password, final EMCallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(username,password,callBack);
            }
        }).start();

    }

    public static void logout(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().logout(true);
            }
        }).start();
    }
    public static void sendMessage(final String content, final String toChatUsername){
        new Thread(new Runnable() {
            @Override
            public void run() {
//创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }
        }).start();

    }
    public static void addFriend(final String username){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(username, "加你还要原因?");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public static void getUserList(final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> usernames = new ArrayList<>();
                try {
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Message message = new Message();
                    message.obj = usernames;
                    handler.sendMessage(message);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

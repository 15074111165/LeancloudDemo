package com.clp.leanclouddemo;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;

/**
 * Created by chenliupushishabi on 2016/8/24.
 */
public class CustomMessageHandler extends AVIMMessageHandler {

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if(LeancloudApp.isRevice) {
            super.onMessage(message, conversation, client);
        }
    }

    @Override
    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        if (LeancloudApp.isRevice) {
            super.onMessageReceipt(message, conversation, client);
        }
    }

}

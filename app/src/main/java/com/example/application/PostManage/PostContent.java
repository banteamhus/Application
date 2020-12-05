package com.example.application.PostManage;

import com.google.firebase.database.ServerValue;

public class PostContent {
    private String content;
    private Object timeStamp;

    public String getContent() {
        return content;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PostContent(String content) {
        this.content = content;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public PostContent(){

    }

    public void setContent(String content) {
        this.content = content;
    }
}

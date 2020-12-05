package com.example.application.object;

public class Comment {

    private String writingId;
    private String userId;
    private String commentId;

    protected Comment (){
        writingId = "UnRegistered";
        userId = "UnRegistered";
        commentId = "UnRegistered";
    }

    protected Comment (String a, String b, String c){
        userId = b;
        writingId = a;
        commentId = c;
    }

}
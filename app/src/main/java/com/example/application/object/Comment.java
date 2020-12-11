package com.example.application.object;

public class Comment {
    private String rootId;
    private int type; //1 = writing - 2 reply
    private String userId;
    private String commentId;
    public Comment (){
    }
    public Comment (String rId, String uId, String cId, int type){
        rootId = rId;
        this.type = type;
        userId = uId;
        commentId = cId;
    }
    public void setRootId (String rId){
        rootId = rId;
    }
    public void setUserId (String uId){
        userId = uId;
    }
    public void setCommentId (String cId){
        commentId = cId;
    }
    public void isReply (int type){
        this.type = type;
    }
    public String getRootId (){
        return rootId;
    }
    public String getUserId (){
        return userId;
    }
    public String getCommentId (){
        return commentId;
    }
    public int getType (){
        return this.type;
    }
}

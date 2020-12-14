package com.example.application.object;

public class Comment {

    //private String rootId;
    //private int type; //1 = writing - 2 reply
    private String userId;
    private String commentId;
    private String comment;
    public Comment (){
    }

    public Comment(String userId, String commentId, String comment) {
        this.userId = userId;
        this.commentId = commentId;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public void setUserId (String uId){
        userId = uId;
    }
    public void setCommentId (String cId){
        commentId = cId;
    }


    public String getUserId (){
        return userId;
    }
    public String getCommentId (){
        return commentId;
    }
    /*
    public Comment (String rId, String uId, String cId, int type){
        rootId = rId;
        this.type = type;
        userId = uId;
        commentId = cId;
    }
     public String getRootId (){
        return rootId;
    }
    public void setRootId (String rId){
        rootId = rId;
    }
    public int getType (){
        return this.type;
    }
    public void isReply (int type){
        this.type = type;
    }*/
}

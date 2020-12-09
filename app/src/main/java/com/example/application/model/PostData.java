package com.example.application.model;

public class PostData {

    String userName;
    String descp;
    String imageUrl;

    public PostData(String userName, String descp, String imageUrl) {
        this.userName = userName;
        this.descp = descp;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.example.application.model;



public class NotificationData {

    String notificationId;
    String descp;
    String imageUrl;
    String time;

    public NotificationData(String notificationId, String descp, String imageUrl, String time) {
        this.notificationId = notificationId;
        this.descp = descp;
        this.imageUrl = imageUrl;
        this.time = time;
    }

    public String getNotificationId(){
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

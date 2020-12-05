package com.example.application.object;

public class Payment {

    String userID = "abc0165";
    private double balance = 500000;

    public Payment(String userID){
        this.userID = userID;
        balance = 0;
    }

    public void deposit(double amount){
        balance=balance+amount;
    }

    public void withdraw( double amount){
        balance=balance-amount;
    }

    public void transfer(double amount, Payment other){
        withdraw(amount);
        other.deposit(amount);
    }

}

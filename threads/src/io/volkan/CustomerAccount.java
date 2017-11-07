package io.volkan;

public class CustomerAccount {
    private int accountType;
    private double balance;

    public CustomerAccount(int type, double bal) {
        accountType = type;
        balance = bal;
    }

    public int getAccountType() {return accountType;}
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}
}

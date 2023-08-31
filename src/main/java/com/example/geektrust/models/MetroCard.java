package com.example.geektrust.models;

public class MetroCard extends BaseModel {

  private int balance;
  private Passenger passenger;

  public MetroCard(String cardNumber, int balance) {
    super(cardNumber);
    this.balance = balance;
    this.passenger = new Passenger();
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public Passenger getPassenger() {
    return passenger;
  }

  public void setPassenger(Passenger passenger) {
    this.passenger = passenger;
  }

  @Override
  public String toString() {
    return "MetroCard{" +
        "balance=" + balance +
        ", passenger=" + passenger +
        '}';
  }
}

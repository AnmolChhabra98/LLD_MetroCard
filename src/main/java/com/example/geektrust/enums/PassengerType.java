package com.example.geektrust.enums;

public enum PassengerType {
  ADULT(200), SENIOR_CITIZEN(100), KID(50);

  private final int ticketPrice;

  PassengerType(int ticketPrice) {
    this.ticketPrice = ticketPrice;
  }

  public int getTicketPrice() {
    return ticketPrice;
  }
}

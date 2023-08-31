package com.example.geektrust.models;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.UserType;

public class Passenger extends User {

  private int journeyCount;
  private PassengerType passengerType;

  public Passenger() {
    super(UserType.PASSENGER);
    this.journeyCount = 0;
  }

  public int getJourneyCount() {
    return journeyCount;
  }

  public void setJourneyCount(int journeyCount) {
    this.journeyCount = journeyCount;
  }

  public PassengerType getPassengerType() {
    return passengerType;
  }

  public void setPassengerType(PassengerType passengerType) {
    this.passengerType = passengerType;
  }

  public void incrementJourneyCount() {
    journeyCount++;
  }

  @Override
  public String toString() {
    return "Passenger{" +
        "journeyCount=" + journeyCount +
        ", passengerType=" + passengerType +
        '}';
  }
}

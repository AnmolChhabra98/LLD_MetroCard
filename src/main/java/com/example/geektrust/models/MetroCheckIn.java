package com.example.geektrust.models;

import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;

public class MetroCheckIn extends BaseModel {

  private String cardNumber;
  private PassengerType passengerType;
  private MetroStation fromStation;

  public MetroCheckIn(String cardNumber, PassengerType passengerType, MetroStation fromStation) {
    this.cardNumber = cardNumber;
    this.passengerType = passengerType;
    this.fromStation = fromStation;
  }
}

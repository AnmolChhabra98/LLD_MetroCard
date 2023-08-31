package com.example.geektrust.services;

import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;

public interface MetroCheckInService {

  void doCheckIn(String cardNumber, PassengerType passengerType, MetroStation fromStation);
}

package com.example.geektrust.services.strategies.recharge;

import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.models.MetroCard;

public interface RechargeStrategy {

  void recharge(MetroCard metroCard, int amount, MetroStation fromStation);
}

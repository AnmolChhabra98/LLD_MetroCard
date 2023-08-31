package com.example.geektrust.services.strategies.recharge;

import com.example.geektrust.enums.MetroCardTransactionType;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.models.MetroCard;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.MetroStationService;
import com.example.geektrust.services.impl.MetroCardServiceImpl;
import com.example.geektrust.services.impl.MetroStationServiceImpl;
import com.example.geektrust.services.strategies.discount.ReturnJourneyDiscountStrategy;

public class AutoRechargeStrategy implements RechargeStrategy {

  private final MetroCardService metroCardService = new MetroCardServiceImpl(this,
      new ReturnJourneyDiscountStrategy());
  private final MetroStationService metroStationService = new MetroStationServiceImpl();

  @Override
  public void recharge(MetroCard metroCard, int amount, MetroStation fromStation) {
    // pay service charge
    metroStationService.addServiceFeesToTotalCharge(amount, fromStation);
    // add money
    metroCardService.makeTransaction(metroCard, amount, fromStation,
        MetroCardTransactionType.CREDIT);
  }
}

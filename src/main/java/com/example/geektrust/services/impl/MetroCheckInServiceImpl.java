package com.example.geektrust.services.impl;

import com.example.geektrust.enums.MetroCardTransactionType;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.models.MetroCard;
import com.example.geektrust.models.MetroCheckIn;
import com.example.geektrust.models.Passenger;
import com.example.geektrust.repositories.MetroCheckInRepository;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.MetroCheckInService;
import com.example.geektrust.services.strategies.discount.ReturnJourneyDiscountStrategy;
import com.example.geektrust.services.strategies.recharge.AutoRechargeStrategy;

public class MetroCheckInServiceImpl implements MetroCheckInService {

  private final MetroCheckInRepository metroCheckInRepository = new MetroCheckInRepository();
  private final MetroCardService metroCardService = new MetroCardServiceImpl(
      new AutoRechargeStrategy(), new ReturnJourneyDiscountStrategy());

  // update passenger details -> make txn -> check discount -> auto-recharge -> increase stats -> increase journey count
  @Override
  public void doCheckIn(String cardNumber, PassengerType passengerType, MetroStation fromStation) {
    MetroCard metroCard = metroCardService.getCard(cardNumber);
    Passenger passenger = metroCard.getPassenger();
    passenger.setPassengerType(passengerType);
    passenger.incrementJourneyCount();
    metroCard.setPassenger(passenger);

    metroCardService.makeTransaction(metroCard, passengerType.getTicketPrice(), fromStation,
        MetroCardTransactionType.DEBIT);
    MetroCheckIn metroCheckIn = new MetroCheckIn(cardNumber, passengerType, fromStation);

    metroCheckInRepository.save(metroCheckIn);
  }
}

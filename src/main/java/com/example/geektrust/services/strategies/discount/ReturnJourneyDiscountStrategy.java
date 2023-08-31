package com.example.geektrust.services.strategies.discount;

import com.example.geektrust.constants.Common;
import com.example.geektrust.models.MetroCard;
import com.example.geektrust.models.Passenger;

public class ReturnJourneyDiscountStrategy implements DiscountStrategy {

  @Override
  public int getDiscountIfEligible(MetroCard metroCard) {
    Passenger passenger = metroCard.getPassenger();
    int passengerJourneyCount = passenger.getJourneyCount();
    int discountAmonut =
        (passengerJourneyCount > 0 && (passengerJourneyCount % 2 == 0)) ? (int) (passenger
            .getPassengerType().getTicketPrice() * Common.RETURN_TICKET_DISCOUNT)
            : 0;

    return discountAmonut;
  }
}

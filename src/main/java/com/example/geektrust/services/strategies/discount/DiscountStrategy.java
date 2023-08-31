package com.example.geektrust.services.strategies.discount;

import com.example.geektrust.models.MetroCard;

public interface DiscountStrategy {

  int getDiscountIfEligible(MetroCard metroCard);
}

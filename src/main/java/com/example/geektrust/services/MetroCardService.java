package com.example.geektrust.services;

import com.example.geektrust.enums.MetroCardTransactionType;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.models.MetroCard;

public interface MetroCardService {

  void addCard(String cardNumber, int balance);

  MetroCard getCard(String cardNumber);

  MetroCard updateCard(MetroCard metroCard);

  MetroCard makeTransaction(MetroCard metroCard, int amount,
      MetroStation fromStation, MetroCardTransactionType metroCardTransactionType);
}

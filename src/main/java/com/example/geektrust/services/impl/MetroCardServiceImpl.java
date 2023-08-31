package com.example.geektrust.services.impl;

import com.example.geektrust.enums.MetroCardTransactionType;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.exceptions.BadRequestException;
import com.example.geektrust.models.MetroCard;
import com.example.geektrust.repositories.MetroCardRepository;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.MetroStationService;
import com.example.geektrust.services.strategies.discount.DiscountStrategy;
import com.example.geektrust.services.strategies.recharge.RechargeStrategy;
import java.util.Optional;


public class MetroCardServiceImpl implements MetroCardService {

  private final MetroCardRepository metroCardRepository = MetroCardRepository.getInstance();
  private final MetroStationService metroStationService = new MetroStationServiceImpl();
  private final RechargeStrategy rechargeStrategy;
  private final DiscountStrategy discountStrategy;

  public MetroCardServiceImpl(RechargeStrategy rechargeStrategy,
      DiscountStrategy discountStrategy) {
    this.rechargeStrategy = rechargeStrategy;
    this.discountStrategy = discountStrategy;
  }

  @Override
  public void addCard(String cardNumber, int balance) {
    MetroCard metroCard = new MetroCard(cardNumber, balance);
    metroCardRepository.save(metroCard);
  }

  @Override
  public MetroCard updateCard(MetroCard metroCard) {
    MetroCard newMetroCard = new MetroCard(metroCard.getId(), metroCard.getBalance());
    newMetroCard.setPassenger(metroCard.getPassenger());
    metroCardRepository.save(metroCard);
    return newMetroCard;
  }

  @Override
  public MetroCard getCard(String cardNumber) {
    Optional<MetroCard> metroCard = metroCardRepository.getMetroCard(cardNumber);
    if (!metroCard.isPresent()) {
      throw new BadRequestException("Card doesn't exists!");
    }

    return metroCard.get();
  }

  // only for making txn from a Metro Station itself
  @Override
  public MetroCard makeTransaction(MetroCard metroCard, int amount,
      MetroStation fromStation, MetroCardTransactionType metroCardTransactionType) {
    if (metroCardTransactionType.equals(MetroCardTransactionType.CREDIT)) {
      return performCreditTransaction(metroCard, amount);
    } else if (metroCardTransactionType.equals(MetroCardTransactionType.DEBIT)) {
      return performDebitTransaction(metroCard, amount, fromStation);
    }
    return metroCard; // Return unchanged metroCard for unsupported transaction type
  }

  private MetroCard performCreditTransaction(MetroCard metroCard, int amount) {
    // Add amount
    metroCard.setBalance(metroCard.getBalance() + amount);
    metroCardRepository.save(metroCard);
    return metroCard;
  }

  private MetroCard performDebitTransaction(MetroCard metroCard, int amount,
      MetroStation fromStation) {
    int discountAmount = discountStrategy.getDiscountIfEligible(metroCard);
    if (discountAmount > 0) {
      amount -= discountAmount;
    }

    if (metroCard.getBalance() < amount) {
      int rechargeAmount = amount - metroCard.getBalance();
      performAutoRecharge(metroCard, rechargeAmount, fromStation);
    }

    // Deduct amount
    metroCard.setBalance(metroCard.getBalance() - amount);
    metroCardRepository.save(metroCard);

    // Dependent operations
    updateMetroStationStats(amount, discountAmount, metroCard.getPassenger().getPassengerType(),
        fromStation);

    return metroCard;
  }

  private void performAutoRecharge(MetroCard metroCard, int rechargeAmount,
      MetroStation fromStation) {
    rechargeStrategy.recharge(metroCard, rechargeAmount, fromStation);
    metroCardRepository.save(metroCard);
  }

  private void updateMetroStationStats(int amount, int discountAmount, PassengerType passengerType,
      MetroStation fromStation) {
    metroStationService.addAmountToTotalCharge(amount, fromStation);
    if (discountAmount > 0) {
      metroStationService.addDiscountToTotalDiscount(discountAmount, fromStation);
    }
    metroStationService.updateJourneyCountToPassengerType(passengerType, fromStation);
  }
}

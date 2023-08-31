package com.example.geektrust.repositories;

import com.example.geektrust.models.MetroCard;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// making all repos as Singleton so that they can act like persistence storage.
public class MetroCardRepository {

  private static MetroCardRepository instance = null;
  private Map<String, MetroCard> cardNumberToMetroCard = new HashMap<>();

  private MetroCardRepository() {

  }

  public static MetroCardRepository getInstance() {
    if (instance == null) {
      instance = new MetroCardRepository();
    }

    return instance;
  }

  public void save(MetroCard metroCard) {
    cardNumberToMetroCard.put(metroCard.getId(), metroCard);
  }

  public Optional<MetroCard> getMetroCard(String cardNumber) {
    MetroCard metroCard = cardNumberToMetroCard.get(cardNumber);
    return Optional.ofNullable(metroCard);
  }
}

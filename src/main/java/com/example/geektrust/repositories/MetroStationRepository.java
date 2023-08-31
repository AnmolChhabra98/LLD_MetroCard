package com.example.geektrust.repositories;

import com.example.geektrust.dtos.response.CollectionSummaryResponseDto;
import com.example.geektrust.dtos.response.PassengerSummaryResponseDto;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroStationRepository {

  private static MetroStationRepository instance = null;
  private Map<MetroStation, CollectionSummaryResponseDto> metroStationToCollectionSummaryResponseDto = new HashMap<>();
  private Map<MetroStation, Map<PassengerType, PassengerSummaryResponseDto>> metroStationToPassengerType = new HashMap<>();

  private MetroStationRepository() {
  }

  public static MetroStationRepository getInstance() {
    if (instance == null) {
      instance = new MetroStationRepository();
    }

    return instance;
  }

  public void addAmount(int amount, MetroStation metroStation) {
    CollectionSummaryResponseDto collectionSummaryResponseDto;
    if (metroStationToCollectionSummaryResponseDto.containsKey(metroStation)) {
      collectionSummaryResponseDto = metroStationToCollectionSummaryResponseDto.get(metroStation);
      int totalCharge = collectionSummaryResponseDto.getTotalCharge();
      collectionSummaryResponseDto.setTotalCharge(totalCharge + amount);
    } else {
      collectionSummaryResponseDto = new CollectionSummaryResponseDto();
      collectionSummaryResponseDto.setMetroStation(metroStation);
      collectionSummaryResponseDto.setTotalCharge(amount);
    }

    metroStationToCollectionSummaryResponseDto.put(metroStation, collectionSummaryResponseDto);
  }

  public void addDiscount(int discount, MetroStation metroStation) {
    CollectionSummaryResponseDto collectionSummaryResponseDto;
    if (metroStationToCollectionSummaryResponseDto.containsKey(metroStation)) {
      collectionSummaryResponseDto = metroStationToCollectionSummaryResponseDto.get(metroStation);
      int totalDiscount = collectionSummaryResponseDto.getTotalDiscount();
      collectionSummaryResponseDto.setTotalDiscount(totalDiscount + discount);
    } else {
      collectionSummaryResponseDto = new CollectionSummaryResponseDto();
      collectionSummaryResponseDto.setMetroStation(metroStation);
      collectionSummaryResponseDto.setTotalDiscount(discount);
    }

    metroStationToCollectionSummaryResponseDto.put(metroStation, collectionSummaryResponseDto);
  }

  public void addPassengerSummary(PassengerType passengerType, MetroStation fromStation) {

    Map<PassengerType, PassengerSummaryResponseDto> passengerTypeToPassengerSummary;
    PassengerSummaryResponseDto passengerSummaryResponseDto;

    if (metroStationToPassengerType.containsKey(fromStation)) {
      passengerTypeToPassengerSummary = metroStationToPassengerType.get(fromStation);
      passengerSummaryResponseDto = passengerTypeToPassengerSummary.getOrDefault(passengerType,
          new PassengerSummaryResponseDto());
    } else {
      passengerTypeToPassengerSummary = new HashMap<>();
      passengerSummaryResponseDto = new PassengerSummaryResponseDto();
    }

    passengerSummaryResponseDto.setPassengerType(passengerType);
    passengerSummaryResponseDto.setJourneyCount(
        passengerSummaryResponseDto.getJourneyCount() + 1
    );
    passengerSummaryResponseDto.setMetroStation(fromStation);

    passengerTypeToPassengerSummary.put(passengerType, passengerSummaryResponseDto);
    metroStationToPassengerType.put(fromStation, passengerTypeToPassengerSummary);
  }

  public List<CollectionSummaryResponseDto> getCollectionSummary() {
    return new ArrayList<>(metroStationToCollectionSummaryResponseDto.values());
  }

  public Map<MetroStation, Map<PassengerType, PassengerSummaryResponseDto>> getPassengerSummary() {
    return metroStationToPassengerType;
  }
}

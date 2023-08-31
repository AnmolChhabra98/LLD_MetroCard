package com.example.geektrust.services.impl;

import com.example.geektrust.constants.Common;
import com.example.geektrust.dtos.response.CollectionSummaryResponseDto;
import com.example.geektrust.dtos.response.PassengerSummaryResponseDto;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.repositories.MetroStationRepository;
import com.example.geektrust.services.MetroStationService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetroStationServiceImpl implements MetroStationService {

  private final MetroStationRepository metroStationRepository = MetroStationRepository.getInstance();

  @Override
  public List<CollectionSummaryResponseDto> getCollectionSummary() {
    return metroStationRepository.getCollectionSummary();
  }

  @Override
  public List<PassengerSummaryResponseDto> getPassengerSummary() {
    Map<MetroStation, Map<PassengerType, PassengerSummaryResponseDto>> passengerSummary = metroStationRepository.getPassengerSummary();
    return passengerSummary.values().stream()
        .flatMap(innerMap -> innerMap.values().stream())
        .collect(Collectors.toList());
  }

  @Override
  public int calculateServiceFees(int amount) {
    return (int) Math.ceil((amount * Common.SERVICE_FEES));
  }

  @Override
  public void addServiceFeesToTotalCharge(int amount, MetroStation fromStation) {
    int serviceFees = calculateServiceFees(amount);
    addAmountToTotalCharge(serviceFees, fromStation);
  }

  @Override
  public void addAmountToTotalCharge(int amount, MetroStation fromStation) {
    metroStationRepository.addAmount(amount, fromStation);
  }

  @Override
  public void addDiscountToTotalDiscount(int discount, MetroStation fromStation) {
    metroStationRepository.addDiscount(discount, fromStation);
  }


  @Override
  public void updateJourneyCountToPassengerType(PassengerType passengerType,
      MetroStation fromStation) {
    metroStationRepository.addPassengerSummary(passengerType, fromStation);
  }
}

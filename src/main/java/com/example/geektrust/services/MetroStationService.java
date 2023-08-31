package com.example.geektrust.services;

import com.example.geektrust.dtos.response.CollectionSummaryResponseDto;
import com.example.geektrust.dtos.response.PassengerSummaryResponseDto;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;
import java.util.List;

public interface MetroStationService {

  List<CollectionSummaryResponseDto> getCollectionSummary();

  List<PassengerSummaryResponseDto> getPassengerSummary();

  int calculateServiceFees(int amount);

  void addServiceFeesToTotalCharge(int amount, MetroStation fromStation);

  void addAmountToTotalCharge(int amount, MetroStation fromStation);

  void addDiscountToTotalDiscount(int discount, MetroStation fromStation);

  void updateJourneyCountToPassengerType(PassengerType passengerType, MetroStation fromStation);
}

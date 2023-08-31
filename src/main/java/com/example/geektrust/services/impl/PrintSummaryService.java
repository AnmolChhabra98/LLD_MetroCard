package com.example.geektrust.services.impl;

import com.example.geektrust.dtos.response.CollectionSummaryResponseDto;
import com.example.geektrust.dtos.response.PassengerSummaryResponseDto;
import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.services.MetroStationService;
import com.example.geektrust.services.SummaryService;
import java.util.Comparator;
import java.util.List;

public class PrintSummaryService implements SummaryService {

  private MetroStationService metroStationService = new MetroStationServiceImpl();

  @Override
  public void summary() {
    List<CollectionSummaryResponseDto> collectionSummary = metroStationService.getCollectionSummary();
    List<PassengerSummaryResponseDto> passengerTypeSummary = metroStationService.getPassengerSummary();

    passengerTypeSummary.sort(
        Comparator.comparing(
                PassengerSummaryResponseDto::getJourneyCount
            ).reversed()
            .thenComparing(dto -> dto.getPassengerType().name())
    );

    for (MetroStation station : MetroStation.values()) {
      System.out.print("TOTAL_COLLECTION" + " " + station.name() + " ");

      for (CollectionSummaryResponseDto collectionSummaryResponseDto : collectionSummary) {
        if (collectionSummaryResponseDto.getMetroStation().equals(station)) {
          System.out.print(collectionSummaryResponseDto.getTotalCharge() + " " +
              collectionSummaryResponseDto.getTotalDiscount());
        }
      }

      System.out.println();
      System.out.println("PASSENGER_TYPE_SUMMARY");

      for (PassengerSummaryResponseDto passengerSummaryResponseDto : passengerTypeSummary) {
        if (passengerSummaryResponseDto.getMetroStation().equals(station)) {
          System.out.println(passengerSummaryResponseDto.getPassengerType() + " "
              + passengerSummaryResponseDto.getJourneyCount());
        }
      }
    }
  }
}

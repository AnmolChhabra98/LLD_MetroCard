package com.example.geektrust.dtos.response;

import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;

public class PassengerSummaryResponseDto {

  private int journeyCount;
  private PassengerType passengerType;
  private MetroStation metroStation;

  public int getJourneyCount() {
    return journeyCount;
  }

  public void setJourneyCount(int journeyCount) {
    this.journeyCount = journeyCount;
  }

  public PassengerType getPassengerType() {
    return passengerType;
  }

  public void setPassengerType(PassengerType passengerType) {
    this.passengerType = passengerType;
  }

  public MetroStation getMetroStation() {
    return metroStation;
  }

  public void setMetroStation(MetroStation metroStation) {
    this.metroStation = metroStation;
  }

  @Override
  public String toString() {
    return "PassengerSummaryResponseDto{" +
        "journeyCount=" + journeyCount +
        ", passengerType=" + passengerType +
        ", metroStation=" + metroStation +
        '}';
  }
}

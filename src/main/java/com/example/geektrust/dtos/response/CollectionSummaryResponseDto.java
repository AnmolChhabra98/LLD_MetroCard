package com.example.geektrust.dtos.response;

import com.example.geektrust.enums.MetroStation;

public class CollectionSummaryResponseDto {

  private MetroStation metroStation;
  private int totalCharge;
  private int totalDiscount;

  public MetroStation getMetroStation() {
    return metroStation;
  }

  public void setMetroStation(MetroStation metroStation) {
    this.metroStation = metroStation;
  }

  public int getTotalCharge() {
    return totalCharge;
  }

  public void setTotalCharge(int totalCharge) {
    this.totalCharge = totalCharge;
  }

  public int getTotalDiscount() {
    return totalDiscount;
  }

  public void setTotalDiscount(int totalDiscount) {
    this.totalDiscount = totalDiscount;
  }

  @Override
  public String toString() {
    return "CollectionSummaryResponseDto{" +
        "metroStation=" + metroStation +
        ", totalCharge=" + totalCharge +
        ", totalDiscount=" + totalDiscount +
        '}';
  }
}

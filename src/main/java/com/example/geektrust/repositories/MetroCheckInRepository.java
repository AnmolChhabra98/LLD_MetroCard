package com.example.geektrust.repositories;

import com.example.geektrust.models.MetroCheckIn;
import java.util.ArrayList;
import java.util.List;

public class MetroCheckInRepository {

  private List<MetroCheckIn> metroCheckIns = new ArrayList<>();

  public void save(MetroCheckIn metroCheckIn) {
    metroCheckIns.add(metroCheckIn);
  }
}

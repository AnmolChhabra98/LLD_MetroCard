package com.example.geektrust.commandparser;

import com.example.geektrust.enums.MetroStation;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.MetroCheckInService;
import com.example.geektrust.services.SummaryService;
import com.example.geektrust.services.impl.MetroCardServiceImpl;
import com.example.geektrust.services.impl.MetroCheckInServiceImpl;
import com.example.geektrust.services.impl.PrintSummaryService;
import com.example.geektrust.services.strategies.discount.ReturnJourneyDiscountStrategy;
import com.example.geektrust.services.strategies.recharge.AutoRechargeStrategy;

public class CommandParser {

  private MetroCardService metroCardService = new MetroCardServiceImpl(new AutoRechargeStrategy(),
      new ReturnJourneyDiscountStrategy());
  private MetroCheckInService metroCheckInService = new MetroCheckInServiceImpl();

  private SummaryService summaryService = new PrintSummaryService();

  public void processCommand(String input) {
    String commandDelimeter = " ";
    String[] parts = input.split(commandDelimeter);
    String action = parts[0];

    switch (action) {
      case "BALANCE": {
        String metroCardNumber = parts[1];
        int balance = Integer.parseInt(parts[2]);

        metroCardService.addCard(metroCardNumber, balance);
        break;
      }
      case "CHECK_IN": {
        String metroCardNumber = parts[1];
        PassengerType passengerType = PassengerType.valueOf(parts[2]);
        MetroStation metroStation = MetroStation.valueOf(parts[3]);

        metroCheckInService.doCheckIn(metroCardNumber, passengerType, metroStation);
        break;
      }
      case "PRINT_SUMMARY": {
        summaryService.summary();
        break;
      }
      case "EXIT":
      default:
        break;
    }
  }
}

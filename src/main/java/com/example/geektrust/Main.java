package com.example.geektrust;

import com.example.geektrust.commandparser.CommandParser;
import com.example.geektrust.exceptions.BadRequestException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    CommandParser commandParser = new CommandParser();
//        Sample code to read from file passed as command line argument
    try {
      // the file to be opened for reading
      String filePath = args[0];
      if (filePath == null || filePath.isEmpty()) {
        throw new BadRequestException("File Path not passed!");
      }

      FileInputStream fis = new FileInputStream(filePath);
      Scanner sc = new Scanner(fis); // file to be scanned
      // returns true if there is another line to read
      while (sc.hasNextLine()) {
        //Add your code here to process input commands
        String inputCommand = sc.nextLine();
        commandParser.processCommand(inputCommand);
      }
      sc.close(); // closes the scanner
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw e;
    }
  }
}

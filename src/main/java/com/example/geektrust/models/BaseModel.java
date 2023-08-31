package com.example.geektrust.models;

import java.util.UUID;

public class BaseModel {

  private final String id;

  public BaseModel() {
    id = UUID.randomUUID().toString();
  }

  public BaseModel(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}

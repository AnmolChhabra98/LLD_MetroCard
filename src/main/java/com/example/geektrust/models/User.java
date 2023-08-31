package com.example.geektrust.models;

import com.example.geektrust.enums.UserType;

public abstract class User extends BaseModel {

  private UserType userType;

  public User(UserType userType) {
    this.userType = userType;
  }
}

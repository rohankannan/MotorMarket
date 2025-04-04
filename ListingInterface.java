package com.cs180proj.app;

public interface ListingInterface {

  // getters
  String getPhotoURL();
  String getCarType();
  String getColor();
  String getSeller();
  int getMileage();
  int getAccidents();
  double getPrice();
  boolean isManual();
  
  // setters
  void setPhotoURL(String url);
  void setCarType(String carType);
  void setColor(String color);
  void setSeller(String seller);
  void setMileage(int mileage);
  void setAccidents(int accidents);
  void setPrice(double price);
  void setManual(boolean manual);


}

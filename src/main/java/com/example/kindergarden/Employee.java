package com.example.kindergarden;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Employee {
    private String firstName;
    private String lastName;
    private String roadName;
    private int roadNumber;
    private String city;
    private int postalCode;
    private int phoneNumber;
    private String email;

    public Employee(){

    }

    public Employee(String firstName, String lastName) {
        //String[] employee = line.split(";");
        this.firstName = firstName;
        this.lastName = lastName;
    }

  public Employee(String firstName, String lastName, String roadName, int roadNumber, String city, int postalCode, int phoneNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.roadName = roadName;
    this.roadNumber = roadNumber;
    this.city = city;
    this.postalCode = postalCode;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public void addEmployeeToList(){
    System.out.println("kom ind");
  }

  public String getRoadName() {
    return roadName;
  }

  public void setRoadName(String roadName) {
    this.roadName = roadName;
  }

  public int getRoadNumber() {
    return roadNumber;
  }

  public void setRoadNumber(int roadNumber) {
    this.roadNumber = roadNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(int postalCode) {
    this.postalCode = postalCode;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

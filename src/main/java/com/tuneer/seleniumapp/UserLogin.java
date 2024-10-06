/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tuneer.seleniumapp;

/**
 *
 * @author itteam2
 */
public class UserLogin {
    
    private String year;
    private String merchantName;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String cellPhone;
    private String streetAddress;
    private String aptBuilding;
    private String city;
    private String zip;
    
    private boolean status=false;
    private int columnCount=0;

    public UserLogin(){
        
    }
    
    public UserLogin(String year, String merchantName, String email, String password, String lastName, String firstName, String cellPhone, String streetAddress, String aptBuilding, String city, String zip) {
        this.year = year;
        this.merchantName = merchantName;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.cellPhone = cellPhone;
        this.streetAddress = streetAddress;
        this.aptBuilding = aptBuilding;
        this.city = city;
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getAptBuilding() {
        return aptBuilding;
    }

    public void setAptBuilding(String aptBuilding) {
        this.aptBuilding = aptBuilding;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
    
    
    
    
    
}

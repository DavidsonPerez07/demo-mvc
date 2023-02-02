package com.cesardiaz.mvc.model.entity;

public class Client {
    private String code;
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private String mobilePhone;
    
    public Client(String code, String dni, String firstName, String lastName) {
        this.code = code;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(String code, String dni, String firstName, String lastName, String address, String mobilePhone) {
        this(code, dni, firstName, lastName);
        this.address = address;
        this.mobilePhone = mobilePhone;
    }

    public String getCode() {
        return code;
    }

    public String getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return dni + " | " + firstName + " " + lastName + " | " + address + " | " + mobilePhone;
    }
}
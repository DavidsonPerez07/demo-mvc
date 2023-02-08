package com.cesardiaz.mvc.model.entity;

public class Car {
    private Integer garage;
    private String plate;
    private String model;
    private String color;
    private String brand;

    public Car(Integer garage, String plate, String model, String color, String brand) {
        this.garage = garage;
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.brand = brand;
    }

    public Integer getGarage() {
        return garage;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return garage + " | " + plate + " | " + model + " | " + color + " | "
                + brand;
    }

    
}

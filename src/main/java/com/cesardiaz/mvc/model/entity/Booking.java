package com.cesardiaz.mvc.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String id;
    private String agency;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Double total;

    private Client client;
    private List<BookingCar> cars;

    public Booking(String id, String agency, LocalDate startDate, LocalDate finishDate, Client client) {
        this.id = id;
        this.agency = agency;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.client = client;
        this.total = 0d;
        this.cars = new ArrayList<>();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getAgency() {
        return agency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public Double getTotal() {
        return total;
    }

    public Client getClient() {
        return client;
    }

    public List<BookingCar> getCars() {
        return cars;
    }

    public void addCar(Car car, Float litersGas) {
        var bookingCar = new BookingCar(this, car, litersGas);
        cars.add(bookingCar);
    }

    public void removeCar(Car car) {
        var bookingCar = cars.stream()
                .filter(bc -> bc.getCar().equals(car))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El carro buscado no esta en la reserva"));

        cars.remove(bookingCar);
    }

    @Override
    public String toString() {
        return id + " | " + client.getDni() + " | " + client.getFirstName() + " | " + client.getLastName() + " | "
                + agency + " | " + startDate + " | " + finishDate + " | " + total + " | " + cars;
    }

    public String getId() {
        return id;
    }

}

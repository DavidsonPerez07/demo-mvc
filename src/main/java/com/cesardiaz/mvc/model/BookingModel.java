package com.cesardiaz.mvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cesardiaz.mvc.model.entity.Booking;
import com.cesardiaz.mvc.model.entity.Car;
import com.cesardiaz.mvc.model.entity.Client;

public class BookingModel {

    private List<Client> clients;
    private List<Car> cars;
    private List<Booking> bookings;

    public BookingModel() {
        clients = new ArrayList<>();
        cars = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public String getNewCode() {
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 20);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}

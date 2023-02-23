package com.cesardiaz.mvc.controller;

import java.util.List;

import com.cesardiaz.mvc.model.BookingModel;
import com.cesardiaz.mvc.model.ConnectionDB;
import com.cesardiaz.mvc.model.entity.Booking;
import com.cesardiaz.mvc.model.entity.Car;
import com.cesardiaz.mvc.model.entity.Client;
import com.cesardiaz.mvc.view.BookingView;

public class BookingController {

    private BookingView view;
    private BookingModel model;

    public BookingController(BookingView view, BookingModel model) {
        this.view = view;
        this.model = model;

        this.view.setController(this);
    }

    public void initApp(){
        ConnectionDB.createTables();
        view.showMenu();
    }

    public List<Client> getClients() {
        return model.getClients();
    }

    public void addClient(String dni, String fistName, String lastName, String address, String phone) {
        var code = model.getNewCode();
        model.addClient(new Client(code, dni, fistName, lastName, address, phone));
    }

    public boolean searchClient(String dni) {
        return model.verifyExistClient(dni);
    }

    public void modifyClient(String dni, String mobilePhone, String address) {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El dni no debe estar vacío");
        } 
        var client = model.getClient(dni);
        if (client == null) {
            throw new RuntimeException("No existe un cliente con el dni dado");
        }

        client.setMobilePhone(mobilePhone);
        client.setAddress(address);

        model.modifyClient(dni, client);
    }

    public void deleteClient(String dni) {
        model.deleteClient(dni);
    }

    public String showClient(String dni) {
        return model.getClient(dni).toString();
    }

    public List<Car> getCars() {
        return model.getCars();
    }

    public void addCar(Integer garage, String plate, String model, String color, String brand) {
        var car = new Car(garage, plate, model, color, brand);
        this.model.addCar(car);
    }
    
    public boolean searchCar(String plate) {
        return model.verifyExistCar(plate);
    }

    public void modifyCar(String plate, String color) {
        if (plate == null || plate.isEmpty()) {
            throw new IllegalArgumentException("La placa no debe estar vacía");
        } 
        var car = model.getCar(plate);
        if (car == null) {
            throw new RuntimeException("No existe un carro con la placa dada");
        }

        car.setColor(color);

        model.modifyCar(plate, car);
    }

    public void deleteCar(String plate) {
        model.deleteCar(plate);
    }

    public String showCar(String plate) {
        return model.getCar(plate).toString();
    }

    public List<Booking> getBookings() {
        return model.getBookings();
    }

    public void addBooking(String id, String agency, String startDate, String finishDate, Client client) {
        model.addBooking(new Booking(id, agency, startDate, finishDate, client));
    }

    public boolean searchBooking(String id) {
        return model.verifyExistBooking(id);
    }

    public Client retClForBooking(String dni) {
        var client = model.getClient(dni);
        return client;
    }

    public void modifyBooking(String id, String startDate, String finishDate) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El id no debe estar vacío");
        } 
        var booking = model.getBooking(id);
        if (booking == null) {
            throw new RuntimeException("No existe una reserva con el id dado");
        }

        booking.setStartDate(startDate);
        booking.setFinishDate(finishDate);

        model.modifyBooking(id, booking);
    }

    public void deleteBooking(String id) {
        model.deleteBooking(id);
    }

    public String showBooking(String id) {
        return model.getBooking(id).toString();
    }

    public void closeApp() {
        ConnectionDB.closeConnection();
        view.stopMenu();
    }
}

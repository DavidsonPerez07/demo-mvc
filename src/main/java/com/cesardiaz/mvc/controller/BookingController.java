package com.cesardiaz.mvc.controller;

import java.time.LocalDate;
import java.util.List;

import com.cesardiaz.mvc.model.BookingModel;
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
        view.showMenu();
    }

    public List<Client> getClients() {
        return model.getClients();
    }

    public void addClient(String dni, String fistName, String lastName, String address, String phone) {
        var code = model.getNewCode();
        model.addClient(new Client(code, dni, fistName, lastName, address, phone));
    }

    public boolean verifyExistClient(String dni) {
        var clients = model.getClients();
        var exists = false;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getDni().equals(dni)) {
                exists = true;
            }
            else {
                exists = false;
            }
        }
        return exists;
    }

    public void modifyClient(String dni, String mobilePhone, String address) {
        var clients = model.getClients();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getDni().equals(dni)) {
                clients.get(i).setAddress(address);
                clients.get(i).setMobilePhone(mobilePhone);
            }
        }
    }

    public void deleteClient(String dni) {
        var clients = model.getClients();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getDni().equals(dni)) {
                clients.remove(i);
            }
        }
    }

    public void showClient(String dni) {
        var clients = model.getClients();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getDni().equals(dni)) {
                clients.get(i).toString();
            }
        }
    }

    public List<Car> getCars() {
        return model.getCars();
    }

    public void addCar(Integer garage, String plate, String model, String color, String brand) {
        //model.addCar(new Car(garage, plate, model, color, brand)); --- Linea no funciona
    }
    
    public boolean verifyExistCar(String plate) {
        var cars = model.getCars();
        var exists = false;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlate().equals(plate)) {
                exists = true;
            }
            else {
                exists = false;
            }
        }
        return exists;
    }
    
    public void modifyCar(String plate, String color) {
        var cars = model.getCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlate().equals(plate)) {
                cars.get(i).setColor(color);
            }
        }
    }

    public void deleteCar(String plate) {
        var cars = model.getCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlate().equals(plate)) {
                cars.remove(i);
            }
        }
    }

    public void showCar(String plate) {
        var cars = model.getCars();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getPlate().equals(plate)) {
                cars.get(i).toString();
            }
        }
    }

    public List<Booking> getBookings() {
        return model.getBookings();
    }

    public void addBooking(String agency, LocalDate startDate, LocalDate finishDate, Client client) {
        model.addBooking(new Booking(agency, startDate, finishDate, client));
    }

    public boolean verifyExistBooking(Client client) {
        var bookings = model.getBookings();
        var exists = false;
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getClient().equals(client)) {
                exists = true;
            }
            else {
                exists = false;
            }
        }
        return exists;
    }

    public void modifyBooking(Client client, LocalDate startDate, LocalDate finishDate) {
        var bookings = model.getBookings();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getClient().equals(client)) {
                bookings.get(i).setStartDate(startDate);
                bookings.get(i).setFinishDate(finishDate);
            }
        }
    }

    public void deleteBooking(Client client) {
        var bookings = model.getBookings();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getClient().equals(client)) {
                bookings.remove(i);
            }
        }
    }

    public void showBooking(Client client) {
        var bookings = model.getBookings();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getClient().equals(client)) {
                bookings.get(i).toString();
            }
        }
    }
}

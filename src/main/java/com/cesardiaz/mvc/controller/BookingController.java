package com.cesardiaz.mvc.controller;

import java.time.LocalDate;
import java.util.List;

import javax.print.DocFlavor.STRING;

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

    public boolean verifyExistClient(String dni) {
        return model.verifyExistClient(dni);
    }

    public void modifyClient(String dni, String mobilePhone, String address) {
        model.modifyClient(dni, mobilePhone, address);
    }

    public void deleteClient(String dni) {
        model.deleteClient(dni);
    }

    public void showClient(String dni) {
        
    }

    public List<Car> getCars() {
        return model.getCars();
    }

    public void addCar(Integer garage, String plate, String model, String color, String brand) {
        //model.addCar(new Car(garage, plate, model, color, brand)); --- Linea no funciona
    }
    
    public boolean verifyExistCar(String plate) {
        return model.verifyExistCar(plate);
    }

    public void modifyCar(String plate, String color) {
        model.modifyCar(plate, color);
    }

    public void deleteCar(String plate) {
        model.deleteCar(plate);
    }

    public void showCar(String plate) {
        
    }

    /*public List<Booking> getBookings() {
        return model.getBookings();
    }*/

    public void addBooking(String id, String agency, LocalDate startDate, LocalDate finishDate, Client client) {
        model.addBooking(new Booking(id, agency, startDate, finishDate, client));
    }

    /*public boolean verifyExistBooking(String id) {
        return model.verifyExistBooking(id);
    }*/

    public void modifyBooking(String id, LocalDate startDate, LocalDate finishDate) {
        model.modifyBooking(id, startDate, finishDate);
    }

    public void deleteBooking(String id) {
        model.deleteBooking(id);
    }

    public void showBooking(String id) {
        
    }
}

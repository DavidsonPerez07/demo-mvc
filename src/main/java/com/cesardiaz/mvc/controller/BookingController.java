package com.cesardiaz.mvc.controller;

import java.util.List;

import com.cesardiaz.mvc.model.BookingModel;
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

    
    
}

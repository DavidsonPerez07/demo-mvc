package com.cesardiaz.mvc;

import com.cesardiaz.mvc.controller.BookingController;
import com.cesardiaz.mvc.model.BookingModel;
import com.cesardiaz.mvc.view.BookingView;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        var controller = new BookingController(new BookingView(), new BookingModel());
        controller.initApp();
    }
}

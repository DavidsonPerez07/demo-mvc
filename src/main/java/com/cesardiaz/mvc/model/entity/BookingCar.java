package com.cesardiaz.mvc.model.entity;

public class BookingCar {
    private Float litersGas;

    private DeliveryState state;
    private Booking booking;
    private Car car;

    public BookingCar(Booking booking, Car car, Float litersGas) {
        this.litersGas = litersGas;
        this.booking = booking;
        this.car = car;
        this.state = DeliveryState.RESERVED;
    }

    public void setLitersGas(Float litersGas) {
        this.litersGas = litersGas;
    }

    public Float getLitersGas() {
        return litersGas;
    }

    public DeliveryState getState() {
        return state;
    }

    public Booking getBooking() {
        return booking;
    }

    public Car getCar() {
        return car;
    }
}

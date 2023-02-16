package com.cesardiaz.mvc.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cesardiaz.mvc.model.entity.Booking;
import com.cesardiaz.mvc.model.entity.Car;
import com.cesardiaz.mvc.model.entity.Client;

public class BookingModel {

    public List<Client> getClients() {
        var clients = new ArrayList<Client>();

        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();
            var rset = stmt.executeQuery("""
                    SELECT client_code, dni, first_name, last_name, address, phone_number
                    FROM customer
                    """);
            while (rset.next()) {
                var client = new Client(rset.getString("client_code"), 
                        rset.getString("dni"), 
                        rset.getString("first_name"), 
                        rset.getString("last_name"), 
                        rset.getString("address"), 
                        rset.getString("phone_number"));

                clients.add(client);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error getClients(): " + e.getMessage());
        }

        return clients;
    }

    public List<Car> getCars() {
        var cars = new ArrayList<Car>();

        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();
            var rset = stmt.executeQuery("""
                    SELECT garage, plate, model, color, brand
                    FROM car
                    """);
            while (rset.next()) {
                var car = new Car(rset.getInt("garage"), 
                rset.getString("plate"), 
                rset.getString("model"), 
                rset.getString("color"), 
                rset.getString("brand"));

                cars.add(car);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error getCars(): " + e.getMessage());
        }

        return cars;
    }

    /*public List<Booking> getBookins() {
        var bookings = new ArrayList<Booking>();

        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();
            var rset = stmt.executeQuery("""
                    SELECT booking_id, agency, start_date, finish_date, total, customer, car
                    FROM booking
                    """);
            while (rset.next()) {
                var booking = new Booking(rset.getString("booking_id"), 
                rset.getString("agency"), 
                rset.getDate("start_date"), 
                rset.getDate("finish_date"), 
                rset.getDouble("total"),
                rset.getObject("customer"),
                rset.getArray("car"));

                bookings.add(booking);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error getBookings(): " + e.getMessage());
        }

        return bookings;
    }*/

    public void addClient(Client client) {
        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();

            // var sql1= """
            //         INSERT INTO customer (client_code, dni, first_name, last_name, address, phone_number)
            //         VALUES (?, ?, ?, ?, ?, ?)
            //         """;

            var sql = new StringBuilder("INSERT INTO customer (client_code, dni, first_name, last_name, address, phone_number) VALUES (")
            .append(client.getCode() == null ? "NULL" : "'"+client.getCode()+"'").append(",")
            .append(client.getDni() == null ? "NULL" : "'"+client.getDni()+"'").append(",")
            .append(client.getFirstName() == null ? "NULL" : "'"+client.getFirstName()+"'").append(",")
            .append(client.getLastName() == null ? "NULL" : "'"+client.getLastName()+"'").append(",")
            .append(client.getAddress() == null ? "NULL" : "'"+client.getAddress()+"'").append(",")
            .append(client.getMobilePhone() == null ? "NULL" : "'"+client.getMobilePhone()+"'").append(")")
            .toString();
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error addClient(): " + e.getMessage());
        }
    }

    public void addCar(Car car) {
        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();

            var sql = new StringBuilder("INSERT INTO car (garage, plate, model, color, brand) VALUES (")
            .append(car.getGarage() == null ? "NULL" : "'"+car.getGarage()+"'").append(",")
            .append(car.getPlate() == null ? "NULL" : "'"+car.getPlate()+"'").append(",")
            .append(car.getModel() == null ? "NULL" : "'"+car.getModel()+"'").append(",")
            .append(car.getColor() == null ? "NULL" : "'"+car.getColor()+"'").append(",")
            .append(car.getBrand() == null ? "NULL" : "'"+car.getBrand()+"'").append(",")
            .toString();
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error addCar(): " + e.getMessage());
        }
    }

    public void addBooking(Booking booking) {
        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();

            var sql = new StringBuilder("INSERT INTO booking (booking_id, agency, start_date, finish_date, total, customer, car) VALUES (")
            .append(booking.getId() == null ? "NULL" : "'"+booking.getId()+"'").append(",")
            .append(booking.getAgency() == null ? "NULL" : "'"+booking.getAgency()+"'").append(",")
            .append(booking.getStartDate() == null ? "NULL" : "'"+booking.getStartDate()+"'").append(",")
            .append(booking.getFinishDate() == null ? "NULL" : "'"+booking.getFinishDate()+"'").append(",")
            .append(booking.getTotal() == null ? "NULL" : "'"+booking.getTotal()+"'").append(",")
            .append(booking.getClient() == null ? "NULL" : "'"+booking.getClient()+"'").append(",")
            .append(booking.getCars() == null ? "NULL" : "'"+booking.getCars()+"'").append(",")
            .toString();
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error addBooking(): " + e.getMessage());
        }
    }

    public void deleteClient(String dni) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "DELETE FROM customer WHERE dni = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, dni);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error deleteClient(): " + e.getMessage());
        } 
    }

    public void deleteCar(String plate) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "DELETE FROM car WHERE plate = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, plate);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error deleteCar(): " + e.getMessage());
        } 
    }

    public void deleteBooking(String id) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "DELETE FROM booking WHERE booking_id = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setNString(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error deleteBooking(): " + e.getMessage());
        } 
    }

    public void modifyClient(String dni, String newMobilePhone, String newAddress) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "UPDATE customer SET phone_number = '" + newMobilePhone + "', address = '" + newAddress + "' WHERE dni = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, dni);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyClient(): " + e.getMessage());
        }
    }

    public void modifyCar(String plate, String color) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "UPDATE car SET color = '" + color + "' WHERE plate = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, plate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyCar(): " + e.getMessage());
        }
    }

    public void modifyBooking(String id, LocalDate newStartDate, LocalDate newFinishDate) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = "UPDATE booking SET start_date = '" + newStartDate + "', finish_date = '" + newFinishDate + "' WHERE booking_id = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyBooking(): " + e.getMessage());
        }
    }

    public boolean verifyExistClient(String dni) {
        var clients = getClients();
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

    public boolean verifyExistCar(String plate) {
        var cars = getCars();
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

    /*public boolean verifyExistBooking(Client client) {
        var bookings = getBookings();
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
    }*/

    public String getNewCode() {
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 20);
    }
}

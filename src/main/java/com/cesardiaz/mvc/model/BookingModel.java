package com.cesardiaz.mvc.model;

import java.sql.Date;
import java.sql.SQLException;
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

    public List<Booking> getBookings() {
        var bookings = new ArrayList<Booking>();

        try {
            var conn = ConnectionDB.getConnection();
            var stmt = conn.createStatement();
            var rset = stmt.executeQuery("""
                    SELECT booking_id, agency, start_date, finish_date, total, customer
                    FROM booking
                    """);
            while (rset.next()) {

                var booking = new Booking(rset.getString("booking_id"),
                        rset.getString("agency"),
                        rset.getDate("start_date").toLocalDate(),
                        rset.getDate("finish_date").toLocalDate(),
                        getClient(rset.getString("customer")));

                bookings.add(booking);
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error getBookings(): " + e.getMessage());
        }

        return bookings;
    }

    public void addClient(Client client) {
        try {
            var conn = ConnectionDB.getConnection();

            var sql = """
                    INSERT INTO customer (client_code, dni, first_name, last_name, address, phone_number)
                    VALUES (?, ?, ?, ?, ?, ?)
                    """;

            var stmt = conn.prepareStatement(sql);

            stmt.setString(1, client.getCode());
            stmt.setString(2, client.getDni());
            stmt.setString(3, client.getFirstName());
            stmt.setString(4, client.getLastName());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getMobilePhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error addClient(): " + e.getMessage());
        }
    }

    public void addCar(Car car) {
        try {
            var conn = ConnectionDB.getConnection();

            var sql = """
                    INSERT INTO car (garage, plate, model, color, brand)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            var stmt = conn.prepareStatement(sql);

            stmt.setInt(1, car.getGarage());
            stmt.setString(2, car.getPlate());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getColor());
            stmt.setString(5, car.getBrand());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error addCar(): " + e.getMessage());
        }
    }

    public void addBooking(Booking booking) {
        try {
            var conn = ConnectionDB.getConnection();

            var sql = """
                    INSERT INTO booking (booking_id, agency, start_date, finish_date, total, customer)
                    VALUES (?, ?, ?, ?, ?, ?)
                    """;

            var stmt = conn.prepareStatement(sql);

            stmt.setString(1, booking.getId());
            stmt.setString(2, booking.getAgency());
            stmt.setDate(3, Date.valueOf(booking.getStartDate()));
            stmt.setDate(4, Date.valueOf(booking.getFinishDate()));
            stmt.setDouble(5, booking.getTotal());
            stmt.setString(6, booking.getClient().getDni());
            stmt.executeUpdate();
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

    public void modifyClient(String dni, Client client) {
        try {
            var conn = ConnectionDB.getConnection();
            var query = """
                    UPDATE customer
                    SET phone_number = ?,
                        address = ?
                        WHERE dni = ?
                        """;
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, client.getMobilePhone());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, dni);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyClient(): " + e.getMessage());
        }
    }

    public void modifyCar(String plate, Car car) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = """
                    UPDATE car
                    SET color = ?
                    WHERE plate = ?
                    """;
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, car.getColor());
            stmt.setString(2, plate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyCar(): " + e.getMessage());
        }
    }

    public void modifyBooking(String id, Booking booking) {
        try {
            var conn = ConnectionDB.getConnection();
            String query = """
                    UPDATE booking
                    SET start_date = ?,
                    finish_date = ?
                    WHERE booking_id = ?
                    """;
            var stmt = conn.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(booking.getStartDate()));
            stmt.setDate(2, Date.valueOf(booking.getFinishDate()));
            stmt.setString(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error modifyBooking(): " + e.getMessage());
        }
    }

    public Client getClient(String dni) {
        Client client = null;
        try {
            var conn = ConnectionDB.getConnection();
            var sql = """
                    SELECT client_code, dni, first_name, last_name, address, phone_number
                    FROM customer
                    WHERE dni = ?
                    """;
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, dni);
            var rset = stmt.executeQuery();
            if (rset.next()) {
                client = new Client(rset.getString("client_code"),
                        rset.getString("dni"),
                        rset.getString("first_name"),
                        rset.getString("last_name"),
                        rset.getString("address"),
                        rset.getString("phone_number"));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error showClient(): " + e.getMessage());
        }
        return client;
    }

    public Car getCar(String plate) {
        Car car = null;
        try {
            var conn = ConnectionDB.getConnection();
            var sql = """
                    SELECT garage, plate, model, color, brand
                    FROM car
                    WHERE plate = ?
                    """;
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, plate);
            var rset = stmt.executeQuery();
            if (rset.next()) {
                car = new Car(rset.getInt("garage"),
                        rset.getString("plate"),
                        rset.getString("model"),
                        rset.getString("color"),
                        rset.getString("brand"));
            }
            rset.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error showCar(): " + e.getMessage());
        }
        return car;
    }

    public Booking getBooking(String id) {
        Booking booking = null;
        try {
            var conn = ConnectionDB.getConnection();
            var stmtC = conn.createStatement();
            var sql = """
                    SELECT booking_id, agency, start_date, finish_date, total, customer
                    FROM booking
                    WHERE booking_id = ?
                    """;
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            var rset = stmt.executeQuery();
            if (rset.next()) {
                booking = new Booking(rset.getString("booking_id"),
                        rset.getString("agency"),
                        rset.getDate("start_date").toLocalDate(),
                        rset.getDate("finish_date").toLocalDate(),
                        getClient(rset.getString("customer")));
                        
                // TODO: Faltan los carros de la reserva
            }
            rset.close();
            stmt.close();
            stmtC.close();
        } catch (SQLException e) {
            System.err.println("Error getBooking(): " + e.getMessage());
        }
        return booking;
    }

    public boolean verifyExistClient(String dni) {
        return getClient(dni) != null;
    }

    public boolean verifyExistCar(String plate) {
        return getCar(plate) != null;
    }

    public boolean verifyExistBooking(String id) {
        return getBooking(id) != null;
    }

    public String getNewCode() {
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 8);
    }
}

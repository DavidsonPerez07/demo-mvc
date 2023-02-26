package com.cesardiaz.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    //private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/car_booking_dv";
    private static final String DATABASE_URL_H2 = "jdbc:h2:./car_booking";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Trosquielpro07";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DATABASE_URL_H2  , DATABASE_USER, DATABASE_PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("No puedo conectarme a la base de datos: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("No puedo cerrar la conexión a la base de datos: " + e.getMessage());
        }
    }

    public static void createTables() {
        try {
            var conn = getConnection();
            var stmt = conn.createStatement();
            stmt.execute("""
                    create table customer (
                        client_code varchar (100) not null,
                        dni varchar (100) not null,
                        first_name varchar (100) not null,
                        last_name varchar (100) not null,
                        address varchar (100),
                        phone_number varchar (100),
                        primary key (dni)
                    )
                    """);
            stmt.execute("""
                    create table car (
                        garage int not null,
                        plate varchar (100) not null,
                        model varchar (100) not null,
                        color varchar (100),
                        brand varchar (100) not null,
                        primary key (plate)
                    )
                    """);
            stmt.execute("""
                    create table booking (
                        booking_id varchar (100) not null,
                        agency varchar (100) not null,
                        start_date varchar (100) not null,
                        finish_date varchar (100) not null,
                        total double not null,
                        customer varchar (100) not null,
                        primary key (booking_id),
                        constraint booking_ibfk_1 foreign key (customer) references customer (dni)
                    )
                    """);
            stmt.execute("""
                    create table booking_car (
                        liter_gas float,
                        delivery_state enum ('RESERVED', 'DELIVERED') not null default 'RESERVED',
                        booking varchar (100) not null,
                        car varchar (100) not null,
                        constraint booking_car_ibfk_1 foreign key (booking) references booking (booking_id),
                        constraint booking_car_ibfk_2 foreign key (car) references car (plate)
                    )
                    """);

            stmt.close();
            closeConnection();
        } catch (SQLException e) {
            System.err.println("Error en SQL: " + e.getMessage());
        }
    }
}

package com.cesardiaz.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/car_booking";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Trosquielpro07";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
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
                    );
                    
                    create table car (
                    garage int (5) not null,
                    plate varchar (100) not null,
                    model varchar (100) not null,
                    color varchar (100),
                    brand varchar (100) not null,
                    primary key (plate)
                    );
                    
                    create table booking (
                    booking_id varchar (100) not null,
                    agency varchar (100) not null,
                    start_date date not null,
                    finish_date date not null,
                    total double not null,
                    customer varchar (100) not null,
                    car varchar (100) not null,
                    primary key (booking_id),
                    key customer (customer),
                    key car (car),
                        constraint booking_ibfk_1 foreign key (customer) references customer (dni),
                        constraint booking_ibfk_2 foreign key (car) references car (plate)
                        );
                    
                    create table booking_car (
                    liter_gas float,
                        delivery_state enum ('RESERVED', 'DELIVERED') not null default 'RESERVED',
                    booking varchar (100) not null,
                    car varchar (100) not null,
                    key booking (booking),
                    key car (car),
                        constraint booking_car_ibfk_1 foreign key (booking) references booking (booking_id),
                        constraint booking_car_ibfk_2 foreign key (car) references car (plate)
                    )
                    """);
            stmt.close();
            closeConnection();
        } catch (SQLException e) {
            
        }
    }
}

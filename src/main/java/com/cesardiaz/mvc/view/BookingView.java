package com.cesardiaz.mvc.view;

import java.util.Scanner;

import com.cesardiaz.mvc.controller.BookingController;

public class BookingView {

    private BookingController controller;
    private Scanner input;

    public void setController(BookingController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void showMenu() {
        var show = true;
        while (show) {
            try {
                System.out.println("""
                        Bienvenido a Reservas de Carros
                        =======================================
                        1. Gestion de Clientes
                        2. Gestion de Carros
                        3. Gestion de Reservas
                        0. Salir de aplicación
                        """);
                System.out.print("Ingrese su opcion: ");
                var opcion = input.nextLine();
                switch (opcion.toLowerCase().strip()) {
                    case "1":
                        showClientMenu();
                        break;
                    case "2":
                        showCarMenu();
                        break;
                    case "3":
                        showBookingMenu();
                        break;
                    case "0", "":
                        show = false;
                        break;
                    default:
                        throw new IllegalArgumentException("Opcion no válida");
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
                waitEnter();
            }
        }
        System.out.println("Hasta pronto.");
    }

    private void showBookingMenu() {
        System.out.println("""
                Gestion de Reservas
                =======================================
                1. Listar reservas
                2. Agregar reserva
                3. Modificar reserva
                4. Eliminar reserva
                5. Consultar reserva
                0. Volver al menú
                """);
        waitEnter();
    }

    private void showCarMenu() {
        System.out.println("""
                Gestion de Carros
                =======================================
                1. Listar carros
                2. Agregar carro
                3. Modificar carro
                4. Eliminar carro
                5. Consultar carro
                0. Volver al menú
                """);
        waitEnter();
    }

    private void showClientMenu() {
        var show = true;
        while (show) {
            System.out.println("""
                    Gestion de Clientes
                    =======================================
                    1. Listar clientes
                    2. Agregar cliente
                    3. Modificar cliente
                    4. Eliminar cliente
                    5. Consultar cliente
                    0. Volver al menú
                    """);
            System.out.print("Ingrese su opcion: ");
            var opcion = input.nextLine();
            switch (opcion.toLowerCase().strip()) {
                case "1":
                    getClients();
                    break;
                case "2":
                    newClient();
                    break;
                case "3":
                    throw new RuntimeException("Aun falta implementar este metodo");
                // break;
                case "4":
                    throw new RuntimeException("Aun falta implementar este metodo");
                // break;
                case "5":
                    throw new RuntimeException("Aun falta implementar este metodo");
                // break;
                case "0", "":
                    show = false;
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no válida");
            }
            waitEnter();
        }
    }

    private void newClient() {
        System.out.print("""
                Agregar un nuevo cliente
                ===========================================
                """);
        System.out.print("Ingrese el DNI: ");
        var dni = input.nextLine();
        System.out.print("Ingrese nombres: ");
        var fistName = input.nextLine();
        System.out.print("Ingrese apellidos: ");
        var lastName = input.nextLine();
        System.out.print("Ingrese direccion (opcional): ");
        var address = input.nextLine();
        System.out.print("Ingrese número telefónico (opcional): ");
        var phone = input.nextLine();

        controller.addClient(dni, fistName, lastName, address, phone);

        System.out.println("Cliente guardado exitosamente!");

    }

    private void getClients() {
        var clients = controller.getClients();
        System.out.print("""
                Listado de Clientes
                ===========================================
                """);
        clients.forEach(System.out::println);
    }

    private void waitEnter() {
        System.out.print("\nPresione ENTER para continuar.");
        input.nextLine();
    }

}

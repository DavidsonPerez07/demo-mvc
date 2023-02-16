package com.cesardiaz.mvc.view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;
import javax.sql.rowset.serial.SerialRef;

import com.cesardiaz.mvc.controller.BookingController;
import com.cesardiaz.mvc.model.entity.Client;

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
        var show = true;
        while (show) {
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
            System.out.println("Ingrese su opción: ");
            var opcion = input.nextLine();
            switch (opcion.toLowerCase().strip()) {
                case "1":
                    //getBookings();
                    break;
                case "2":
                    newBooking();
                    break;
                case "3":
                    //modifyBooking();
                    break;
                case "4":
                    //deleteBooking();
                    break;
                case "5":
                    //showBooking();
                    break;
                case "0":
                    show = false;
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no válida");
                }
                waitEnter();
        }
    }

    private void showCarMenu() {
        var show = true;
        while (show) {
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
            System.out.println("Ingrese su opción: ");
            var opcion = input.nextLine();
            switch (opcion.toLowerCase().strip()) {
                case "1":
                    getCars();
                    break;
                case "2":
                    newCar();
                    break;
                case "3":
                    modifyCar();
                    break;
                case "4":
                    deleteCar();
                    break;
                case "5":
                    showCar();
                    break;
                case "0":
                    show = false;
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no válida");
                }
                waitEnter();
        }
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
                    modifyClient();
                    break;
                case "4":
                    deleteClient();
                    break;
                case "5":
                    showClient();
                    break;
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
                Listado de clientes
                ===========================================
                """);
        clients.forEach(System.out::println);
    }

    private void modifyClient() {
        System.out.println("Ingrese el DNI del cliente a modificar: ");
        var dni = input.nextLine();
        var exists = controller.verifyExistClient(dni);
        if (exists == true) {
            System.out.println("Ingrese nuevo número de celular: ");
            var mobilePhone = input.nextLine();
            System.out.println("Ingrese nueva dirección: ");
            var address = input.nextLine();
            controller.modifyClient(dni, mobilePhone, address);
            System.out.println("Cliente modificado exitosamente!");
        }
        else {
            System.out.println("No existen clientes registrados con este DNI");
        }
    }

    private void deleteClient() { 
        System.out.println("Ingrese el DNI del cliente a eliminar: ");
        var dni = input.nextLine();
        var exists = controller.verifyExistClient(dni);
        if (exists == true) {
            controller.deleteClient(dni);
            System.out.println("Cliente eliminado existosamente!");
        }
        else {
            System.out.println("No existen clientes registrados con este DNI");
        }
    }

    private void showClient() {
        System.out.println("Ingrese el DNI del cliente a consultar: ");
        var dni = input.nextLine();
        var exists = controller.verifyExistClient(dni);
        if (exists == true) {
            controller.showClient(dni);
        }
        else {
            System.out.println("No existe ningún cliente registado con este DNI");
        }
    }

    /*private void showBooking() {
        System.out.println("Ingrese el id de la reserva a consultar: ");
        var id = input.nextLine();
        var exists = controller.verifyExistBooking(id);
        if (exists == true) {
            controller.showBooking(id);
        }
        else {
            System.out.println("No existe ninguna reserva con este id");
        }
    }*/

    private void newCar() {
        System.out.println("""
                Agregar un nuevo carro
                ============================================
                """);
        System.out.println("Ingrese el garage: ");
        var garage = input.nextLine();
        System.out.println("Ingrese la placa: ");
        var plate = input.nextLine();
        System.out.println("Ingrese el modelo: ");
        var model = input.nextLine();
        System.out.println("Ingrese el color (opcional): ");
        var color = input.nextLine();
        System.out.println("Ingrese la marca: ");
        var brand = input.nextLine();

        //controller.addCar(garage, plate, model, color, brand); --- Linea no funciona
        System.out.println("Carro guardado exitosamente!");
    }

    private void getCars() {
        var cars = controller.getCars();
        System.out.println("""
                Listado de carros
                ===========================================
                """);
        cars.forEach(System.out::println);
    }

    private void modifyCar() {
        System.out.println("Ingrese la placa del carro a modificar: ");
        var plate = input.nextLine();
        var exists = controller.verifyExistCar(plate);
        if (exists == true) {
            System.out.println("Ingrese un nuevo color: ");
            var color = input.nextLine();
            controller.modifyCar(plate, color);
            System.out.println("Carro modificado exitosamente!");
        }
        else {
            System.out.println("No existen carros registrados con esta placa");
        }
    }

    private void deleteCar() {
        System.out.println("Ingrese la placa del carro a eliminar: ");
        var plate = input.nextLine();
        var exists = controller.verifyExistCar(plate);
        if (exists == true) {
            controller.deleteCar(plate);
            System.out.println("Carro eliminado exitosamente!");
        }
        else {
            System.out.println("No existen carros registrados con esta placa");
        }
    }

    private void showCar() {
        System.out.println("Ingrese la placa del carro a consultar: ");
        var plate = input.nextLine();
        var exists = controller.verifyExistCar(plate);
        if (exists == true) {
            controller.showCar(plate);
        }
        else {
            System.out.println("No existe ningún carro registrado con esta placa");
        }
    }

    private void waitEnter() {
        System.out.print("\nPresione ENTER para continuar.");
        input.nextLine();
    }

    private void newBooking() {
        System.out.println("""
                Agregar una nueva reserva
                ============================================
                """);
        System.out.println("Ingrese el id: ");
        var id = input.nextLine();
        System.out.println("Ingrese la agencia: ");
        var agency = input.nextLine();
        System.out.println("Ingrese la fecha de inicio de la reserva: ");
        var sDate = input.nextLine();
        var startDate = LocalDate.parse(sDate);
        System.out.println("Ingrese la fecha de finalización de la reserva");
        var fDate = input.nextLine();
        var finishDate = LocalDate.parse(fDate);
        System.out.println("Ingrese el cliente: ");

        var client = inClient();
        
        controller.addBooking(id, agency, startDate, finishDate, client);
    }

    /*private void getBookings() {
        var bookings = controller.getBookings();
        System.out.println("""
                Listado de reservas
                ===========================================
                """);
        bookings.forEach(System.out::println);
    }*/

    /*private void modifyBooking() {
        System.out.println("Ingrese el id de la reserva a modificar: ");
        var id = input.nextLine();
        var exists = controller.verifyExistBooking(id);
        if (exists == true) {
            System.out.println("Ingrese una nueva fecha de inicio: ");
            var sDate = input.nextLine();
            var startDate = LocalDate.parse(sDate);
            System.out.println("Ingrese una nueva fecha de finalización: ");
            var fDate = input.nextLine();
            var finishDate = LocalDate.parse(fDate);
            controller.modifyBooking(id, startDate, finishDate);
            System.out.println("Reserva modificada exitosamente!");
        }
        else {
            System.out.println("No existen reservas registradas con este id");
        }
    }*/

    public Client inClient() {
        System.out.print("Ingrese el DNI: ");
        var dni = input.nextLine();
        System.out.print("Ingrese nombres: ");
        var firstName = input.nextLine();
        System.out.print("Ingrese apellidos: ");
        var lastName = input.nextLine();
        System.out.print("Ingrese direccion (opcional): ");
        var address = input.nextLine();
        System.out.print("Ingrese número telefónico (opcional): ");
        var phone = input.nextLine();

        return (new Client(null, dni, firstName, lastName, address, phone));
    }

    /*private void deleteBooking() {
        System.out.println("Ingrese el id de la reserva a eliminar: ");
        var id = input.nextLine();
        var exists = controller.verifyExistBooking(id);
        if (exists == true) {
            controller.deleteBooking(id);
            System.out.println("Reserva eliminada exitosamente!");
        }
        else {
            System.out.println("No existen reservas registradas con este id");
        }
    }*/
}

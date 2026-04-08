package views;

import models.Landlord;
import models.Rental;
import models.Tenant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TenantView {
    private Scanner sc = new Scanner(System.in);

    public Tenant createTenant(){
        Scanner sc = new Scanner(System.in);
        IO.println("CREAR NUEVO INQUILINO");

        String dni = null;
        do{
            IO.print("Dni: ");
            dni = sc.nextLine();
        } while(!dni.matches("^[0-9]{8}[A-Z]$"));

        LocalDate birthDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        do {
            IO.print("Fecha nacimiento (yyyy-MM-dd): ");
            String input = sc.nextLine().trim();
            try {
                birthDate = LocalDate.parse(input, formatter);
                // Evitar fechas futuras
                if (birthDate.isAfter(LocalDate.now())) {
                    IO.println("Fecha no valida");
                    birthDate = null;
                }
            } catch (DateTimeParseException e) {
                IO.println("Formato de fecha no valido");
            }
        } while (birthDate == null);

        String name = null;
        do{
            IO.print("Nombre: ");
            name = sc.nextLine();
        } while(name.isEmpty() || name.length()>10);


        String last_name = null;
        do{
            IO.print("Apellido: ");
            last_name = sc.nextLine();
        } while(last_name.isEmpty() || last_name.length()>10);

        return new Tenant(dni, birthDate, name, last_name);
    }

    public static void details(Tenant tenant) {
        IO.println(
                "\n--- DATOS DEL INQUILINO ---" +
                "\nID: " + tenant.getId() +
                "\nDNI: " + tenant.getDni() +
                "\nNombre: " + tenant.getName() +
                "\nApellido: " + tenant.getLast_name() +
                "\nFecha Nacimiento: " + tenant.getBirth_date()
        );
        IO.println("\nHistorial de alquileres: ");
        List<Rental> rentals = tenant.rentals();
        if(rentals.isEmpty()){
            IO.println("No hay historial de alquileres");
        } else {
            for (Rental rental : rentals) {
                IO.println(rental);
            }
        }
    }

    public Map<Integer,Tenant> detailMenu(Tenant tenant){
        IO.println("\n\nOpciones:");
        IO.println("1) Actualizar \n2) Eliminar");
        int option =  sc.nextInt();
        Map<Integer,Tenant> mapa = new HashMap<>();
        mapa.put(option,tenant);
        return mapa;
    }

    public String findbyDni() {
        Scanner sc = new Scanner(System.in);
        IO.print("Introduce el DNI del inquilino: ");
        return sc.nextLine().trim();
    }

    public void index(List<Tenant> tenants){
        for(Tenant t : tenants){
            IO.println(t);
        }
    }

    public int menu(){
        IO.println("1) Ver listado \n2) Crear inquilino \n3) Buscar inquilino");
        return sc.nextInt();
    }

    public Tenant edit(Tenant tenant){
        IO.println("CREAR NUEVO INQUILINO");

        String dni = null;
        do{
            IO.print("Dni: ");
            dni = sc.nextLine();
        } while(!dni.matches("^[0-9]{8}[A-Z]$"));

        LocalDate birthDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        do {
            IO.print("Fecha nacimiento (yyyy-MM-dd): ");
            String input = sc.nextLine().trim();
            try {
                birthDate = LocalDate.parse(input, formatter);
                // Evitar fechas futuras
                if (birthDate.isAfter(LocalDate.now())) {
                    IO.println("Fecha no valida");
                    birthDate = null;
                }
            } catch (DateTimeParseException e) {
                IO.println("Formato de fecha no valido");
            }
        } while (birthDate == null);

        String name = null;
        do{
            IO.print("Nombre: ");
            name = sc.nextLine();
        } while(name.isEmpty() || name.length()>10);


        String last_name = null;
        do{
            IO.print("Apellido: ");
            last_name = sc.nextLine();
        } while(last_name.isEmpty() || last_name.length()>10);

        tenant.setDni(dni);
        tenant.setBirth_date(birthDate);
        tenant.setName(name);
        tenant.setLast_name(last_name);
        return tenant;
    }


}

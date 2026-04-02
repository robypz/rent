package views;

import models.Landlord;
import models.Tenant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
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

    public static void details(Tenant t) {
        IO.println("\n--- DATOS DEL INQUILINO ---");
        if (t != null && t.getDni() != null) {
            IO.println("ID: " + t.getId());
            IO.println("DNI: " + t.getDni());
            IO.println("Nombre: " + t.getName());
            IO.println("Apellido: " + t.getLast_name());
            IO.println("Fecha Nacimiento: " + t.getBirth_date());
        } else {
            IO.println("Inquilino no encontrado o datos vacíos.");
        }
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


}

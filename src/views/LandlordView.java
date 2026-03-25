package views;

import models.Landlord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class LandlordView {
    private Scanner sc = new Scanner(System.in);
    //crear las clases vista para todas las clases existentes, landlord, property, rental y tenant
    public Landlord createLandlord() {
        Scanner sc = new Scanner(System.in);
        IO.println("CREAR NUEVO PROPIETARIO");

        String name;
        do{
            IO.print("Nombre: ");
            name = sc.nextLine();
        } while(name.isEmpty() || name.length()>10);


        String last_name;
        do{
            IO.print("Apellido: ");
            last_name = sc.nextLine();
        } while(last_name.isEmpty() || last_name.length()>10);

        String dni;
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

        Landlord landlord = new Landlord();
        landlord.setName(name);
        landlord.setBirth_date(birthDate);
        landlord.setDni(dni);
        landlord.setLast_name(last_name);
        return landlord;
    }

    public void details(Landlord landlord){
        IO.println(
                "\nID: " + landlord.getId() +
                "\nNombre: " + landlord.getName() +
                 "\nApellido: " + landlord.getLast_name() +
                 "\nDNI: " + landlord.getDni()+
                 "\nFecha de nacimiento: " + landlord.getBirth_date()
        );
    }

    public String findByDni(){
        String dni;
        do{
            IO.print("Busqueda por dni: ");
            dni = sc.nextLine();
        } while(!dni.matches("^[0-9]{8}[A-Z]$"));
        return dni;
    }

}

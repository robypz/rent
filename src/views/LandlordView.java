package views;

import models.Landlord;
import models.Property;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LandlordView {
    //crear las clases vista para todas las clases existentes, landlord, property, rental y tenant
    private Scanner sc = new Scanner(System.in);
    public Landlord createLandlord() {

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
        landlord.setLast_name(last_name);
        landlord.setDni(dni);
        landlord.setBirth_date(birthDate);
        return landlord;
    }

    public void details(Landlord landlord) {
        IO.println(
                "\n--- DATOS DEL PROPIETARIO ---" +
                "\nID: " + landlord.getId() +
                "\nNombre: " + landlord.getName() +
                "\nApellido: " + landlord.getLast_name() +
                "\nDni: " + landlord.getDni() +
                "\nFecha nacimiento: " + landlord.getBirth_date()
        );
        IO.println("\nPropiedades:\n");
        for(Property p: landlord.propierties()){
            IO.println(p);
        }
        this.detailMenu(landlord);
    }

    public Map<Integer,Landlord> detailMenu(Landlord landlord) {
        IO.println("\n\nOpciones:");
        IO.println("1) Actualizar \n2) Eliminar");
        int option =  sc.nextInt();
        Map<Integer,Landlord> mapa = new HashMap<>();
        mapa.put(option,landlord);
        return mapa;
    }

    public String findbyDni(){
        String dni;
        do{
            IO.print("Búsqueda por dni: ");
            dni = sc.nextLine();

        } while(!dni.matches("^[0-9]{8}[A-Z]$"));
        return dni;
    }

    public int menu(){
        IO.println("1) Ver listado \n2) Crear propietario \n3) Buscar propietario");
        return sc.nextInt();
    }

    public void index(List<Landlord> landlords){
        for(Landlord l: landlords){
            IO.println(l);
        }
    }

    public Landlord edit(Landlord landlord){
        IO.println("CREAR NUEVO PROPIETARIO");
        String name;
        do{
            IO.println("Nombre: " + landlord.getName());
            name = sc.nextLine();
        } while(name.isEmpty() || name.length()>10);


        String last_name;
        do{
            IO.println("Apellido: " + landlord.getLast_name());
            last_name = sc.nextLine();
        } while(last_name.isEmpty() || last_name.length()>10);

        String dni;
        do{
            IO.println("Dni: " + landlord.getDni());
            dni = sc.nextLine();
        } while(!dni.matches("^[0-9]{8}[A-Z]$"));

        LocalDate birthDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        do {
            IO.println("Fecha nacimiento (yyyy-MM-dd): " + landlord.getBirth_date());
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

        landlord.setName(name);
        landlord.setLast_name(last_name);
        landlord.setDni(dni);
        landlord.setBirth_date(birthDate);
        return landlord;
    }

}

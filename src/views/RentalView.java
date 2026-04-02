package views;

import models.Landlord;
import models.Rental;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class RentalView {
    private Scanner sc = new Scanner(System.in);
    public Rental createRental(){
        Scanner sc = new Scanner(System.in);
        IO.println("CREAR NUEVO ALQUILER");

        int tenant_id = 0;
        while(tenant_id<=0){
            try{
                IO.print("Identificador de alquiler: ");
                tenant_id = Integer.parseInt(sc.nextLine());
                if(tenant_id<=0){
                    IO.println("Error, identificador negativo o nulo ");
                }
            } catch(NumberFormatException e){
                IO.println("Error, introduzca número entero válido");
            }

        }

        LocalDate start_date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        do {
            IO.print("Fecha inicio (yyyy-MM-dd): ");
            String input = sc.nextLine().trim();
            try {
                start_date = LocalDate.parse(input, formatter);
                // Evitar fechas futuras
                if (start_date.isAfter(LocalDate.now())) {
                    IO.println("Fecha no valida");
                    start_date= null;
                }
            } catch (DateTimeParseException e) {
                IO.println("Formato de fecha no valido");
            }
        } while (start_date == null);

        LocalDate end_date = null;
        do {
            IO.print("Fecha final (yyyy-MM-dd): ");
            String input = sc.nextLine().trim();
            try {
                LocalDate auxDate = LocalDate.parse(input, formatter);
                // Evitar fechas futuras
                if (auxDate.isBefore(start_date)) {
                    IO.println("Fecha no valida");
                    end_date = null;
                } else{
                    end_date = auxDate;
                }
            } catch (DateTimeParseException e) {
                IO.println("Formato de fecha no valido");
            }
        } while (end_date == null);

        int property_id = 0;
        while(property_id<=0){
            try{
                IO.print("Identificador de propiedad: ");
                property_id = Integer.parseInt(sc.nextLine());
                if(property_id<=0){
                    IO.println("Error, identificador negativo o nulo");
                }
            } catch(NumberFormatException e){
                IO.println("Error, introduzca número entero válido");
            }
        }
        return new Rental(tenant_id, start_date, end_date,  property_id);
    }

    public int searchById() {
        Scanner sc = new Scanner(System.in);
        int id = -1;
        while (id <= 0) {
            try {
                IO.print("Introduce el ID del alquiler: ");
                id = Integer.parseInt(sc.nextLine());
                if (id <= 0) IO.println("Error, el ID debe ser mayor a 0");
            } catch (NumberFormatException e) {
                IO.println("Error, introduce un número entero válido.");
            }
        }
        return id;
    }

    public static void details(Rental r) {
        IO.println("\n--- DETALLES DEL ALQUILER ---");
        IO.println("ID Alquiler: " + r.getId());
        IO.println("ID Inquilino: " + r.getTenant_id());
        IO.println("Fecha Inicio: " + r.getStart_date());
        IO.println("Fecha Fin: " + r.getEnd_date());
        IO.println("ID Propiedad: " + r.getProperty_id());
    }

    public int menu(){
        IO.println("1) Ver listado \n2) Crear contrato \n3) Buscar contrato");
        return sc.nextInt();
    }

    public void index(List<Rental> rentals){
        for(Rental r: rentals){
            IO.println(r);
        }
    }
}

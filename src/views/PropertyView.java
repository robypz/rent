package views;

import models.Property;

import java.util.Scanner;

public class PropertyView {
    public Property createProperty() {
        Scanner sc = new Scanner(System.in);
        IO.println("CREAR NUEVA PROPIEDAD");

        int id = 0;
        while(id<=0){
            try{
                IO.print("Identificador: ");
                id = Integer.parseInt(sc.nextLine());
                if(id<=0){
                    IO.println("Error, id negativo o nulo");
                }
            } catch(NumberFormatException e){
                IO.println("Error, introduzca número entero válido");
            }
        }

        String address;
        do{
            IO.print("Direccion: ");
            address = sc.nextLine();
        } while (address.trim().isEmpty());

        Double price = 0.0;
        while(price<=0.0){
            IO.print("Precio: ");
            try{
                price = Double.parseDouble(sc.nextLine());
                if(price <= 0){
                    IO.println("Error, precio negativo o nulo");
                }
            } catch (NumberFormatException e){
                IO.println("Error, introduzca número decimal válido");
            }
        }


        int floors = 0;
        while(floors<=0){
            IO.print("Número de pisos: ");
            try {
                floors = Integer.parseInt(sc.nextLine());
                if(floors<=0){
                    IO.println("Error, precio negativo o nulo");
                }
            } catch (NumberFormatException e){
                IO.println("Error, introduzca número entero válido");
            }
        }
        return new Property(address, price, floors, id);
    }

    public int findbyId() {
        Scanner sc = new Scanner(System.in);
        int id = -1;
        while (id < 0) {
            try {
                IO.print("Introduce el ID de la propiedad: ");
                id = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                IO.println("Error, número no válido.");
            }
        }
        return id;
    }

    public void details(Property p) {
        IO.println("\n--- DETALLES DE LA PROPIEDAD ---");
        IO.println("ID: " + p.getId());
        IO.println("Dirección: " + p.getAddress());
        IO.println("Precio: " + p.getPrice() + " €");
        IO.println("Pisos: " + p.getFloors());
        IO.println("ID Arrendador: " + p.getLandlord_id());
    }
}

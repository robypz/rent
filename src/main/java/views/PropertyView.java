package views;

import models.Landlord;
import models.Property;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class PropertyView extends JPanel {
    private Scanner sc = new Scanner(System.in);

    public JPanel getPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Propiedades
        panel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Propiedades"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10,10,10,10);
        JButton botonCrearPropiedad = new JButton("Crear");
        botonCrearPropiedad.addActionListener(l -> {
            createPropertyFrame();
        });
        panel.add(botonCrearPropiedad,gbc);
        return panel;
    }

    public Property createProperty() {
        Scanner sc = new Scanner(System.in);
        IO.println("CREAR NUEVA PROPIEDAD");

        int landlord_id = 0;
        while(landlord_id<=0){
            try{
                IO.print("Identificador del landlord: ");
                landlord_id = Integer.parseInt(sc.nextLine());
                if(landlord_id<=0){
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
        return new Property(address, price, floors, landlord_id);
    }

    public void createPropertyFrame(){
        JFrame frame = new JFrame("Create property");
        JPanel panel = new JPanel(new GridLayout(5,2,3,3));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel id = new JLabel("ID");
        JTextField id_field = new JTextField();
        panel.add(id);
        panel.add(id_field);

        JLabel address = new JLabel("Dirección");
        JTextField address_field = new JTextField();
        panel.add(address);
        panel.add(address_field);


        JLabel price = new JLabel("Precio");
        JTextField price_field = new JTextField();
        panel.add(price);
        panel.add(price_field);

        JLabel floors = new JLabel("Pisos");
        JTextField floors_field = new JTextField();
        panel.add(floors);
        panel.add(floors_field);

        JButton crearButton = new JButton("Guardar");
        panel.add(crearButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(l->{
            frame.dispose();
        });
        panel.add(cancelButton);

        frame.add(panel);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

    public void index(List<Property> properties){
        for(Property p: properties){
            IO.println(p);
        }
    }

    public int menu(){
        IO.println("1) Ver listado \n2) Crear propiedad \n3) Buscar propidad");
        return sc.nextInt();
    }
}

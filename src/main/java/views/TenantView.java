package views;

import models.Landlord;
import models.Rental;
import models.Tenant;

import javax.swing.*;
import java.awt.*;
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

    public JPanel getPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Inquilinos
        panel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Inquilinos"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10,10,10,10);
        JButton botonCrearPropiedad = new JButton("Crear");
        botonCrearPropiedad.addActionListener(l -> {
            createTenantFrame();
        });
        panel.add(botonCrearPropiedad,gbc);
        return panel;
    }

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

    public void createTenantFrame(){
        JFrame frame = new JFrame("Create tenant");
        JPanel panel = new JPanel(new GridLayout(6,2,3,3));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel id = new JLabel("ID");
        JTextField id_field = new JTextField();
        panel.add(id);
        panel.add(id_field);

        JLabel dni = new JLabel("Dni");
        JTextField dni_field = new JTextField();
        panel.add(dni);
        panel.add(dni_field);

        JLabel birth_date = new JLabel("Fecha de nacimiento");
        JTextField birth_date_field = new JTextField();
        panel.add(birth_date);
        panel.add(birth_date_field);

        JLabel name = new JLabel("Nombre");
        JTextField name_field = new JTextField();
        panel.add(name);
        panel.add(name_field);

        JLabel last_name = new JLabel("Apellidos");
        JTextField last_name_field = new JTextField();
        panel.add(last_name);
        panel.add(last_name_field);

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

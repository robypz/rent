package views;

import controllers.LandlordController;
import models.Landlord;
import models.LandlordTableModel;
import models.Property;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.List;

public class LandlordView extends JPanel {

    private Scanner sc = new Scanner(System.in);

    public JPanel getPanel() throws SQLException {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Propietarios
        panel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Propietarios"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10,10,10,10);
        List<Object> datos = new ArrayList<Object>();
        for(Landlord l: Landlord.index()){
            datos.add(new Object[]{l.getName(),l.getLast_name(), l.getDni(), l.getBirth_date().toString()});
        }
        Object[] columnas = { "ID", "Nombre", "Apellidos", "DNI", "Fecha nacimiento" };
        JTable table = new JTable((Object[][]) datos.toArray(),columnas);

        panel.add(table,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10,10,10,10);
        JButton botonCrearPropietario = new JButton("Crear");
        botonCrearPropietario.addActionListener(l -> {
            createLandlordFrame();
        });
        panel.add(botonCrearPropietario,gbc);
        return panel;
    }

    //crear las clases vista para todas las clases existentes, landlord, property, rental y tenant
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

    public void createLandlordFrame(){
        JFrame frame = new JFrame("Create landlord");
        JPanel panel = new JPanel(new GridLayout(5,2,3,3));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel name = new JLabel("Nombre");
        JTextField name_field = new JTextField();
        panel.add(name);
        panel.add(name_field);

        JLabel last_name = new JLabel("Apellido");
        JTextField last_name_field = new JTextField();
        panel.add(last_name);
        panel.add(last_name_field);


        JLabel dni = new JLabel("Dni");
        JTextField dni_field = new JTextField();
        panel.add(dni);
        panel.add(dni_field);

        JLabel birth_date = new JLabel("Fecha nacimiento");
        JTextField birth_date_field = new JTextField();
        panel.add(birth_date);
        panel.add(birth_date_field);

        JButton crearButton = new JButton("Guardar");
        crearButton.addActionListener(l -> {
            LandlordController.store(new Landlord(
                    name_field.getText(),
                    last_name_field.getText(),
                    dni_field.getText(),
                    LocalDate.parse(birth_date_field.getText())
            ));
        });
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

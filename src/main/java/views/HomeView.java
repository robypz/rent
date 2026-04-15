package views;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class HomeView extends JFrame {
    private JPanel propietarios;
    private JPanel inquilinos;
    private JPanel contratos;
    private JPanel propiedades;
    private JTabbedPane tabsPrincipal;

    public HomeView() {

        setTitle("Administración de alquileres");

        tabsPrincipal = new JTabbedPane();

        tabsPrincipal.add("Propietarios", new LandlordView().getPanel());

        //Inquilinos
        inquilinos = new JPanel();
        inquilinos.add(new JLabel("Inquilino"));
        tabsPrincipal.add("Inquilinos", inquilinos);

        //Contratos
        contratos = new JPanel();
        contratos.add(new JLabel("Contrato"));
        tabsPrincipal.add("Contratos", contratos);

        //Propiedades
        propiedades = new JPanel();
        propiedades.add(new JLabel("Propiedades"));
        tabsPrincipal.add("Propiedades", propiedades);

        add(tabsPrincipal);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package views;

import controllers.HomeController;
import controllers.LandlordController;
import views.landlord.LandlordMainView;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeView extends JFrame {
    private LandlordMainView landlordMainView;
    private JPanel inquilinos;
    private JPanel contratos;
    private JPanel propiedades;
    private JTabbedPane tabsPrincipal;

    public HomeView(LandlordMainView landlordMainView) {

        setTitle("Administración de alquileres");

        tabsPrincipal = new JTabbedPane();
        tabsPrincipal.add("Propietarios",landlordMainView.getPanel());

        //Inquilinos
        inquilinos = new JPanel();
        inquilinos.add(new JLabel("Inquilino"));
        tabsPrincipal.add("Inquilinos", new TenantView().getPanel());

        //Contratos
        contratos = new JPanel();
        contratos.add(new JLabel("Contrato"));
        tabsPrincipal.add("Contratos", new RentalView().getPanel());

        //Propiedades
        propiedades = new JPanel();
        propiedades.add(new JLabel("Propiedades"));
        tabsPrincipal.add("Propiedades", new PropertyView().getPanel());

        add(tabsPrincipal);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

package views.landlord;

import javax.swing.*;
import java.awt.*;

public class LandlordMainView {
    private JPanel panel;
    private GridBagConstraints gbc;
    private LandlordTableVeiw landlordTableVeiw;

    public JPanel getPanel() {
        return panel;
    }


    public LandlordMainView(LandlordTableVeiw landlordTableVeiw) {
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Propietarios"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        // MUY IMPORTANTE: permitir que el componente crezca
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel.add(landlordTableVeiw.getTable(),gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        JButton botonCrearPropietario = new JButton("Crear");
        botonCrearPropietario.addActionListener(l -> {
        });
        panel.add(botonCrearPropietario, gbc);
    }
}

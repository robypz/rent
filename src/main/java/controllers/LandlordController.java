package controllers;

import models.Landlord;
import views.landlord.LandlordTableModel;
import views.landlord.LandlordMainView;
import views.landlord.LandlordTableVeiw;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandlordController {
    private LandlordTableModel  landlordTableModel;
    private LandlordTableVeiw landlordTableVeiw;
    private LandlordMainView landlordMainView;
    public LandlordController (){
        landlordTableModel = new LandlordTableModel(new java.util.ArrayList<>());
        landlordTableVeiw = new LandlordTableVeiw(landlordTableModel);
        landlordMainView = new LandlordMainView(landlordTableVeiw);

        loadDataAsync();

        landlordTableVeiw.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount()==2){
                    IO.println(landlordTableVeiw.getTable().getValueAt(landlordTableVeiw.getTable().getSelectedRow(), 0));
                }
            }
        });
    }
    private void loadDataAsync() {
        javax.swing.SwingWorker<java.util.List<Landlord>, Void> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected java.util.List<Landlord> doInBackground() throws Exception {
                return new repository.LandlordDAO().findAll();
            }

            @Override
            protected void done() {
                try {
                    java.util.List<Landlord> landlords = get();
                    landlordTableModel.setLandlords(landlords);
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al cargar propietarios: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    public LandlordMainView getLandlordMainView(){
        return landlordMainView;
    }


}

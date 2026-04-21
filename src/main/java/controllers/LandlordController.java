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
        landlordTableModel = new LandlordTableModel(Landlord.index());
        landlordTableVeiw = new LandlordTableVeiw(landlordTableModel);
        landlordMainView = new LandlordMainView(landlordTableVeiw);

        landlordTableVeiw.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount()==2){
                    IO.println(landlordTableVeiw.getTable().getValueAt(landlordTableVeiw.getTable().getSelectedRow(), 0));
                }
            }
        });
    }
    public LandlordMainView getLandlordMainView(){
        return landlordMainView;
    }


}

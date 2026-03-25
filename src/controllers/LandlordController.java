package controllers;

import models.Landlord;
import views.LandlordView;

import java.sql.SQLException;

public class LandlordController {
    private LandlordView landlordView = new LandlordView();

    public void store (){
        Landlord landlord = landlordView.createLandlord();

        //Comprobamos que el dni no existe en la base de datos
        if (Landlord.dniExist(landlord.getDni())){
            IO.println("Este DNI ya está registrado");
        }else{
            try {
                landlord.store();
                landlordView.details(landlord);
            } catch (SQLException e) {
                IO.println(e.getMessage());
            }

        }

    }

    public void searchByDni(){
        String dni= landlordView.findByDni();
        try {
            Landlord landlord = Landlord.findByDni(dni);
            landlordView.details(landlord);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

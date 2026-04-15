package controllers;

import models.Landlord;
import models.Rental;
import views.LandlordView;
import views.RentalView;

import java.sql.SQLException;
import java.util.List;

public class RentalController {
    private RentalView rentalview = new RentalView();
    public void store() {
        Rental rental = rentalview.createRental();
        rental.store();
        if (rental.getId() > 0) {
            RentalView.details(rental);
            IO.println("Alquiler registrado con éxito.");
        } else {
            IO.println("No se pudo registrar el alquiler.");
        }
    }

    public void searchById() {
        int id = rentalview.searchById();
        Rental rental = Rental.findById(id);
        if (rental.getId() != 0) {
            RentalView.details(rental);
        } else {
            IO.println("No se encontró ningún alquiler con el ID: " + id);
        }
    }

    public void index() throws  SQLException{
        List<Rental> rentals= Rental.index();
        rentalview.index(rentals);
    }

    public void menu() throws SQLException {
        int option = rentalview.menu();
        switch(option){
            case 1:
                this.index();
                break;
            case 2:
                this.store();
                break;
            case 3:
                rentalview.searchById();
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }
}

package controllers;

import models.Rental;
import views.RentalView;

public class RentalController {
    private RentalView rentalView = new RentalView();
    public void store() {
        Rental rental = rentalView.createRental();
        rental.store();
        if (rental.getId() > 0) {
            RentalView.details(rental);
            IO.println("Alquiler registrado con éxito.");
        } else {
            IO.println("No se pudo registrar el alquiler.");
        }
    }

    public void searchById() {
        int id = rentalView.requestId();
        Rental rental = Rental.findById(id);
        if (rental.getId() != 0) {
            RentalView.details(rental);
        } else {
            IO.println("No se encontró ningún alquiler con el ID: " + id);
        }
    }
}

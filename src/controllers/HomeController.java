package controllers;

import models.Landlord;
import models.Tenant;
import views.HomeView;
import views.LandlordView;

import java.sql.SQLException;

public class HomeController {
    public HomeController() throws SQLException {}{
        HomeView homeView = new HomeView();
        int option = homeView.mainMenu();

        switch(option){
            case 1:
                LandlordController landlordController = new LandlordController();
                landlordController.menu();
                break;
            case 2:
                PropertyController propertyController = new PropertyController();
                propertyController.menu();
                break;
            case 3:
                TenantController tenantController = new TenantController();
                tenantController.menu();
                break;
            case 4:
                RentalController rentalController = new RentalController();
                rentalController.menu();
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }
}

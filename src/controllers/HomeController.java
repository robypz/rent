package controllers;

import models.Landlord;
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
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }
}

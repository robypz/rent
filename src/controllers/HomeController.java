package controllers;

import views.HomeView;
import views.LandlordView;

import java.sql.SQLException;

public class HomeController {

    public HomeController () throws SQLException {
        HomeView homeView = new HomeView();
        int option = homeView.mainMenu();

        switch (option){
            case 1:
                LandlordController landlordController = new LandlordController();
                landlordController.menu();
            case 2:
            case 3:
            case 4:
            default:
                IO.println("¡Opción invalida!");

        }
    }
}

package controllers;

import views.HomeView;

import java.sql.SQLException;

public class HomeController {
    LandlordController LandlordController = new LandlordController();
    public HomeController(LandlordController landlordController) {}{
        HomeView homeView = new HomeView(LandlordController.getLandlordMainView());
    }
}

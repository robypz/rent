package org.example;

import controllers.HomeController;
import controllers.LandlordController;
import views.LandlordView;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        LandlordController landlordController = new LandlordController();
        HomeController homeController = new HomeController(
                landlordController
        );
    }

}

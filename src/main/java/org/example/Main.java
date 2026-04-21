package org.example;

import controllers.HomeController;
import controllers.LandlordController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        HomeController homeController = new HomeController(
                new LandlordController());
    }
}

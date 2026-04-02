package controllers;

import models.Landlord;
import models.Property;
import views.LandlordView;
import views.PropertyView;

import java.sql.SQLException;
import java.util.List;

public class PropertyController {
    private PropertyView propertyView = new PropertyView();

    public void store() {
        Property property = propertyView.createProperty();
        if (property != null) {
            property.store();
            IO.println("Propiedad guardada con ID: " + property.getId());
        }


    }

    public void searchById() {
        int id = propertyView.findbyId();
        Property property = Property.show(id);

        if (property != null) {
            propertyView.details(property);
        } else {
            IO.println("Id no encontrado: " + id);
        }
    }

    public void index() throws  SQLException{
        List<Property> property = Property.index();
        propertyView.index(property);
    }

    public void menu() throws SQLException {
        int option = propertyView.menu();
        switch(option){
            case 1:
                this.index();
                break;
            case 2:
                propertyView.createProperty();
                break;
            case 3:
                propertyView.findbyId();
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }
}


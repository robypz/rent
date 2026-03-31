package controllers;

import models.Property;
import views.PropertyView;

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
}


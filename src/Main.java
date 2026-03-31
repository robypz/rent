import controllers.LandlordController;
import controllers.PropertyController;

import java.sql.SQLException;


void main() throws SQLException {
    PropertyController propertyController = new  PropertyController();
    propertyController.store();
}

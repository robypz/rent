import controllers.LandlordController;

import java.sql.SQLException;


void main() throws SQLException {
    LandlordController landlordController = new LandlordController();
    landlordController.searchByDni();
}

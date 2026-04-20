package controllers;

import models.Landlord;
import models.MariaDB;
import views.LandlordView;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class LandlordController {
    private LandlordView landlordview = new LandlordView();
    private Landlord landlordModel = new Landlord();

    public static void store(Landlord landlord) {
        try {
            landlord.store();
        }catch (SQLException e){
            JDialog dialog = new JDialog();
            dialog.setTitle("Error");
            dialog.add(new JLabel(e.getMessage()));
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(null);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    public static Landlord findByDni(String dni){
        Landlord landlord = new Landlord();
        String sql = "SELECT * FROM landlords WHERE dni = ? LIMIT 1";
        try (
                Connection conn = MariaDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1,dni);

            try (ResultSet keys = stmt.executeQuery()) {
                if(keys.next()) {
                    landlord.setId(keys.getInt("id"));
                    landlord.setDni(keys.getString("dni"));
                    landlord.setName(keys.getString("name"));
                    landlord.setLast_name(keys.getString("last_name"));
                    landlord.setBirth_date(keys.getObject("birth_date", LocalDate.class));
                }
            }

        } catch (SQLException e) {
            IO.println("Error en show de landlord " + e.getMessage());
        }
        return landlord;
    }

    public void searchByDni(){
        String dni = landlordview.findbyDni();
        Landlord landlord = Landlord.findByDni(dni);
        landlordview.details(landlord);
    }

    public void index() throws  SQLException{
        List<Landlord> landlords = Landlord.index();
        landlordview.index(landlords);
    }

    public void menu() throws SQLException{
        LandlordView landlordview = new LandlordView();
        int option = landlordview.menu();
        switch(option){
            case 1:
                this.index();
                break;
            case 2:
                landlordview.createLandlord();
                break;
            case 3:
                landlordview.details(Landlord.findByDni(landlordview.findbyDni()));
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }

    public void detailMenu(Map<Integer,Landlord> option){
        landlordview.detailMenu(option.values().iterator().next());
        switch(option.keySet().iterator().next()){
            case 1:
                Landlord landlord = landlordview.edit(option.get(1));
                landlord.update();
                landlordview.details(landlord);
                break;
            case 2:
                Landlord.destroy(option.get(2));
               break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }
}

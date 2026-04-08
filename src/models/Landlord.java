package models;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Landlord {
    private int id;
    private String name;
    private String last_name;
    private String dni;
    private LocalDate birth_date;

    //meotodos para operar tabla en db
    public static List<Landlord> index() throws SQLException{
        List<Landlord> landlords = new ArrayList<>();
        String sql = "SELECT * FROM landlords";
        try (
                Connection conn = MariaDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            //stmt.executeQuery();

            try (ResultSet keys = stmt.executeQuery()) {
                while (keys.next()) {
                    Landlord l = new Landlord();
                    l.setId(keys.getInt("id"));
                    l.setDni(keys.getString("dni"));
                    l.setName(keys.getString("name"));
                    l.setLast_name(keys.getString("last_name"));
                    l.setBirth_date(keys.getObject("birth_date",LocalDate.class));
                    landlords.add(l);
                }
            }

        } catch (SQLException e) {
            IO.println("Error en index de landlord " + e.getMessage());
        }
        return landlords;
    }
    public void store () throws SQLException {
        String sql = "INSERT INTO landlords (name, last_name, dni, birth_date) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = MariaDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1,this.getName());
            stmt.setString(2,this.getLast_name());
            stmt.setString(3,this.getDni());
            stmt.setObject(4,this.getBirth_date());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    this.setId(keys.getInt("insert_id"));
                    this.setName(keys.getString("name"));
                    this.setLast_name(keys.getString("last_name"));
                    this.setDni(keys.getString("dni"));
                    this.setBirth_date(keys.getObject("birth_date",LocalDate.class));
                }
            }

        } catch (SQLException e) {
            IO.println("Error en store de landlord " + e.getMessage());
        }
    }

    public void update (){
        String sql = "UPDATE landlords SET name = ?, last_name = ?, dni = ?, birth_date = ? WHERE id = ?";

        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,this.getName());
            stmt.setString(2,this.getLast_name());
            stmt.setString(3,this.getDni());
            stmt.setInt(5,this.getId());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    this.setName(keys.getString("name"));
                    this.setLast_name(keys.getString("last_name"));
                    this.setDni(keys.getString("dni"));
                    this.setBirth_date(keys.getObject("birth_date",LocalDate.class));
                }
            }

        } catch (SQLException e) {
            IO.println("Error en update de landlord " + e.getMessage());
        }

    }

    public static Landlord show (int id){
        Landlord landlord = new Landlord();
        String sql = "SELECT * FROM landlords WHERE id = ?";
        try (
                Connection conn = MariaDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1,id);

            try (ResultSet keys = stmt.executeQuery()) {
                while (keys.next()) {
                    landlord.setId(keys.getInt("id"));
                    landlord.setDni(keys.getString("dni"));
                    landlord.setName(keys.getString("name"));
                    landlord.setLast_name(keys.getString("last_name"));
                    landlord.setBirth_date(keys.getObject("birth_date",LocalDate.class));
                }
            }

        } catch (SQLException e) {
            IO.println("Error en show de landlord " + e.getMessage());
        }
        return landlord;
    }

    public static void destroy (Landlord landlord){
        String sql = "DELETE FROM landlords WHERE id = ?";
        try(Connection conn = MariaDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, landlord.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            IO.println("Error en destroy de landlord " + e.getMessage());
        }
    }

    public static boolean dniExist(String dni){
        String sql = "SELECT * FROM landlords WHERE dni = ? LIMIT 1";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,dni);
            try (ResultSet keys = stmt.executeQuery()) {
                if(keys.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            IO.println("Error en dniExist de landlord " + e.getMessage());
        }
        return false;
    }

    public static Landlord findByDni (String dni){
        Landlord landlord = new Landlord();
        String sql = "SELECT * FROM landlords WHERE dni = ? LIMIT 1";
        try (
                Connection conn = MariaDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1,dni);

            try (ResultSet keys = stmt.executeQuery()) {
                if (keys.next()) {
                    landlord.setId(keys.getInt("id"));
                    landlord.setDni(keys.getString("dni"));
                    landlord.setName(keys.getString("name"));
                    landlord.setLast_name(keys.getString("last_name"));
                    landlord.setBirth_date(keys.getObject("birth_date",LocalDate.class));
                }
            }

        } catch (SQLException e) {
            IO.println("Error en show de landlord " + e.getMessage());
        }
        return landlord;
    }

    public List<Property> propierties(){
        return Property.getByLandlordId(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "models.Landlord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dni='" + dni + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}

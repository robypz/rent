import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Landlord {
    private int id;
    private String name;
    private String last_name;
    private String dni;
    private Date birth_date;

    //meotodos para operar tabla en db
    public static List<Landlord> index (){
        List<Landlord> landlords = new ArrayList<>();
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
            stmt.setInt(5,this.getId());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    this.setName(keys.getString("name"));
                    this.setLast_name(keys.getString("last_name"));
                    this.setDni(keys.getString("dni"));
                    this.setBirth_date(keys.getDate("birth_date"));
                }
            }

        } catch (SQLException e) {
            IO.println(e.getMessage());
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
                    this.setBirth_date(new Date(keys.getDate("birth_date").getTime()));
                }
            }

        } catch (SQLException e) {
            IO.println(e.getMessage());
        }

    }

    public static Landlord show (int id){
        Landlord landlord = new Landlord();
        return landlord;
    }

    public static void delete (Landlord landlord){

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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
}

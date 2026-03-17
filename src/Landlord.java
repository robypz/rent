import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Landlord {
    private int id;
    private String name;
    private String last_name;
    private String dni;
    private LocalDate birth_date;

    public static List<Landlord> index (){
        List<Landlord> landlords = new ArrayList<>();
        String sql = "SELECT * FROM landlords";
        try (
                Connection conn = MariaDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Landlord landlord = new Landlord();
                    landlord.setId(rs.getInt("id"));
                    landlord.setName(rs.getString("name"));
                    landlord.setLast_name(rs.getString("last_name"));
                    landlord.setDni(rs.getString("dni"));
                    landlord.setBirthDate(rs.getObject("birth_date",LocalDate.class));

                    landlords.add(landlord);

                }
            }

        } catch (SQLException e) {
            IO.println(e.getMessage());
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
            stmt.setObject(4,this.getBirtDate());


            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    this.setId(rs.getInt("id"));
                    this.setName(rs.getString("name"));
                    this.setLast_name(rs.getString("last_name"));
                    this.setDni(rs.getString("dni"));
                    this.setBirthDate(rs.getObject("birth_date",LocalDate.class));
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

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    this.setName(rs.getString("name"));
                    this.setLast_name(rs.getString("last_name"));
                    this.setDni(rs.getString("dni"));
                    this.setBirthDate(rs.getObject("birth_date",LocalDate.class));
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

    @Override
    public String toString() {
        return "Landlord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dni='" + dni + '\'' +
                ", birth_date=" + birth_date +
                '}';
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

    public LocalDate getBirtDate() {
        return birth_date;
    }

    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tenant {
    private int id;
    private String dni;
    private Date birth_date;
    private String name;
    private String last_name;

    public Tenant(String dni, Date birth_date, String name, String last_name) {
        this.dni = dni;
        this.birth_date = birth_date;
        this.name = name;
        this.last_name = last_name;
    }

    public static List<Tenant> index(){
        List<Tenant> tenants = new ArrayList<>();
        String sql = "SELECT * FROM Tenant";
        try(Connection conn = MariaDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                Tenant t = new Tenant(rs.getString("dni"),

                        rs.getDate("birth_date"),
                        rs.getString("name"),
                        rs.getString("last_name")
                );
                t.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            IO.println("Error en index de tentant " + e.getMessage());
        }
        return tenants;
    }

    public void store() {
        String sql = "INSERT INTO tenants (dni, birth_date, name, last_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, this.getDni());
            stmt.setDate(2, new java.sql.Date(this.getBirth_date().getTime()));
            stmt.setString(3, this.getName());
            stmt.setString(4, this.getLast_name());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    this.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            IO.println("Error en store de tenant " + e.getMessage());
        }
    }

    public void update() {
        String sql = "UPDATE tenants SET dni = ?, birth_date = ?, name = ?, last_name = ? WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.getDni());
            stmt.setDate(2, new java.sql.Date(this.getBirth_date().getTime()));
            stmt.setString(3, this.getName());
            stmt.setString(4, this.getLast_name());
            stmt.setInt(5, this.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            IO.println("Error en update de tenant " + e.getMessage());
        }
    }

    public static Tenant show(int id) {
        Tenant tenant = null;
        String sql = "SELECT * FROM tenants WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tenant = new Tenant(
                            rs.getString("dni"),
                            rs.getDate("birth_date"),
                            rs.getString("name"),
                            rs.getString("last_name")
                    );
                    tenant.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            IO.println("Error en show de tenant " + e.getMessage());
        }
        return tenant;
    }

    public static void destroy(Tenant tenant) {
        String sql = "DELETE FROM tenants WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tenant.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            IO.println("Error en destroy de landlord " + e.getMessage());
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}

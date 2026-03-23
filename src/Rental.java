import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rental {
    private int id;
    private int tenant_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private int property_id;

    public Rental(int tenant_id, LocalDate start_date, LocalDate end_date, int property_id) {
        this.tenant_id = tenant_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.property_id = property_id;
    }

    public static List<Rental> index() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";
        try(Connection con = MariaDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Rental r = new Rental(
                        rs.getInt("tenant_id"),
                        rs.getObject("start_date",LocalDate.class),
                        rs.getObject("end_date",LocalDate.class),
                        rs.getInt("property_id")
                );
                r.setId(rs.getInt("id"));
                rentals.add(r);
            }
        } catch (SQLException e){
            IO.println("Error en index de rental " + e.getMessage());
        }
        return rentals;
    }

    public void store() {
        String sql = "INSERT INTO rentals (tenant_id, start_date, end_date, property_id) VALUES (?, ?, ?, ?)";
        try(Connection con = MariaDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, this.tenant_id);
            stmt.setObject(2, this.start_date);
            stmt.setObject(3,this.end_date);
            stmt.setInt(4, this.property_id);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    this.id = keys.getInt(1);
                }
            }
        } catch (SQLException e){
            IO.println("Error en store de rental " + e.getMessage());
        }
    }

    public void update() {
        String sql = "UPDATE rentals SET tenant_id = ?, start_date = ?, end_date = ?, property_id = ? WHERE id = ?";
        try(Connection con = MariaDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, this.tenant_id);
            stmt.setObject(2, this.start_date);
            stmt.setObject(3,this.end_date);
            stmt.setInt(4, this.property_id);
            stmt.setInt(5, this.id);
            stmt.executeUpdate();
        } catch (SQLException e){
            IO.println("Error en update de rental " + e.getMessage());
        }
    }

    public static Rental show(int id){
        Rental rental = null;
        String sql = "SELECT * FROM rentals WHERE id = ?";
        try(Connection con = MariaDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    rental = new Rental(
                            rs.getInt("tenant_id"),
                            rs.getObject("start_date",LocalDate.class),
                            rs.getObject("end_date",LocalDate.class),
                            rs.getInt("property_id")
                    );
                    rental.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e){
            IO.println("Error en show de rental " + e.getMessage());
        }
        return rental;
    }

    public static void destory(Rental rental){
        String sql = "DELETE FROM rentals WHERE id = ?";
        try(Connection con = MariaDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, rental.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            IO.println("Error en destroy de rental " + e.getMessage());
        }
    }


    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    public int getTenant_id() { return this.tenant_id; }
    public void setTenant_id(int tenant_id) { this.tenant_id = tenant_id; }
    public LocalDate getStart_date() { return this.start_date; }
    public void setStart_date(LocalDate start_date) { this.start_date = start_date; }
    public LocalDate getEnd_date() { return this.end_date; }
    public void setEnd_date(LocalDate end_date) { this.end_date = end_date; }
    public int getProperty_id() { return this.property_id; }
    public void setProperty_id(int property_id) { this.property_id = property_id; }

}

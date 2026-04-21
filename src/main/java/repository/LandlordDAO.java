package repository;

import models.Landlord;
import models.MariaDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LandlordDAO {

    public List<Landlord> findAll() throws SQLException {
        List<Landlord> landlords = new ArrayList<>();
        String sql = "SELECT * FROM landlords";
        
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Landlord l = new Landlord();
                    l.setId(rs.getInt("id"));
                    l.setDni(rs.getString("dni"));
                    l.setName(rs.getString("name"));
                    l.setLast_name(rs.getString("last_name"));
                    l.setBirth_date(rs.getObject("birth_date", LocalDate.class));
                    landlords.add(l);
                }
            }
        }
        return landlords;
    }

    public void save(Landlord landlord) throws SQLException {
        String sql = "INSERT INTO landlords (name, last_name, dni, birth_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, landlord.getName());
            stmt.setString(2, landlord.getLast_name());
            stmt.setString(3, landlord.getDni());
            stmt.setObject(4, landlord.getBirth_date());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    landlord.setId(keys.getInt(1)); // getInt(1) is safer than "insert_id" depending on driver
                }
            }
        }
    }

    public void update(Landlord landlord) throws SQLException {
        String sql = "UPDATE landlords SET name = ?, last_name = ?, dni = ?, birth_date = ? WHERE id = ?";

        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, landlord.getName());
            stmt.setString(2, landlord.getLast_name());
            stmt.setString(3, landlord.getDni());
            stmt.setObject(4, landlord.getBirth_date());
            stmt.setInt(5, landlord.getId());

            stmt.executeUpdate();
        }
    }

    public Landlord findById(int id) throws SQLException {
        Landlord landlord = null;
        String sql = "SELECT * FROM landlords WHERE id = ?";
        
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    landlord = new Landlord();
                    landlord.setId(rs.getInt("id"));
                    landlord.setDni(rs.getString("dni"));
                    landlord.setName(rs.getString("name"));
                    landlord.setLast_name(rs.getString("last_name"));
                    landlord.setBirth_date(rs.getObject("birth_date", LocalDate.class));
                }
            }
        }
        return landlord; // returns null if not found
    }

    public void delete(Landlord landlord) throws SQLException {
        String sql = "DELETE FROM landlords WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, landlord.getId());
            stmt.executeUpdate();
        }
    }

    public boolean dniExists(String dni) throws SQLException {
        String sql = "SELECT 1 FROM landlords WHERE dni = ? LIMIT 1";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Landlord findByDni(String dni) throws SQLException {
        Landlord landlord = null;
        String sql = "SELECT * FROM landlords WHERE dni = ? LIMIT 1";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, dni);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    landlord = new Landlord();
                    landlord.setId(rs.getInt("id"));
                    landlord.setDni(rs.getString("dni"));
                    landlord.setName(rs.getString("name"));
                    landlord.setLast_name(rs.getString("last_name"));
                    landlord.setBirth_date(rs.getObject("birth_date", LocalDate.class));
                }
            }
        }
        return landlord;
    }
}

package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Property {
    private int id;
    private String address;
    private Double price;
    private int floors;

    public Property(String address, Double price, int floors, int landlord_id) {
        this.address = address;
        this.price = price;
        this.floors = floors;
        this.landlord_id = landlord_id;
    }

    public static List<Property> index() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Property p = new Property(
                        rs.getString("address"),
                        rs.getDouble("price"),
                        rs.getInt("floors"),
                        rs.getInt("landlord_id")
                );
                p.setId(rs.getInt("id"));
                properties.add(p);
            }
        } catch (SQLException e) {
            IO.println("Error en index de property " + e.getMessage());
        }
        return properties;
    }

    public void store() {
        String sql = "INSERT INTO properties (address, price, floors, landlord_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, this.getAddress());
            stmt.setDouble(2, this.getPrice());
            stmt.setInt(3, this.getFloors());
            stmt.setInt(4, this.getLandlord_id());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    this.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            IO.println("Error de store en property " + e.getMessage());
        }
    }

    public void update() {
        String sql = "UPDATE properties SET address = ?, price = ?, floors = ?, landlord_id = ? WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.getAddress());
            stmt.setDouble(2, this.getPrice());
            stmt.setInt(3, this.getFloors());
            stmt.setInt(4,this.getLandlord_id());
            stmt.setInt(5, this.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            IO.println("Error en update de property " + e.getMessage());
        }
    }

    public static Property show(int id) {
       Property property = null;
        String sql = "SELECT * FROM properties WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    property = new Property(
                            rs.getString("address"),
                            rs.getDouble("price"),
                            rs.getInt("floors"),
                            rs.getInt("landlord_id")
                    );
                    property.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            IO.println("Error en show de propoerty " + e.getMessage());
        }
        return property;
    }

    public static void destroy(Property property) {
        String sql = "DELETE FROM properties WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, property.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            IO.println("Error en destroy de property " + e.getMessage());
        }
    }

    public static List<Property> getByLandlordId(Landlord landlord) {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties WHERE landlord_id = ?";
        try(Connection con = MariaDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, landlord.getId());
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    Property p = new Property(
                            rs.getString("address"),
                            rs.getDouble("price"),
                            rs.getInt("floors"),
                            rs.getInt("landlord_id")
                    );
                    p.setId(rs.getInt("id"));
                    properties.add(p);
                }
            }
        } catch (SQLException e) {
            IO.println("Error en getByLandlordId de propoerty " + e.getMessage());
        }
        return properties;
    }




    public static boolean exists(int id) {
        String sql = "SELECT COUNT(*) FROM properties WHERE id = ?";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            IO.println("Error al verificar existencia: " + e.getMessage());
        }
        return false;
    }

    public Property() {}

    public static Property findById(int id) {
        Property property = new Property();
        String sql = "SELECT * FROM properties WHERE id = ? LIMIT 1";
        try (Connection conn = MariaDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    property.setId(rs.getInt("id"));
                    property.setAddress(rs.getString("address"));
                    property.setPrice(rs.getDouble("price"));
                    property.setFloors(rs.getInt("floors"));
                    property.setLandlord_id(rs.getInt("landlord_id"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en findById: " + e.getMessage());
        }
        return property;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(int landlord_id) {
        this.landlord_id = landlord_id;
    }

    private int landlord_id;

}


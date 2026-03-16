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

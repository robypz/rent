package models;

import java.time.LocalDate;

public class Landlord {
    private int id;
    private String name;
    private String last_name;
    private String dni;
    private LocalDate birth_date;

    public Landlord() {
    }

    public Landlord(String name, String last_name, String dni, LocalDate birth_date) {
        this.name = name;
        this.last_name = last_name;
        this.dni = dni;
        this.birth_date = birth_date;
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

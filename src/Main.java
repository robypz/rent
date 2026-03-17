import java.sql.SQLException;

void main() throws SQLException {
    Landlord landlord = new Landlord();
    landlord.setDni("7777777");
    landlord.setName("Maria");
    landlord.setLast_name("Jimenez");
    landlord.setBirthDate((LocalDate.of(1998,11,8)));
    landlord.store();
    for (Landlord l : Landlord.index()){
        IO.println(l);
    }
}

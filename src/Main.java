import java.sql.SQLException;

// para crear una nueva rama en github: git checkout -b nombre_rama
// para cambiar de rama: git checkout nombre_rama
// para ver en qué rama estás: git brach
// para cargar la rama otra vez en caso de no ver los cambios: git pull
// actualizar repositorio local con el remoto: git fetch

void main() throws SQLException {
    /*
    Landlord l = new Landlord();
    l.setDni("1111");
    l.setName("Alvaro");
    l.setLast_name("Alvarez");
    l.setBirth_date(new Date(1999,5,11));
    //System.out.println(l.toString());
    IO.println(l.toString());
    l.store();

    for(Landlord lt: Landlord.index()) {
        IO.println(lt);
    }
    */

    IO.println(Landlord.show(3));
}

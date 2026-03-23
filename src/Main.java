import java.sql.SQLException;

// para crear una nueva rama en github: git checkout -b nombre_rama
// para cambiar de rama: git checkout nombre_rama
// para ver en qué rama estás: git brach
// para cargar la rama otra vez en caso de no ver los cambios: git pull
// actualizar repositorio local con el remoto: git fetch
// para traer los cambios hechos en otra rama: git merge origin/nombre_rama Ej: origin/robypz
// para guardar en vim comando: :w
// para salir de vim comando: :q
// para subir un repositorio a github: git add . && git commit -m "mensaje del commit" && git push


// crear metodo que con un objetos tipo landlord nos devuelva una lista con las propiedades de ese propietario
// buscar información sobre MVC y Patrón de diseño de software
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
    Property p = new Property("calle", 20000.0, 6, 1);
    Tenant t = new Tenant("123456789", LocalDate.of("1995-02-02"), "hola", "adios");
    Rental r = new Rental(1,LocalDate.of("1995-02-02"),LocalDate.of("1995-02-02"), 1);
    IO.println(Landlord.show(3));
}

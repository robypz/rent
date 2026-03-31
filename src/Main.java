import controllers.*;
import models.Property;
import views.*;

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

// para que vuelva a funcionar MariaBD hacer win + R, y poner services.msc. Luego buscar MariaBD.exe y darle a iniciar
// (lo mismo de arriba pero para MySQL si no funciona)

void main() throws SQLException {
    HomeController homeController = new HomeController();
}

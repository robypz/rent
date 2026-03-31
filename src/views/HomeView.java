package views;

import java.util.Scanner;

public class HomeView {
    private Scanner sc = new Scanner(System.in);
    public HomeView(){
        this.init();
    }

    private void init(){
        IO.println("¡Bienvenido al programa de administración de rentas");
    }

    public int mainMenu(){
        IO.println("1) Propietarios \n2) Propiedades \n3) Inquilinos \n4) Contratos");
        return sc.nextInt();

    }

}

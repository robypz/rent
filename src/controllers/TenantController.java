package controllers;

import models.Landlord;
import models.Tenant;
import views.LandlordView;
import views.TenantView;

import java.sql.SQLException;
import java.util.List;

public class TenantController {
    private TenantView tenantView = new TenantView();
    public void store() {
        Tenant tenant = tenantView.createTenant();

        if (Tenant.dniExist(tenant.getDni())) {
            IO.println("Este DNI ya está registrado.");
        } else {
            tenant.store();
            TenantView.details(tenant);
            IO.println("Inquilino guardado con éxito.");
        }
    }

    public void searchByDni() {
        String dni = tenantView.findbyDni();
        Tenant tenant = Tenant.findByDni(dni);
        if (tenant.getDni() != null) {
            TenantView.details(tenant);
        } else {
            IO.println("No se encontró ningún inquilino con el DNI: " + dni);
        }
    }

    public void index() throws  SQLException{
        List<Tenant> tenants = Tenant.index();
        tenantView.index(tenants);
    }

    public void menu() throws SQLException{
        int option = tenantView.menu();
        switch(option){
            case 1:
                this.index();
                break;
            case 2:
                tenantView.createTenant();
                break;
            case 3:
                tenantView.findbyDni();
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }
}

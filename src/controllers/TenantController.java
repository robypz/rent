package controllers;

import models.Landlord;
import models.Tenant;
import views.LandlordView;
import views.TenantView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    public void index() throws SQLException{
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
                this.store();
                break;
            case 3:
                this.searchByDni();
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }

    public void detailMenu(Map<Integer,Tenant> option){
        tenantView.detailMenu(option.values().iterator().next());
        switch(option.keySet().iterator().next()){
            case 1:
                Tenant tenant = tenantView.edit(option.get(1));
                tenant.update();
                tenantView.details(tenant);
                break;
            case 2:
                Tenant.destroy(option.get(2));
                break;
            default:
                IO.println("Opción inválida");
                break;
        }
    }

}

package controllers;

import models.Tenant;
import views.TenantView;

import java.sql.SQLException;

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
        String dni = tenantView.requestDni();
        Tenant tenant = Tenant.findByDni(dni);
        if (tenant.getDni() != null) {
            TenantView.details(tenant);
        } else {
            IO.println("No se encontró ningún inquilino con el DNI: " + dni);
        }
    }
}

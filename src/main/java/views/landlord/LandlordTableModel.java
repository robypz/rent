package views.landlord;

import models.Landlord;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LandlordTableModel extends AbstractTableModel {

    private final String[] columnas = {
            "ID", "Nombre", "Apellido", "DNI", "Fecha de Nacimiento"
    };

    private List<Landlord> landlords;

    public LandlordTableModel(List<Landlord> landlords) {
        this.landlords = landlords;
    }

    public void setLandlords(List<Landlord> landlords) {
        this.landlords = landlords;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return landlords.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Landlord l = landlords.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> l.getId();
            case 1 -> l.getName();
            case 2 -> l.getLast_name();
            case 3 -> l.getDni();
            case 4 -> l.getBirth_date();
            default -> null;
        };
    }

    public Landlord getLandlordAt(int row) {
        return landlords.get(row);
    }
}


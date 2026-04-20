package models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LandlordTableModel extends AbstractTableModel {
    private final String[] columnNames = { "ID", "Nombre", "Apellidos", "DNI", "Fecha nacimiento" };
    private List<Landlord> landlords;

    public LandlordTableModel(List<Landlord> landlords) {
        this.landlords = landlords;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Landlord landlord = landlords.get(rowIndex);

        return switch(columnIndex){
            case 0 -> landlord.getId();
            case 1 -> landlord.getName();
            case 2 -> landlord.getLast_name();
            case 3 -> landlord.getDni();
            case 4 -> landlord.getBirth_date();
            default -> throw new IllegalArgumentException("Unexpected value: " + columnIndex);
        };
    }
}

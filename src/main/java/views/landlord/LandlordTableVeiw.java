package views.landlord;

import javax.swing.*;

public class LandlordTableVeiw {
    private JTable  table;
    public LandlordTableVeiw(LandlordTableModel landlordTableModel)
    {
        table = new JTable(landlordTableModel);
    }

    public JTable getTable()
    {
        return table;
    }

    public void  setTable(JTable table)
    {
        this.table = table;
    }

}

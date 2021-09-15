/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel.modelitabela;

import entities.Trener;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TrenerTable extends AbstractTableModel{
    private final List<Trener> treneri;

    private String[] naziviKolona = new String[]{"Ime","Prezime","Datum rodjenja", "Broj licence"};
    private Class[] tipovikolona = new Class[]{ String.class, String.class, Date.class, String.class};

    public TrenerTable(List<Trener> treneri) {
        this.treneri = treneri;
    }

    @Override
    public int getRowCount() {
        if (treneri == null) {
            return 0;
        }
        return treneri.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trener trener = treneri.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return trener.getIme();
            case 1:
                return trener.getPrezime();
            case 2:
                return trener.getDatumRodjenja();
            case 3:
                return trener.getBrojLicence();
            default:
                return "n/a";
        }
    }
   
    @Override
     public String getColumnName(int column) {
        if (column > naziviKolona.length) {
            return "n/a";
        }
        return naziviKolona[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return tipovikolona[column];
    } 
}

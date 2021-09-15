/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel.modelitabela;

import entities.Kategorija;
import entities.Tim;
import entities.Trener;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TimTable extends AbstractTableModel{
    private final List<Tim> timovi;

    private String[] naziviKolona = new String[]{"ID","Naziv tima","Trener", "Kategorija"};
    private Class[] tipovikolona = new Class[]{ Long.class, String.class, Trener.class, Kategorija.class };

    public TimTable(List<Tim> timovi) {
        this.timovi = timovi;
    }

    @Override
    public int getRowCount() {
        if (timovi == null) {
            return 0;
        }
        return timovi.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tim tim = timovi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tim.getSifraTima();
            case 1:
                return tim.getNazivTima();
            case 2:
                return tim.getTrener();
            case 3:
                return tim.getKategorija();
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

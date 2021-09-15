/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel.modelitabela;

import entities.Igrac;
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
public class IgracTable extends AbstractTableModel{
    private final List<Igrac> igraci;

    private String[] naziviKolona = new String[]{"Ime","Prezime","Datum rodjenja", "Broj na dresu", "Visina", "Tim"};
    private Class[] tipovikolona = new Class[]{ String.class, String.class, Date.class, Integer.class,Double.class, Tim.class };

    public IgracTable(List<Igrac> igraci) {
        this.igraci = igraci;
    }

    @Override
    public int getRowCount() {
        if (igraci == null) {
            return 0;
        }
        return igraci.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Igrac igrac = igraci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return igrac.getIme();
            case 1:
                return igrac.getPrezime();
            case 2:
                return igrac.getDatumRodjenja();
            case 3:
                return igrac.getBrojNaDresu();
            case 4:
                return igrac.getVisina();
            case 5:
                return igrac.getTim();
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

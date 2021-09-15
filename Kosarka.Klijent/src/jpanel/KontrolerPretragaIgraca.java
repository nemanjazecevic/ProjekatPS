/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.Igrac;
import entities.Kategorija;
import entities.KriterijumPretrage;
import entities.Trener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jpanel.modelitabela.IgracTable;
import komunikacija.Operation;
import komunikacija.Response;
import transfer.Komunikacija;

/**
 *
 * @author PC
 */
public class KontrolerPretragaIgraca {
    private static KontrolerPretragaIgraca instance;
    private List<Igrac> lista;
    private PretragaIgraca jpanel;
    private KontrolerPretragaIgraca() {
    }

    public static KontrolerPretragaIgraca getInstance() {
        if (instance == null) {
            instance = new KontrolerPretragaIgraca();
        }
        return instance;
    }

    public PretragaIgraca getJpanel() {
        jpanel = new PretragaIgraca();
        osluskivaciAkcija();
        inicijalnoUcitavanje();
        jpanel.setVisible(true);
        return jpanel;
    }

    private void osluskivaciAkcija() {
        this.jpanel.getjBtnPretrazi().addActionListener(e -> osluskivacPretrazi());
        this.jpanel.getjBtnObrisi().addActionListener(e -> osluskivacObrisi());
        this.jpanel.getjBtnAzuriraj().addActionListener(e -> osluskivacAzuriraj());
        this.jpanel.getjBtnUcitaj().addActionListener(e -> inicijalnoUcitavanje());
    }

    private void osluskivacPretrazi() {
        try {
            String pretragaTekst = jpanel.getjTxtPretraga().getText();
            if(pretragaTekst.isEmpty()){
                JOptionPane.showMessageDialog(jpanel, "Unesite neki tekst", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Response res =  Komunikacija.getInstance().GetResponse(new KriterijumPretrage(pretragaTekst, null), Operation.PronadjiIgraca);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je nasao igrace po zadatoj vrednosti", "", JOptionPane.INFORMATION_MESSAGE);
                lista = (List<Igrac>) res.getObjekat();
                PodesiTabelu();
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da nadje igrace po zadatoj vrednosti!", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerPretragaIgraca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicijalnoUcitavanje() {
        try {
            Response res =  Komunikacija.getInstance().GetResponse(new Igrac(), Operation.VratiListuIgraca);
            if(res.isZnak()){
                 lista = (List<Igrac>) res.getObjekat();
                 PodesiTabelu();
            }
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void osluskivacAzuriraj() {
        int row = jpanel.getjTable1().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(jpanel, "Morate selektovati red", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Igrac igrac = lista.get(row);
        KontrolerPanel panel = new KontrolerPanel();
        panel.kreiranjeIgraca(igrac);
        inicijalnoUcitavanje();
    }

    private void osluskivacObrisi() {
        try {
           int row = jpanel.getjTable1().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(jpanel, "Morate selektovati red", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Igrac igrac = lista.get(row);
            Response res =  Komunikacija.getInstance().GetResponse(igrac, Operation.ObrisiIgraca);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je obrisao igraca", "", JOptionPane.INFORMATION_MESSAGE);
                inicijalnoUcitavanje();
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da obrise igraca", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerPretragaIgraca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void PodesiTabelu() {
        jpanel.getjTable1().setModel(new IgracTable(lista));
    }
}

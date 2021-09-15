/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.Igrac;
import entities.KriterijumPretrage;
import entities.Tim;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jpanel.modelitabela.TimTable;
import komunikacija.Operation;
import komunikacija.Response;
import transfer.Komunikacija;

/**
 *
 * @author PC
 */
public class KontrolerPretragaTima {
    private static KontrolerPretragaTima instance;
    private PretragaTima jpanel;
     private List<Tim> lista;
    private KontrolerPretragaTima() {
    }

    public static KontrolerPretragaTima getInstance() {
        if (instance == null) {
            instance = new KontrolerPretragaTima();
        }
        return instance;
    }

    public PretragaTima getJpanel() {
        jpanel = new PretragaTima();
        osluskivaciAkcija();
        inicijalnoUcitavanje();
        jpanel.setVisible(true);
        return jpanel;
    }

    private void osluskivaciAkcija() {
        this.jpanel.getjBtnPretrazi().addActionListener(e -> osluskivacPretrazi());
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
            Response res =  Komunikacija.getInstance().GetResponse(new KriterijumPretrage(pretragaTekst, null), Operation.PronadjiTim);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je nasao timove po zadatoj vrednosti", "", JOptionPane.INFORMATION_MESSAGE);
                lista = (List<Tim>) res.getObjekat();
                PodesiTabelu();
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da nadje timove po zadatoj vrednosti", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerPretragaTima.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicijalnoUcitavanje() {
        try {
            Response res =  Komunikacija.getInstance().GetResponse(new Tim(), Operation.VratiListuTimova);
            if(res.isZnak()){
                 lista = (List<Tim>) res.getObjekat();
                 PodesiTabelu();
            }
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerPretragaTima.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void osluskivacAzuriraj() {
         int row = jpanel.getjTable1().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(jpanel, "Morate selektovati red", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Tim tim = lista.get(row);
        KontrolerPanel panel = new KontrolerPanel();
        panel.kreiranjeTima(tim);
        inicijalnoUcitavanje();
    }

    private void PodesiTabelu() {
        jpanel.getjTable1().setModel(new TimTable(lista));
    }

}

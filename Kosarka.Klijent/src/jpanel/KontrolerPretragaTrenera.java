/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.KriterijumPretrage;
import entities.Tim;
import entities.Trener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jpanel.modelitabela.TrenerTable;
import komunikacija.Operation;
import komunikacija.Response;
import transfer.Komunikacija;

/**
 *
 * @author PC
 */
public class KontrolerPretragaTrenera {
    private static KontrolerPretragaTrenera instance;
    private PretragaTrenera jpanel;
    private List<Trener> lista;
    private KontrolerPretragaTrenera() {
    }

    public static KontrolerPretragaTrenera getInstance() {
        if (instance == null) {
            instance = new KontrolerPretragaTrenera();
        }
        return instance;
    }

    public PretragaTrenera getJpanel() {
        jpanel = new PretragaTrenera();
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
                JOptionPane.showMessageDialog(jpanel, "NE", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Response res =  Komunikacija.getInstance().GetResponse(new KriterijumPretrage(pretragaTekst, null), Operation.PronadjiTrenera);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je nasao trenere po zadatoj vrednosti", "", JOptionPane.INFORMATION_MESSAGE);
                lista = (List<Trener>) res.getObjekat();
                PodesiTabelu();
                
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da nadje trenere po zadatoj vrednosti", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerPretragaTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicijalnoUcitavanje() {
        try {
            Response res =  Komunikacija.getInstance().GetResponse(new Trener(), Operation.VratiListuTrenera);
            if(res.isZnak()){
                 lista = (List<Trener>) res.getObjekat();
                 PodesiTabelu();
            }
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerPretragaTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void osluskivacAzuriraj() {
         int row = jpanel.getjTable1().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(jpanel, "Morate selektovati red", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Trener trener = lista.get(row);
        KontrolerPanel panel = new KontrolerPanel();
        panel.kreiranjeTrenera(trener);
        inicijalnoUcitavanje();
    }

    private void PodesiTabelu() {
        jpanel.getjTable1().setModel(new TrenerTable(lista));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.Igrac;
import entities.Kategorija;
import entities.Tim;
import entities.Trener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class KontrolerUnosTima {
    private static KontrolerUnosTima instance;
    private UnosTima jpanel;
    private Tim tim;
    private List<Tim> lista;
    private KontrolerUnosTima() {
    }

    public static KontrolerUnosTima getInstance() {
        if (instance == null) {
            instance = new KontrolerUnosTima();
        }
        return instance;
    }

    public UnosTima getJpanel(Tim tim) {
        jpanel = new UnosTima();
        osluskivaciAkcija();
        inicijalnoUcitavanje();
        
        if(tim == null) {
            jpanel.getjBtnSacuvaj().setText("Sacuvaj");
            jpanel.getjBtnDodaj().setEnabled(true);
            jpanel.getjBtnUkloni().setEnabled(true);
        }else{
            jpanel.getjBtnSacuvaj().setText("Izmeni");
            this.tim = tim; 
            jpanel.getjTxtIme().setText(tim.getNazivTima());
            jpanel.getjCmbTrener().setSelectedItem(tim.getTrener());
            jpanel.getjCmbKategorija().setSelectedItem(tim.getKategorija());
            jpanel.getjBtnDodaj().setEnabled(false);
            jpanel.getjBtnUkloni().setEnabled(false);
        }
        
        jpanel.setVisible(true);
        return jpanel;
    }

    private void osluskivaciAkcija() {
        this.jpanel.getjBtnSacuvaj().addActionListener(e -> osluskivacIzmenaSacuvaj());
        this.jpanel.getjBtnDodaj().addActionListener(e -> osluskivacDodaj());
        this.jpanel.getjBtnUkloni().addActionListener(e -> osluskivacUkloni());

        
    }
    
    private void osluskivacIzmenaSacuvaj() {
        if(tim == null){
            osluckivacSacuvaj();
        }else{
            osluckivacIzmeni();
        }
    }

    private void osluckivacSacuvaj() {
       try {
           if(lista.size() == 0){
               JOptionPane.showMessageDialog(jpanel, "NE", "", JOptionPane.ERROR_MESSAGE);
               return;
           }
            Response res =  Komunikacija.getInstance().GetResponse(lista, Operation.ZapamtiTim);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je zapamtio timove!", "", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da zapamti timove", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void osluskivacDodaj() {
         try {
            lista.add(kreirajTim());
            PodesiTabelu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void osluskivacUkloni() {
        int row = jpanel.getjTable1().getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(jpanel, "Morate selektovati red", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        lista.remove(row);
        PodesiTabelu();
                
    }

    private void inicijalnoUcitavanje() {
        try {
            Response res =  Komunikacija.getInstance().GetResponse(new Kategorija(), Operation.VratiListuKategorija);
            if(res.isZnak()){
                List<Kategorija> lista = (List<Kategorija>) res.getObjekat();
                for (Kategorija kategorija : lista) { 
                    jpanel.getjCmbKategorija().addItem(kategorija);
                }
            }
            
            res =  Komunikacija.getInstance().GetResponse(new Trener(), Operation.VratiListuTrenera);
            if(res.isZnak()){
                List<Trener> lista = (List<Trener>) res.getObjekat();
                for (Trener trener : lista) { 
                    jpanel.getjCmbTrener().addItem(trener);
                }
            }
            lista = new ArrayList();
            PodesiTabelu();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void PodesiTabelu(){
        jpanel.getjTable1().setModel(new TimTable(lista));
    }

    private void osluckivacIzmeni() {
        try {
          
            Response res =  Komunikacija.getInstance().GetResponse(kreirajTim(), Operation.ZapamtiTim);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je zapamtio tim", "", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da zapamti tim", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Tim kreirajTim() throws Exception {
        String naziv = jpanel.getjTxtIme().getText();
        if(naziv.isEmpty()){
            throw new Exception("Morate popuniti naziv. Obavezan je");
        }
        Kategorija kategorija = (Kategorija) jpanel.getjCmbKategorija().getSelectedItem();
        Trener tr = (Trener) jpanel.getjCmbTrener().getSelectedItem();
        Long id = new Long(0);
        if(tim != null){
            id = tim.getSifraTima();
        }else{
            if(lista.size() >0){
                for (Tim tim1 : lista) {
                    if(tim1.getNazivTima().toLowerCase().equals(naziv.toLowerCase())){
                        throw new Exception("Vec ste uneli tim sa ovim nazivom");
                    }
                    if(tim1.getTrener().getIdClana().equals(tr.getIdClana())){
                        throw new Exception("Vec ste uneli ekipu za ovog trenera");
                    }
                }
            }
        }
        
        Tim tim = new Tim(id, naziv, kategorija, tr);
        ProveraPostoji(tim);
        return tim;
    }

    private void ProveraPostoji(Tim tim) throws Exception {
        Response res =  Komunikacija.getInstance().GetResponse(new Tim(), Operation.VratiListuTimova);
        if(res.isZnak()){
             List<Tim> timovi = (List<Tim>) res.getObjekat();
              for (Tim tim1 : timovi) {
                if(tim1.getNazivTima().toLowerCase().equals(tim.getNazivTima().toLowerCase()) && !tim1.getSifraTima().equals(tim.getSifraTima())){
                    throw new Exception("Vec ste uneli tim sa ovim nazivom");
                }
                if(tim1.getTrener().getIdClana().equals(tim.getTrener().getIdClana()) && !tim1.getSifraTima().equals(tim.getSifraTima())){
                    throw new Exception("Vec ste uneli ekipu za ovog trenera");
                }
            }
        }
    }
}

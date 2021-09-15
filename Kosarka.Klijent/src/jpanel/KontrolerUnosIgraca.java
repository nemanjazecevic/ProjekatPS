/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.Igrac;
import entities.Tim;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Operation;
import komunikacija.Response;
import transfer.Komunikacija;

/**
 *
 * @author PC
 */
public class KontrolerUnosIgraca {
    private static KontrolerUnosIgraca instance;
    private UnosIgraca jpanel;
    private Igrac igrac;
    private KontrolerUnosIgraca() {
    }

    public static KontrolerUnosIgraca getInstance() {
        if (instance == null) {
            instance = new KontrolerUnosIgraca();
        }
        return instance;
    }

    public UnosIgraca getJpanel(Igrac igrac) {
        jpanel = new UnosIgraca();
        osluskivaciAkcija();
        inicijalnoUcitavanje();
        if(igrac == null) {
            jpanel.getjBtnSacuvaj().setText("Sacuvaj");
        }else{
            jpanel.getjBtnSacuvaj().setText("Izmeni");
            this.igrac = igrac; 
            jpanel.getjTxtIme().setText(igrac.getIme());
            jpanel.getjTxtPrezime().setText(igrac.getPrezime());
            jpanel.getjTxtJmbg().setText(igrac.getJmbg());
            jpanel.getjTxtDatum().setText(igrac.getDatumRodjenja().toString());
            jpanel.getjTxtBrojNaDresu().setText( String.valueOf(igrac.getBrojNaDresu()));
            jpanel.getjTxtVisina().setText(String.valueOf(igrac.getVisina()));
            jpanel.getjCmbTim().setSelectedItem(igrac.getTim());
        }
        jpanel.setVisible(true);
        return jpanel;
    }

    private void osluskivaciAkcija() {
        this.jpanel.getjBtnSacuvaj().addActionListener(e -> osluckivacSacuvaj());
    }
    
    private void osluckivacSacuvaj() {
        try {
            Response res =  Komunikacija.getInstance().GetResponse(kreirajIgraca(), Operation.ZapamtiIgraca);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je zapamtio igraca", "", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da zapamti igraca", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicijalnoUcitavanje() {
        try {
            Response res =  Komunikacija.getInstance().GetResponse(new Tim(), Operation.VratiListuTimova);
            if(res.isZnak()){
                List<Tim> lista = (List<Tim>) res.getObjekat();
                for (Tim tim : lista) {
                    jpanel.getjCmbTim().addItem(tim);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Igrac kreirajIgraca() throws Exception {
        
        String ime = jpanel.getjTxtIme().getText();
        String prezime = jpanel.getjTxtPrezime().getText();
        String jmbg = jpanel.getjTxtJmbg().getText();
        if(ime.isEmpty() || prezime.isEmpty() || jmbg.isEmpty() || jpanel.getjTxtBrojNaDresu().getText().isEmpty()
                || jpanel.getjTxtVisina().getText().isEmpty()){
            throw new Exception("Morate popuniti sva polja. Obavezna su");
        }
        int broj = 0;
        try{
            broj =  Integer.parseInt(jpanel.getjTxtBrojNaDresu().getText());
        }catch(Exception ex){
            throw new Exception("Broj na dresu mora biti broj");
        }
        if(broj <= 0) {
            throw new Exception("Broj na dresu mora biti veci od 0");
        }
        double visina = 0;
        try{
            visina =  Double.parseDouble(jpanel.getjTxtVisina().getText());
        }catch(Exception ex){
            throw new Exception("Visina mora biti broj");
        }
        
        Date datum;
        try{
            datum = new SimpleDateFormat("yyyy-MM-dd").parse(jpanel.getjTxtDatum().getText());
        }catch(Exception e){
            throw new Exception("Unesite ispravan format datuma(yyyy-MM-dd)");
        }
        Tim t = (Tim) jpanel.getjCmbTim().getSelectedItem();
        Long id = new Long(0);
        if(igrac != null){
            id = igrac.getIdClana();
        }
        
        if(jmbg.trim().length() != 13){
            throw new Exception("JMBG mora imati tacno 13 cifara");
        }
        Igrac igrac = new Igrac(id, ime, prezime, jmbg, datum, broj, visina, t);
        return igrac;
    }
}

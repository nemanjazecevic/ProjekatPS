/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.Igrac;
import entities.Tim;
import entities.Trener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class KontrolerUnosTrenera {
    private static KontrolerUnosTrenera instance;
    private UnosTrenera jpanel;
    private Trener trener;
    private KontrolerUnosTrenera() {
    }

    public static KontrolerUnosTrenera getInstance() {
        if (instance == null) {
            instance = new KontrolerUnosTrenera();
        }
        return instance;
    }

    public UnosTrenera getJpanel(Trener trener) {
        jpanel = new UnosTrenera();
        osluskivaciAkcija();
        
        if(trener == null) {
            jpanel.getjBtnSacuvaj().setText("Sacuvaj");
        }else{
            jpanel.getjBtnSacuvaj().setText("Izmeni");
            this.trener = trener; 
            jpanel.getjTxtIme().setText(trener.getIme());
            jpanel.getjTxtPrezime().setText(trener.getPrezime());
            jpanel.getjTxtJmbg().setText(trener.getJmbg());
            jpanel.getjTxtDatum().setText(trener.getDatumRodjenja().toString());
            jpanel.getjTxtLicenca().setText( trener.getBrojLicence());
        }
        
        jpanel.setVisible(true);
        return jpanel;
    }

    private void osluskivaciAkcija() {
        this.jpanel.getjBtnSacuvaj().addActionListener(e -> osluckivacSacuvaj());
    }
    
    private void osluckivacSacuvaj() {
        
        try {
            Response res =  Komunikacija.getInstance().GetResponse(kreirajTrenera(), Operation.ZapamtiTrenera);
            if(res.isZnak()){
                JOptionPane.showMessageDialog(jpanel, "Sistem je zapamtio trenera", "", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(jpanel, "Sistem ne moze da zapamti trenera", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jpanel, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(KontrolerUnosTrenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Trener kreirajTrenera() throws Exception {
        String ime = jpanel.getjTxtIme().getText();
        String prezime = jpanel.getjTxtPrezime().getText();
        String jmbg = jpanel.getjTxtJmbg().getText();
        String licenca = jpanel.getjTxtLicenca().getText();
        
        if(ime.isEmpty() || prezime.isEmpty() || jmbg.isEmpty() 
                || jpanel.getjTxtDatum().getText().isEmpty()){
            throw new Exception("Morate popuniti sva polja. Obavezna su");
        }
        
        Date datum;
        try{
            datum = new SimpleDateFormat("yyyy-MM-dd").parse(jpanel.getjTxtDatum().getText());
        }catch(Exception e){
            throw new Exception("Unesite ispravan format datuma(yyyy-MM-dd)");
        }
        Long id = new Long(0);
        if(trener != null){
            id = trener.getIdClana();
        }
        if(jmbg.trim().length() != 13){
            throw new Exception("JMBG mora imati tacno 13 cifara");
        }
        
        Trener trener = new Trener(id, ime, prezime, jmbg,datum,licenca);
        return trener;
    }

   
}

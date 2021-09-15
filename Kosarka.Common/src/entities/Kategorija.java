/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Kategorija implements IDomenskiObjekat, Serializable{
    private Long sifraKategorije; 
    private String nazivKategorije;

    public Kategorija(Long sifraKategorije, String nazivKategorije) {
        this.sifraKategorije = sifraKategorije;
        this.nazivKategorije = nazivKategorije;
    }

    public Kategorija() {
    }

    @Override
    public String toString() {
        return nazivKategorije;
    }
    

    public Long getSifraKategorije() {
        return sifraKategorije;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategorija other = (Kategorija) obj;
        if (!Objects.equals(this.nazivKategorije, other.nazivKategorije)) {
            return false;
        }
        if (!Objects.equals(this.sifraKategorije, other.sifraKategorije)) {
            return false;
        }
        return true;
    }

    public void setSifraKategorije(Long sifraKategorije) {
        this.sifraKategorije = sifraKategorije;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    @Override
    public String nazivTabeleBaza() {
        return "Kategorija";
    }

    @Override
    public String nazivTabeleBazaPlus() {
        return "Kategorija k";
    }

    @Override
    public String vrednostiUnosBaza() {
        return ""; 
    }

    @Override
    public String naziviKolonaUnosBaza() {
        return "";
    }

    @Override
    public String vrednostiIzmenaBaza() {
        return "";
    }

    @Override
    public String joinUslovBaza() {
        return "";
    }

    @Override
    public String whereUslovBaza() {
        return "";
    }

    @Override
    public List<IDomenskiObjekat> objektiBaza(ResultSet rs) {
        List<IDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                
                Long sifraKat = rs.getLong("sifraKategorije");
                String nazivKat = rs.getString("nazivKategorije");
                Kategorija kat = new Kategorija(sifraKat, nazivKat);
                lista.add(kat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kategorija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class ClanKluba implements IDomenskiObjekat, Serializable{
    public Long idClana; 
    public String ime; 
    public String prezime; 
    public String jmbg; 
    public Date datumRodjenja;

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final ClanKluba other = (ClanKluba) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        if (!Objects.equals(this.idClana, other.idClana)) {
            return false;
        }
        if (!Objects.equals(this.datumRodjenja, other.datumRodjenja)) {
            return false;
        }
        return true;
    }

    public ClanKluba() {
    }

    public Long getIdClana() {
        return idClana;
    }

    public void setIdClana(Long idClana) {
        this.idClana = idClana;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public ClanKluba(Long idClana, String ime, String prezime, String jmbg, Date datumRodjenja) {
        this.idClana = idClana;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public String nazivTabeleBaza() {
        return "ClanKluba";
    }

    @Override
    public String nazivTabeleBazaPlus() {
        return "ClanKluba ck";
    }

    @Override
    public String vrednostiUnosBaza() {
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + ime + "', '" + prezime + "','" + jmbg + "','" + form.format(datumRodjenja) + "'"; 
    }

    @Override
    public String naziviKolonaUnosBaza() {
        return "ime,prezime,jmbg,datumRodjenja";
    }

    @Override
    public String vrednostiIzmenaBaza() {
        DateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        return "ime = '" + ime + "', prezime =  '" + prezime + "',jmbg = '" + jmbg + "',datumRodjenja = '" + form.format(datumRodjenja) + "'"; 
    }

    @Override
    public String joinUslovBaza() {
        return "";
    }

    @Override
    public String whereUslovBaza() {
        return "idClana = " + idClana;
    }

    @Override
    public List<IDomenskiObjekat> objektiBaza(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

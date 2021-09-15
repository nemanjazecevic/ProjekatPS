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
public class Tim implements IDomenskiObjekat, Serializable{
    private Long sifraTima;
    private String nazivTima; 
    private Kategorija kategorija; 
    private Trener trener;

    public Tim(Long sifraTima, String nazivTima, Kategorija kategorija, Trener trener) {
        this.sifraTima = sifraTima;
        this.nazivTima = nazivTima;
        this.kategorija = kategorija;
        this.trener = trener;
    }

    public Tim() {
    }

    @Override
    public String toString() {
        return nazivTima;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Tim other = (Tim) obj;
        if (!Objects.equals(this.nazivTima, other.nazivTima)) {
            return false;
        }
        if (!Objects.equals(this.sifraTima, other.sifraTima)) {
            return false;
        }
        if (!Objects.equals(this.kategorija, other.kategorija)) {
            return false;
        }
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        return true;
    }

    public Long getSifraTima() {
        return sifraTima;
    }

    public void setSifraTima(Long sifraTima) {
        this.sifraTima = sifraTima;
    }

    public String getNazivTima() {
        return nazivTima;
    }

    public void setNazivTima(String nazivTima) {
        this.nazivTima = nazivTima;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }
    
      @Override
    public String nazivTabeleBaza() {
        return "Tim";
    }

    @Override
    public String nazivTabeleBazaPlus() {
        return "Tim t";
    }

    @Override
    public String vrednostiUnosBaza() {
        return "'" + nazivTima + "', " + trener.getIdClana() + ", " + kategorija.getSifraKategorije(); 
    }

    @Override
    public String naziviKolonaUnosBaza() {
        return "nazivTima, idClana, sifraKategorije";
    }

    @Override
    public String vrednostiIzmenaBaza() {
        return "nazivTima = '" + nazivTima + "',idClana =  " + trener.getIdClana() + ",sifraKategorije = " + kategorija.getSifraKategorije();
    }

    @Override
    public String joinUslovBaza() {
        return "join Kategorija k on (k.sifraKategorije = t.sifraKategorije) join Trener tr on (t.idClana = tr.idClana) join ClanKluba ck on (ck.idClana = tr.idClana)";
    }

    @Override
    public String whereUslovBaza() {
        return "sifraTima = " + sifraTima;
    }

    @Override
    public List<IDomenskiObjekat> objektiBaza(ResultSet rs) {
        List<IDomenskiObjekat> lista = new ArrayList();
        try {
            while(rs.next()){
                Long id = rs.getLong("idClana");
                String ime  = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String jmbg = rs.getString("jmbg");
                Date datum = rs.getDate("datumRodjenja");
                String brojLicence = rs.getString("brojLicence");
                
                Trener trener = new Trener(id, ime, prezime, jmbg, datum, brojLicence);
                
                Long timId = rs.getLong("sifraTima");
                String naziv = rs.getString("nazivTima");
                
                Long sifraKat = rs.getLong("sifraKategorije");
                String nazivKat = rs.getString("nazivKategorije");
                Kategorija kat = new Kategorija(sifraKat, nazivKat);
                
                Tim tim = new Tim(timId, naziv, kat, trener);
                lista.add(tim);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
    
}

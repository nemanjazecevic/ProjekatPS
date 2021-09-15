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
public class Trener extends ClanKluba implements IDomenskiObjekat, Serializable{
    private String brojLicence; 

    public Trener() {
    }

    public Trener(Long idClana, String ime, String prezime, String jmbg, Date datumRodjenja, String brojLicence) {
        super(idClana, ime, prezime, jmbg, datumRodjenja);
        this.brojLicence = brojLicence;
        
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
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.brojLicence, other.brojLicence)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public String getBrojLicence() {
        return brojLicence;
    }

    public void setBrojLicence(String brojLicence) {
        this.brojLicence = brojLicence;
    }
    
     @Override
    public String nazivTabeleBaza() {
        return "Trener";
    }

    @Override
    public String nazivTabeleBazaPlus() {
        return "Trener t";
    }

    @Override
    public String vrednostiUnosBaza() {
//        return "'" + ime + "', '" + prezime + "','" + jmbg + "','" + datumRodjenja + "', " + brojNaDresu + ", " + visina + ", " + tim.getSifraTima(); 
          return  idClana + ", '" + brojLicence + "'"; 
    }

    @Override
    public String naziviKolonaUnosBaza() {
        return "idClana,brojLicence";
    }

    @Override
    public String vrednostiIzmenaBaza() {
        return "brojLicence =  '" + brojLicence + "'";
    }

    @Override
    public String joinUslovBaza() {
        return "join ClanKluba ck on (ck.idClana = t.idClana)";
    }

    @Override
    public String whereUslovBaza() {
        return "idClana = " + idClana;
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
                lista.add(trener);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
    
    
}

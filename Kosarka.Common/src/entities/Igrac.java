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
public class Igrac extends ClanKluba implements IDomenskiObjekat, Serializable{
    private int brojNaDresu; 
    private double visina; 
    private Tim tim; 

    public Igrac() {
    }

    public Igrac(Long idClana, String ime, String prezime, String jmbg, Date datumRodjenja, int brojNaDresu, double visina, Tim tim) {
        super(idClana, ime, prezime, jmbg, datumRodjenja);
        this.brojNaDresu = brojNaDresu;
        this.visina = visina; 
        this.tim = tim;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " - " + tim.getNazivTima();
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
        final Igrac other = (Igrac) obj;
        if (this.brojNaDresu != other.brojNaDresu) {
            return false;
        }
        if (Double.doubleToLongBits(this.visina) != Double.doubleToLongBits(other.visina)) {
            return false;
        }
        if (!Objects.equals(this.tim, other.tim)) {
            return false;
        }
        return true;
    }

    public int getBrojNaDresu() {
        return brojNaDresu;
    }

    public void setBrojNaDresu(int brojNaDresu) {
        this.brojNaDresu = brojNaDresu;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }
    
    @Override
    public String nazivTabeleBaza() {
        return "Igrac";
    }

    @Override
    public String nazivTabeleBazaPlus() {
        return "Igrac i";
    }

    @Override
    public String vrednostiUnosBaza() {
        return idClana + "," + brojNaDresu + ", " + visina + ", " + tim.getSifraTima(); 
    }

    @Override
    public String naziviKolonaUnosBaza() {
        return "idClana, brojNaDresu, visina, sifraTima";
    }

    @Override
    public String vrednostiIzmenaBaza() {
        return "brojNaDresu = " + brojNaDresu + ", visina = " + visina + ", sifraTima = " + tim.getSifraTima(); 
    }

    @Override
    public String joinUslovBaza() {
        return "join ClanKluba ck on (ck.idClana = i.idClana) join Tim t on (i.sifraTima = t.sifraTima)";
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
                int broj = rs.getInt("brojNaDresu");
                double visina = rs.getDouble("visina");
                
                Long timId = rs.getLong("sifraTima");
                String naziv = rs.getString("nazivTima");
                Tim tim = new Tim(timId, naziv, null, null);
                
                Igrac igrac = new Igrac(id, ime, prezime, jmbg, datum, broj, visina, tim);
                lista.add(igrac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Igrac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}

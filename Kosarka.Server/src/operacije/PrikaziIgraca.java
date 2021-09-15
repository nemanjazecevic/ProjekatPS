/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Igrac;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class PrikaziIgraca extends OpstaSO{
    public PrikaziIgraca(IDomenskiObjekat idomenskiobjekat){
        this.idomenskiobjekat = idomenskiobjekat;
    }
    
    @Override
    protected void so() throws Exception {
        Igrac ig = (Igrac) idomenskiobjekat;
        List<Igrac> lista = broker.getObjects((Igrac) idomenskiobjekat).stream().map(Igrac.class::cast).collect(Collectors.toList());
        for (Igrac igrac : lista) {
            if(igrac.getIdClana().equals(ig.getIdClana())){
                idomenskiobjekat = igrac; 
                return;
            }
        }
        throw new Exception("Ne postoji korisnik sa idem " + ig.getIdClana());
    }
}

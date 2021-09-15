/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Igrac;
import entities.Tim;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class PrikaziTim extends OpstaSO{
    public PrikaziTim(IDomenskiObjekat idomenskiobjekat){
        this.idomenskiobjekat = idomenskiobjekat;
    }
    
    @Override
    protected void so() throws Exception {
        Tim t = (Tim) idomenskiobjekat;
        List<Tim> lista = broker.getObjects((Tim) idomenskiobjekat).stream().map(Tim.class::cast).collect(Collectors.toList());
        for (Tim tim : lista) {
            if(tim.getSifraTima().equals(tim.getSifraTima())){
                idomenskiobjekat = tim; 
                return;
            }
        }
        throw new Exception("Ne postoji korisnik sa idem " + t.getSifraTima());
    }
}

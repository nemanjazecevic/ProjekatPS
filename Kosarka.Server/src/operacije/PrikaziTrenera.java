/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Igrac;
import entities.Trener;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class PrikaziTrenera extends OpstaSO{
    public PrikaziTrenera(IDomenskiObjekat idomenskiobjekat){
        this.idomenskiobjekat = idomenskiobjekat;
    }
    
    @Override
    protected void so() throws Exception {
        Trener tr = (Trener) idomenskiobjekat;
        List<Trener> lista = broker.getObjects((Trener) idomenskiobjekat).stream().map(Trener.class::cast).collect(Collectors.toList());
        for (Trener trener : lista) {
            if(trener.getIdClana().equals(tr.getIdClana())){
                idomenskiobjekat = trener; 
                return;
            }
        }
        throw new Exception("Ne postoji trener sa idem " + tr.getIdClana());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.ClanKluba;
import entities.IDomenskiObjekat;
import entities.Igrac;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class ObrisiIgraca extends OpstaSO{

    public ObrisiIgraca(IDomenskiObjekat idomenskiobjekat){
        this.idomenskiobjekat = idomenskiobjekat;
    }
    
    @Override
    protected void so() throws Exception {
        Igrac igrac = (Igrac) idomenskiobjekat;
        broker.deleteObject(igrac);
        ClanKluba clan = new ClanKluba();
        clan.setIdClana(igrac.getIdClana());
        broker.deleteObject(clan);
    }
    
}

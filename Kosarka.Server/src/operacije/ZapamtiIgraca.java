/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.ClanKluba;
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
public class ZapamtiIgraca extends OpstaSO{
    public ZapamtiIgraca(IDomenskiObjekat idomenskiobjekat){
        this.idomenskiobjekat = idomenskiobjekat;
    }
    
    @Override
    protected void so() throws Exception {
        Igrac igrac = (Igrac) idomenskiobjekat;
        ClanKluba clan = new ClanKluba(igrac.getIdClana(), igrac.getIme(), igrac.getPrezime(), igrac.getJmbg(), igrac.getDatumRodjenja());
        Provera(clan);
        if(clan.getIdClana() > 0){
            broker.updateObject(clan);
            broker.updateObject(igrac);
        }else{
            Long id = broker.insertObject(clan);
            igrac.setIdClana(id);
            broker.insertObject(igrac);
        }
        
    }

    private void Provera(ClanKluba clan) throws Exception{
        List<Igrac> igraci = broker.getObjects(new Igrac()).stream().map(Igrac.class::cast).collect(Collectors.toList());
        List<Trener> treneri = broker.getObjects(new Trener()).stream().map(Trener.class::cast).collect(Collectors.toList());
        for (Igrac igrac : igraci) {
            if(igrac.getJmbg().equals(clan.getJmbg()) && !igrac.getIdClana().equals(clan.getIdClana())){
                throw new Exception("Postoji");
            }
        }
        for (Trener trener : treneri) {
            if(trener.getJmbg().equals(clan.getJmbg()) && !trener.getIdClana().equals(clan.getIdClana())){
                throw new Exception("Postoji");
            }
        }
    }
}

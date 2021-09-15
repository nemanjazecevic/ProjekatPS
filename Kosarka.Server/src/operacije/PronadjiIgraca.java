/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Igrac;
import entities.KriterijumPretrage;
import entities.Trener;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class PronadjiIgraca extends OpstaSO{
    List<Igrac> igraci;
    public PronadjiIgraca(IDomenskiObjekat idomenskiobjekat,List<Igrac> igraci){
        this.idomenskiobjekat = idomenskiobjekat;
        this.igraci = igraci;
    }
    
    @Override
    protected void so() throws Exception {
        KriterijumPretrage kp = (KriterijumPretrage) idomenskiobjekat;
        List<Igrac> lista = broker.getObjects(new Igrac()).stream().map(Igrac.class::cast)
                .filter(e -> e.getIme().contains(kp.getText()) || e.getPrezime().contains(kp.getText()) || e.jmbg.equals(kp.getText()) || e.getTim().getNazivTima().contains(kp.getText()))
                .collect(Collectors.toList());
        if(lista == null || lista.size() == 0) {
            throw new Exception();
        }
        igraci.addAll(lista);
    }
}

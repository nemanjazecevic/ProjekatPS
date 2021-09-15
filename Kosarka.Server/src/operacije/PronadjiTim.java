/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.KriterijumPretrage;
import entities.Tim;
import entities.Trener;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class PronadjiTim extends OpstaSO{
    List<Tim> timovi;
    public PronadjiTim(IDomenskiObjekat idomenskiobjekat,List<Tim> timovi){
        this.idomenskiobjekat = idomenskiobjekat;
         this.timovi = timovi;
    }
    
    @Override
    protected void so() throws Exception {
        KriterijumPretrage kp = (KriterijumPretrage) idomenskiobjekat;
        List<Tim> lista = broker.getObjects(new Tim()).stream().map(Tim.class::cast)
                .filter(e -> e.getNazivTima().contains(kp.getText()) || e.getKategorija().getNazivKategorije().contains(kp.getText()) || e.getSifraTima().toString().equals(kp.getText()))
                .collect(Collectors.toList());
        if(lista == null || lista.size() == 0) {
            throw new Exception();
        }
        timovi.addAll(lista);
    }
}

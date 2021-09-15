/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.KriterijumPretrage;
import entities.Trener;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class PronadjiTrenera extends OpstaSO{
    List<Trener> treneri;
    public PronadjiTrenera(IDomenskiObjekat idomenskiobjekat,List<Trener> treneri){
        this.idomenskiobjekat = idomenskiobjekat;
         this.treneri = treneri;
    }
    
    @Override
    protected void so() throws Exception {
        KriterijumPretrage kp = (KriterijumPretrage) idomenskiobjekat;
        List<Trener> lista = broker.getObjects(new Trener()).stream().map(Trener.class::cast)
                .filter(e -> e.getIme().contains(kp.getText()) || e.getPrezime().contains(kp.getText()) || e.jmbg.equals(kp.getText()) || e.getBrojLicence().contains(kp.getText()))
                .collect(Collectors.toList());
        if(lista == null || lista.size() == 0) {
            throw new Exception();
        }
        treneri.addAll(lista);
    }
}

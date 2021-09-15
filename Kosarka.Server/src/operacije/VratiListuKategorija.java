/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Igrac;
import entities.Kategorija;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class VratiListuKategorija extends OpstaSO{
    List<Kategorija> kategorije;
    public VratiListuKategorija(IDomenskiObjekat idomenskiobjekat,List<Kategorija> kategorije){
        this.idomenskiobjekat = idomenskiobjekat;
         this.kategorije = kategorije;
    }
    
    @Override
    protected void so() throws Exception {
        List<Kategorija> lista = broker.getObjects((Kategorija) idomenskiobjekat).stream().map(Kategorija.class::cast).collect(Collectors.toList());
        kategorije.addAll(lista);
    }
}

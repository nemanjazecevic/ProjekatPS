/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Kategorija;
import entities.Tim;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class VratiListuTimova extends OpstaSO{
    List<Tim> timovi;
    public VratiListuTimova(IDomenskiObjekat idomenskiobjekat,List<Tim> timovi){
        this.idomenskiobjekat = idomenskiobjekat;
         this.timovi = timovi;
    }
    
    @Override
    protected void so() throws Exception {
        List<Tim> lista = broker.getObjects((Tim) idomenskiobjekat).stream().map(Tim.class::cast).collect(Collectors.toList());
        timovi.addAll(lista);
    }
}

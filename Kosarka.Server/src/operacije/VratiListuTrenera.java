/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Tim;
import entities.Trener;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class VratiListuTrenera extends OpstaSO{
    List<Trener> treneri;
    public VratiListuTrenera(IDomenskiObjekat idomenskiobjekat,List<Trener> treneri){
        this.idomenskiobjekat = idomenskiobjekat;
         this.treneri = treneri;
    }
    
    @Override
    protected void so() throws Exception {
        List<Trener> lista = broker.getObjects((Trener) idomenskiobjekat).stream().map(Trener.class::cast).collect(Collectors.toList());
        treneri.addAll(lista);
    }
}

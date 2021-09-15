/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import entities.IDomenskiObjekat;
import entities.Igrac;
import java.util.List;
import java.util.stream.Collectors;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class VratiListuIgraca extends OpstaSO{
    List<Igrac> igraci;
    public VratiListuIgraca(IDomenskiObjekat idomenskiobjekat,List<Igrac> igraci){
        this.idomenskiobjekat = idomenskiobjekat;
         this.igraci = igraci;
    }
    
    @Override
    protected void so() throws Exception {
        List<Igrac> lista = broker.getObjects((Igrac) idomenskiobjekat).stream().map(Igrac.class::cast).collect(Collectors.toList());
        igraci.addAll(lista);
    }
}

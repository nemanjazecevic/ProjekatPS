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
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class ZapamtiTim extends OpstaSO{
    List<Tim> lista;
    public ZapamtiTim(IDomenskiObjekat idomenskiobjekat, List<Tim> lista){
        this.idomenskiobjekat = idomenskiobjekat;
        this.lista = lista;
    }
    
    @Override
    protected void so() throws Exception {
        if(lista.size() > 0){
            for (Tim tim : lista) {
                broker.insertObject(tim);
            }
        }else{
            Tim tim = (Tim) idomenskiobjekat;
            broker.updateObject(tim);
        }
        
    }
}

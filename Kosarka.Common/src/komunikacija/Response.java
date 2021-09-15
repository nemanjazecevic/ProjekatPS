/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Response implements Serializable{
    private boolean znak;
    private Object objekat; 

    public Response(boolean znak, Object objekat) {
        this.znak = znak;
        this.objekat = objekat;
    }

    public boolean isZnak() {
        return znak;
    }

    public void setZnak(boolean znak) {
        this.znak = znak;
    }

    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }
    
}

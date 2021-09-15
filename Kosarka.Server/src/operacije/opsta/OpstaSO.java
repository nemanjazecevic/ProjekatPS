/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije.opsta;

import broker.Broker;
import entities.IDomenskiObjekat;

/**
 *
 * @author PC
 */
public abstract class OpstaSO {
    protected Broker broker;
    protected IDomenskiObjekat idomenskiobjekat;

    public OpstaSO() {
        broker = new Broker();
    }
    protected abstract void so() throws Exception;

    public void execute() throws Exception {
        broker.connect();
        try {
            so();
            broker.commit();
        } catch (Exception ex) {
            broker.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            broker.disconnect();
        }
    }

    public IDomenskiObjekat getIdomenskiobjekat() {
        return idomenskiobjekat;
    }

}

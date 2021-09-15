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
public class Request implements Serializable{
    private Operation operacija;
    private Object object;

    public Request(Operation operacija, Object object) {
        this.operacija = operacija;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
   

    public Operation getOperacija() {
        return operacija;
    }

    public void setOperacija(Operation operacija) {
        this.operacija = operacija;
    }
    
    
    
}

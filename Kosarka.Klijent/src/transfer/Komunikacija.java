/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Operation;
import komunikacija.Request;
import komunikacija.Response;

/**
 *
 * @author PC
 */
public class Komunikacija {
    private static Komunikacija instance;

    private final Socket soket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    

    private Komunikacija() throws IOException {
        soket = new Socket("localhost", 9000);
        output = new ObjectOutputStream(soket.getOutputStream());
        input = new ObjectInputStream(soket.getInputStream());
    }
    public static Komunikacija getInstance() throws IOException {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public Response GetResponse(Object object, Operation operation) {
        try {
            Request request = new Request(operation,object);
            output.writeObject(request);
            return (Response) input.readObject();
        } catch (Exception ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
}

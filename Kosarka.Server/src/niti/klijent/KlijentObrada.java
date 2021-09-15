/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti.klijent;

import apl_logika.Kontroler;
import java.io.EOFException;
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
public class KlijentObrada extends Thread{
    private final Socket nitKlijent;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    public KlijentObrada(Socket socket) throws IOException {
        this.nitKlijent = socket;
        this.input = new ObjectInputStream(socket.getInputStream());
        this.output = new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket getNitKlijent() {
        return nitKlijent;
    }


    @Override
    public void run() {
        while (nitKlijent.isClosed() == false) {
            try {
                Request request = (Request) input.readObject();
                Response response = GetResponse(request);
                this.output.writeObject(response);
            } catch (Exception ex) {
                try {
                    nitKlijent.close();
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(KlijentObrada.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } 
        }
    }
    private Response GetResponse(Request request){
        Response response = null;
        try{
            switch(request.getOperacija()){
                case ZapamtiIgraca: return Kontroler.getInstance().ZapamtiIgraca(request);
                case ZapamtiTrenera: return Kontroler.getInstance().ZapamtiTrenera(request);
                case ZapamtiTim: return Kontroler.getInstance().ZapamtiTim(request);
                case PronadjiIgraca: return Kontroler.getInstance().PronadjiIgraca(request);
                case PrikaziIgraca: return Kontroler.getInstance().PrikaziIgraca(request);
                case VratiListuIgraca: return Kontroler.getInstance().VratiListuIgraca(request);
                case PronadjiTrenera: return Kontroler.getInstance().PronadjiTrenera(request);
                case PrikaziTrenera: return Kontroler.getInstance().PrikaziTrenera(request);
                case VratiListuTrenera: return Kontroler.getInstance().VratiListuTrenera(request);
                case PronadjiTim: return Kontroler.getInstance().PronadjiTim(request);
                case PrikaziTim: return Kontroler.getInstance().PrikaziTim(request);
                case VratiListuTimova: return Kontroler.getInstance().VratiListuTimova(request);
                case ObrisiIgraca: return Kontroler.getInstance().ObrisiIgraca(request);
                case VratiListuKategorija: return Kontroler.getInstance().VratiListuKategorija(request);
                default: return new Response(false,null);
            }
            
        }catch(Exception ex){
            return new Response(false, null);
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import niti.klijent.KlijentObrada;

/**
 *
 * @author PC
 */
public class Server extends Thread{
    private final List<KlijentObrada> nitiKlijent;
    private final ServerSocket server;

    public Server() throws IOException {
        this.nitiKlijent = new ArrayList();
        this.server = new ServerSocket(9000);
    }

    public ServerSocket getServer() {
        return server;
    }

    @Override
    public void run() {
        while (server.isClosed() == false) {
            System.out.println("Cekaj korisnike nove");
            try {
                Socket socket = server.accept();
                KlijentObrada klijentObrada = new KlijentObrada(socket);
                nitiKlijent.add(klijentObrada);
                klijentObrada.start();
            } catch (SocketException ex) {
                System.out.println("Greska. Pukao je klijent");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        for (KlijentObrada nitKlijent : nitiKlijent) {
            try {
                nitKlijent.getNitKlijent().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void zaustaviServer() throws IOException {
        server.close();
    }
}

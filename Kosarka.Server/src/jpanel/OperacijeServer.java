/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import java.io.IOException;
import niti.server.Server;

/**
 *
 * @author PC
 */
public class OperacijeServer {
    private static OperacijeServer instance;

    public static OperacijeServer getInstance() {
        if(instance == null){
            instance = new OperacijeServer();
        }
        return instance; 
    }
    private Server nitServer;
    
    private OperacijeServer(){}
    
    public void pokreniServer() throws IOException{
        if(nitServer == null || nitServer.isAlive() == false){
            nitServer = new Server();
            nitServer.start();
        }
    }

    public void zaustaviServer() throws IOException {
        if(nitServer.getServer().isBound()){
            nitServer.zaustaviServer();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apl_logika;

import entities.Igrac;
import entities.Kategorija;
import entities.KriterijumPretrage;
import entities.Tim;
import entities.Trener;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Request;
import komunikacija.Response;
import operacije.ObrisiIgraca;
import operacije.PrikaziIgraca;
import operacije.PrikaziTim;
import operacije.PrikaziTrenera;
import operacije.PronadjiIgraca;
import operacije.PronadjiTim;
import operacije.PronadjiTrenera;
import operacije.VratiListuIgraca;
import operacije.VratiListuKategorija;
import operacije.VratiListuTimova;
import operacije.VratiListuTrenera;
import operacije.ZapamtiIgraca;
import operacije.ZapamtiTim;
import operacije.ZapamtiTrenera;
import operacije.opsta.OpstaSO;

/**
 *
 * @author PC
 */
public class Kontroler {
    private static Kontroler instance; 
    private Kontroler() {
    
    }

    public static Kontroler getInstance() {
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }

    public Response ZapamtiIgraca(Request request) throws Exception{
        OpstaSO opstaSo = new ZapamtiIgraca((Igrac) request.getObject());
        opstaSo.execute();
        return new Response(true, request);
    }

    public Response ZapamtiTrenera(Request request) throws Exception {
        OpstaSO opstaSo = new ZapamtiTrenera((Trener) request.getObject());
        opstaSo.execute();
        return new Response(true, request);
    }

    public Response ZapamtiTim(Request request) throws Exception {
        OpstaSO opstaSo;
        try{
           opstaSo = new ZapamtiTim((Tim) request.getObject(), new ArrayList());
        }catch(Exception e){
            opstaSo = new ZapamtiTim(new Tim(),(List<Tim>) request.getObject());
        }
        opstaSo.execute();
        return new Response(true, request);
    }

    public Response PronadjiIgraca(Request request) throws Exception{
        List<Igrac> lista = new ArrayList();
        OpstaSO opstaSo = new PronadjiIgraca((KriterijumPretrage) request.getObject(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }

    public Response PrikaziIgraca(Request request) throws Exception{
        OpstaSO opstaSo = new PrikaziIgraca((Igrac) request.getObject());
        opstaSo.execute();
        return new Response(true, (Igrac)opstaSo.getIdomenskiobjekat());
    }

    public Response VratiListuIgraca(Request request) throws Exception{
        List<Igrac> lista = new ArrayList();
        OpstaSO opstaSo = new VratiListuIgraca(new Igrac(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }

    public Response PronadjiTrenera(Request request) throws Exception{
        List<Trener> lista = new ArrayList();
        OpstaSO opstaSo = new PronadjiTrenera((KriterijumPretrage) request.getObject(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }

    public Response PrikaziTrenera(Request request) throws Exception{
        OpstaSO opstaSo = new PrikaziTrenera((Trener) request.getObject());
        opstaSo.execute();
        return new Response(true, (Trener)opstaSo.getIdomenskiobjekat());
    }

    public Response VratiListuTrenera(Request request) throws Exception{
        List<Trener> lista = new ArrayList();
        OpstaSO opstaSo = new VratiListuTrenera(new Trener(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }

    public Response PronadjiTim(Request request) throws Exception{
        List<Tim> lista = new ArrayList();
        OpstaSO opstaSo = new PronadjiTim((KriterijumPretrage) request.getObject(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }

    public Response PrikaziTim(Request request) throws Exception{
        OpstaSO opstaSo = new PrikaziTim((Tim) request.getObject());
        opstaSo.execute();
        return new Response(true, (Tim)opstaSo.getIdomenskiobjekat());
    }

    public Response VratiListuTimova(Request request) throws Exception{
        List<Tim> lista = new ArrayList();
        OpstaSO opstaSo = new VratiListuTimova(new Tim(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }

    public Response ObrisiIgraca(Request request) throws Exception {
        OpstaSO opstaSo = new ObrisiIgraca((Igrac) request.getObject());
        opstaSo.execute();
        return new Response(true, request);
    }

    public Response VratiListuKategorija(Request request) throws Exception{
        List<Kategorija> lista = new ArrayList();
        OpstaSO opstaSo = new VratiListuKategorija((Kategorija) request.getObject(),lista);
        opstaSo.execute();
        return new Response(true, lista);
    }
}


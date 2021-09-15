/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IDomenskiObjekat {
    public String nazivTabeleBaza();
    public String nazivTabeleBazaPlus();
    public String vrednostiUnosBaza();
    public String naziviKolonaUnosBaza();
    public String vrednostiIzmenaBaza();
    public String joinUslovBaza();
    public String whereUslovBaza();
    public List<IDomenskiObjekat> objektiBaza(ResultSet rs);
}

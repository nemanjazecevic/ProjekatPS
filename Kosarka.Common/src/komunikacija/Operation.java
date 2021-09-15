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
public enum Operation implements Serializable{
    ZapamtiIgraca,
    ZapamtiTrenera,
    ZapamtiTim,
    PronadjiIgraca,
    PrikaziIgraca,
    VratiListuIgraca,
    PronadjiTrenera, 
    PrikaziTrenera, 
    VratiListuTrenera, 
    PronadjiTim, 
    PrikaziTim,
    VratiListuTimova,
    ObrisiIgraca,
    VratiListuKategorija
}

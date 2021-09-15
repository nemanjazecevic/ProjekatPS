/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import java.awt.Font;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author PC
 */
public class KontrolerPanel {
    private Panel jpanel;
    private static KontrolerPanel instance;

    private KontrolerPanel() {
    }

    public static KontrolerPanel getInstance() {
        if (instance == null) {
            instance = new KontrolerPanel();
        }
        return instance;
    }

     public Panel getJpanel() {
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            podesiIzgledJpanel(new javax.swing.plaf.FontUIResource(new Font("Verdana", Font.BOLD, 12)));
        } catch (Exception e) {
        } 
        jpanel = new Panel();
        osluskivaciDogadjaja();
        jpanel.setVisible(true);
        return jpanel;
    }
   
    private void osluskivaciDogadjaja() {
        this.jpanel.getjButton1().addActionListener(e -> osluskivacPokreni());
        this.jpanel.getjButton2().addActionListener(e -> osluskivacZaustavi());
    }


    private void osluskivacPokreni() {
        try {
            OperacijeServer.getInstance().pokreniServer();
            JOptionPane.showMessageDialog(jpanel, "Uspesno startovan server", "", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(jpanel, "Neuspesno startovan server", "Pokreni", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    private void osluskivacZaustavi() {
        try {
            OperacijeServer.getInstance().zaustaviServer();
            JOptionPane.showMessageDialog(jpanel, "Uspesno stopiran server", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(jpanel, "Neuspesno stopiran server", "", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    
    private static void podesiIzgledJpanel(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}

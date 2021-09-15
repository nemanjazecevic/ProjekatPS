/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanel;

import entities.Igrac;
import entities.Tim;
import entities.Trener;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author PC
 */
public class KontrolerPanel {
    private Panel panel;

    public void getPanel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            podesiIzgledJpanel(new javax.swing.plaf.FontUIResource(new Font("Verdana", Font.BOLD, 15)));
        } catch (Exception e) {
        } 
        if (panel == null) {
            panel = new Panel();
            this.panel.getjMenuItem1().addActionListener(e -> kreiranjeIgraca(null));
            this.panel.getjMenuItem2().addActionListener(e -> pretrazivanjeIgraca());
            this.panel.getjMenuItem3().addActionListener(e -> pretrazivanjeIgraca());
            this.panel.getjMenuItem4().addActionListener(e -> kreiranjeTima(null));
            this.panel.getjMenuItem5().addActionListener(e -> pretrazivanjeTima());
            this.panel.getjMenuItem6().addActionListener(e -> pretrazivanjeTima());
            this.panel.getjMenuItem7().addActionListener(e -> kreiranjeTrenera(null));
            this.panel.getjMenuItem8().addActionListener(e -> pretrazivanjeTrenera());
            this.panel.getjMenuItem9().addActionListener(e -> pretrazivanjeTrenera());
            panel.setSize(300, 400);
            panel.setLocationRelativeTo(null);
            panel.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        }
        panel.setVisible(true);
        this.panel.getContentPane().removeAll();

    }

    public void setPanelToDialog(JPanel panel) {
        JDialog dialog = new JDialog(new JFrame(), true);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    
    public void pretrazivanjeIgraca() {
        PretragaIgraca jpanel = KontrolerPretragaIgraca.getInstance().getJpanel();
        setPanelToDialog(jpanel);
         
    }
    private void pretrazivanjeTima() {
        PretragaTima jpanel = KontrolerPretragaTima.getInstance().getJpanel();
        setPanelToDialog(jpanel);
    }

    private void pretrazivanjeTrenera() {
        PretragaTrenera jpanel = KontrolerPretragaTrenera.getInstance().getJpanel();
        setPanelToDialog(jpanel);
    }

    public void kreiranjeIgraca(Igrac igrac) {
        UnosIgraca jpanel = KontrolerUnosIgraca.getInstance().getJpanel(igrac);
        setPanelToDialog(jpanel);
    }

    public void kreiranjeTima(Tim tim) {
        UnosTima jpanel = KontrolerUnosTima.getInstance().getJpanel(tim);
        setPanelToDialog(jpanel);
    }

    public void kreiranjeTrenera(Trener trener) {
        UnosTrenera jpanel = KontrolerUnosTrenera.getInstance().getJpanel(trener);
        setPanelToDialog(jpanel);
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

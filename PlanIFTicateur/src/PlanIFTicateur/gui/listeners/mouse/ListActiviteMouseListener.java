/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.drawing.HoraireDrawer;
import PlanIFTicateur.gui.HorairePanel;
import PlanIFTicateur.gui.ListeActivitesPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class ListActiviteMouseListener extends MouseAdapter
{
    private ListeActivitesPanel listeActivitesPanel;
    private HorairePanel horairePanel;
    private HoraireDrawer horaireDrawer;
    
    
    public ListActiviteMouseListener(ListeActivitesPanel listeActivitesPanel, HoraireDrawer horaireDrawer, HorairePanel horairePanel)
    {
        this.listeActivitesPanel = listeActivitesPanel;
        this.horairePanel = horairePanel;
        this.horaireDrawer = horaireDrawer;
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
        
        horaireDrawer.ajouterActivite(activite);
        horairePanel.repaint();
      
    }
    
}

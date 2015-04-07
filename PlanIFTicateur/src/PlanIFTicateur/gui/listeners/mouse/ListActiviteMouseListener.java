/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.drawing.HoraireDrawer;
import PlanIFTicateur.gui.HorairePanel;
import PlanIFTicateur.gui.ListeActivitesPanel;
import PlanIFTicateur.gui.MainWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class ListActiviteMouseListener extends MouseAdapter implements MouseMotionListener
{
    private ListeActivitesPanel listeActivitesPanel;
 
    private MainWindow mainWindow;
    
    private boolean isSelected = false;
    
    public ListActiviteMouseListener(MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        
       isSelected = true;
       
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e); //To change body of generated methods, choose Tools | Templates.
        
    }

    
    
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
        
       if(isSelected)
       {
       mainWindow.horairePanel.getHoraireDrawer().ajouterActivite(listeActivitesPanel.getListeActivites().getSelectedValue());
       mainWindow.horairePanel.repaint();
       }
      
    }
    
}

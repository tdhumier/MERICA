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
import java.awt.Point;
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
    protected Point position;
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
       position = new Point(e.getX(), e.getY());
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e); //To change body of generated methods, choose Tools | Templates.
        if(isSelected)
        System.out.println(e.getY());
        
    }

    
    
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
        
       if(isSelected && e.getX() <= mainWindow.horairePanel.getInitialDimension().width && e.getY() <= mainWindow.horairePanel.getInitialDimension().height)
       {
       //mainWindow.horairePanel.getHoraireDrawer().ajouterActivite(listeActivitesPanel.getListeActivites().getSelectedValue());
       mainWindow.horairePanel.repaint();
       System.out.println("Tu as debarquer mon osti");
       }
       isSelected = false;
       System.out.println("criss");
      
    }
    
}

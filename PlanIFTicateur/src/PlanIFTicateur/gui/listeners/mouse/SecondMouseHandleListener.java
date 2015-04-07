/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.mouse;

import PlanIFTicateur.gui.MainWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Alexandre
 */
public class SecondMouseHandleListener extends MouseAdapter implements MouseMotionListener
{

    private boolean nouveauDragged;
    private MainWindow mainWindow;
    
      public SecondMouseHandleListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.nouveauDragged = false;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Je suis sortie");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Je suis entre");
        mainWindow.horairePanel.setMouse(e.getPoint());
        
    }
    

    
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
        nouveauDragged = true;
    }
    
    
    
    
}

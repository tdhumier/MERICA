/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import PlanIFTicateur.drawing.HoraireDrawer;
import PlanIFTicateur.gui.listeners.mouse.MouseHandleListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.BevelBorder;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author tristandhumieres
 */
public class HorairePanel extends JPanel implements HoraireControleurObserveur {

    private Dimension initialDimension;
    private MainWindow mainWindow;
    private HoraireDrawer horaireDrawer;
    private Point mouse;

    public HorairePanel() {
    }

    public Dimension getInitialDimension() {
        return this.initialDimension;
    }

    public HorairePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension((int) (width * 0.7), 800));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setBackground(Color.WHITE);
        int largeurCase = (int) (width * 0.7 - 90) / 28;
        int hauteurCase = (int) 770 / 48;
        initialDimension = new Dimension(largeurCase, hauteurCase);
        mainWindow.controleur.registerObserver(this);
        setVisible(true);
        MouseHandleListener mouseHandleListener = new MouseHandleListener(mainWindow);
        this.addMouseListener(mouseHandleListener);
        this.addMouseMotionListener(mouseHandleListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (mainWindow != null) {
            super.paintComponent(g);
           horaireDrawer = new HoraireDrawer(mainWindow.controleur, initialDimension);
            horaireDrawer.draw(g);
            
            setTransferHandler(new ImportTransferHandler());
            
        }
    }
    
     private class ImportTransferHandler extends TransferHandler
    {
        public boolean canImport(TransferHandler.TransferSupport support)
        {
            if(!support.isDataFlavorSupported(DataFlavor.stringFlavor))
            {
                return false;
            }
            return true;
        }
        public boolean importData(TransferHandler.TransferSupport support)
        {
            Transferable t = support.getTransferable();
            String data = "";
            try
            {
                data = (String)t.getTransferData(DataFlavor.stringFlavor);
                
            } catch(Exception e)
            {
                System.out.println(e.getMessage());
                return true;
            }
            
            //ENDROIT OU FAIRE LACTION
            
            
            System.out.println(mouse);
            mainWindow.horairePanel.getHoraireDrawer().ajouterActivite(mainWindow.rightPanel.getListeActivitesPanel().getListeActivites().getSelectedValue(),intValue(mouse.getX()), intValue(mouse.getY()));
            mainWindow.horairePanel.repaint();
            return true;
        }
    }

    @Override
    public void notifyUpdatedItems() {
        repaint();
    }
    
    public HoraireDrawer getHoraireDrawer()
    {
        return horaireDrawer;
    }

    public Point getMouse() {
        return mouse;
    }

    public void setMouse(Point mouse) {
        this.mouse = mouse;
    }

    
    
}

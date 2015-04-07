/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.action;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.MainWindow;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Alexandre
 *//*
public class ListTransferHandler extends TransferHandler
{
    private MainWindow mainWindow;
    private int[] indices = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.
    private JList<Activite> listeActivites;
    
    
    public ListTransferHandler(MainWindow mainWindow, int i)
    {
        this.mainWindow = mainWindow;
         listeActivites = mainWindow.rightPanel.getListeActivitesPanel().getListeActivites();
        
    }
    */
    
    /*
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
            Point mouse = MouseInfo.getPointerInfo().getLocation();
            mainWindow.horairePanel.getHoraireDrawer().ajouterActivite(listeActivites.getSelectedValue(),intValue(mouse.getX()), intValue(mouse.getY()));
            mainWindow.horairePanel.repaint();
            return true;
        }
    }
 
  
}
*/
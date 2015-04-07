/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.listeners.action;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.MainWindow;
import javax.swing.JList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author tristandhumieres
 */
public class ListeSelectionListener implements ListSelectionListener {

    private MainWindow mainWindow;

    public ListeSelectionListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList listeActivites = (JList) e.getSource();
        mainWindow.rightPanel.updateDetailsActivitePanel((Activite) listeActivites.getSelectedValue());
<<<<<<< HEAD
    }
=======
    } 
    
    

>>>>>>> Interface3
}

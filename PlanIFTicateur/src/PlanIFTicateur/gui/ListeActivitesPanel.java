/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.listeners.action.ListeSelectionListener;
import PlanIFTicateur.gui.listeners.mouse.ListActiviteMouseListener;
import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

/**
 *
 * @author tristandhumieres
 */
public class ListeActivitesPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel listeActivitesLabel;
    private JList<Activite> listeActivites;
    DefaultListModel<Activite> listModel;
    TransferHandler th;

    public ListeActivitesPanel() {
    }

    public ListeActivitesPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
        ListActiviteMouseListener listeActiviteMouseListener = new ListActiviteMouseListener(mainWindow);
        this.listeActivites.addMouseListener(listeActiviteMouseListener);
        this.listeActivites.addMouseMotionListener(listeActiviteMouseListener);
    }

    private void buildUp() {

        setLayout(new BorderLayout());
        listeActivitesLabel = new JLabel("Liste Activites");

        add(listeActivitesLabel, BorderLayout.NORTH);

        List<Activite> activites = mainWindow.controleur.getActivitesNonAssignees();
        listModel = new DefaultListModel<>();
        for (Activite activite : activites) {
            listModel.addElement(activite);
        }
        listeActivites = new JList(listModel);
        listeActivites.setCellRenderer(new ActiviteRenderer());

        listeActivites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listeActivites.addListSelectionListener(new ListeSelectionListener(mainWindow));

        add(new JScrollPane(listeActivites), BorderLayout.CENTER);
        
       th = listeActivites.getTransferHandler();
       listeActivites.setDragEnabled(true);
       listeActivites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       listeActivites.setDropMode(DropMode.ON);
       
    }
    
    
 

    @Override
    public void notifyUpdatedItems() {
        List<Activite> activites = mainWindow.controleur.getActivitesNonAssignees();
        activites.stream().filter((activite) -> (!listModel.contains(activite))).forEach((activite) -> {
            listModel.addElement(activite);
        });
    }
    
    public JList<Activite> getListeActivites()
    {
        return listeActivites;
    }
    
    
    
}

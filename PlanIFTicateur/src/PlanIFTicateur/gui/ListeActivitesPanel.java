/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.HoraireControleurObserveur;
import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.listeners.action.ListeSelectionListener;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author tristandhumieres
 */
public class ListeActivitesPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel listeActivitesLabel;
    private JList<Activite> listeActivites;
    DefaultListModel<Activite> listModel;

    public ListeActivitesPanel() {
    }

    public ListeActivitesPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
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

        listeActivites.addListSelectionListener(new ListeSelectionListener(mainWindow));

        listeActivites.setDragEnabled(true);
        listeActivites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(listeActivites), BorderLayout.CENTER);
    }

    @Override
    public void notifyUpdatedItems() {
        List<Activite> activites = mainWindow.controleur.getActivitesNonAssignees();
        activites.stream().filter((activite) -> (!listModel.contains(activite))).forEach((activite) -> {
            listModel.addElement(activite);
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import PlanIFTicateur.gui.listeners.mouse.ListActiviteMouseListener;
import PlanIFTicateur.gui.renderers.ActiviteRenderer;
import java.awt.BorderLayout;
import java.awt.Dimension;
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

        setPreferredSize(new Dimension((int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) * 0.29), 250));
        int height = (int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 52) * 0.2);
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

        add(new JScrollPane(listeActivites), BorderLayout.CENTER);

        ListActiviteMouseListener listActiviteMouseListener = new ListActiviteMouseListener(mainWindow);
        listeActivites.addMouseMotionListener(listActiviteMouseListener);
        listeActivites.addMouseListener(listActiviteMouseListener);

    }

    @Override
    public void notifyUpdatedItems() {
        List<Activite> activites = mainWindow.controleur.getActivitesNonAssignees();
        listModel.removeAllElements();
        activites.stream().filter((activite) -> (!listModel.contains(activite))).forEach((activite) -> {
            listModel.addElement(activite);
        });
    }

    public JList<Activite> getListeActivites() {
        return listeActivites;
    }

}

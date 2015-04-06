/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class RightPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    private JPanel listeActivitesPanel;
    private JLabel listeActivitesLabel;
    private JList<Activite> listeActivites;
    DefaultListModel<Activite> listModel;
    private JPanel detailsActivitePanel;
    private JLabel detailsActiviteLabel;
    private JPanel statistiquePanel;
    private JLabel statistiqueLabel;

    public RightPanel() {
    }

    public RightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        buildUp();
    }

    private void buildUp() {
        int width = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        int height = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        setPreferredSize(new Dimension((int) (width * 0.3), (int) (height * 0.3)));
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setLayout(new GridLayout(0, 1));

        listeActivitesPanel = new JPanel();
        listeActivitesPanel.setLayout(new BorderLayout());
        listeActivitesLabel = new JLabel("Liste Activites");

        listeActivitesPanel.add(listeActivitesLabel, BorderLayout.NORTH);

        List<Activite> activites = mainWindow.controleur.getActivitesNonAssignees();
        listModel = new DefaultListModel<>();
        for (Activite activite : activites) {
            listModel.addElement(activite);
        }
        listeActivites = new JList(listModel);
        listeActivites.setCellRenderer(new ActiviteRenderer());

        listeActivitesPanel.add(new JScrollPane(listeActivites), BorderLayout.CENTER);
        add(listeActivitesPanel);

        detailsActivitePanel = new JPanel();

        detailsActiviteLabel = new JLabel("Détails Activité");
        detailsActivitePanel.add(detailsActiviteLabel);

        add(detailsActivitePanel);

        statistiquePanel = new JPanel();
        statistiqueLabel = new JLabel("Statistiques");
        statistiquePanel.add(statistiqueLabel);

        add(statistiquePanel);

        setVisible(true);
    }

    public void miseAjourListe() {
        List<Activite> activites = mainWindow.controleur.getActivitesNonAssignees();
        activites.stream().filter((activite) -> (!listModel.contains(activite))).forEach((activite) -> {
            listModel.addElement(activite);
        });
    }
}

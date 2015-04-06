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
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class RightPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    private JPanel listeActivitesPanel;
    private JPanel detailsActivitePanel;
    private JLabel listeActivitesLabel;
    private JList listeActivites;
    private JLabel detailsActiviteLabel;

    public RightPanel() {
        buildUp();
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

        List<Activite> activites = mainWindow.controleur.getActiviteListe();
        listeActivites = new JList(new Vector(activites));
        listeActivites.setVisibleRowCount(10);
        listeActivites.setCellRenderer(new ActiviteRenderer());

        listeActivitesPanel.add(listeActivites, BorderLayout.CENTER);
        add(listeActivitesPanel);

        detailsActivitePanel = new JPanel();

        detailsActiviteLabel = new JLabel("Détails Activité");
        detailsActivitePanel.add(detailsActiviteLabel);

        add(detailsActivitePanel);

        setVisible(true);
    }

    public void miseAjourListe() {
        List<Activite> activites = mainWindow.controleur.getActiviteListe();
        listeActivites.setListData(new Vector(activites));
    }
}

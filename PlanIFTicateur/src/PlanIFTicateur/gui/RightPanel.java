/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.listeners.mouse.ListActiviteMouseListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class RightPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    protected ListeActivitesPanel listeActivitesPanel;
    private DetailsActivitePanel detailsActivitePanel;
    private StatistiquesPanel statistiquesPanel;

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

        listeActivitesPanel = new ListeActivitesPanel(mainWindow);
        add(listeActivitesPanel);

        detailsActivitePanel = new DetailsActivitePanel(mainWindow);

        add(detailsActivitePanel);

        statistiquesPanel = new StatistiquesPanel(mainWindow);

        add(statistiquesPanel);

        setVisible(true);
    }

    public void updateDetailsActivitePanel(Activite activite) {
        detailsActivitePanel.updateLabel(activite);
        detailsActivitePanel.repaint();
    }
    public ListeActivitesPanel getListeActivitesPanel()
    {
        return listeActivitesPanel;
    }
}

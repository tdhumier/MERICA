/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.Serializable;
import javax.swing.JLabel;
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
    private JLabel detailsActiviteLabel;

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

        listeActivitesPanel = new JPanel();
        listeActivitesPanel.setLayout(new FlowLayout());

        listeActivitesLabel = new JLabel("Liste Activites");
        listeActivitesPanel.add(listeActivitesLabel);

        add(listeActivitesPanel, BorderLayout.NORTH);

        detailsActivitePanel = new JPanel();
        detailsActivitePanel.setLayout(new FlowLayout());

        detailsActiviteLabel = new JLabel("Détails Activité");
        detailsActivitePanel.add(detailsActiviteLabel);

        add(detailsActivitePanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

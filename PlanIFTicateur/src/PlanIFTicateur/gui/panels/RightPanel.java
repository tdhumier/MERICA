/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

/**
 *
 * @author tristandhumieres
 */
public class RightPanel extends JPanel implements Serializable {

    private MainWindow mainWindow;
    protected ListeActivitesPanel listeActivitesPanel;
    private DetailsActivitePanel detailsActivitePanel;
    private ConflitsPanel conflitsPanel;
    private HistoriquePanel historiquePanel;

    public RightPanel() {
    }

    public RightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        buildUp();
    }

    private void buildUp() {
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setLayout(new BorderLayout());

        listeActivitesPanel = new ListeActivitesPanel(mainWindow);
        add(listeActivitesPanel, BorderLayout.NORTH);

        detailsActivitePanel = new DetailsActivitePanel(mainWindow);

        add(detailsActivitePanel, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension((int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) * 0.29), 190));

        conflitsPanel = new ConflitsPanel();

        tabbedPane.add("Conflits", new JScrollPane(conflitsPanel));

        historiquePanel = new HistoriquePanel();

        tabbedPane.add("Historique", new JScrollPane(historiquePanel));

        add(tabbedPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    public ListeActivitesPanel getListeActivitesPanel() {
        return listeActivitesPanel;
    }
}

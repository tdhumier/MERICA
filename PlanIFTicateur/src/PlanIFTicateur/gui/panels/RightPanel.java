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
    private NotesPanel notesPanel;
    private StatistiquesOngletPanel statistiquesPanel;

    public RightPanel() {
    }

    public RightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        statistiquesPanel = new StatistiquesOngletPanel(mainWindow);
        detailsActivitePanel = new DetailsActivitePanel(mainWindow);
        buildUp();
    }

    private void buildUp() {
        setBorder(new javax.swing.border.BevelBorder(BevelBorder.LOWERED));
        setLayout(new BorderLayout());

        listeActivitesPanel = new ListeActivitesPanel(mainWindow);
        add(listeActivitesPanel, BorderLayout.NORTH);

        add(detailsActivitePanel, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension((int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) * 0.29), (int) ((java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) * 0.3)));

        conflitsPanel = new ConflitsPanel();

        tabbedPane.add("Conflits", new JScrollPane(conflitsPanel));

        notesPanel = new NotesPanel();

        tabbedPane.add("Notes", new JScrollPane(notesPanel));
        
        
        tabbedPane.add("Statistiques", statistiquesPanel);

        add(tabbedPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    public ListeActivitesPanel getListeActivitesPanel() {
        return listeActivitesPanel;
    }
    
    public DetailsActivitePanel getDetailsActivitePanel()
    {
        return detailsActivitePanel;
    }
    
}

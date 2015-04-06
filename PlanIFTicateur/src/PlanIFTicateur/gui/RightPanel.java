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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
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
    private JPanel rightCenterPanel;
    private JPanel statistiquePanel;
    private JLabel statistiqueLabel;

    private JLabel codeLabel;
    private JLabel sectionLabel;
    private JLabel titreLabel;
    private JLabel profLabel;
    private JLabel typeLabel;
    private JLabel dureeLabel;
    private JLabel debutMinLabel;
    private JLabel finMaxLabel;
    private JLabel jourLabel;
    private JLabel heureLabel;
    
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

        rightCenterPanel = new JPanel(new BorderLayout());

        detailsActiviteLabel = new JLabel("\t Détails Activité \n\n");
        detailsActiviteLabel.setHorizontalAlignment(SwingConstants.CENTER);
       
        detailsActivitePanel = new JPanel(new GridLayout(10,1));   
        
        codeLabel = new JLabel("Code activité : ");
        sectionLabel = new JLabel("Section : ");
        titreLabel = new JLabel("Titre : ");
        profLabel = new JLabel("Professeur : ");
        typeLabel = new JLabel("Type : ");
        dureeLabel = new JLabel("Durée : ");
        debutMinLabel = new JLabel("Début minimal possible : ");
        finMaxLabel = new JLabel("Fin maximal possible :");
        jourLabel = new JLabel("Jour : ");
        heureLabel = new JLabel("Heure  : ");
        
        detailsActivitePanel.add( codeLabel);
        detailsActivitePanel.add(sectionLabel);
        detailsActivitePanel.add(titreLabel);
        detailsActivitePanel.add(profLabel); 
        detailsActivitePanel.add(typeLabel);
        detailsActivitePanel.add(dureeLabel);
        detailsActivitePanel.add(debutMinLabel);
        detailsActivitePanel.add(finMaxLabel);
        detailsActivitePanel.add( jourLabel);
        detailsActivitePanel.add(heureLabel);
                   
        rightCenterPanel.add(detailsActiviteLabel, BorderLayout.PAGE_START);
        rightCenterPanel.add(detailsActivitePanel, BorderLayout.CENTER);
        
        add(rightCenterPanel);
      
        
        

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

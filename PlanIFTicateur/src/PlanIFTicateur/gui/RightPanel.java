/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    
    DragSource dragSource = null;
    
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
        
        listeActivites.setDragEnabled(true);
        listeActivites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
       
        

        listeActivites.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modifierLabel(listeActivites.getSelectedValue());
            }
        });
        listeActivites.addMouseListener(new MouseAdapter()
        {
             public void mousePressed(MouseEvent mouseEvent)
            { 
                if(mouseEvent.getButton() == MouseEvent.BUTTON1)
                {
                   
                }
            }
        });
        
        
      
        

        listeActivitesPanel.add(new JScrollPane(listeActivites), BorderLayout.CENTER);
        add(listeActivitesPanel);

        rightCenterPanel = new JPanel(new BorderLayout());

        detailsActiviteLabel = new JLabel("\t Détails Activité \n\n");
        detailsActiviteLabel.setHorizontalAlignment(SwingConstants.CENTER);

        detailsActivitePanel = new JPanel(new GridLayout(10, 1));

        codeLabel = new JLabel("Code activité : ");
        sectionLabel = new JLabel("Section : ");
        titreLabel = new JLabel("Titre : ");
        profLabel = new JLabel("Professeur : ");
        typeLabel = new JLabel("Type : ");
        dureeLabel = new JLabel("Durée : ");
        debutMinLabel = new JLabel("Début minimal possible : ");
        finMaxLabel = new JLabel("Fin maximal possible : ");
        jourLabel = new JLabel("Jour : ");
        heureLabel = new JLabel("Heure  : ");

        detailsActivitePanel.add(codeLabel);
        detailsActivitePanel.add(titreLabel);
        detailsActivitePanel.add(sectionLabel);
        detailsActivitePanel.add(profLabel);
        detailsActivitePanel.add(typeLabel);
        detailsActivitePanel.add(dureeLabel);
        detailsActivitePanel.add(debutMinLabel);
        detailsActivitePanel.add(finMaxLabel);
        detailsActivitePanel.add(jourLabel);
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

    public void modifierLabel(Activite activite) {
        codeLabel.setText("Code activité : " + activite.getCode());
        sectionLabel.setText("Section : " + activite.getSection());
        titreLabel.setText("Titre : " + activite.getTitre());
        profLabel.setText("Professeur : " + activite.getProfesseur());
        typeLabel.setText("Type : " + activite.getType());
        dureeLabel.setText("Durée : " + activite.getDuree() + " heures");
        debutMinLabel.setText("Début minimal possible : " + activite.getHeureDebutMin() + " heures");
        finMaxLabel.setText("Fin maximal possible : " + activite.getHeureFinMax() + " heures");
        if (activite.getJour() != 0 || activite.getHeureDebut() != 0) {
            jourLabel.setText("Jour : " + getNomJour(activite.getJour()));
            heureLabel.setText("Heure  : " + activite.getHeureDebut());
        }
    }

    public String getNomJour(int i) {
        String[] JourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};

        return JourModes[i + 1];
    }

 
}

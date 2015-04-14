/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.gui.frames.ActiviteWindow;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static oracle.jrockit.jfr.events.Bits.doubleValue;


/**
 *
 * @author tristandhumieres
 */
public class ActivitePanel extends JPanel {

    
      private MainWindow mainWindow;
      private ActiviteWindow activiteWindow;
      private JPanel detailsActivitePanel;
      private JPanel boutonPanel;
      private JLabel detailsActiviteLabel;
      private JLabel codeLabel;
      private JLabel sectionLabel;
      private JLabel titreLabel;
      private JLabel profLabel;
      private JLabel typeLabel;
      private JLabel dureeLabel;
      private JLabel debutMinLabel;
      private JLabel finMaxLabel;
     
    
    private JTextField codeText;
    private JTextField sectionText;
    private JTextField titreText;
    private JTextField profText;
    private JTextField typeText;
    private JComboBox dureeBox;
    private JComboBox debutMinBox;
    private JComboBox finMaxBox;
    
    private JButton boutonConfirmer;
    private JButton boutonQuitter;
      
    private Activite activite;
    
    private List<Double> heurePossible;
    private List<Double> heureLimitePossible;

    
    
    public ActivitePanel(MainWindow mainWindow, ActiviteWindow activiteWindow) 
    {
        this.mainWindow = mainWindow;
        this.activiteWindow = activiteWindow;
        activite = mainWindow.controleur.getActiviteSelectionnee().get();
        
        heurePossible = Arrays.asList(1.0 , 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0);
        heureLimitePossible = Arrays.asList(8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0,
                                            13.5, 14.0, 14.5, 15.0, 15.5, 16.0, 16.5, 17.0, 17.5, 18.0, 18.5, 19.0, 19.5, 
                                            20.0, 20.5, 21.0, 21.5, 22.0, 22.5);
        
        buildUp();
    }
    
    
    private void buildUp()
    {
        setLayout(new BorderLayout());
    
        detailsActivitePanel = new JPanel();
        detailsActivitePanel.setLayout(new GridLayout(0,2));
        boutonPanel = new JPanel();
     
        
        codeLabel = new JLabel("Code de l'activité : ");
        sectionLabel = new JLabel("Section : ");
        titreLabel = new JLabel("Titre : ");
        profLabel = new JLabel("Professeur : ");
        typeLabel = new JLabel("Type : ");
        dureeLabel = new JLabel("Durée : ");
        debutMinLabel = new JLabel("Début au plus tôt : ");
        finMaxLabel = new JLabel("Fin au plus tard : ");


        codeText = new JTextField(activite.getCode());
        sectionText= new JTextField(activite.getSection());
        titreText= new JTextField(activite.getTitre());
        profText= new JTextField(activite.getProfesseur());
        typeText= new JTextField(activite.getType());
        
        
        dureeBox = new JComboBox(heurePossible.toArray());
        dureeBox.setSelectedItem(activite.getDuree());
        debutMinBox = new JComboBox(heureLimitePossible.toArray());
        debutMinBox.setSelectedItem(activite.getHeureDebutMin());
        finMaxBox = new JComboBox(heureLimitePossible.toArray());
        finMaxBox.setSelectedItem(activite.getHeureFinMax());
      
        
        boutonConfirmer = new JButton("Confirmer");
        boutonQuitter = new JButton("Quitter");
        
       
        detailsActivitePanel.add(codeLabel);
        detailsActivitePanel.add(codeText);
        detailsActivitePanel.add(titreLabel);
         detailsActivitePanel.add(titreText);
        detailsActivitePanel.add(sectionLabel);
         detailsActivitePanel.add(sectionText);
        detailsActivitePanel.add(profLabel);
         detailsActivitePanel.add(profText);
        detailsActivitePanel.add(typeLabel);
         detailsActivitePanel.add(typeText);
        detailsActivitePanel.add(dureeLabel);
         detailsActivitePanel.add(dureeBox);
        detailsActivitePanel.add(debutMinLabel);
         detailsActivitePanel.add(debutMinBox);
        detailsActivitePanel.add(finMaxLabel);
         detailsActivitePanel.add(finMaxBox);
        
         boutonPanel.add(boutonConfirmer);
         boutonPanel.add(boutonQuitter);
         
        add(detailsActivitePanel, BorderLayout.CENTER);
        add(boutonPanel, BorderLayout.SOUTH);
        
        
        boutonConfirmer.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                modifierValeursActivite();
                mainWindow.controleur.notifyObserversForUpdatedItems();
                activiteWindow.dispose();
                }
        });
        boutonQuitter.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              activiteWindow.dispose();
            }
        });
    }
    
    public void modifierValeursActivite()
    {
        activite.setCode(codeText.getText());
        activite.setSection(sectionText.getText());
        activite.setTitre(titreText.getText());
        activite.setProfesseur(profText.getText());
        activite.setType(typeText.getText());
        activite.setDuree(doubleValue(dureeBox.getSelectedItem()));
        activite.setHeureDebutMin(doubleValue(debutMinBox.getSelectedItem()));
        activite.setHeureFinMax(doubleValue(finMaxBox.getSelectedItem()));
        activite.setDimension(new Dimension(mainWindow.horairePanel.getDimensionsCase()));
    }

}

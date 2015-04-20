/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author tristandhumieres
 */
public class StatistiquesOngletPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JPanel statPanel;
 
    private JLabel lundiLabel;
    private JLabel mardiLabel;
    private JLabel mercrediLabel;
    private JLabel jeudiLabel;
    private JLabel vendrediLabel;
    private JLabel samediLabel;
    private JComboBox comboBox;
    
    
    private String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};


    public StatistiquesOngletPanel() {
    }

    public StatistiquesOngletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        
        setLayout(new BorderLayout());
        
        comboBox = new JComboBox();
       
        lundiLabel = new JLabel("");
        mardiLabel = new JLabel("");
        mercrediLabel = new JLabel("");
        jeudiLabel = new JLabel("");
        vendrediLabel = new JLabel("");
        samediLabel = new JLabel("");
        statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(0,1));
        
        
        
        comboBox.setBackground(Color.WHITE);
        comboBox.addItem("Cours par jour");
        comboBox.addItem("Indice de congestion");
        comboBox.addItem("Nombre maximal de cours");
        comboBox.addItem("Indice de covoiturage");
        add(comboBox, BorderLayout.NORTH);
      
        statPanel.add(lundiLabel);
        statPanel.add(mardiLabel);
        statPanel.add(mercrediLabel);
        statPanel.add(jeudiLabel);
        statPanel.add(vendrediLabel);
        statPanel.add(samediLabel);
        
        add(statPanel, BorderLayout.CENTER);
        
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              
                statistiqueActionPerformed(evt);
              
            }
        });          
    }
    
    
    private void statistiqueActionPerformed(java.awt.event.ActionEvent evt)
    {
        mainWindow.controleur.notifyObserversForUpdatedItems();
    }

    
    @Override
    public void notifyUpdatedItems() 
    {
        JLabel[] labelModes  = {lundiLabel, mardiLabel, mercrediLabel, jeudiLabel, vendrediLabel, samediLabel};
   
        if(comboBox.getSelectedItem()== "Cours par jour")
        {
        ArrayList<Integer> nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        
        
        modificationIntegerLabel(labelModes, nbCoursParJour, " Cours");
        /*lundiLabel.setText(jourModes[0] + " : " + nbCoursParJour.get(0) + " cours");
        mardiLabel.setText(jourModes[1] + " : " + nbCoursParJour.get(1) + " cours");
        mercrediLabel.setText(jourModes[2] + " : " + nbCoursParJour.get(2) + " cours");
        jeudiLabel.setText(jourModes[3] + " : " + nbCoursParJour.get(3) + " cours");
        vendrediLabel.setText(jourModes[4] + " : " + nbCoursParJour.get(4) + " cours");
        samediLabel.setText(jourModes[5] + " : " + nbCoursParJour.get(5) + " cours");*/
        repaint();
        }
        
        else if(comboBox.getSelectedItem()== "Indice de congestion")
        {
             ArrayList<Float> indiceCongestionParJour = new ArrayList<Float>();
             for(int i = 1; i < 7 ; i++)
             {
                 indiceCongestionParJour.add(mainWindow.controleur.getIndiceCongestion(i));
             }
             
        modificationFloatLabel(labelModes, indiceCongestionParJour, " %");
       /* lundiLabel.setText(jourModes[0] + " : " + indiceCongestionParJour.get(0) + " %");
        mardiLabel.setText(jourModes[1] + " : " + indiceCongestionParJour.get(1) + " %");
        mercrediLabel.setText(jourModes[2] + " : " + indiceCongestionParJour.get(2) + " %");
        jeudiLabel.setText(jourModes[3] + " : " + indiceCongestionParJour.get(3) + " %");
        vendrediLabel.setText(jourModes[4] + " : " + indiceCongestionParJour.get(4) + " %");
        samediLabel.setText(jourModes[5] + " : " + indiceCongestionParJour.get(5) + " %");*/
        repaint();
        }
        
        
        else if(comboBox.getSelectedItem()== "Nombre maximal de cours")
        {
             ArrayList<Integer> nbMaxCoursParJour = new ArrayList<Integer>();
             ListeGrillesCheminement listeGrilleCheminement = mainWindow.controleur.getHoraire().getListeGrillesCheminement();
             
             for(int i = 1; i < 7 ; i++)
             {
                 nbMaxCoursParJour.add(mainWindow.controleur.getNbMaxCoursParJour(listeGrilleCheminement, i));
             }
             
               modificationIntegerLabel(labelModes, nbMaxCoursParJour, " Cours");
       /* lundiLabel.setText(jourModes[0] + " : " + nbMaxCoursParJour.get(0) + " Cours");
        mardiLabel.setText(jourModes[1] + " : " + nbMaxCoursParJour.get(1) + " Cours");
        mercrediLabel.setText(jourModes[2] + " : " + nbMaxCoursParJour.get(2) + " Cours");
        jeudiLabel.setText(jourModes[3] + " : " + nbMaxCoursParJour.get(3) + " Cours");
        vendrediLabel.setText(jourModes[4] + " : " + nbMaxCoursParJour.get(4) + " Cours");
        samediLabel.setText(jourModes[5] + " : " + nbMaxCoursParJour.get(5) + " Cours");*/
        repaint();
        }
        
        else if (comboBox.getSelectedItem() == "Indice de covoiturage")
        {
             ArrayList<Float> indiceCovoiturage = new ArrayList<Float>();
             
             for(int i = 1; i < 7; i++)
             {
                 indiceCovoiturage.add(mainWindow.controleur.getIndiceCovoiturage(i));
             }
        }
    }
    
    public void modificationIntegerLabel(JLabel[] labelModes, ArrayList<Integer> list, String texte)
    {    
        
        for(int i = 0; i < 6; i++)
        {
            labelModes[i].setText(jourModes[i] + " : " + list.get(i) + texte);
        }
    }
       public void modificationFloatLabel(JLabel[] labelModes, ArrayList<Float> list, String texte)
    {    
        
        for(int i = 0; i < 6; i++)
        {
            labelModes[i].setText(jourModes[i] + " : " + list.get(i) + texte);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author tristandhumieres
 */
public class StatistiquesPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JPanel statPanel;
    private JPanel boutonStatPanel;
    private JLabel lundiLabel;
    private JLabel mardiLabel;
    private JLabel mercrediLabel;
    private JLabel jeudiLabel;
    private JLabel vendrediLabel;
    private JLabel samediLabel;
    private JButton bCoursParJour;
    private JButton bCongestion;
    private boolean statParJour = false;
    private boolean statIndiceCongestion = false;

    public StatistiquesPanel() {
    }

    public StatistiquesPanel(MainWindow maindow) {
        this.mainWindow = maindow;
        maindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        
        setLayout(new BorderLayout());
        
        bCoursParJour = new JButton("Cours par jour");
        bCongestion = new JButton("Indice de Congestion");
        lundiLabel = new JLabel("");
        mardiLabel = new JLabel("");
        mercrediLabel = new JLabel("");
        jeudiLabel = new JLabel("");
        vendrediLabel = new JLabel("");
        samediLabel = new JLabel("");
        statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(0,1));
        boutonStatPanel = new JPanel();
        
        
        
        boutonStatPanel.add(bCoursParJour);
        boutonStatPanel.add(bCongestion);
        add(boutonStatPanel, BorderLayout.NORTH);
      
        statPanel.add(lundiLabel);
        statPanel.add(mardiLabel);
        statPanel.add(mercrediLabel);
        statPanel.add(jeudiLabel);
        statPanel.add(vendrediLabel);
        statPanel.add(samediLabel);
        
        add(statPanel, BorderLayout.CENTER);
        
        bCoursParJour.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statistiqueCoursParJourActionPerformed(evt);
            }
        });
        bCongestion.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                statistiqueIndicesCongestionActionPerformed(evt);
        }
        });
                
                
    }
    private void statistiqueIndicesCongestionActionPerformed(java.awt.event.ActionEvent evt)
    {
        statIndiceCongestion = true;
        statParJour = false;
        bCongestion.setBackground(java.awt.Color.LIGHT_GRAY);
        bCoursParJour.setBackground(new JButton().getBackground());
        mainWindow.controleur.notifyObserversForUpdatedItems();
    }
    
    private void statistiqueCoursParJourActionPerformed(java.awt.event.ActionEvent evt)
    {
      statIndiceCongestion = false;
      statParJour = true;
       bCoursParJour.setBackground(java.awt.Color.LIGHT_GRAY);
       bCongestion.setBackground(new JButton().getBackground());
      mainWindow.controleur.notifyObserversForUpdatedItems();
    }

    @Override
    public void notifyUpdatedItems() {
        
        if(statParJour)
        {
        ArrayList<Integer> nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        lundiLabel.setText(jourModes[0] + " : " + nbCoursParJour.get(0) + " cours");
        mardiLabel.setText(jourModes[1] + " : " + nbCoursParJour.get(1) + " cours");
        mercrediLabel.setText(jourModes[2] + " : " + nbCoursParJour.get(2) + " cours");
        jeudiLabel.setText(jourModes[3] + " : " + nbCoursParJour.get(3) + " cours");
        vendrediLabel.setText(jourModes[4] + " : " + nbCoursParJour.get(4) + " cours");
        samediLabel.setText(jourModes[5] + " : " + nbCoursParJour.get(5) + " cours");
        repaint();
        }
        else if(statIndiceCongestion)
        {
             ArrayList<Integer> nbCoursParJour = mainWindow.controleur.getNbCoursSemaine();
        String[] jourModes = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        lundiLabel.setText(jourModes[0] + " : " + nbCoursParJour.get(0) + " %");
        mardiLabel.setText(jourModes[1] + " : " + nbCoursParJour.get(1) + " %");
        mercrediLabel.setText(jourModes[2] + " : " + nbCoursParJour.get(2) + " %");
        jeudiLabel.setText(jourModes[3] + " : " + nbCoursParJour.get(3) + " %");
        vendrediLabel.setText(jourModes[4] + " : " + nbCoursParJour.get(4) + " %");
        samediLabel.setText(jourModes[5] + " : " + nbCoursParJour.get(5) + " %");
        repaint();
        }
    }
}

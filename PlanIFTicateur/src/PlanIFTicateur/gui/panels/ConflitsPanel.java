/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import javax.swing.JPanel;

/**
 *
 * @author tristandhumieres
 */
public class ConflitsPanel extends JPanel implements HoraireControleurObserveur{
    private MainWindow mainWindow;
    private JTextArea text;
    
    public ConflitsPanel() {
    }

    
    public ConflitsPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.controleur.registerObserver(this);
        buildUp();
    }

    private void buildUp() {
        setLayout(new BorderLayout());
        text = new JTextArea();
        text.setEditable(false);
        text.setForeground(Color.red);
        add(text);             
    }
    
   

    
    @Override
    public void notifyUpdatedItems() {
        text.setText("");
        try{
        List<Activite> activitesConflitCheminement = mainWindow.controleur.getActivitesConflitCheminement();
      
        for (int i = 0; i< activitesConflitCheminement.size(); i++){
            text.append("Il y a un conflit avec le cours : ");
            text.append(activitesConflitCheminement.get(i).getCode() + "\n"); 
        }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.StatistiquesPanels;

import PlanIFTicateur.gui.frames.MainWindow;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class indiceCongestionPanel extends JPanel
{
    private MainWindow mainWindow;
    private JLabel indiceTitre;
    private JLabel lundiIndiceLabel;
    private JLabel mardiIndiceLabel;
    private JLabel mercrediIndiceLabel;
    private JLabel jeudiIndiceLabel;
    private JLabel vendrediIndiceLabel;
    private JLabel samediIndiceLabel;
    private JLabel moyenneIndiceLabel; 
    
    
    public indiceCongestionPanel(MainWindow mainWindow, JLabel[] listeLabel)
    {
        this.mainWindow = mainWindow;
        buildUp(listeLabel);
    }
    
    private void buildUp(JLabel[] listeLabel)
    {
        indiceTitre = new JLabel("Nombre de cours par jour");
        lundiIndiceLabel = new JLabel();
        mardiIndiceLabel = new JLabel();
        mercrediIndiceLabel = new JLabel();
        jeudiIndiceLabel = new JLabel();
        vendrediIndiceLabel = new JLabel();
        samediIndiceLabel = new JLabel();
        moyenneIndiceLabel = new JLabel();
        
        ajouterIndiceCongestion(listeLabel);
    }
    
     private void ajouterIndiceCongestion(JLabel[] listeLabel)
    {
      /*  lundiIndiceLabel.setText(listeLabel[0].getText() + nbCoursParJour.get(0) + " %");
        mardiIndiceLabel.setText(listeLabel[1].getText() + nbCoursParJour.get(1) + " %");
        mercrediIndiceLabel.setText(listeLabel[2].getText() + nbCoursParJour.get(2) + " %");
        jeudiIndiceLabel.setText(listeLabel[3].getText() + nbCoursParJour.get(3) + " %");
        vendrediIndiceLabel.setText(listeLabel[4].getText() + nbCoursParJour.get(4) + " %");
        samediIndiceLabel.setText(listeLabel[5].getText() + nbCoursParJour.get(5) + " %");
        moyenneIndiceLabel.setText(listeLabel[6].getText() + " 10%");*/
        
        add(indiceTitre);
        add(lundiIndiceLabel);
        add(mardiIndiceLabel);
        add(mercrediIndiceLabel);
        add(jeudiIndiceLabel);
        add(vendrediIndiceLabel);
        add(samediIndiceLabel);
       
    }
}

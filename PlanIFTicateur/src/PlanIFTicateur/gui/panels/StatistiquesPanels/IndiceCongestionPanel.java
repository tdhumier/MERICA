/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.StatistiquesPanels;

import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class IndiceCongestionPanel extends JPanel
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
    
    
    public IndiceCongestionPanel(MainWindow mainWindow, String[] jourModes)
    {
        this.mainWindow = mainWindow;
        setLayout(new GridLayout(8,1));
        buildUp(jourModes);
    }
    
    private void buildUp(String[] jourModes)
    {
        indiceTitre = new JLabel("Nombre de cours par jour");
        lundiIndiceLabel = new JLabel();
        mardiIndiceLabel = new JLabel();
        mercrediIndiceLabel = new JLabel();
        jeudiIndiceLabel = new JLabel();
        vendrediIndiceLabel = new JLabel();
        samediIndiceLabel = new JLabel();
        moyenneIndiceLabel = new JLabel();
        
      
        JLabel[] listeLabel = {lundiIndiceLabel, mardiIndiceLabel, mercrediIndiceLabel, jeudiIndiceLabel, vendrediIndiceLabel, samediIndiceLabel};
        
        ajouterIndiceCongestion(listeLabel, jourModes);
    }
    
     private void ajouterIndiceCongestion(JLabel[] listeLabel, String[] jourModes)
    {
        
        
          ArrayList<Float> indiceCongestionParJour = new ArrayList<Float>();
             for(int i = 0; i < 6 ; i++)
             {
                 indiceCongestionParJour.add(mainWindow.controleur.getIndiceCongestion(i));
             }
           listeLabel = mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, listeLabel, indiceCongestionParJour, " %");
         
            add(indiceTitre);
              
            for(int j = 0; j < 6; j++)
            {
                add(listeLabel[j]);
            }
      
       
       
    }
}

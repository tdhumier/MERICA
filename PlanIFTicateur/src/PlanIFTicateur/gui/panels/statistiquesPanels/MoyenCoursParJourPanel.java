/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui.panels.statistiquesPanels;

/**
 *
 * @author Alexandre
 */
import PlanIFTicateur.domaine.cheminement.ListeGrillesCheminement;
import PlanIFTicateur.domaine.horaire.HoraireControleurObserveur;
import PlanIFTicateur.gui.frames.MainWindow;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class MoyenCoursParJourPanel extends JPanel implements HoraireControleurObserveur {

    private MainWindow mainWindow;
    private JLabel moyenCoursParJourTitre;
    private JLabel lundiMoyenCPJLabel;
    private JLabel mardiMoyenCPJLabel;
    private JLabel mercrediMoyenCPJLabel;
    private JLabel jeudiMoyenCPJLabel;
    private JLabel vendrediMoyenCPJLabel;
    private JLabel samediMoyenCPJLabel;
    private JLabel moyenneMoyenCPJLabel;
    private JLabel[] listeLabel;
    private String[] jourModes;

    private ArrayList<Float> nbMoyenCoursParJour;
    String moyenneText;

    public MoyenCoursParJourPanel(MainWindow mainWindow, String[] jourModes) {
        this.mainWindow = mainWindow;
        this.jourModes = jourModes;
        mainWindow.controleur.registerObserver(this);
        setLayout(new GridLayout(8, 1));
        buildUp();
    }

    private void buildUp() {
        moyenCoursParJourTitre = new JLabel("Nombre moyen cours par jour");
        moyenCoursParJourTitre.setFont(new Font("Helvetica", Font.BOLD, 16));
        lundiMoyenCPJLabel = new JLabel();
        mardiMoyenCPJLabel = new JLabel();
        mercrediMoyenCPJLabel = new JLabel();
        jeudiMoyenCPJLabel = new JLabel();
        vendrediMoyenCPJLabel = new JLabel();
        samediMoyenCPJLabel = new JLabel();
        moyenneMoyenCPJLabel = new JLabel();

        listeLabel = new JLabel[]{lundiMoyenCPJLabel, mardiMoyenCPJLabel, mercrediMoyenCPJLabel, jeudiMoyenCPJLabel, vendrediMoyenCPJLabel, samediMoyenCPJLabel};

        ajouterMoyenCoursParJour(listeLabel);
    }

    private void ajouterMoyenCoursParJour(JLabel[] listeLabel) {
        float moyenne = 0;
        ListeGrillesCheminement listeGrilleCheminement = mainWindow.controleur.getHoraire().getListeGrillesCheminement();

        nbMoyenCoursParJour = new ArrayList<Float>();
        for (int i = 0; i < 6; i++) {
            nbMoyenCoursParJour.add(mainWindow.controleur.getNbMoyenCoursParJour(listeGrilleCheminement, i + 1));
            moyenne += nbMoyenCoursParJour.get(i);
        }

        moyenne = moyenne / 6;
        moyenneText = Float.toString(moyenne);

        listeLabel = mainWindow.controleur.getHoraire().getStatistiques().modificationFloatLabel(jourModes, listeLabel, nbMoyenCoursParJour, " Cours");

        moyenneMoyenCPJLabel.setText("Moyenne : " + moyenneText + " Cours");

        add(moyenCoursParJourTitre);

        for (int j = 0; j < 6; j++) {
            add(listeLabel[j]);
        }
        add(moyenneMoyenCPJLabel);
    }

    @Override
    public void notifyUpdatedItems() {
        ajouterMoyenCoursParJour(listeLabel);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.gui;

import PlanIFTicateur.domaine.activite.Activite;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author tristandhumieres
 */
public class DetailsActivitePanel extends JPanel {

    private MainWindow mainWindow;
    private JPanel detailsActivitePanel;
    private JLabel detailsActiviteLabel;
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

    public DetailsActivitePanel() {
    }

    public DetailsActivitePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        buildUp();
    }

    private void buildUp() {

        setLayout(new BorderLayout());

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

        add(detailsActiviteLabel, BorderLayout.PAGE_START);
        add(detailsActivitePanel, BorderLayout.CENTER);
    }

    public void updateLabel(Activite activite) {
        codeLabel.setText("Code activité : " + activite.getCode());
        sectionLabel.setText("Section : " + activite.getSection());
        titreLabel.setText("Titre : " + activite.getTitre());
        profLabel.setText("Professeur : " + activite.getProfesseur());
        typeLabel.setText("Type : " + activite.getType());
        dureeLabel.setText("Durée : " + activite.getDuree() + "heures");
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

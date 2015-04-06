/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

import PlanIFTicateur.domaine.activite.Activite;
import PlanIFTicateur.domaine.conflit.Conflit;
import PlanIFTicateur.domaine.conflit.ConflitCheminement;
import PlanIFTicateur.domaine.conflit.ConflitHoraire;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Horaire {

    private boolean valide;
    private ListeActivites listeActivite;
    private ListeGrillesCheminement grillesCheminement;
    private ListeConflits listeConflits;
    private Statistique statistique;

    public Horaire() {
        this.listeActivite = new ListeActivites();
        this.grillesCheminement = new ListeGrillesCheminement();
        this.listeConflits = new ListeConflits();
        this.statistique = new Statistique();

    }

    public ListeActivites getListeActivite() {
        return listeActivite;
    }

    public Statistique getStatistiques() {
        return statistique;
    }

    public void setListeActivite(ListeActivites listeActivite) {
        this.listeActivite = listeActivite;
    }

    public void setGrillesCheminement(ListeGrillesCheminement grillesCheminement) {
        this.grillesCheminement = grillesCheminement;
    }

    public void genererAutomatiquement() {

    }

    public void deplacerActivite(Activite activite) {

    }

    public void verifierHoraireActivite(Activite activite) {
        if (!activite.horaireValide()) {
            Conflit conflitHoraire = new ConflitHoraire(activite);
            listeConflits.ajouterConflit(conflitHoraire);
        }
        List<Activite> activitesConflitCheminement = grillesCheminement.activitesAuMemeHoraire(activite);
        activitesConflitCheminement.stream().map((activiteEnConflit) -> new ConflitCheminement(activite, activiteEnConflit)).forEach((conflitCheminement) -> {
            listeConflits.ajouterConflit(conflitCheminement);
        });
    }
}

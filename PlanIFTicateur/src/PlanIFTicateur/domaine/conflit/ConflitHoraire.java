package PlanIFTicateur.domaine.conflit;

import PlanIFTicateur.domaine.activite.Activite;

public class ConflitHoraire implements Conflit {

    private Activite activite;

    public ConflitHoraire(Activite activite) {
        this.activite = activite;
    }

    @Override
    public String afficherConflit() {
        return activite.getCode() + " ne respecte pas ses contraintes horaires.";
    }

    @Override
    public boolean activitePresente(Activite activite) {
        return this.activite.equals(activite);
    }
}

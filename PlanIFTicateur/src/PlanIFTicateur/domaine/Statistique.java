/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine;

/**
 *
 * @author Alexandre
 */
public class Statistique 
{

    private int nbCours;
    private int nbMaxCoursEtudiantMemeJour;
    private int nbMoyenCoursEtudiantMemeJour;
    private float indiceCongestion;
    private float indiceCovoiturage;
    
    public int coursParJour(Horaire horaire, int jour)
    {
     return  nbCours;
    }
    public int nbMaxCoursParJour(Horaire horaire)
    {
        return nbMaxCoursEtudiantMemeJour;
    }
    public int nbMoyenCoursParJour(Horaire horaire)
    {
        return nbMoyenCoursEtudiantMemeJour;
    }
    public float congestionCirculation(Horaire horaire)
    {
        return indiceCongestion;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.activite;


import java.awt.Color;
/**
 *
 * @author Alexandre
 */
public abstract class Activite 
{
  private int idActivite;
  private String code;
  private float duree;
  private int jour;
  private float heureDebut;
  private String professeur;
  private float heureDebutMin;
  private float heureDebutMax;
  private float heureFinMax;

  public Activite(int idActivite,
                  String code,
                  float duree,
                  int jour,
                  float heureDebut,
                  String professeur,
                  float heureDebutMin,
                  float heureDebutMax,
                  float heureFinMax)
  {
      this.idActivite = idActivite;
      this.code = code;
      this.duree = duree;
      this.jour = jour;
      this.heureDebut = heureDebut;
      this.professeur = professeur;
      this.heureDebutMin = heureDebutMin;
      this.heureDebutMax = heureDebutMax;
      this.heureFinMax = heureFinMax;
     
  }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public float getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(float heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }

    public float getHeureDebutMin() {
        return heureDebutMin;
    }

    public void setHeureDebutMin(float heureDebutMin) {
        this.heureDebutMin = heureDebutMin;
    }

    public float getHeureDebutMax() {
        return heureDebutMax;
    }

    public void setHeureDebutMax(float heureDebutMax) {
        this.heureDebutMax = heureDebutMax;
    }

    public float getHeureFinMax() {
        return heureFinMax;
    }

    public void setHeureFinMax(float heureFinMax) {
        this.heureFinMax = heureFinMax;
    }

  /*
    Methode abstraite obligatoire dans les differentes sortes d'activite.
    */
  public abstract Color getCouleur();
  public abstract String getType();
  
  public void deplacerActivite(Activite activite)
  {
      
  }
  
  public boolean horaireValide()
  {
      return !(getHeureDebut() < getHeureDebutMin()||getHeureDebut() > getHeureDebutMax()||(getHeureDebut() + getDuree()) > getHeureFinMax());
  }
  
  public boolean memeHoraire(Activite autreActivite){
      return (getJour() == autreActivite.getJour()&&(getHeureDebut() == autreActivite.getHeureDebut() || (getHeureDebut() > autreActivite.getHeureDebut()&& getHeureDebut()<(autreActivite.getHeureDebut() + autreActivite.getDuree())) || (getHeureDebut() > autreActivite.getHeureDebut()&& (getHeureDebut() + getDuree())> (autreActivite.getHeureDebut() + autreActivite.getDuree()))));
  }
}


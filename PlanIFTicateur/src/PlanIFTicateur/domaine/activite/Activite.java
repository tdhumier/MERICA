/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.activite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Alexandre
 */
public abstract class Activite {

    private String code;
    private double duree;
    private int jour;
    private double heureDebut;
    private String professeur;
    private double heureDebutMin;
    private double heureFinMax;
    private String section;
    private String titre;
    private boolean isSelected;
    private Point point;
    private int width;
    private int height;

    public Activite(String code,
            String section,
            String titre,
            String professeur,
            String type,
            double duree,
            double heureDebutMin,
            double heureFinMax,
            int jour,
            double heureDebut) {
        this.code = code;
        this.section = section;
        this.titre = titre;
        this.professeur = professeur;
        this.duree = duree;
        this.heureDebutMin = heureDebutMin;
        this.heureFinMax = heureFinMax;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.isSelected = false;
        this.point = new Point();
    }

    public Point getPoint() {
        return point;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public double getDuree() {
        return duree;
    }

    public String getSection() {
        return section;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public double getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(double heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getProfesseur() {
        return professeur;
    }

    public double getHeureDebutMin() {
        return heureDebutMin;
    }

    public void setHeureDebutMin(float heureDebutMin) {
        this.heureDebutMin = heureDebutMin;
    }

    public double getHeureFinMax() {
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

    public void deplacerActivite(int x, int y) {
        this.point.x = x;
        this.point.y = y;
    }

    public boolean horaireValide() {
        return !(getHeureDebut() < getHeureDebutMin() || (getHeureDebut() + getDuree()) > getHeureFinMax());
    }

    public boolean memeHoraire(Activite autreActivite) {
        return (getJour() == autreActivite.getJour() && (getHeureDebut() == autreActivite.getHeureDebut() || (getHeureDebut() > autreActivite.getHeureDebut() && getHeureDebut() < (autreActivite.getHeureDebut() + autreActivite.getDuree())) || (getHeureDebut() > autreActivite.getHeureDebut() && (getHeureDebut() + getDuree()) > (autreActivite.getHeureDebut() + autreActivite.getDuree()))));
    }

    public boolean isAssignee() {
        return (jour != 0 && heureDebut != 0.0d);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean contient(int x, int y) {
        return (xEstDansLaLargeur(x) && yEstDansLaHauteur(y));
    }

    public boolean xEstDansLaLargeur(int x) {
        return (x > point.x && x < point.x + width);
    }

    public boolean yEstDansLaHauteur(int y) {
        return (y > point.y && y < point.y + height);
    }

    public void modifierStatutSelection(int x, int y) {
        this.isSelected = this.contient(x, y);
    }

    public void setDimension(Dimension dimension) {
        this.width = (int) (dimension.width * duree * 2);
        this.height = dimension.height;
    }

    public void setPoint(Dimension dimension) {
        this.point.x = (int) (dimension.width * ((getHeureDebut() - 8) * 2) + 80);
        this.point.y = dimension.height * (getJour() - 1) * 8 + 20;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void deplacerActivite(Point point, double heure, int jour) {
        this.point = point;
        this.heureDebut = heure;
        this.jour = jour;
    }

}

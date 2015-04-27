/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Pierre-Luc
 */
public class NotesDao {
    private File notes;
   
    public NotesDao(String file) {
        try{
        notes = new File(file);
        
        } catch (Exception e){
           
        }
    }
    
    public ArrayList<String> importerNotes(){
        ArrayList<String> notesListe = new ArrayList();
        String ligne;
        try{
            BufferedReader brNotes = new BufferedReader(new FileReader(notes));
            while((ligne = brNotes.readLine())!= null){
                notesListe.add(ligne);
            }
            brNotes.close();
        }catch (Exception e) {
            
        }
        return notesListe;
    }
    
    public void sauvegarderNotes(ArrayList<String> notesLignes){
        try{
            
            FileWriter notesWriter = new FileWriter(notes);
            BufferedWriter notesBW = new BufferedWriter(notesWriter);
            PrintWriter notesPW = new PrintWriter(notesBW);
            for (String ligne : notesLignes){
                notesPW.println(ligne);
            }
            notesPW.close();
        } catch (Exception e){
            System.out.println("fuck you");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martindeligny1
 */
public class EcritureCsv {

    private File file;
    private char separator;

    public EcritureCsv(String file) {
        this(new File(file), ',');
    }

    private EcritureCsv() {
    }

    public EcritureCsv(File file, char separator) {

        if (file == null) {
            throw new IllegalArgumentException("Le fichier ne peut pas etre nul");
        }

        this.file = file;
        this.separator = separator;
    }

    public void write(List<ArrayList<String>> mappedData) throws IOException {

        System.out.println("Dans ActiviteDao / Write 1");

        if (mappedData == null) {
            throw new IllegalArgumentException("la liste ne peut pas être nulle");
        }

        if (mappedData.isEmpty()) {
            //writeEmptyFile();
        }

        final String[] titles = new String[]{"CodeActivité", "Section", "Titre", "PROF", "Type", "Durée", "DébutMin", "FinMax", "Jour", "Heure"};

        write(mappedData, titles);
    }

    public void write(List<ArrayList<String>> mappedData, String[] titles) throws IOException {

        System.out.println("Dans ActiviteDao / Write 2");

        if (mappedData == null) {
            throw new IllegalArgumentException("la liste ne peut pas être nulle");
        }

        if (mappedData.isEmpty()) {
            //writeEmptyFile();
        }

        FileOutputStream out = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "cp850"));

        // Les titres
        boolean first = true;
        for (String title : titles) {
            if (first) {
                first = false;
            } else {
                bw.write(separator);
            }
            write(title, bw);
        }
        bw.write("\n");

        // Les données
        for (ArrayList<String> oneData : mappedData) {
            first = true;
            for (String val : oneData) {
                if (first) {
                    first = false;
                } else {
                    bw.write(separator);
                }
                write(val, bw);
            }
            bw.write("\n");

        }
        bw.close();
    }

    private void write(String value, BufferedWriter bw) throws IOException {

        System.out.println("Dans ActiviteDao / Write 3");

        if (value == null) {
            value = "";
        }

        boolean needQuote = false;

        if (value.indexOf("\n") != -1) {
            needQuote = true;
        }

        if (value.indexOf(separator) != -1) {
            needQuote = true;
        }

        if (value.indexOf("\"") != -1) {
            needQuote = true;
            value = value.replaceAll("\"", "\"\"");
        }

        if (needQuote) {
            value = "\"" + value + "\"";
        }
        System.out.println(value);
        bw.write(value);
    }

}

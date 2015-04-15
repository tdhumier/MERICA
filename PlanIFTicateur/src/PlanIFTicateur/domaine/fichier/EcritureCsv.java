/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanIFTicateur.domaine.fichier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author martindeligny1
 */
public class EcritureCsv {

    private File file;
    private char separator;

    public EcritureCsv(File file) {
        this(file, ';');
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

    public void write(List<Map<String, String>> mappedData) throws IOException {

        if (mappedData == null) {
            throw new IllegalArgumentException("la liste ne peut pas être nulle");
        }

        if (mappedData.isEmpty()) {
            //writeEmptyFile();
        }
        final Map<String, String> oneData = mappedData.get(0);

        final String[] titles = new String[oneData.size()];

        int i = 0;
        for (String key : oneData.keySet()) {
            titles[i++] = key;
        }
        write(mappedData, titles);
    }

    public void write(List<Map<String, String>> mappedData, String[] titles) throws IOException {

        if (mappedData == null) {
            throw new IllegalArgumentException("la liste ne peut pas être nulle");
        }

        if (titles == null) {
            throw new IllegalArgumentException("les titres ne peuvent pas être nuls");
        }

        if (mappedData.isEmpty()) {
            //writeEmptyFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

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
        for (Map<String, String> oneData : mappedData) {
            first = true;
            for (String title : titles) {
                if (first) {
                    first = false;
                } else {
                    bw.write(separator);
                }
                final String value = oneData.get(title);
                write(value, bw);

            }
            bw.write("\n");

            bw.close();
            fw.close();
        }
    }

    private void write(String value, BufferedWriter bw) throws IOException {

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

        bw.write(value);
    }

}

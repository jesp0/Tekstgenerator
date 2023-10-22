package oblig2.tekstgenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SisteOrd {
    protected LinkedList<String> ordListe;
    protected LinkedList<Integer> tallListe;

    public SisteOrd() {
        ordListe = new LinkedList<>();
        tallListe = new LinkedList<>();
    }

    public void leggTilOrd(String ord) {
        // Clean the input word by removing special characters and converting to lowercase
        ord = ord.replaceAll("[.,!;]", "").toLowerCase().trim();

        int index = -1; // Initialize with -1 to indicate that the word is not found in the list.

        for (int i = 0; i < ordListe.size(); i++) {
            String ordNå = ordListe.get(i).replaceAll("[.,!;:]", "").toLowerCase().trim(); // Clean and normalize list word
            //System.out.println("Comparing " + ord + " with " + ordListe.get(i)); // Debugging output
            if (ordNå.equals(ord)) {
                index = i;
                break; // Exit the loop once a match is found.
            }
        }

        if (index != -1) {
            int count = tallListe.get(index);
            tallListe.set(index, count + 1);
        } else {
            ordListe.add(ord);
            tallListe.add(1);
        }
        //System.out.println("Index: " + index); // Debugging output
    }

    public LinkedList<String> getOrdListe() {
        return ordListe;
    }

    public LinkedList<Integer> getTallListe() {
        return tallListe;
    }
}




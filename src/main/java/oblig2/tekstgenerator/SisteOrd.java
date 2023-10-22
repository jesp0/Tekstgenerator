package oblig2.tekstgenerator;

import java.util.LinkedList;

/**
 * Klassen oppretter et objekt som holder på to linkedlister for å holde på statistikk over forekomst av tredje ord.
 */
public class SisteOrd {
    protected LinkedList<String> ordListe;
    protected LinkedList<Integer> tallListe;

    public SisteOrd() {
        ordListe = new LinkedList<>();
        tallListe = new LinkedList<>();
    }

    /**
     * Metoden sjekker om ordet eksisterer fra før.
     * Dersom det eksisterer endres verdien i tallListe parallellt med ordListe.
     * Dersom den ikke eksisterer legges den bare til.
     * @param ord
     */
    public void leggTilOrd(String ord) {
        // Gi verdi -1 dersom ordet ikke finnes i lista.
        int index = -1;
        for (int i = 0; i < ordListe.size(); i++) {
            String ordNå = ordListe.get(i);
            if (ordNå.equals(ord)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int tell = tallListe.get(index);
            tallListe.set(index, tell + 1);
        } else {
            ordListe.add(ord);
            tallListe.add(1);
        }
    }
    public LinkedList<String> getOrdListe() {
        return ordListe;
    }
    public LinkedList<Integer> getTallListe() {
        return tallListe;
    }
}
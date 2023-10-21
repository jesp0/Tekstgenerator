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
        int index = ordListe.indexOf(ord);
        if (index != -1) {
            int count = tallListe.get(index);
            tallListe.set(index, count + 1);
        } else {
            ordListe.add(ord);
            tallListe.add(1);
        }
        System.out.println(ordListe.indexOf(ord));
    }

    public LinkedList<String> getOrdListe() {
        return ordListe;
    }

    public LinkedList<Integer> getTallListe() {
        return tallListe;
    }
}




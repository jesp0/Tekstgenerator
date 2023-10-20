package oblig2.tekstgenerator;

import java.util.LinkedList;

public class SisteOrd {

    protected LinkedList<String> ordListe;
    protected LinkedList<Integer> tallListe;

    public SisteOrd(LinkedList<String> ordListe, LinkedList<Integer> tallListe){
        this.ordListe = ordListe;
        this.tallListe = tallListe;
    }


    public LinkedList<String> getOrdListe() {
        return ordListe;
    }

    public LinkedList<Integer> getTallListe() {
        return tallListe;
    }

    public void setOrdListe(LinkedList<String> ordListe) {
        this.ordListe = ordListe;
    }

    public void setTallListe(LinkedList<Integer> tallListe) {
        this.tallListe = tallListe;
    }
}

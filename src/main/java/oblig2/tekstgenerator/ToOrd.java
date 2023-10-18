package oblig2.tekstgenerator;

import java.util.LinkedList;

public class ToOrd implements Comparable<ToOrd> {

    private String ord1;
    private String ord2;
    protected static LinkedList<String> ordListe = new LinkedList<>();
    protected static LinkedList<Integer> tallListe = new LinkedList<>();


    public ToOrd(String ord1, String ord2) {
        this.ord1 = ord1;
        this.ord2 = ord2;

    }

    public String getOrd1() {
        return ord1;
    }

    public String getOrd2() {
        return ord2;
    }

    @Override
    public int compareTo(ToOrd o) {
        if (this.getOrd1().equals(o.getOrd1()) && this.getOrd2().equals(o.getOrd2()))
            return 0;
        return -1;
    }

}

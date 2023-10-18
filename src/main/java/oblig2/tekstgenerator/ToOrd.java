package oblig2.tekstgenerator;

import java.util.HashMap;
import java.util.LinkedList;

public class ToOrd implements Comparable<ToOrd> {

    private String ord1;
    private String ord2;
    private int hyppighet;
    protected HashMap<LinkedList<String>,LinkedList<Integer>> tredjeOrdMap = new HashMap<>(); // Det tredje ordet som blir sendt med fra ordsamling
    // putte ordliste som key, tallListe som value
    protected static LinkedList<String> ordListe = new LinkedList<>();
    protected static LinkedList<Integer> tallListe = new LinkedList<>();

    @Override
    public String toString() {
        return "ToOrd{" +
                "ord1='" + ord1 + '\'' +
                ", ord2='" + ord2 + '\'' +
                ", hyppighet=" + hyppighet +
                ", tredjeOrdMap=" + tredjeOrdMap +
                '}';
    }

    // hver toOrd må ha sitt eget hashmap med key: tredjeord, value: hyppighet
    public ToOrd(String ord1, String ord2, String ord3) {
        this.ord1 = ord1;
        this.ord2 = ord2;
        //System.out.println("To Ord 1");
        if(ordListe.isEmpty()){
            ordListe.add(ord3);
        }else{
            for(String s : ordListe){
                if(ord3.equals(s)) {
                    tallListe.add(++hyppighet);
                }
            }
            ordListe.add(ord3);
        }
        tredjeOrdMap.put(ordListe, tallListe);
        if(hyppighet>1){
            System.out.println(this);
            // Alle ord 3 havner i samme linkedlist indeks
        }

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

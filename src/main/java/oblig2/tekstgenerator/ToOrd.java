package oblig2.tekstgenerator;

import java.util.HashMap;
import java.util.LinkedList;

public class ToOrd implements Comparable<ToOrd> {

    private String ord1;
    private String ord2;
    private String ord3;
    private int hyppighet;
    protected HashMap<LinkedList<String>,LinkedList<Integer>> tredjeOrdMap = new HashMap<>(); // Det tredje ordet som blir sendt med fra ordsamling
    // putte ordliste som key, tallListe som value
    //protected LinkedList<String> ordListe;
    //protected LinkedList<Integer> tallListe;
    protected static LinkedList<ToOrd> samlingToOrd = new LinkedList<>();

    // hver toOrd må ha sitt eget hashmap med key: tredjeord, value: hyppighet

    public ToOrd(String ord1, String ord2, SisteOrd sisteOrd) {
        this.ord1 = ord1;
        this.ord2 = ord2;
        // Denne skal inn i ordTab
        //this.ord3 = ord3;
        //this.ordListe = ordTab;
        //this.tallListe = tallTab;

        /*
            if(ordTab.isEmpty()){
                ordTab.add(ord3);
                //System.out.println("TEST");
            }else{
                for(String s : ordTab){
                    if(ord3.equals(s)) {
                        //System.out.println("test2");
                        tallTab.add(++hyppighet);
                    }
                }
                ordTab.add(ord3);
            }

        tredjeOrdMap.put(ordTab, tallTab);*/
        //System.out.println(ordTab);
        if(hyppighet>1){
            //System.out.println(this);
            // Alle ord 3 havner i samme linkedlist indeks
        }
            //if (samlingToOrd.isEmpty() || ord.compareTo(this) != 0) {
                samlingToOrd.add(this);


    }
    @Override
    public String toString() {
        return "ToOrd{" +
                "ord1='" + ord1 + '\'' +
                ", ord2='" + ord2 + '\'' +
                ", hyppighet=" + hyppighet +
                ", tredjeOrdMap=" + tredjeOrdMap +
                '}';
    }

    public String getOrd1() {
        return ord1;
    }

    public String getOrd2() {
        return ord2;
    }

    public String getOrd3() {
        return ord3;
    }



    @Override
    public int compareTo(ToOrd o) {
        if (this.getOrd1().equals(o.getOrd1()) && this.getOrd2().equals(o.getOrd2()))
            return 0;
        return -1;
    }

    // Må endre hshcode her også, så vi kan sammenligne
    @Override
    public int hashCode() {
        return ord1.hashCode() + ord2.hashCode();
    }

    /*Lage en metode som tar inn ord mapet og sjekker
    * at det er flere av dem. Hvis det er flere så legger
    * sp lager vi en toOrd av det og legger siste ordet i
    * den linkedListen. Den må kanskje finnes i TG*/

}

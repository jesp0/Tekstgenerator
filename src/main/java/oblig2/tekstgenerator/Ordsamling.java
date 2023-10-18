package oblig2.tekstgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Ordsamling implements Comparable<Ordsamling> {
    protected static HashMap<Ordsamling, Integer> ordMap = new HashMap<>();
    protected int teller = 0;
    protected String[] treOrd = new String[3];
    protected String ord1;
    protected String ord2;
    protected String ord3;
    protected static ArrayList<String> muligeOrd;
    public  int ant = 0;


    public Ordsamling(String ord1, String ord2, String ord3){
        this.ord1 = ord1;
        this.ord2 = ord2;
        this.ord3 = ord3;
        ToOrd toOrd = new ToOrd(ord1,ord2);
        //if (toOrd.compareTo())
        ToOrd.ordListe.add(ord3);





        // if de to første orda er like som et annet objekt sine to første ord, så tell denne forekomsten (sjekkk dette med å bruke compare
        // if(ordMap.containsKey(this){
        //  Endre value på denne keyen
        // }Else{
        //  ordMap.put(this, antall)
        // }
    }

    public Ordsamling(String[] ordTab, String ord3){
        //toOrd.put(ordTab, ord3);
    }
/*
    public HashMap<> getToOrd() {
        return toOrd;
    }
*/
    public void sjekk(){
        for(Ordsamling key : ordMap.keySet()){
            if(key.compareTo(this) == 0){
                System.out.println("Like ord!!!!!!");
            }
        }

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
    public int getAnt() {
        return ant;
    }

    public String[] getTreOrd() {
        return treOrd;
    }

    public void setAnt(int ant) {
        this.ant = ant;
    }

    /*
        public void setToOrd(String[] toOrd) {
            this.toOrd = toOrd;
        }
    */
    public void setTreOrd(String[] treOrd) {
        this.treOrd = treOrd;
    }

    public void organisering(){

    }


// hvis keyen allerede finns henter vi ut arraylisten som er value til key, hent ut og legg inn igjen (pga overskriving)
        /*
        if (litenTab eksisterer som key i et hashMap){
            legg til aktuelt tredje ord i value (som er arraylist)
            ta ut alle orda i arraylist, sjekk likhet, tell forekomster av tredje ord (i metode)
        }
        else{

        }
         */


    /*
    public String toString(Ordsamling treOrd){
        return "" + treOrd.get;
    }
*/
    // Generert av IntelliJ
    @Override
    public String toString() {
        return "Ordsamling{" +
                " ord1='" + ord1 + '\'' +
                ", ord2='" + ord2 + '\'' +
                ", ord3='" + ord3 + '\'' +
                ", ant='" + ant + '\'' +
                '}';
    }



    @Override
    public int compareTo(Ordsamling o) {
        if(this.getOrd1().equals(o.getOrd1()) && this.getOrd2().equals(o.getOrd2()) && this.getOrd3().equals(o.getOrd3())){
            return 0;
        }
        return -1;
    }


    /*
            Til et objekt
            Sjekk deg ved bruk av compare to
            Hvis du får false
            opprett egen arraylist og legg ditt tredje ord i lista
            dersom true
            fjern din egen key (dere har samme key) og legg tredje ord i arraylist til eksisterende key som er lik

     */

}

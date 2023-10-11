package oblig2.tekstgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Ordsamling {
    protected static HashMap<Ordsamling, Integer> ordMap = new HashMap<>();
    protected int teller = 0;
    protected String[] treOrd = new String[3];
    protected HashMap<String[], ArrayList<String>> toOrd = new HashMap<>();


    // Flytte ordMap(hasmap) hit???
    // HashMap<Ordsamling, Integer> ordMap = new HashMap<>();

    public Ordsamling(String ord1, String ord2, String ord3){
        treOrd[0] = ord1;
        treOrd[1] = ord2;
        treOrd[2] = ord3;
        ordDeling(treOrd);


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
    public String[] getTreOrd() {
        return treOrd;
    }
/*
    public void setToOrd(String[] toOrd) {
        this.toOrd = toOrd;
    }
*/
    public void setTreOrd(String[] treOrd) {
        this.treOrd = treOrd;
    }

    public void søk(){

    }
    public void ordDeling(String[] ordTab){

        String[] litenTab = new String[]{ordTab[0], ordTab[1]};
        ArrayList<String> muligeOrd = new ArrayList<>();
        String ord3 = ordTab[2];

        if(toOrd.containsKey(litenTab)) {
            /*if(toOrd.containsValue()) {
                // tell
            }else{
                muligeOrd.add(ord3);
                toOrd.put(litenTab, muligeOrd);
            }
            */

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

    }
    /*
    @Override
    public String toString(Ordsamling treOrd){
        return "" + treOrd.get;
    }

     */
}

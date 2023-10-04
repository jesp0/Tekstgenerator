package oblig2.tekstgenerator;

public class Ordsamling {
    protected String[] treOrd = new String[3];
    protected String[] toOrd = new String[2];

    public Ordsamling(String ord1, String ord2, String ord3){
        treOrd[0] = ord1;
        treOrd[1] = ord2;
        treOrd[2] = ord3;

        // if de to første orda er like som et annet objekt sine to første ord, så tell denne forekomsten (sjekkk dette med å bruke compare
        //
    }
    public Ordsamling(String ord1, String ord2){
        toOrd[0] = ord1;
        toOrd[1] = ord2;
    }

    public String[] getToOrd() {
        return toOrd;
    }

    public String[] getTreOrd() {
        return treOrd;
    }

    public void setToOrd(String[] toOrd) {
        this.toOrd = toOrd;
    }

    public void setTreOrd(String[] treOrd) {
        this.treOrd = treOrd;
    }
}

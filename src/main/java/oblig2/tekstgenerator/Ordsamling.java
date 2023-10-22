package oblig2.tekstgenerator;

import java.util.*;

/**
 * Klassen holder på en samling av tre ord. Den kan sammenligne en treOrd-samling.
 */
public class Ordsamling implements Comparable<Ordsamling> {
    protected String ord1;
    protected String ord2;
    protected String ord3;

    public Ordsamling(String ord1, String ord2, String ord3){
        this.ord1 = ord1;
        this.ord2 = ord2;
        this.ord3 = ord3;

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
    public String toString() {
        return "Ordsamling{" +
                " ord1='" + ord1 + '\'' +
                ", ord2='" + ord2 + '\'' +
                ", ord3='" + ord3 + '\'' +
                '}';
    }
    @Override
    public int compareTo(Ordsamling o) {
        if(this.getOrd1().equals(o.getOrd1()) && this.getOrd2().equals(o.getOrd2()) && this.getOrd3().equals(o.getOrd3())){
            return 0;
        }
        return -1;
    }

    /**
     * Legger sammen verdien av hver Strings hashcode for å få en enkelt sum.
     * @return hashcode til objektet
     */
    @Override
    public int hashCode() {
        return ord1.hashCode() + ord2.hashCode() + ord3.hashCode();
    }

    /**
     * Avgjør om alle tre ord er like for at et objekt skal være likt.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordsamling annen = (Ordsamling) o;
        return Objects.equals(ord1, annen.ord1) &&
                Objects.equals(ord2, annen.ord2) &&
                Objects.equals(ord3, annen.ord3);
    }
}



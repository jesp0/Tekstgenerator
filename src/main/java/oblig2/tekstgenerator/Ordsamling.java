package oblig2.tekstgenerator;

import java.util.*;

public class Ordsamling implements Comparable<Ordsamling> {

    protected int teller = 0;
    protected String ord1;
    protected String ord2;
    protected String ord3;

    protected int toOrdTeller = 0;



    public Ordsamling(String ord1, String ord2, String ord3){
        this.ord1 = ord1;
        this.ord2 = ord2;
        this.ord3 = ord3;

    }

    public Ordsamling(String[] ordTab, String ord3){
        //toOrd.put(ordTab, ord3);
    }

    public void sjekk(){

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






    /*
        public void setToOrd(String[] toOrd) {
            this.toOrd = toOrd;
        }
    */


    public void organisering(){

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

    @Override
    public int hashCode() {
        return ord1.hashCode() + ord2.hashCode() + ord3.hashCode();
    }

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



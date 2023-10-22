package oblig2.tekstgenerator;

import java.util.Objects;

/**
 * Klassen oppretter et objekt av to ord.
 */
public class ToOrd implements Comparable<ToOrd> {
    private String ord1;
    private String ord2;

    public ToOrd(String ord1, String ord2){
        this.ord1=ord1;
        this.ord2=ord2;
    }
    @Override
    public String toString() {
        return "ToOrd{" +
                "ord1='" + ord1 + '\'' +
                ", ord2='" + ord2 + '\'' +
                '}';
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
    @Override
    public int hashCode() {
        return ord1.hashCode() + ord2.hashCode();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToOrd toOrd = (ToOrd) o;
        return Objects.equals(ord1, toOrd.ord1) && Objects.equals(ord2, toOrd.ord2);
    }
}

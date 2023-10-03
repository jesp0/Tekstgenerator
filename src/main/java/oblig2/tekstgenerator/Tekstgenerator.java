package oblig2.tekstgenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Tekstgenerator extends Application {
    LinkedHashMap<Ordsamling,Integer> ordTab;
    // ord som indeks, plusse på talla
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scanner sc = new Scanner(new URL("https://www.vg.no/nyheter/innenriks/i/76Ov8W/lysbakken-stadig-mer-alvorlig-for-solberg").openStream());
/*

        String inputLine;
        while (sc.hasNextLine()) {
            inputLine = sc.next();
            System.out.println(inputLine);
        }
        sc.close();

        URL yahoo = new URL("https://snl.no/Norge_i_senmiddelalderen");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yahoo.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
*/



        Scene scene = new Scene(pane, 320, 240);
        stage.setTitle("Oblig 2 - Tekstgenerator");
        stage.setScene(scene);
        stage.show();
    }

    public void hentTekst(){
        try {
            Scanner leser = new Scanner(new File("src/main/resources/oblig2/tekstgenerator/historie.txt"));
           //String ord;
            String ord1;
            String ord2;
            String ord3;
            int teller = 0;
            Ordsamling treOrd;
            while (leser.hasNextLine()) {
                ord1 = leser.next();
                ord2 = leser.next();
                ord3 = leser.next();
                if(!leser.hasNext()) {
                    // lag en ordsamblig
                }else{
                    //rekursivt kall
                }


                treOrd = new Ordsamling(ord1,ord2,ord3);
                ordTab.put(treOrd, teller);

               // ord = leser.next();
               // System.out.println(ord);
            }
            leser.close();
        }catch (FileNotFoundException e){
            System.out.println("feil " + e);
        }
    }
    public void organiserOrd(Ordsamling array){
        // Splitt 3 og 3 og legg inn i matrise
        // Splitt, matrise, hashmap
        StringBuffer buffer = new StringBuffer();
        // få tak i tekst
        // splitte på mellomrom
        // buffer.append(next)
        // tre ganger, så det ligger i bufferen
        // ordsamling.toOrd(bruker konstruktøren)
        // sende inn bufferen i stedet for 2 strings


    }

    public void rekursiv(Ordsamling treOrd){
        // ta de 3 orda,hent de to siste, legg til et nytt
        //
    }

    public static void main(String[] args) {
        launch();
    }
}
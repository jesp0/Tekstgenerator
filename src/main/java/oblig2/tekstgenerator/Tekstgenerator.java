package oblig2.tekstgenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Tekstgenerator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        hentTekst();
        Scene scene = new Scene(pane, 320, 240);
        stage.setTitle("Oblig 2 - Tekstgenerator");
        stage.setScene(scene);
        stage.show();
    }

    public void hentTekst(){
        // Fungerer fordi alle URLene er (nesten) like
        String[] linker = {"norskeeventyr", "SAS-Wikipedia", "straffeloven", "tusenogennatt", "vikingene", "Helsesørøst", "Norgeisenmiddelalderen"};
        int teller = 0;
        for(String link : linker){
            try {
                URL url = new URL("https://web01.usn.no/~lonnesta/kurs/kurs6124/Oblig/tekst/" + link + ".txt");
                Scanner leser = new Scanner(new InputStreamReader(url.openStream()));

                String ord1 = leser.next();
                String ord2 = leser.next();
                String ord3;
                Ordsamling treOrd;
                while (leser.hasNext()) {
                    ord3 = leser.next();
                    treOrd = new Ordsamling(ord1, ord2, ord3);
                    //System.out.println(ord1 + " " + ord2 + " " + ord3);
                    ord1 = ord2;
                    ord2 = ord3;
                    //teller
                    Ordsamling.ordMap.put(treOrd, teller);
                    //Ordsamling.toOrd.put(ord1, ord2);

                    teller++;
                    System.out.println(teller);

                    if(leser.hasNext()) {
                        // lag en ordsamblig
                    }else{
                        //rekursivt kall
                    }
                }
                leser.close();
            }catch (FileNotFoundException e){
                System.out.println("feil " + e);
            }catch (IOException e){
                System.out.println("URL feil - " + e);
            }
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

    /**
     * Metoden henter tre og tre ord fra innlest fil, og legger de til i et Hashmap
     * @param scanner
     * @param ord1
     * @param ord2
     */
    public void rekursiv(Scanner scanner, String ord1, String ord2){
        int teller = 0;
        if(scanner.hasNext()) {
            String ord3 = scanner.next();
            switch (ord3.charAt((ord3.length()-1))){
                case '.': break;
                case ',': break;
                case ';': break;
                case ':': break;
                case '!': break;
                case '?': break;
                // tell tegna så vi kan bruke sannsynlighet for å finne ut av når de skal dukke opp
            }
            Ordsamling ordsamling = new Ordsamling(ord1, ord2, ord3);
            //OrordMap.put(ordsamling, 1);
            //System.out.println(ord1 + " " + ord2 +  " "+ ord3);
            rekursiv(scanner, ord2, ord3);
            // if last character i stringen er .;,:!?
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
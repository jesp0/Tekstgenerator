package oblig2.tekstgenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Tekstgenerator extends Application {
    //protected  ArrayList<Ordsamling> ordListe = new ArrayList<>();
    protected static HashMap<Ordsamling, Integer> ordMap;
    protected static int treOrdAnt;
    protected static HashMap<ToOrd,SisteOrd> toOrdSamling ;
    @Override
    public void start(Stage stage) throws IOException {
        ordMap = new HashMap<>();
        toOrdSamling = new HashMap<>();
        Pane pane = new Pane();
        hentTekst();
        opprettToOrd();
        //System.out.println(ordMap.toString());
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
                //URL url = new URL("https://web01.usn.no/~lonnesta/kurs/kurs6124/Oblig/tekst/" + link + ".txt");
                URL url = new URL("https://web01.usn.no/~lonnesta/kurs/kurs6124/Oblig/tekst/" + link + ".txt");

                Scanner leser = new Scanner(new InputStreamReader(url.openStream(), StandardCharsets.UTF_16));

                String ord1 = leser.next();
                String ord2 = leser.next();
                String ord3;
                Ordsamling treOrd;
                while (leser.hasNext()) {
                    ord3 = leser.next();
                    treOrd = new Ordsamling(ord1, ord2, ord3);
                    opprettStatistikk(treOrd);
                    ord1 = ord2;
                    ord2 = ord3;
                }
                leser.close();

            }catch (FileNotFoundException e){
                System.out.println("Fil ikke funnet - " + e);
            }catch (IOException e){
                System.out.println("URL feil - " + e);
            }
        }
        //sjekkToOrd();
    }

    // Lager statistikk på hyppigheten av en bestemt ordsamling
    public void opprettStatistikk(Ordsamling treOrd) {
        if (ordMap.containsKey(treOrd)) {
            int tell = ordMap.get(treOrd);
            ordMap.put(treOrd, tell + 1);
        } else {
            ordMap.put(treOrd, 1);
        }
    }

    public void opprettToOrd() {
        for (Ordsamling key : ordMap.keySet()) {
            ToOrd toOrd = new ToOrd(key.getOrd1(), key.getOrd2());
            SisteOrd sisteOrd = toOrdSamling.get(toOrd);

            if (sisteOrd == null) {
                toOrdSamling.put(toOrd, new SisteOrd());
                sisteOrd = toOrdSamling.get(toOrd);
            }

            sisteOrd.leggTilOrd(key.getOrd3()); // This will add the third word to SisteOrd
        }
    }


   /* public void sjekkSisteOrd(SisteOrd sisteOrd, String ord){
       /* LinkedList<String> ordliste = sisteOrd.getOrdListe();

        for (String o : ordliste){
            if (ordliste.contains(ord)){
                System.out.println("if kjører!");
                int index = ordliste.indexOf(o);
                sisteOrd.getTallListe().add(index, sisteOrd.getTallListe().get(index)+1);
            }else {
                System.out.println("else kjører!");
                sisteOrd.getOrdListe().add(ord);
                sisteOrd.getTallListe().add(1);
            }
        }

        LinkedList<String> ordliste = sisteOrd.getOrdListe();
        boolean found = false;

        for (String o : ordliste) {
            if (o.equals(ord)) {
                found = true;
                int index = ordliste.indexOf(o);
                sisteOrd.getTallListe().set(index, sisteOrd.getTallListe().get(index) + 1);
                System.out.println("Samme");
            }
        }

        if (!found) {
            sisteOrd.getOrdListe().add(ord);
            sisteOrd.getTallListe().add(1);
            System.out.println("Nytt ord");
        }


    }*/



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
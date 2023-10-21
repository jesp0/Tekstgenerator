package oblig2.tekstgenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
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
    String[] linker = {"norskeeventyr", "SAS-Wikipedia", "straffeloven", "tusenogennatt", "vikingene", "Helsesørøst", "Norgeisenmiddelalderen"};
    //ComboBox<String>  filValg = new ComboBox<>();

    @Override
    public void start(Stage stage) throws IOException {
        //filValg.getItems().addAll(linker);

        ordMap = new HashMap<>();
        toOrdSamling = new HashMap<>();
        FlowPane panel = new FlowPane();
        hentTekst();
        opprettToOrd();

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(40);

        Button generateButton = new Button("Lag tekst");
        generateButton.setOnAction(event -> {
            String randomText = genererTekst(new SisteOrd(), 1000); // Adjust the desired text length
            textArea.setText(randomText);
        });

        panel.getChildren().addAll(textArea, generateButton);


        //System.out.println(ordMap.toString());
        Scene scene = new Scene(panel, 400, 240);
        stage.setTitle("Oblig 2 - Tekstgenerator");
        stage.setScene(scene);
        stage.show();
    }

    public void hentTekst(){
        // Fungerer fordi alle URLene er (nesten) like

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
    public String genererTekst(SisteOrd sisteOrd, int textLength) {
        Random random = new Random();
        String[] nyeOrd = hentOrd();
        String ord1 = nyeOrd[0];
        String ord2 = nyeOrd[1];
        return genererTekst(sisteOrd, textLength, ord1, ord2, new StringBuilder());
    }

    private String genererTekst(SisteOrd sisteOrd, int remainingLength, String ord1, String ord2, StringBuilder generatedText) {
        if (remainingLength == 0) {
            return generatedText.toString();
        }

        Random random = new Random();
        ToOrd toOrd = new ToOrd(ord1, ord2);
        SisteOrd currentSisteOrd = toOrdSamling.get(toOrd);

        if (currentSisteOrd != null) {
            LinkedList<String> ordListe = currentSisteOrd.getOrdListe();
            LinkedList<Integer> tallListe = currentSisteOrd.getTallListe();
            int totalTeller = tallListe.stream().mapToInt(Integer::intValue).sum();
            int randomCount = random.nextInt(totalTeller);
            int cumulativeCount = 0;
            String nextWord = null;

            for (int j = 0; j < ordListe.size(); j++) {
                cumulativeCount += tallListe.get(j);
                if (cumulativeCount >= randomCount) {
                    nextWord = ordListe.get(j);
                    break;
                }
            }

            if (nextWord != null) {
                generatedText.append(nextWord).append(" ");
                return genererTekst(sisteOrd, remainingLength - 1, ord2, nextWord, generatedText);
            }
        }

        // If no suitable word is found, return the generated text.
        return generatedText.toString();
    }
    public String[] hentOrd() {
        if (ordMap.isEmpty()) {
            return new String[]{"", ""}; // Handle the case where the map is empty.
        }

        List<Ordsamling> keysAsList = new ArrayList<>(ordMap.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(keysAsList.size());

        Ordsamling randomOrdsamling = keysAsList.get(randomIndex);
        String[] words = new String[2];
        words[0] = randomOrdsamling.getOrd1();
        words[1] = randomOrdsamling.getOrd2();

        return words;
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

package oblig2.tekstgenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** Programmet tar inn tekst fra varierende tekstfiler, behandler denne teksten ved å lage statistikk på forekomst av to-ords kombinasjoner
 * ved å deretter plukke ut det tredje ordet som skal skrives basert på statistikken. Ved noen tilfeller vil sluttdelen av teksten være det samme
 * ordet x antall ganger. Da er det bare å trykke "lag tekst" på nytt, så forsvinner det (det er en bug).
 * Spesialtegn blir plukket ut og plassert tilfeldig i den genererte teksten.
 *
 * Applikasjonen er laget av:
 * Marion Amlie
 * Andreas Kjørås
 * Jesper Kraft
 */
public class Tekstgenerator extends Application {
    protected static HashMap<Ordsamling, Integer> ordMap;
    protected static HashMap<ToOrd,SisteOrd> toOrdSamling ;
    String[] linker = {"norskeeventyr", "SAS-Wikipedia", "tusenogennatt", "vikingene", "Helsesørøst", "Norgeisenmiddelalderen"};
    TextArea textArea = new TextArea();
    TextField input = new TextField();
    Label label = new Label("Ønsket antall ord:");
    int brukerInput;

    @Override
    public void start(Stage stage) throws IOException {
        final int BREDDE = 800;
        final int HØYDE = 500;
        BorderPane panel = new BorderPane();
        VBox vbox = new VBox();
        ordMap = new HashMap<>();
        toOrdSamling = new HashMap<>();

        hentTekst();
        opprettToOrd();

        textArea.setPrefColumnCount(80);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(50);

        Button genKnapp = new Button("Lag tekst");
        genKnapp.setOnAction(e -> {
            genererTekst();
        });

        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(label, input, genKnapp);
        panel.setCenter(textArea);
        panel.setRight(vbox);
        panel.setPadding(new Insets(10));

        Scene scene = new Scene(panel, BREDDE, HØYDE);
        stage.setTitle("Oblig 2 - Tekstgenerator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoden leser tekst fra fil.
     * Oppretter tre-ords samlinger og kaller på opprettStatistikk()
     */
    public void hentTekst(){
        for(String link : linker){
            try {
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
    }

    /**
     * Lager statistikk på hyppigheten av en bestemt ordsamling
     * @param treOrd blir sendt med fra hentTekst()
     */
    public void opprettStatistikk(Ordsamling treOrd) {
        if (ordMap.containsKey(treOrd)) {
            int tell = ordMap.get(treOrd);
            ordMap.put(treOrd, tell + 1);
        } else {
            ordMap.put(treOrd, 1);
        }
    }

    /**
     * I denne metoden oppretter vi et ToOrd samt at vi lager et SisteOrd-objekt som knytter det tredje ordet til ToOrd-objektet.
     * Det tredje ordet blir sendt videre til en sjekk i leggTilOrd()
     */
    public void opprettToOrd() {
        for (Ordsamling key : ordMap.keySet()) {
            ToOrd toOrd = new ToOrd(key.getOrd1(), key.getOrd2());
            SisteOrd sisteOrd = toOrdSamling.get(toOrd);

            if (sisteOrd == null) {
                toOrdSamling.put(toOrd, new SisteOrd());
                sisteOrd = toOrdSamling.get(toOrd);
            }
            sisteOrd.leggTilOrd(key.getOrd3());
        }
    }

    /**
     * Drivermetode for å gjøre en rekursiv generering av tekst ved å hente en tilfeldig ToOrd med getRandomToOrd().
     * Teksten blir lagt ut i brukergrensenittet.
     */
    private void genererTekst() {
        try{
            label.setTextFill(Color.BLACK);
            // Henter antall ord fra bruker
            brukerInput = Integer.parseInt(input.getText());
        }catch(NumberFormatException e){
            input.setText("");
            input.setPromptText("KUN HELTALL!");
            label.setTextFill(Color.RED);
        }
        ToOrd randomToOrd = getRandomToOrd();
        String generatedText = genererTekst(toOrdSamling, brukerInput, randomToOrd, "", false);
        textArea.setText(generatedText);
    }

    /**
     * Metoden avgjør hvilket tredje ord som skal skrives ut med de to ordene.
     * Metoden sørger også for at det blir stor forbokstav etter .!?
     * Metoden er rekursiv
     *
     * @param toOrdMap
     * @param antOrd gis fra bruker
     * @param toOrd
     * @param generertTekst teksten som bygges og returneres
     * @param storForbokstav må være false ved første innsending, ellers blir det rot
     * @return returnerer det tredje ordet
     */
    private String genererTekst(HashMap<ToOrd, SisteOrd> toOrdMap, int antOrd, ToOrd toOrd, String generertTekst, boolean storForbokstav) {
        if (antOrd == 0) {
            return generertTekst;
        }
        SisteOrd sisteOrd = toOrdMap.get(toOrd);

        if (sisteOrd != null) {
            LinkedList<String> ordListe = sisteOrd.getOrdListe();
            LinkedList<Integer> tallListe = sisteOrd.getTallListe();
            // Denne setningen har blitt generert i samarbeid med ChatGPT.
            // Total sum av forekomster i tallListe
            int total = tallListe.stream().mapToInt(Integer::intValue).sum();
            // Velger et tilfeldig tall fra summen av total
            int randomTell = new Random().nextInt(total);
            int kummulativTeller = 0;
            String nesteOrd = null;

            for (int i = 0; i < ordListe.size(); i++) {
                kummulativTeller += tallListe.get(i);
                if (kummulativTeller >= randomTell) {
                    nesteOrd = ordListe.get(i);
                    break;
                }
            }
            if (nesteOrd != null) {
                if (storForbokstav) {
                    nesteOrd = storForbokstav(nesteOrd);
                    storForbokstav = false;
                }
                if (erSpesialTegn(nesteOrd)) {
                    generertTekst += nesteOrd;
                    storForbokstav = (nesteOrd.equals(".")) || (nesteOrd.equals("?")) || (nesteOrd.equals("!"));
                } else {
                    generertTekst += " " + nesteOrd;
                }
                ToOrd nextToOrd = new ToOrd(toOrd.getOrd2(), nesteOrd);
                return genererTekst(toOrdMap, antOrd - 1, nextToOrd, generertTekst, storForbokstav);
            }
        }
        return generertTekst;
    }

    /**
     * Avgjør om et ord inneholder spesialtegn
     * @param word ordet som skal sjekkes
     * @return boolean
     */
    private boolean erSpesialTegn(String word) {
        return ".,;?:;".contains(word);
    }

    /**
     * Gir et innsendt ord stor forbokstav
     * @param word ordet som skal gjøres om
     * @return String, omgjort ord
     */
    private String storForbokstav(String word) {
        if (word.length() > 0) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
        return word;
    }

    /**
     * Henter et tilfeldig ToOrd-objekt
     * @return aktuelt objekt
     */
    private ToOrd getRandomToOrd() {
        if (toOrdSamling.isEmpty()) {
            return null;
        }
        ArrayList<ToOrd> toOrdListe = new ArrayList<>(toOrdSamling.keySet());
        Random random = new Random();
        int randomIndeks = random.nextInt(toOrdListe.size());

        return toOrdListe.get(randomIndeks);
    }
    public static void main(String[] args) {
        launch();
    }
}

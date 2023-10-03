module oblig2.tekstgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens oblig2.tekstgenerator to javafx.fxml;
    exports oblig2.tekstgenerator;
}
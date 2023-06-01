package at.dimaweb.control;

import at.dimaweb.entity.DbDomainverwaltung;
import at.dimaweb.entity.DbExchange;
import at.dimaweb.entity.DbGeraete;
import at.dimaweb.entity.DbHardware;
import at.dimaweb.entity.DbInternetanbindung;
import at.dimaweb.entity.DbKunde;
import at.dimaweb.entity.DbWebmail;
import at.dimaweb.entity.DbWebspace;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* Zum Ergänzen weiterer Datenblätter:
    1.	Neue Klasse DbInternetanbindung erstellen
        a.	die entspreechenden Variablen erstellen
        b.	die Tabellendefinion erstellen
        c.	alle auzugebenden Fewhlermeldungen ausbessern
        d.	2x Preparedstatements anpassen (Anzahl = Anzahl der Felder im Formular +1) 
            ACHTUNG: beim 2. prepStmt ist ein Eintrag mehr mit String.valueOf(this.getId()));
    2.	App.java – die Datenbank erstellen
    3.	Var.java den Array alleDatenblätter erweitern
    4.	GuiIa.java neue Klasse anlegen
    5.	Hauptschirm.java ind der switch Anweisung den Eintrag ergänzen
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(Var.hs.erstellen());
        stage.setScene(scene);
        stage.setMaximized(true);

        // alle Tabellen für die Datenbank erstellen
        Var.dbba.erstelleTabelle("Kunde", new DbKunde().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Internetanbindung", new DbInternetanbindung().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Domainverwaltung", new DbDomainverwaltung().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Webspace", new DbWebspace().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Exchange", new DbExchange().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Webmail", new DbWebmail().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Hardware", new DbHardware().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Geraete", new DbGeraete().getTabellenDefinition());
        new DbGeraete().geInitialisieren();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
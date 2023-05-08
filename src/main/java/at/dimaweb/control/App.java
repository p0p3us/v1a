package at.dimaweb.control;

import at.dimaweb.entity.DbInternetanbindung;
import at.dimaweb.entity.DbKunde;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(Var.hs.erstellen());
        stage.setScene(scene);
        stage.setMaximized(true);

        // alle Tabellen für die Datenbank erstellen
        Var.dbba.erstelleTabelle("Kunde", new DbKunde().getTabellenDefinition());
        Var.dbba.erstelleTabelle("Internetanbindung", new DbInternetanbindung().getTabellenDefinition());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
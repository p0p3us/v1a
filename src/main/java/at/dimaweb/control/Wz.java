package at.dimaweb.control;

import java.util.Optional;

import at.dimaweb.boundary.DlgJaNein;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Wz {
    
    public static void fehler (String meldung) {
        new Alert(AlertType.ERROR, meldung).showAndWait();
    }

    public static boolean sicherheitsabfrage (String meldung) {
        DlgJaNein dlgJaNein = new DlgJaNein(meldung);
        Optional<Boolean> optAntwort = dlgJaNein.showAndWait();
        if (optAntwort.get()) return true;
        return false;
    }

    public static void info (String meldung) {
        new Alert(AlertType.INFORMATION, meldung).showAndWait();
    }
}

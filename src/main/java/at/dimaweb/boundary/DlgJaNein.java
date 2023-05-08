package at.dimaweb.boundary;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.util.Callback;

public class DlgJaNein extends Dialog<Boolean> {
    
    public DlgJaNein (String frage) {
        this.setTitle("Sind Sie sich sicher");
        this.getDialogPane().setHeader(new Label ("Sicherheitsabfrage"));
        this.getDialogPane().setContent(new Label (frage));
        ButtonType btnJa = new ButtonType("Ja", ButtonData.OK_DONE);
        ButtonType btnNein = new ButtonType("Nein", ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(btnNein, btnJa);

        this.setResultConverter(new Callback<ButtonType,Boolean>() {
            @Override 
            public Boolean call (ButtonType b) {
                if (b == btnJa) {
                    return true;
                }
                return false;
            }
        });
    }
}

package at.dimaweb.boundary;

import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbArt;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.util.Callback;

public class DlgNeueArt extends Dialog<String> {
    
    public DlgNeueArt () {
        this.setTitle("Art hinzufügen");
        this.getDialogPane().setHeader(new Label ("Name des neuen Programms:"));
        TextField txtArt = new TextField();
        txtArt.requestFocus();
        this.getDialogPane().setContent(txtArt);
        ButtonType btnJa = new ButtonType("Speichern", ButtonData.OK_DONE);
        ButtonType btnNein = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(btnNein, btnJa);

        Button ok = (Button)this.getDialogPane().lookupButton(btnJa);
        ok.addEventFilter(ActionEvent.ACTION, e -> {
            if(txtArt.getText().length() == 0) {
                Wz.fehler("Bitte einen Namen angeben oder Abbrechen");
                e.consume();
            }
            DbArt[] alleDbar = new DbArt().alleArAuslesen();
            for (int i = 0; i < alleDbar.length; i++) {
                if (txtArt.getText().equals(alleDbar[i].getArt())) {
                    Wz.fehler("ein Programm mit dem Namen " + alleDbar[i].getArt() + " exisitiert bereits\nbitte wählen Sie einen anderen Namen");
                    e.consume();
                }
            }
            });

        this.setResultConverter(new Callback<ButtonType, String>() {
            @Override 
            public String call (ButtonType b) {
                if (b == btnJa) {
                    return txtArt.getText();
                }
                return "FEHLER";
            }
        });
    }
}

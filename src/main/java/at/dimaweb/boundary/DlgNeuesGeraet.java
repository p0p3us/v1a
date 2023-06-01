package at.dimaweb.boundary;

import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbGeraete;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.util.Callback;

public class DlgNeuesGeraet extends Dialog<String> {
    
    public DlgNeuesGeraet () {
        this.setTitle("Gerät hinzufügen");
        this.getDialogPane().setHeader(new Label ("Name des neuen Geräts:"));
        TextField txtGeraet = new TextField();
        txtGeraet.requestFocus();
        this.getDialogPane().setContent(txtGeraet);
        ButtonType btnJa = new ButtonType("Speichern", ButtonData.OK_DONE);
        ButtonType btnNein = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(btnNein, btnJa);

        Button ok = (Button)this.getDialogPane().lookupButton(btnJa);
        ok.addEventFilter(ActionEvent.ACTION, e -> {
            if(txtGeraet.getText().length() == 0) {
                Wz.fehler("Bitte einen Namen angeben oder Abbrechen");
                e.consume();
            }
            DbGeraete[] alleDbge = new DbGeraete().alleGeAuslesen();
            for (int i = 0; i < alleDbge.length; i++) {
                if (txtGeraet.getText().equals(alleDbge[i].getGeraet())) {
                    Wz.fehler("ein Gerät mit dem Namen " + alleDbge[i].getGeraet() + " exisitiert bereits\nbitte wählen Sie einen anderen Namen");
                    e.consume();
                }
            }
            });

        this.setResultConverter(new Callback<ButtonType, String>() {
            @Override 
            public String call (ButtonType b) {
                if (b == btnJa) {
                    return txtGeraet.getText();
                }
                return "FEHLER";
            }
        });
    }
}

package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbKunde;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class DlgNeuerKunde extends Dialog<DbKunde> {

    private Label lblHeader = new Label("Kundenanlage");
    private Label lblName = new Label("Name des Kunden: ");
    private TextField txtName = new TextField();
    private ButtonType btnSpeichern = new ButtonType("Speichern", ButtonData.OK_DONE);
    private ButtonType btnAbbrechen = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
    
    public DlgNeuerKunde () {
        this.setTitle("Neuer Kunde");
        this.getDialogPane().setHeader(this.erstelleHeader());
        this.getDialogPane().setContent(this.erstelleContent());
        this.getDialogPane().getButtonTypes().addAll(btnAbbrechen, btnSpeichern);
    
        Button btnOk = (Button)this.getDialogPane().lookupButton(btnSpeichern);
        btnOk.addEventFilter(ActionEvent.ACTION, e -> {
            if ((txtName.getText().equals("")) || (txtName == null)) {
                Wz.fehler("Bitte geben Sie einen Namen ein oder klicken Sie auf abbrechen.");
                e.consume();
            }
            DbKunde[] alleKunden = new DbKunde().kundeAusTabelle();
            for (int i = 0; i < alleKunden.length; i++) {
                if (txtName.getText().equals(alleKunden[i].getName())) {
                    Wz.fehler("Dieser Kundenname ist bereits in der Datenbank gespeichert. Bitte wählen Sie einen anderen.");
                    e.consume();
                    break;
                }
            }
        });

        this.setResultConverter(new Callback<ButtonType,DbKunde>() {
            @Override 
            public DbKunde call (ButtonType b) {
                if (b == btnSpeichern) {
                    DbKunde tempKunde = new DbKunde();
                    tempKunde.setName(txtName.getText());
                    return tempKunde;
                }
                return null;
            }
        });
    }

    private GridPane erstelleHeader () {
        GridPane gp = new GridPane();
        gp.add(lblHeader, 0, 0);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        return gp;
    }

    private GridPane erstelleContent () {
        lblName.setPadding(Var.lblPadding);
        txtName.setPadding(Var.lblPadding);
        GridPane gp = new GridPane();
        gp.add(lblName, 0, 0);
        gp.add(txtName, 1, 0);
        return gp;
    }
}

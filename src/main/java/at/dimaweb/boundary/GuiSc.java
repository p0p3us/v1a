package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbServerclient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GuiSc {

    private TitledPane erstelleTpSc (int nummer, DbServerclient dbsc) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));
        Label lblUser = new Label("Username");
        Label lblPw = new Label("Passwort");
        Label lblSonstiges = new Label("Sonstiges");

        TextField txtUser = new TextField();
        if (!(dbsc.getUser() == null)) {
            txtUser.setText(dbsc.getUser());
        }
        TextField txtPw = new TextField();
        if (!(dbsc.getPw() == null)) {
            txtPw.setText(dbsc.getPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbsc.getSonstiges() == null)) {
            txaSonstiges.setText(dbsc.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblUser, 0, 1);
        gp.add(lblPw, 0, 2);
        gp.add(lblSonstiges, 0, 3);
        
        gp.add(txtUser, 1, 1);
        gp.add(txtPw, 1, 2);
        gp.add(txaSonstiges, 1, 3);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiSc());
        });

        Button btnSpeichern = new Button("Speichern");
        btnSpeichern.setOnAction(e -> {
            int accId = -1;
            String accTitel = tp.getText();
            if (accTitel.contains("id:")) {
                int anfang = accTitel.indexOf("id:") + 3;
                int ende = accTitel.length() - 1;
                accId = Integer.valueOf(accTitel.substring(anfang, ende));
            } 
            DbServerclient tempDbsc = new DbServerclient();
            tempDbsc.setId(Integer.valueOf(accId));
            tempDbsc.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbsc.setUser(txtUser.getText());
            tempDbsc.setPw(txtPw.getText());
            tempDbsc.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",        tp.getText(),                   "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",      String.valueOf(Var.kd.getId()), "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"user",      txtUser.getText(),              "STRING",   "nein",     "nein",       null,               null},
                {"pw",        txtPw.getText(),                "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges", txaSonstiges.getText(),         "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbsc.setTabellenDefinition(tempDef);
            tempDbsc.scSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiSc().erstelleGuiSc());

        });
        Button btnLoeschen = new Button("Löschen");
        btnLoeschen.setOnAction(e -> {
            int accId = -1;
            String accTitel = tp.getText();
            if (accTitel.contains("id:")) {
                int anfang = accTitel.indexOf("id:") + 3;
                int ende = accTitel.length() - 1;
                accId = Integer.valueOf(accTitel.substring(anfang, ende));
            }
            new DbServerclient().scLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiSc());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiSc () {
        Accordion acc = new Accordion();
        DbServerclient[] alleDbsc =  new DbServerclient().alleScAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbsc.length; i++) {
            TitledPane tp  = erstelleTpSc(alleDbsc[i].getId(), alleDbsc[i]);
            tp.setText("Serverclient " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbsc[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpSc(-1, new DbServerclient());
        tp.setText("neuen Serverclient anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

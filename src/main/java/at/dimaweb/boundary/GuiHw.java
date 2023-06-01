package at.dimaweb.boundary;

import java.util.Optional;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbGeraete;
import at.dimaweb.entity.DbHardware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GuiHw {

    private TitledPane erstelleTpHw (int nummer, DbHardware dbhw) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));

        // Combox
        ComboBox<String> cmbGeraet = new ComboBox<String>();
        DbGeraete dbge = new DbGeraete();
        DbGeraete[] alleDbge = dbge.alleGeAuslesen();
        ObservableList<String> olGeraete = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbge.length; i++) {
            olGeraete.add(alleDbge[i].getGeraet());
        }
        olGeraete.add("neues Gerät hinzufügen");
        cmbGeraet.setItems(olGeraete);

        if (!(dbhw.getGeraet() == null)) {
            cmbGeraet.getSelectionModel().select(dbhw.getGeraet());
        } else {
            cmbGeraet.getSelectionModel().select("neues Gerät hinzufügen");
            cmbGeraet.getSelectionModel().select("neues Gerät");
        }
        cmbGeraet.setOnAction(e -> {
            if (cmbGeraet.getSelectionModel().getSelectedItem().toString().equals("neues Gerät hinzufügen")) {
                DlgNeuesGeraet dlgNeuesGeraet = new DlgNeuesGeraet();
                Optional<String> optGeraet = dlgNeuesGeraet.showAndWait();
                String geraetNeu = optGeraet.get();
                if (!(geraetNeu.equals("FEHLER"))) {
                    DbGeraete dbgeNeu = new DbGeraete();
                    String[][] tabellenDefinition = {
                            {"id",     "-1",         "INTEGER",  "ja",       "ja",         null,               null},
                            {"geraet", geraetNeu,           "STRING",   "nein",     "nein",       null,               null}
                        };
                    dbgeNeu.setId(-1);
                    dbgeNeu.setTabellenDefinition(tabellenDefinition);
                    dbgeNeu.geSpeichern();
                    olGeraete.add(geraetNeu);
                    cmbGeraet.setItems(olGeraete);
                }
                e.consume();
            }
        });            
        

        //weitere Felder
        Label lblGeraet = new Label("Gerät");
        Label lblVerwaltung = new Label("Verwaltung");
        Label lblType = new Label("Type");
        Label lblHersteller = new Label("Hersteller/Type");
        Label lblIp = new Label("IP-Adresse");
        Label lblHost = new Label("Hostname");
        Label lblUser = new Label("Username");
        Label lblPw = new Label("Passwort");
        Label lblSonstiges = new Label("Sonstiges");
        
        TextField txtVerwaltung = new TextField();
        if (!(dbhw.getVerwaltung() == null)) {
            txtVerwaltung.setText(dbhw.getVerwaltung());
        }
        TextField txtType = new TextField();
        if (!(dbhw.getType() == null)) {
            txtType.setText(dbhw.getType());
        }
        TextField txtHersteller = new TextField();
        if (!(dbhw.getHersteller() == null)) {
            txtHersteller.setText(dbhw.getHersteller());
        }
        TextField txtIp = new TextField();
        if (!(dbhw.getIp() == null)) {
            txtIp.setText(dbhw.getIp());
        }
        TextField txtHost = new TextField();
        if (!(dbhw.getHost() == null)) {
            txtHost.setText(dbhw.getHost());
        }
        TextField txtUser = new TextField();
        if (!(dbhw.getUser() == null)) {
            txtUser.setText(dbhw.getUser());
        }
        TextField txtPw = new TextField();
        if (!(dbhw.getPw() == null)) {
            txtPw.setText(dbhw.getPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbhw.getSonstiges() == null)) {
            txaSonstiges.setText(dbhw.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblGeraet, 0, 1);
        gp.add(lblVerwaltung, 0, 2);
        gp.add(lblType, 0, 3);
        gp.add(lblHersteller, 0, 4);
        gp.add(lblIp, 0, 5);
        gp.add(lblHost, 0, 6);
        gp.add(lblUser, 0, 7);
        gp.add(lblPw, 0, 8);
        gp.add(lblSonstiges, 0, 9);
        
        gp.add(cmbGeraet, 1, 1);
        gp.add(txtVerwaltung, 1, 2);
        gp.add(txtType, 1, 3);
        gp.add(txtHersteller, 1, 4);
        gp.add(txtIp, 1, 5);
        gp.add(txtHost, 1, 6);
        gp.add(txtUser, 1, 7);
        gp.add(txtPw, 1, 8);
        gp.add(txaSonstiges, 1, 9);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiHw());
        });
        Button btnSpeichern = new Button("Speichern");
        btnSpeichern.setOnAction(e -> {
            String accTitel = tp.getText();
            int accId = 0;
            if (accTitel.contains("id:")) {
                int anfang = accTitel.indexOf("id:") + 3;
                int ende = accTitel.length() - 1;
                accId = Integer.valueOf(accTitel.substring(anfang, ende));
            } else {
                accId = -1;
            }
            DbHardware tempDbhw = new DbHardware();
            tempDbhw.setId(Integer.valueOf(accId));
            tempDbhw.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbhw.setVerwaltung(txtVerwaltung.getText());
            tempDbhw.setType(txtType.getText());
            tempDbhw.setHersteller(txtHersteller.getText());
            tempDbhw.setIp(txtIp.getText());
            tempDbhw.setHost(txtHost.getText());
            tempDbhw.setUser(txtUser.getText());
            tempDbhw.setPw(txtPw.getText());
            tempDbhw.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",         tp.getText(),                  "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",       String.valueOf(Var.kd.getId()),"INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"geraet",     cmbGeraet.getSelectionModel().getSelectedItem().toString(),       "STRING",   "nein",     "nein",       null,               null},
                {"verwaltung", txtVerwaltung.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"type",       txtType.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"hersteller", txtHersteller.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"ip",         txtIp.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"host",       txtHost.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"user",       txtUser.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"pw",         txtPw.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges",  txaSonstiges.getText(),        "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbhw.setTabellenDefinition(tempDef);
            tempDbhw.hwSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiHw().erstelleGuiHw());

        });
        Button btnLoeschen = new Button("Löschen");
        btnLoeschen.setOnAction(e -> {
            String accTitel = tp.getText();
            int accId = 0;
            if (accTitel.contains("id:")) {
                int anfang = accTitel.indexOf("id:") + 3;
                int ende = accTitel.length() - 1;
                accId = Integer.valueOf(accTitel.substring(anfang, ende));
            } else {
                accId = -1;
            }
            
            new DbHardware().hwLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiHw());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiHw () {
        Accordion acc = new Accordion();
        DbHardware[] alleDbhw =  new DbHardware().alleHwAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbhw.length; i++) {
            TitledPane tp  = erstelleTpHw(alleDbhw[i].getId(), alleDbhw[i]);
            tp.setText("Hardware-Verwaltung " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbhw[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpHw(-1, new DbHardware());
        tp.setText("neue Hardware - Verwaltung anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

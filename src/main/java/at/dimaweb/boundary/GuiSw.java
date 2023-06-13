package at.dimaweb.boundary;

import java.util.Optional;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbArt;
import at.dimaweb.entity.DbSoftware;
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

public class GuiSw {

    private TitledPane erstelleTpSw (int nummer, DbSoftware dbsw) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));

        // Combox
        ComboBox<String> cmbArt = new ComboBox<String>();
        DbArt dbAr = new DbArt();
        DbArt[] alleDbArtbar = dbAr.alleArAuslesen();
        ObservableList<String> olArt = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbArtbar.length; i++) {
            olArt.add(alleDbArtbar[i].getArt());
        }
        olArt.add("neues Programm hinzufügen");
        cmbArt.setItems(olArt);

        if (!(dbAr.getArt() == null)) {
            cmbArt.getSelectionModel().select(dbAr.getArt());
        } else {
            cmbArt.getSelectionModel().select("neues Programm hinzufügen");
            cmbArt.getSelectionModel().select("neues Programm");
        }
        cmbArt.setOnAction(e -> {
            if (cmbArt.getSelectionModel().getSelectedItem().toString().equals("neues Programm hinzufügen")) {
                DlgNeueArt dlgNeueArt  = new DlgNeueArt();
                Optional<String> optArt = dlgNeueArt.showAndWait();
                String artNeu = optArt.get();
                if (!(artNeu.equals("FEHLER"))) {
                    DbArt dbArtbarNeu = new DbArt();
                    String[][] tabellenDefinition = {
                            {"id",  "-1",   "INTEGER",  "ja",       "ja",         null,               null},
                            {"art", artNeu, "STRING",   "nein",     "nein",       null,               null}
                        };
                    dbArtbarNeu.setId(-1);
                    dbArtbarNeu.setTabellenDefinition(tabellenDefinition);
                    dbArtbarNeu.geSpeichern();
                    olArt.add(artNeu);
                    e.consume();
                    cmbArt.setItems(olArt);
                }
            }
        });            
        

        //weitere Felder
        Label lblArt = new Label("Art");
        Label lblStandort = new Label("Standort");
        Label lblSoftware = new Label("Software");
        Label lblInterface = new Label("Interface (Web/App)");
        Label lblMail = new Label("Mailadresse");
        Label lblUser = new Label("Username");
        Label lblPw = new Label("Passwort");
        Label lblSonstiges = new Label("Sonstiges");
        
        TextField txtArt = new TextField();
        if (!(dbsw.getArt() == null)) {
            txtArt.setText(dbsw.getArt());
        }
        TextField txtStandort = new TextField();
        if (!(dbsw.getStandort() == null)) {
            txtStandort.setText(dbsw.getStandort());
        }
        TextField txtSoftware = new TextField();
        if (!(dbsw.getSoftware() == null)) {
            txtSoftware.setText(dbsw.getSoftware());
        }
        TextField txtInter = new TextField();
        if (!(dbsw.getInterf() == null)) {
            txtInter.setText(dbsw.getInterf());
        }
        TextField txtMail = new TextField();
        if (!(dbsw.getMail() == null)) {
            txtMail.setText(dbsw.getMail());
        }
        TextField txtUser = new TextField();
        if (!(dbsw.getUser() == null)) {
            txtUser.setText(dbsw.getUser());
        }
        TextField txtPw = new TextField();
        if (!(dbsw.getPw() == null)) {
            txtPw.setText(dbsw.getPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbsw.getSonstiges() == null)) {
            txaSonstiges.setText(dbsw.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblArt, 0, 1);
        gp.add(lblStandort, 0, 2);
        gp.add(lblSoftware, 0, 3);
        gp.add(lblInterface, 0, 4);
        gp.add(lblMail, 0, 5);
        gp.add(lblUser, 0, 7);
        gp.add(lblPw, 0, 8);
        gp.add(lblSonstiges, 0, 9);
        
        gp.add(cmbArt, 1, 1);
        gp.add(txtStandort, 1, 2);
        gp.add(txtSoftware, 1, 3);
        gp.add(txtInter, 1, 4);
        gp.add(txtMail, 1, 5);
        gp.add(txtUser, 1, 7);
        gp.add(txtPw, 1, 8);
        gp.add(txaSonstiges, 1, 9);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiSw());
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
            DbSoftware tempDbSw = new DbSoftware();
            tempDbSw.setId(Integer.valueOf(accId));
            tempDbSw.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbSw.setArt(cmbArt.getValue());
            tempDbSw.setStandort(txtStandort.getText());
            tempDbSw.setInterf(txtInter.getText());
            tempDbSw.setMail(txtMail.getText());
            tempDbSw.setUser(txtUser.getText());
            tempDbSw.setPw(txtPw.getText());
            tempDbSw.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",        tp.getText(),                  "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",      String.valueOf(Var.kd.getId()),"INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"art",       cmbArt.getSelectionModel().getSelectedItem().toString(),       "STRING",   "nein",     "nein",       null,               null},
                {"standort",  txtStandort.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"software",  txtSoftware.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"interf",    txtInter.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"mail",      txtMail.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"user",      txtUser.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"pw",        txtPw.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges", txaSonstiges.getText(),        "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbSw.setTabellenDefinition(tempDef);
            tempDbSw.swSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiSw().erstelleGuiSw());

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
            
            new DbSoftware().swLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiSw());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiSw () {
        Accordion acc = new Accordion();
        DbSoftware[] alledbSw =  new DbSoftware().alleSwAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alledbSw.length; i++) {
            TitledPane tp  = erstelleTpSw(alledbSw[i].getId(), alledbSw[i]);
            tp.setText("Sopftware-Verwaltung " + Var.kd.getName() + " " + (i+1) + " (id:" + alledbSw[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpSw(-1, new DbSoftware());
        tp.setText("neue Software - Verwaltung anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

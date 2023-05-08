package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbExchange;
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

public class GuiEx {

    private TitledPane erstelleTpEx (int nummer, DbExchange dbex) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));
        Label lblArt = new Label("Art (Protokoll)");
        Label lblEingang = new Label("Eingangsserver");
        Label lblAusgang = new Label("Ausgangsserver");
        Label lblAdminInterface = new Label("Admin-Interface (Link)");
        Label lblAdminUser = new Label("Admin-User");
        Label lblAdminPw = new Label("Admin-Passwort");
        Label lblMail = new Label("Mailadresse");
        Label lblMailUser = new Label("Mailadresse-User");
        Label lblMailPw = new Label("Mailadresse-Passwort");
        Label lblSonstiges = new Label("Sonstiges");

        TextField txtArt = new TextField();
        if (!(dbex.getArt() == null)) {
            txtArt.setText(dbex.getArt());
        }
        TextField txtEingang = new TextField();
        if (!(dbex.getEingang() == null)) {
            txtEingang.setText(dbex.getEingang());
        }
        TextField txtAusgang = new TextField();
        if (!(dbex.getAusgang() == null)) {
            txtAusgang.setText(dbex.getAusgang());
        }
        TextField txtAdminInterface = new TextField();
        if (!(dbex.getAdminInterface() == null)) {
            txtAdminInterface.setText(dbex.getAdminInterface());
        }
        TextField txtAdminUser = new TextField();
        if (!(dbex.getAdminUser() == null)) {
            txtAdminUser.setText(dbex.getAdminUser());
        }
        TextField txtAdminPw = new TextField();
        if (!(dbex.getAdminPw() == null)) {
            txtAdminPw.setText(dbex.getAdminPw());
        }
        TextField txtMail = new TextField();
        if (!(dbex.getMail() == null)) {
            txtMail.setText(dbex.getMail());
        }
        TextField txtMailUser = new TextField();
        if (!(dbex.getMailUser() == null)) {
            txtMailUser.setText(dbex.getMailUser());
        }
        TextField txtMailPw = new TextField();
        if (!(dbex.getMailPw() == null)) {
            txtMailPw.setText(dbex.getMailPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbex.getSonstiges() == null)) {
            txaSonstiges.setText(dbex.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblArt, 0, 1);
        gp.add(lblEingang, 0, 2);
        gp.add(lblAusgang, 0, 3);
        gp.add(lblAdminInterface, 0, 4);
        gp.add(lblAdminUser, 0, 5);
        gp.add(lblAdminPw, 0, 6);
        gp.add(lblMail, 0, 7);
        gp.add(lblMailUser, 0, 8);
        gp.add(lblMailPw, 0, 9);
        gp.add(lblSonstiges, 0, 10);
        
        gp.add(txtArt, 1, 1);
        gp.add(txtEingang, 1, 2);
        gp.add(txtAusgang, 1, 3);
        gp.add(txtAdminInterface, 1, 4);
        gp.add(txtAdminUser, 1, 5);
        gp.add(txtAdminPw, 1, 6);
        gp.add(txtMail, 1, 7);
        gp.add(txtMailUser, 1, 8);
        gp.add(txtMailPw, 1, 9);
        gp.add(txaSonstiges, 1, 10);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiEx());
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
            DbExchange tempDbex = new DbExchange();
            tempDbex.setId(Integer.valueOf(accId));
            tempDbex.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbex.setArt(txtArt.getText());
            tempDbex.setEingang(txtEingang.getText());
            tempDbex.setAusgang(txtAusgang.getText());
            tempDbex.setAdminInterface(txtAdminInterface.getText());
            tempDbex.setAdminUser(txtAdminUser.getText());
            tempDbex.setAdminPw(txtAdminPw.getText());
            tempDbex.setMail(txtMail.getText());
            tempDbex.setMailUser(txtMailUser.getText());
            tempDbex.setMailPw(txtMailPw.getText());
            tempDbex.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",             tp.getText(),                   "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",           String.valueOf(Var.kd.getId()), "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"art",            txtArt.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"eingang",        txtEingang.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"ausgang",        txtAusgang.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"adminInterface", txtAdminInterface.getText(),    "STRING",   "nein",     "nein",       null,               null},
                {"adminUser",      txtAdminUser.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"adminPw",        txtAdminPw.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"mail",           txtMail.getText(),              "STRING",   "nein",     "nein",       null,               null},
                {"mailUser",       txtMailUser.getText(),          "STRING",   "nein",     "nein",       null,               null},
                {"mailPw",         txtMailPw.getText(),            "INTEGER",  "nein",     "nein",       null,               null},
                {"sonstiges",      txaSonstiges.getText(),         "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbex.setTabellenDefinition(tempDef);
            tempDbex.exSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiEx().erstelleGuiEx());

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
            
            new DbExchange().exLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiEx());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiEx () {
        Accordion acc = new Accordion();
        DbExchange[] alleDbex =  new DbExchange().alleExAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbex.length; i++) {
            TitledPane tp  = erstelleTpEx(alleDbex[i].getId(), alleDbex[i]);
            tp.setText("E-Mail-Verwaltung (Exchange) " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbex[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpEx(-1, new DbExchange());
        tp.setText("neue E-Mail-Verwaltung (Exchange) anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

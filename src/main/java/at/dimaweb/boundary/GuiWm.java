package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbWebmail;
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

public class GuiWm {

    private TitledPane erstelleTpWm (int nummer, DbWebmail dbwm) {
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
        if (!(dbwm.getArt() == null)) {
            txtArt.setText(dbwm.getArt());
        }
        TextField txtEingang = new TextField();
        if (!(dbwm.getEingang() == null)) {
            txtEingang.setText(dbwm.getEingang());
        }
        TextField txtAusgang = new TextField();
        if (!(dbwm.getAusgang() == null)) {
            txtAusgang.setText(dbwm.getAusgang());
        }
        TextField txtAdminInterface = new TextField();
        if (!(dbwm.getAdminInterface() == null)) {
            txtAdminInterface.setText(dbwm.getAdminInterface());
        }
        TextField txtAdminUser = new TextField();
        if (!(dbwm.getAdminUser() == null)) {
            txtAdminUser.setText(dbwm.getAdminUser());
        }
        TextField txtAdminPw = new TextField();
        if (!(dbwm.getAdminPw() == null)) {
            txtAdminPw.setText(dbwm.getAdminPw());
        }
        TextField txtMail = new TextField();
        if (!(dbwm.getMail() == null)) {
            txtMail.setText(dbwm.getMail());
        }
        TextField txtMailUser = new TextField();
        if (!(dbwm.getMailUser() == null)) {
            txtMailUser.setText(dbwm.getMailUser());
        }
        TextField txtMailPw = new TextField();
        if (!(dbwm.getMailPw() == null)) {
            txtMailPw.setText(dbwm.getMailPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbwm.getSonstiges() == null)) {
            txaSonstiges.setText(dbwm.getSonstiges());
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
            Var.hs.getBp().setCenter(this.erstelleGuiWm());
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
            DbWebmail tempDbwm = new DbWebmail();
            tempDbwm.setId(Integer.valueOf(accId));
            tempDbwm.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbwm.setArt(txtArt.getText());
            tempDbwm.setEingang(txtEingang.getText());
            tempDbwm.setAusgang(txtAusgang.getText());
            tempDbwm.setAdminInterface(txtAdminInterface.getText());
            tempDbwm.setAdminUser(txtAdminUser.getText());
            tempDbwm.setAdminPw(txtAdminPw.getText());
            tempDbwm.setMail(txtMail.getText());
            tempDbwm.setMailUser(txtMailUser.getText());
            tempDbwm.setMailPw(txtMailPw.getText());
            tempDbwm.setSonstiges(txaSonstiges.getText());
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
            tempDbwm.setTabellenDefinition(tempDef);
            tempDbwm.wmSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiWm().erstelleGuiWm());

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
            
            new DbWebmail().wmLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiWm());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiWm () {
        Accordion acc = new Accordion();
        DbWebmail[] alleDbwm =  new DbWebmail().alleWmAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbwm.length; i++) {
            TitledPane tp  = erstelleTpWm(alleDbwm[i].getId(), alleDbwm[i]);
            tp.setText("E-Mail-Verwaltung (Webmail) " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbwm[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpWm(-1, new DbWebmail());
        tp.setText("neue E-Mail-Verwaltung (Webmail) anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbDomainverwaltung;
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

public class GuiDv {

    private TitledPane erstelleTpDv (int nummer, DbDomainverwaltung dbdv) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));
        Label lblDomainName = new Label("Domainname");
        Label lblRegistrar = new Label("Registrar / DNS");
        Label lblIp = new Label("IP-Adresse");
        Label lblWebportal = new Label("Webportal (Link)");
        Label lblName = new Label("Login (Username)");
        Label lblPw = new Label("Login (Passwort)");
        Label lblSonstiges = new Label("Sonstiges");

        TextField txtDomainName = new TextField();
        if (!(dbdv.getDomainName() == null)) {
            txtDomainName.setText(dbdv.getDomainName());
        }
        TextField txtRegistrar = new TextField();
        if (!(dbdv.getRegistrar() == null)) {
            txtRegistrar.setText(dbdv.getRegistrar());
        }
        TextField txtIp = new TextField();
        if (!(dbdv.getIp() == null)) {
            txtIp.setText(dbdv.getIp());
        }
        TextField txtWebportal = new TextField();
        if (!(dbdv.getWebportal() == null)) {
            txtWebportal.setText(dbdv.getWebportal());
        }
        TextField txtName = new TextField();
        if (!(dbdv.getName() == null)) {
            txtName.setText(dbdv.getName());
        }
        TextField txtPw = new TextField();
        if (!(dbdv.getPw() == null)) {
            txtPw.setText(dbdv.getPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbdv.getSonstiges() == null)) {
            txaSonstiges.setText(dbdv.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblDomainName, 0, 1);
        gp.add(lblRegistrar, 0, 2);
        gp.add(lblIp, 0, 3);
        gp.add(lblWebportal, 0, 4);
        gp.add(lblName, 0, 5);
        gp.add(lblPw, 0, 6);
        gp.add(lblSonstiges, 0, 7);
        
        gp.add(txtDomainName, 1, 1);
        gp.add(txtRegistrar, 1, 2);
        gp.add(txtIp, 1, 3);
        gp.add(txtWebportal, 1, 4);
        gp.add(txtName, 1, 5);
        gp.add(txtPw, 1, 6);
        gp.add(txaSonstiges, 1, 7);

        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiDv());
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
            DbDomainverwaltung tempDbdv = new DbDomainverwaltung();
            tempDbdv.setId(Integer.valueOf(accId));
            tempDbdv.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbdv.setDomainName(txtDomainName.getText());
            tempDbdv.setRegistrar(txtRegistrar.getText());
            tempDbdv.setIp(txtIp.getText());
            tempDbdv.setWebportal(txtWebportal.getText());
            tempDbdv.setName(txtName.getText());
            tempDbdv.setPw(txtPw.getText());
            tempDbdv.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",         String.valueOf(accId),          "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",       String.valueOf(Var.kd.getId()), "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"domainName", txtDomainName.getText(),        "STRING",   "nein",     "nein",       null,               null},
                {"registrar",  txtRegistrar.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"ip",         txtIp.getText(),                "STRING",   "nein",     "nein",       null,               null},
                {"webportal",  txtWebportal.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"name",       txtName.getText(),              "STRING",   "nein",     "nein",       null,               null},
                {"pw",         txtPw.getText(),                "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges",  txaSonstiges.getText(),         "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbdv.setTabellenDefinition(tempDef);
            tempDbdv.dvSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiDv().erstelleGuiDv());

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
            
            new DbDomainverwaltung().dvLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiDv());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiDv () {
        Accordion acc = new Accordion();
        DbDomainverwaltung[] alleDbdv =  new DbDomainverwaltung().alleDvAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbdv.length; i++) {
            TitledPane tp  = erstelleTpDv(alleDbdv[i].getId(), alleDbdv[i]);
            tp.setText("Domainverwaltung " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbdv[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpDv(-1, new DbDomainverwaltung());
        tp.setText("neue Domainverwaltung anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

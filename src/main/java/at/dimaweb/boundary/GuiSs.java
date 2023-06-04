package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbServersystem;
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

public class GuiSs {

    private TitledPane erstelleTpSs (int nummer, DbServersystem dbss) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));
        Label lblOs = new Label("Betriebssystem");
        Label lblDomain = new Label("Domäne");
        Label lblIpAdresse = new Label("IP Adresse");
        Label lblIpRangeVon = new Label("IP Range von");
        Label lblIpRangeBis = new Label("bis");
        Label lblSubnet = new Label("Subnet Mask");
        Label lblGateway = new Label("Gateway");
        Label lblVerwendung = new Label("Verwendung");
        Label lblAdminUser = new Label("Username Admin");
        Label lblAdminPw = new Label("Passwort Admin");
        Label lblLokalUser = new Label("lokaler Username");
        Label lblLokalPw = new Label("lokales Passwort");
        Label lblSonstiges = new Label("Sonstiges");

        TextField txtOs = new TextField();
        if (!(dbss.getOs() == null)) {
            txtOs.setText(dbss.getOs());
        }
        TextField txtDomain = new TextField();
        if (!(dbss.getDomain() == null)) {
            txtDomain.setText(dbss.getDomain());
        }
        TextField txtIpAdresse = new TextField();
        if (!(dbss.getIpAdresse() == null)) {
            txtIpAdresse.setText(dbss.getIpAdresse());
        }
        TextField txtIpRangeVon = new TextField();
        if (!(dbss.getIpRangeVon() == null)) {
            txtIpRangeVon.setText(dbss.getIpRangeVon());
        }
        TextField txtIpRangeBis = new TextField();
        if (!(dbss.getIpRangeBis() == null)) {
            txtIpRangeBis.setText(dbss.getIpRangeBis());
        }
        TextField txtSubnet = new TextField();
        if (!(dbss.getSubnet() == null)) {
            txtSubnet.setText(dbss.getSubnet());
        }
        TextField txtGateway = new TextField();
        if (!(dbss.getGateway() == null)) {
            txtGateway.setText(dbss.getGateway());
        }
        TextField txtVerwendung = new TextField();
        if (!(dbss.getVerwendung() == null)) {
            txtVerwendung.setText(dbss.getVerwendung());
        }
        TextField txtAdminUser = new TextField();
        if (!(dbss.getAdminUser() == null)) {
            txtAdminUser.setText(dbss.getAdminUser());
        }
        TextField txtAdminPw = new TextField();
        if (!(dbss.getAdminPw() == null)) {
            txtAdminPw.setText(dbss.getAdminPw());
        }
        TextField txtLokalUser = new TextField();
        if (!(dbss.getLokalUser() == null)) {
            txtLokalUser.setText(dbss.getLokalUser());
        }
        TextField txtLokalPw = new TextField();
        if (!(dbss.getLokalPw() == null)) {
            txtLokalPw.setText(dbss.getLokalPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbss.getSonstiges() == null)) {
            txaSonstiges.setText(dbss.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblOs, 0, 1);
        gp.add(lblDomain, 0, 2);
        gp.add(lblIpAdresse, 0, 3);
        gp.add(lblIpRangeVon, 0, 4);
        gp.add(lblIpRangeBis, 0, 5);
        gp.add(lblSubnet, 0, 6);
        gp.add(lblGateway, 0, 7);
        gp.add(lblVerwendung, 0, 8);
        gp.add(lblAdminUser, 0, 9);
        gp.add(lblAdminPw, 0, 10);
        gp.add(lblLokalUser, 0, 11);
        gp.add(lblLokalPw, 0, 12);
        gp.add(lblSonstiges, 0, 13);
        
        gp.add(txtOs, 1, 1);
        gp.add(txtDomain, 1, 2);
        gp.add(txtIpAdresse, 1, 3);
        gp.add(txtIpRangeVon, 1, 4);
        gp.add(txtIpRangeBis, 1, 5);
        gp.add(txtSubnet, 1, 6);
        gp.add(txtGateway, 1, 7);
        gp.add(txtVerwendung, 1, 8);
        gp.add(txtAdminUser, 1, 9);
        gp.add(txtAdminPw, 1, 10);
        gp.add(txtLokalUser, 1, 11);
        gp.add(txtLokalPw, 1, 12);
        gp.add(txaSonstiges, 1, 13);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiSs());
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
            DbServersystem tempDbSs = new DbServersystem();
            tempDbSs.setId(Integer.valueOf(accId));
            tempDbSs.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbSs.setOs(txtOs.getText());
            tempDbSs.setDomain(txtDomain.getText());
            tempDbSs.setIpAdresse(txtIpAdresse.getText());
            tempDbSs.setIpRangeVon(txtIpRangeVon.getText());
            tempDbSs.setIpRangeBis(txtIpRangeBis.getText());
            tempDbSs.setSubnet(txtSubnet.getText());
            tempDbSs.setGateway(txtGateway.getText());
            tempDbSs.setVerwendung(txtVerwendung.getText());
            tempDbSs.setAdminUser(txtAdminUser.getText());
            tempDbSs.setAdminPw(txtAdminPw.getText());
            tempDbSs.setLokalUser(txtLokalUser.getText());
            tempDbSs.setLokalPw(txtLokalPw.getText());
            tempDbSs.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",         tp.getText(),                  "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",       String.valueOf(Var.kd.getId()),"INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"os",         txtOs.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"domain",     txtDomain.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"ipAdresse",  txtIpAdresse.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"ipRangeVon", txtIpRangeVon.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"ipRangeBis", txtIpRangeBis.getText(),     "STRING",   "nein",     "nein",       null,               null},
                {"subnet",     txtSubnet.getText(),   "STRING",   "nein",     "nein",       null,               null},
                {"gateway",    txtGateway.getText(),     "STRING",   "nein",     "nein",       null,               null},
                {"verwendung", txtVerwendung.getText(),     "STRING",   "nein",     "nein",       null,               null},
                {"adminUser",  txtAdminUser.getText(),  "INTEGER",  "nein",     "nein",       null,               null},
                {"adminPw",    txtAdminPw.getText(), "STRING",   "nein",     "nein",       null,               null},
                {"lokalUser",  txtLokalUser.getText(),   "STRING",   "nein",     "nein",       null,               null},
                {"lokalPw",    txtLokalPw.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges",  txaSonstiges.getText(),        "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbSs.setTabellenDefinition(tempDef);
            tempDbSs.ssSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiSs().erstelleGuiSs());

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
            new DbServersystem().ssLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiSs());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiSs () {
        Accordion acc = new Accordion();
        DbServersystem[] alleDbss =  new DbServersystem().alleSsAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbss.length; i++) {
            TitledPane tp  = erstelleTpSs(alleDbss[i].getId(), alleDbss[i]);
            tp.setText("Serversystem " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbss[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpSs(-1, new DbServersystem());
        tp.setText("neues Serversystem anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

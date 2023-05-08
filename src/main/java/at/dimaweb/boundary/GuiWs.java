package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbWebspace;
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

public class GuiWs {

    private TitledPane erstelleTpWs (int nummer, DbWebspace dbws) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));
        Label lblAnbieter = new Label("Anbieter");
        Label lblWebspace = new Label("Webspace (Link)");
        Label lblFtp = new Label("FTP-Server");
        Label lblFtpUser = new Label("Ftp-Server Username:");
        Label lblFtpPw = new Label("FTP-Server Passwort");
        Label lblCms = new Label("CMS-System");
        Label lblCmsUser = new Label("CMS-System Username:");
        Label lblCmsPw = new Label("CMS-System Passwort");
        Label lblSql = new Label("SQL-Datenbank");
        Label lblSqlUser = new Label("SQL-Datenbank Username:");
        Label lblSqlPw = new Label("SQL-Datenbank Passwort");
        Label lblSonstiges = new Label("Sonstiges");

        TextField txtAnbieter = new TextField();
        if (!(dbws.getAnbieter() == null)) {
            txtAnbieter.setText(dbws.getAnbieter());
        }
        TextField txtWebspace = new TextField();
        if (!(dbws.getWebspace() == null)) {
            txtWebspace.setText(dbws.getWebspace());
        }
        TextField txtFtp = new TextField();
        if (!(dbws.getFtp() == null)) {
            txtFtp.setText(dbws.getFtp());
        }
        TextField txtFtpUser = new TextField();
        if (!(dbws.getFtpUser() == null)) {
            txtFtpUser.setText(dbws.getFtpUser());
        }
        TextField txtFtpPw = new TextField();
        if (!(dbws.getFtpPw() == null)) {
            txtFtpPw.setText(dbws.getFtpPw());
        }
        TextField txtCms = new TextField();
        if (!(dbws.getCms() == null)) {
            txtCms.setText(dbws.getCms());
        }
        TextField txtCmsUser = new TextField();
        if (!(dbws.getCmsUser() == null)) {
            txtCmsUser.setText(dbws.getCmsUser());
        }
        TextField txtCmsPw = new TextField();
        if (!(dbws.getCmsPw() == null)) {
            txtCmsPw.setText(dbws.getCmsPw());
        }
        TextField txtSql = new TextField();
        if (!(dbws.getSql() == null)) {
            txtSql.setText(dbws.getSql());
        }
        TextField txtSqlUser = new TextField();
        if (!(dbws.getSqlUser() == null)) {
            txtSqlUser.setText(dbws.getSqlUser());
        }
        TextField txtSqlPw = new TextField();
        if (!(dbws.getSqlPw() == null)) {
            txtSqlPw.setText(dbws.getSqlPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbws.getSonstiges() == null)) {
            txaSonstiges.setText(dbws.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblAnbieter, 0, 1);
        gp.add(lblWebspace, 0, 2);
        gp.add(lblFtp, 0, 3);
        gp.add(lblFtpUser, 0, 4);
        gp.add(lblFtpPw, 0, 5);
        gp.add(lblCms, 0, 6);
        gp.add(lblCmsUser, 0, 7);
        gp.add(lblCmsPw, 0, 8);
        gp.add(lblSql, 0, 9);
        gp.add(lblSqlUser, 0, 10);
        gp.add(lblSqlPw, 0, 11);
        gp.add(lblSonstiges, 0, 12);
        
        gp.add(txtAnbieter, 1, 1);
        gp.add(txtWebspace, 1, 2);
        gp.add(txtFtp, 1, 3);
        gp.add(txtFtpUser, 1, 4);
        gp.add(txtFtpPw, 1, 5);
        gp.add(txtCms, 1, 6);
        gp.add(txtCmsUser, 1, 7);
        gp.add(txtCmsPw, 1, 8);
        gp.add(txtSql, 1, 9);
        gp.add(txtSqlUser, 1, 10);
        gp.add(txtSqlPw, 1, 11);
        gp.add(txaSonstiges, 1, 12);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiWs());
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
            DbWebspace tempDbws = new DbWebspace();
            tempDbws.setId(Integer.valueOf(accId));
            tempDbws.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbws.setAnbieter(txtAnbieter.getText());
            tempDbws.setWebspace(txtWebspace.getText());
            tempDbws.setFtp(txtFtp.getText());
            tempDbws.setFtpUser(txtFtpUser.getText());
            tempDbws.setFtpPw(txtFtpPw.getText());
            tempDbws.setCms(txtCms.getText());
            tempDbws.setCmsUser(txtCmsUser.getText());
            tempDbws.setCmsPw(txtCmsPw.getText());
            tempDbws.setSql(txtSql.getText());
            tempDbws.setSqlUser(txtSqlUser.getText());
            tempDbws.setSqlPw(txtSqlPw.getText());
            tempDbws.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",        tp.getText(),                   "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",      String.valueOf(Var.kd.getId()), "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"anbieter",  txtAnbieter.getText(),          "STRING",   "nein",     "nein",       null,               null},
                {"webspace",  txtWebspace.getText(),          "STRING",   "nein",     "nein",       null,               null},
                {"ftp",       txtFtp.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"ftpUser",   txtFtpUser.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"ftpPw",     txtFtpPw.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"cms",       txtCms.getText(),               "STRING",   "nein",     "nein",       null,               null},
                {"cmsUser",   txtCmsUser.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"cmsPw",     txtCmsPw.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"sql",       txtSql.getText(),               "INTEGER",  "nein",     "nein",       null,               null},
                {"sqlUser",   txtSqlUser.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"sqlPw",     txtSqlPw.getText(),             "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges", txaSonstiges.getText(),         "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbws.setTabellenDefinition(tempDef);
            tempDbws.wsSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiWs().erstelleGuiWs());

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
            
            new DbWebspace().wsLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiWs());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiWs () {
        Accordion acc = new Accordion();
        DbWebspace[] alleDbws =  new DbWebspace().alleWsAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbws.length; i++) {
            TitledPane tp  = erstelleTpWs(alleDbws[i].getId(), alleDbws[i]);
            tp.setText("Webspace / FTP / CMS " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbws[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpWs(-1, new DbWebspace());
        tp.setText("neuen Webspace / FTP / CMS anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

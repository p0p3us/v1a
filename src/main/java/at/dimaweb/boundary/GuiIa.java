package at.dimaweb.boundary;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbInternetanbindung;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class GuiIa {

    private TitledPane erstelleTpIa (int nummer, DbInternetanbindung dbia) {
        TitledPane tp = new TitledPane();
        tp.setText(String.valueOf(nummer));
        Label lblAnbieter = new Label("Anbieter");
        Label lblZugangUser = new Label("Zugang User");
        Label lblZugangPw = new Label("Zugang Passwort");
        Label lblWlanRouter = new Label("WLan Router");
        Label lblWlanRouterIp = new Label("WLan Router IP-Adresse");
        Label lblWlanRouterUser = new Label("WLanRouter User");
        Label lbllblWlanRouterPw = new Label("WLanRouter Passwort");
        Label lblWlanRepeater = new Label("WLan Repeater");
        Label lblWlanRepeaterUser = new Label("WLanRepeater User");
        Label lblWlanRepeaterPw = new Label("WLan Repeater Passwort");
        Label lblWlanRepeaterStk = new Label("WLan Repeater Stück");
        Label lblWlanName = new Label("WLan Name");
        Label lblWlanPw = new Label("WLan Passwort");
        Label lblSonstiges = new Label("Sonstiges");

        TextField txtAnbieter = new TextField();
        if (!(dbia.getAnbieter() == null)) {
            txtAnbieter.setText(dbia.getAnbieter());
        }
        TextField txtZugangUser = new TextField();
        if (!(dbia.getZugangUser() == null)) {
            txtZugangUser.setText(dbia.getZugangUser());
        }
        TextField txtZugangPw = new TextField();
        if (!(dbia.getZugangPw() == null)) {
            txtZugangPw.setText(dbia.getZugangPw());
        }
        TextField txtWlanRouter = new TextField();
        if (!(dbia.getAnbieter() == null)) {
            txtAnbieter.setText(dbia.getAnbieter());
        }
        TextField txtWlanRouterIp = new TextField();
        if (!(dbia.getRouterIp() == null)) {
            txtWlanRouterIp.setText(dbia.getRouterIp());
        }
        TextField txtWlanRouterUser = new TextField();
        if (!(dbia.getRouterUser() == null)) {
            txtWlanRouterUser.setText(dbia.getRouterUser());
        }
        TextField txtWlanRouterPw = new TextField();
        if (!(dbia.getRouterPw() == null)) {
            txtWlanRouterPw.setText(dbia.getRouterPw());
        }
        TextField txtWlanRepeater = new TextField();
        if (!(dbia.getRepeater() == null)) {
            txtWlanRepeater.setText(dbia.getRepeater());
        }
        TextField txtWlanRepeaterUser = new TextField();
        if (!(dbia.getRepeaterUser() == null)) {
            txtWlanRepeaterUser.setText(dbia.getRepeaterUser());
        }
        TextField txtWlanRepeaterPw = new TextField();
        if (!(dbia.getRepeaterPw() == null)) {
            txtWlanRepeaterPw.setText(dbia.getRepeaterPw());
        }
        TextField txtWlanRepeaterStk = new TextField(String.valueOf(dbia.getRepeaterStk()));
        txtWlanRepeaterStk.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                if (txtWlanRepeaterStk.getText() == "") {
                    txtWlanRepeaterStk.setText("0");
                }
                try {
                    Integer.valueOf(txtWlanRepeaterStk.getText());
                } catch (Exception e) {
                    Wz.fehler("Die Stückzahl darf nur Ziffern beinhalten!\n" + txtWlanRepeaterStk.getText() + " ist nicht erlaubt");
                    txtWlanRepeaterStk.setText("0");
                }    
            }
            });

        TextField txtWlanName = new TextField();
        if (!(dbia.getWlanName() == null)) {
            txtWlanName.setText(dbia.getWlanName());
        }

        TextField txtWlanPw = new TextField();
        if (!(dbia.getWlanPw() == null)) {
            txtWlanPw.setText(dbia.getWlanPw());
        }
        TextArea txaSonstiges = new TextArea();
        if (!(dbia.getSonstiges() == null)) {
            txaSonstiges.setText(dbia.getSonstiges());
        }

        GridPane gp = new GridPane();
        gp.add(lblAnbieter, 0, 1);
        gp.add(lblZugangUser, 0, 2);
        gp.add(lblZugangPw, 0, 3);
        gp.add(lblWlanRouter, 0, 4);
        gp.add(lblWlanRouterIp, 0, 5);
        gp.add(lblWlanRouterUser, 0, 6);
        gp.add(lbllblWlanRouterPw, 0, 7);
        gp.add(lblWlanRepeater, 0, 8);
        gp.add(lblWlanRepeaterUser, 0, 9);
        gp.add(lblWlanRepeaterPw, 0, 10);
        gp.add(lblWlanRepeaterStk, 0, 11);
        gp.add(lblWlanName, 0, 12);
        gp.add(lblWlanPw, 0, 13);
        gp.add(lblSonstiges, 0, 14);
        
        gp.add(txtAnbieter, 1, 1);
        gp.add(txtZugangUser, 1, 2);
        gp.add(txtZugangPw, 1, 3);
        gp.add(txtWlanRouter, 1, 4);
        gp.add(txtWlanRouterIp, 1, 5);
        gp.add(txtWlanRouterUser, 1, 6);
        gp.add(txtWlanRouterPw, 1, 7);
        gp.add(txtWlanRepeater, 1, 8);
        gp.add(txtWlanRepeaterUser, 1, 9);
        gp.add(txtWlanRepeaterPw, 1, 10);
        gp.add(txtWlanRepeaterStk, 1, 11);
        gp.add(txtWlanName, 1, 12);
        gp.add(txtWlanPw, 1, 13);
        gp.add(txaSonstiges, 1, 14);
        gp.setPadding(Var.gpPadding);
        gp.setHgap(Var.gpHgap);
        gp.setVgap(Var.gpVgap);
        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            Var.hs.getBp().setCenter(this.erstelleGuiIa());
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
            DbInternetanbindung tempDbia = new DbInternetanbindung();
            tempDbia.setId(Integer.valueOf(accId));
            tempDbia.setKdId(Integer.valueOf(Var.kd.getId()));
            tempDbia.setAnbieter(txtAnbieter.getText());
            tempDbia.setZugangUser(txtZugangUser.getText());
            tempDbia.setZugangPw(txtZugangPw.getText());
            tempDbia.setRouter(txtWlanRouter.getText());
            tempDbia.setRouterIp(txtWlanRouterIp.getText());
            tempDbia.setRouterUser(txtWlanRouterUser.getText());
            tempDbia.setRouterPw(txtWlanRouterPw.getText());
            tempDbia.setRepeater(txtWlanRepeater.getText());
            tempDbia.setRepeaterUser(txtWlanRepeaterUser.getText());
            tempDbia.setRepeaterPw(txtWlanRepeaterPw.getText());
            tempDbia.setRepeaterStk(Integer.valueOf(txtWlanRepeaterStk.getText()));
            tempDbia.setWlanName(txtWlanName.getText());
            tempDbia.setWlanPw(txtWlanPw.getText());
            tempDbia.setSonstiges(txaSonstiges.getText());
            String[][] tempDef = {
                {"id",           tp.getText(),                  "INTEGER",  "ja",       "ja",         null,               null},
                {"kdId",         String.valueOf(Var.kd.getId()),"INTEGER",  "nein",     "nein",       "Kunde",            "id"},
                {"anbieter",     txtAnbieter.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"zugangUser",   txtZugangUser.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"zugangPw",     txtZugangPw.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"router",       txtWlanRouter.getText(),       "STRING",   "nein",     "nein",       null,               null},
                {"routerIp",     txtWlanRouterIp.getText(),     "STRING",   "nein",     "nein",       null,               null},
                {"routerUser",   txtWlanRouterUser.getText(),   "STRING",   "nein",     "nein",       null,               null},
                {"routerPw",     txtWlanRouterPw.getText(),     "STRING",   "nein",     "nein",       null,               null},
                {"repeater",     txtWlanRepeater.getText(),     "STRING",   "nein",     "nein",       null,               null},
                {"repeaterStk",  txtWlanRepeaterStk.getText(),  "INTEGER",  "nein",     "nein",       null,               null},
                {"repeaterUser", txtWlanRepeaterUser.getText(), "STRING",   "nein",     "nein",       null,               null},
                {"repeaterPw",   txtWlanRepeaterPw.getText(),   "STRING",   "nein",     "nein",       null,               null},
                {"wlanName",     txtWlanName.getText(),         "STRING",   "nein",     "nein",       null,               null},
                {"wlanPw",       txtWlanPw.getText(),           "STRING",   "nein",     "nein",       null,               null},
                {"sonstiges",    txaSonstiges.getText(),        "STRING",   "nein",     "nein",       null,               null}
            };
            tempDbia.setTabellenDefinition(tempDef);
            tempDbia.iaSpeichern();
            Wz.info("Datensatz wurde erfolgreich gespeichert");
            Var.hs.getBp().setCenter(new GuiIa().erstelleGuiIa());

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
            
            new DbInternetanbindung().iaLoeschen(accId);
            Var.hs.getBp().setCenter(this.erstelleGuiIa());
        });
        HBox hb = new HBox(btnLoeschen, btnAbbrechen, btnSpeichern);
        VBox vb = new VBox (hb, gp);
        tp.setContent(vb);
        return tp;
    }

    public Accordion erstelleGuiIa () {
        Accordion acc = new Accordion();
        DbInternetanbindung[] alleDbia =  new DbInternetanbindung().alleIaAuslesen();
        ObservableList<TitledPane> olTp = FXCollections.observableArrayList();
        for (int i = 0; i < alleDbia.length; i++) {
            TitledPane tp  = erstelleTpIa(alleDbia[i].getId(), alleDbia[i]);
            tp.setText("Internetanbindung " + Var.kd.getName() + " " + (i+1) + " (id:" + alleDbia[i].getId() + ")");
            olTp.add(tp);
        }
        TitledPane tp = erstelleTpIa(-1, new DbInternetanbindung());
        tp.setText("neue Internetanbindung anlegen");
        olTp.add(tp);
        acc.getPanes().addAll(olTp);
        return acc;
    }
}

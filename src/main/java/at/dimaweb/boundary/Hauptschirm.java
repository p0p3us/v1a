package at.dimaweb.boundary;

import java.util.Optional;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import at.dimaweb.entity.DbKunde;
import at.dimaweb.entity.PropDatenblatt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Data;

@Data
public class Hauptschirm {

    private ComboBox<String> cmbKunde = new ComboBox<>();
    private Label lblUeberschrift = new Label ();
    private BorderPane bp = new BorderPane();
    
    public BorderPane erstellen () {
        bp.setTop(this.erstelleUeberschrift());
        bp.setBottom(this.erstelleFusszeile());
        bp.setLeft(this.erstelleDatenblattMenue());
        return bp;
    }

    public void cmbAktualisieren () {
        ObservableList<String> olDbKunden = FXCollections.observableArrayList();
        DbKunde[] alleKunden = new DbKunde().kundeAusTabelle();
        for (int i = 0; i < alleKunden.length; i++) {
            olDbKunden.add(alleKunden[i].getName());
        }
        this.cmbKunde.setItems(olDbKunden);
        this.cmbKunde.getSelectionModel().select(Var.kd.getName());
        if (!(Var.kd.getName() == null)) {
            lblUeberschrift.setText("Datenblätter " + Var.kd.getName());
        } else {
            lblUeberschrift.setText("Oh Oh");
        }
    }

    private HBox erstelleUeberschrift () {
        // links: Combobox zur Kundenauswahl (Objektvariable siehe oben) und Button zur Kundenanlage
        cmbKunde.setOnAction(e -> {
            lblUeberschrift.setText("Datenblatt " + cmbKunde.getSelectionModel().getSelectedItem());
            Var.kd.setName(cmbKunde.getSelectionModel().getSelectedItem());
            Var.kd.setId(Var.kd.nameInId());
            bp.setCenter(new Label(""));
        });
        Button btnKunde = new Button("Kunden anlegen");
        btnKunde.setOnAction(e -> {
            DlgNeuerKunde dlgNeuerKunde = new DlgNeuerKunde();
            Optional<DbKunde> optKunde = dlgNeuerKunde.showAndWait();
            if (!(optKunde.get()==null) && !(optKunde.get().getName().equals(""))) {
                DbKunde dbKunde = new DbKunde();
                dbKunde.setId(-1);
                dbKunde.setName(optKunde.get().getName());
                String[][] tempDef = {
                    {"id", "-1", "INTEGER", "ja", "ja", null, null},
                    {"name", optKunde.get().getName(), "STRING", "nein", "nein", null, null}
                };
                Var.kd = dbKunde;
                dbKunde.setTabellenDefinition(tempDef);
                dbKunde.kundeInTabelle();
                this.cmbAktualisieren();
            }
        });
        this.cmbAktualisieren();
        VBox vbKunde = new VBox(cmbKunde, btnKunde);
        cmbKunde.setPrefWidth(Var.breite*0.1);
        btnKunde.setPrefWidth(Var.breite*0.1);
        vbKunde.setPadding(Var.vbPadding);
        vbKunde.setSpacing(Var.vbSpacing);

        // Mitte: Label mit Überschrift
        lblUeberschrift.setText("Datenblattablage");
        lblUeberschrift.setFont(new Font(Var.hlFont, Var.hlSize));

        // Rechts: Logo
        Image imgLogo = new Image(getClass().getResourceAsStream(Var.logo));  
        ImageView ivLogo = new ImageView();
        ivLogo.setImage(imgLogo); 
        
        // Komponenten links mitte und rechts zusammenführen
        HBox hb = new HBox(vbKunde, this.lblUeberschrift, ivLogo);
        hb.setPadding(Var.hbPadding);
        hb.setSpacing(Var.hbSpacing);
        hb.setPrefWidth(Var.breite);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    private HBox erstelleFusszeile () {
        Label lblFusszeile = new Label ("(c) 2022-2023 MPP");
        lblFusszeile.setFont(new Font(Var.footerFont, Var.footerSize));
        HBox hb = new HBox(lblFusszeile);
        hb.setPrefWidth(Var.breite);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    private TableView<PropDatenblatt> erstelleDatenblattMenue () {
        TableView<PropDatenblatt> tvDatenblatt = new TableView<>();
        TableColumn<PropDatenblatt, String> tcName = new TableColumn<>("Datenblatt");
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcName.setPrefWidth(Var.breite*0.1);
        tvDatenblatt.getColumns().add(tcName);
        ObservableList<PropDatenblatt> olDatenblatt = FXCollections.observableArrayList();
        for (int i = 0; i < Var.alleDatenblaetter.length; i++) {
            PropDatenblatt temp = new PropDatenblatt();
            temp.setAbr(Var.alleDatenblaetter[i][0]);
            temp.setName(Var.alleDatenblaetter[i][1]);
            olDatenblatt.add(temp);
        }
        tvDatenblatt.setItems(olDatenblatt);
        tvDatenblatt.setOnMouseClicked(e -> {
            if (Var.kd.getId() == 0) {
                Wz.fehler("Bitte zuerst einen Kunden auswählen!");
            } else {
                if (tvDatenblatt.getSelectionModel().getSelectedItem() != null) {
                    switch (tvDatenblatt.getSelectionModel().getSelectedItem().getAbr()) {
                    case "ia" :
                        Var.hs.bp.setCenter(new GuiIa().erstelleGuiIa());
                        break;
                    case "dv" :
                        Var.hs.bp.setCenter(new GuiDv().erstelleGuiDv());
                        break;
                    case "ws" :
                        Var.hs.bp.setCenter(new GuiWs().erstelleGuiWs());
                        break;		
                    case "ex" :
                        Var.hs.bp.setCenter(new GuiEx().erstelleGuiEx());
                        break;	
                    }
                }
            }
		});
        return tvDatenblatt;
    }
}

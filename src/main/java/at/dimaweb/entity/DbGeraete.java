package at.dimaweb.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import at.dimaweb.control.Var;
import at.dimaweb.control.Wz;
import lombok.Data;
@Data
// 1/4: alle Spalten in der Tabelle als Variablen deklarieren
public class DbGeraete {
    private int id;
    private String geraet;
    
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",     "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"geraet", "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public void geInitialisieren () {
        DbGeraete dbge = new DbGeraete();
        dbge.setId(-1);
        DbGeraete[] alleGeraete= new DbGeraete().alleGeAuslesen();
        boolean nas = false;
        boolean wlan = false;
        boolean drucker = false;
        for (int i = 0; i < alleGeraete.length; i++) {
            if (alleGeraete[i].getGeraet().equals("NAS-Server")) nas = true;
            if (alleGeraete[i].getGeraet().equals("WLan-Router")) wlan = true;
            if (alleGeraete[i].getGeraet().equals("Netzwerkdrucker")) drucker = true;
        }
        if (!nas) {
            dbge.tabellenDefinition[1][1] = ("NAS-Server");
            dbge.geSpeichern();
        }
        if (!wlan) {
            dbge.tabellenDefinition[1][1] = ("WLan-Router");
            dbge.geSpeichern();
        }
        
        if (!drucker) {
            dbge.tabellenDefinition[1][1] = ("Netzwerkdrucker");
            dbge.geSpeichern();
        }
    }

    public DbGeraete[] alleGeAuslesen () {
        String sqlBefehl = "SELECT * FROM Geraete;";
        List<DbGeraete> alleDbge = new ArrayList<DbGeraete>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbGeraete dbge = new DbGeraete();
                dbge.id = rs.getInt("id");
                if (!(rs.getString("geraet").equals(null))) {
                    dbge.geraet = rs.getString("geraet");
                }
                
                dbge.tabellenDefinition[0][1] = String.valueOf(dbge.getId());
                dbge.tabellenDefinition[1][1] = dbge.getGeraet();
                alleDbge.add(dbge);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Gerätetabelle nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbGeraete[] tempDbge = new DbGeraete[alleDbge.size()];
        alleDbge.toArray(tempDbge);
        return (tempDbge);
    }

    public void geSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Geraete (";
            //startet erst bei eins, da die Id nicht in die Datenbank geschrieben wird wenn es um eine Neuanlage handelt 
            //(Neuanlage wird definier durch das setzen der ID -1)
            for (int i = 1; i < this.tabellenDefinition.length; i++) {
                sqlBefehl += this.tabellenDefinition[i][0];
                if (i+1 == this.tabellenDefinition.length) {
                    sqlBefehl += ") VALUES (";
                } else {
                    sqlBefehl += ", ";
                }
            }
            for (int i = 1; i < this.tabellenDefinition.length; i++) {
                sqlBefehl += "?";
                if (i+1 == this.tabellenDefinition.length) {
                    sqlBefehl += ");";
                } else {
                    sqlBefehl += ", ";
                }
            }
            try {
                PreparedStatement pstmt = Var.dbba.getConn().prepareStatement(sqlBefehl);
                pstmt.setString(1, this.tabellenDefinition[1][1]);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Gerätetabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Geraete SET ";
            for (int i = 1; i < this.tabellenDefinition.length; i++) {
                if (i+1 == this.tabellenDefinition.length) {
                    sqlBefehl += this.tabellenDefinition[i][0] + " = ?";
                } else {
                    sqlBefehl += this.tabellenDefinition[i][0] + " = ?,";
                }
            }
            sqlBefehl += " WHERE id = ?;";
            try {
                PreparedStatement pstmt = Var.dbba.getConn().prepareStatement(sqlBefehl);
                pstmt.setString(1, this.tabellenDefinition[1][1]);
                pstmt.setString(2, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Gerätetabelle Tabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void geLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Geraete WHERE id = ?";
            try {
                PreparedStatement pstmt = Var.dbba.getConn().prepareStatement(sqlBefehl);
                pstmt.setInt(1, loescheId);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler(sqlBefehl + "\nkonnte nicht ausgeführt werden\niehe Konsolenausgabe");
                e.printStackTrace();
            }
        }
    }
}

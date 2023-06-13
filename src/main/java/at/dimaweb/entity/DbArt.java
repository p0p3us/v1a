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
public class DbArt {
    private int id;
    private String art;
    
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",  "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"art", "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public void arInitialisieren () {
        DbArt dbar = new DbArt();
        dbar.setId(-1);
        DbArt[] alleAr= new DbArt().alleArAuslesen();
        boolean googleCal = false;
        for (int i = 0; i < alleAr.length; i++) {
            if (alleAr[i].getArt().equals("Google-Kalender")) googleCal = true;
        }
        if (!googleCal) {
            dbar.tabellenDefinition[1][1] = ("Google-Kalender");
            dbar.geSpeichern();
        }
    }

    public DbArt[] alleArAuslesen () {
        String sqlBefehl = "SELECT * FROM Art;";
        List<DbArt> alleDbar = new ArrayList<DbArt>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbArt dbar = new DbArt();
                dbar.id = rs.getInt("id");
                if (!(rs.getString("art").equals(null))) {
                    dbar.art = rs.getString("art");                }
                
                dbar.tabellenDefinition[0][1] = String.valueOf(dbar.getId());
                dbar.tabellenDefinition[1][1] = dbar.getArt();
                alleDbar.add(dbar);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Softwarenamentabelle nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbArt[] tempDbar = new DbArt[alleDbar.size()];
        alleDbar.toArray(tempDbar);
        return (tempDbar);
    }

    public void geSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Art (";
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
                Wz.fehler("Daten konnten nicht in die Softwarenamentabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Art SET ";
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
                Wz.fehler("Daten konnten nicht in der Softwarenamentabelle Tabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void arLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Art WHERE id = ?";
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

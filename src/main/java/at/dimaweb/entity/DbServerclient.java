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
public class DbServerclient
 {
    private int id;
    private int kdId;
    private String user;
    private String pw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",        "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",      "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"user",      "",           "STRING",   "nein",     "nein",       null,               null},
        {"pw",        "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges", "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbServerclient[] alleScAuslesen () {
        String sqlBefehl = "SELECT * FROM Serverclient WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbServerclient> alleDbsc = new ArrayList<DbServerclient>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbServerclient dbss = new DbServerclient();
                dbss.id = rs.getInt("id");
                dbss.kdId = rs.getInt("kdid");
                if (!(rs.getString("user").equals(null))) {
                    dbss.user = rs.getString("user");
                }
                if (!(rs.getString("pw").equals(null))) {
                    dbss.pw = rs.getString("pw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbss.sonstiges = rs.getString("sonstiges");
                }
                dbss.tabellenDefinition[0][1] = String.valueOf(dbss.getId());
                dbss.tabellenDefinition[1][1] = String.valueOf(dbss.getKdId());
                dbss.tabellenDefinition[2][1] = dbss.getUser();
                dbss.tabellenDefinition[3][1] = dbss.getPw();
                dbss.tabellenDefinition[4][1] = dbss.getSonstiges();
                alleDbsc.add(dbss);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Serverclients nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbServerclient[] tempDbsc = new DbServerclient[alleDbsc.size()];
        alleDbsc.toArray(tempDbsc);
        return (tempDbsc);
    }

    public void scSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Serverclient (";
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
                pstmt.setInt(1, Integer.valueOf(this.tabellenDefinition[1][1]));
                pstmt.setString(2, this.tabellenDefinition[2][1]);
                pstmt.setString(3, this.tabellenDefinition[3][1]);
                pstmt.setString(4, this.tabellenDefinition[4][1]);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Serverclienttabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Serverclient SET ";
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
                pstmt.setInt(1, Integer.valueOf(this.tabellenDefinition[1][1]));
                pstmt.setString(2, this.tabellenDefinition[2][1]);
                pstmt.setString(3, this.tabellenDefinition[3][1]);
                pstmt.setString(4, this.tabellenDefinition[4][1]);
                pstmt.setString(5, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Serverclienttabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void scLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Serverclient WHERE id = ?";
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

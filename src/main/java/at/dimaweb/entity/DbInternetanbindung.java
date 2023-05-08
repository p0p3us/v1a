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
public class DbInternetanbindung {
    private int id;
    private int kdId;
    private String anbieter;
    private String zugangUser;
    private String zugangPw;
    private String router;
    private String routerIp;
    private String routerUser;
    private String routerPw;
    private String repeater;
    private int repeaterStk;
    private String repeaterUser;
    private String repeaterPw;
    private String wlanName;
    private String wlanPw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",           "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",         "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"anbieter",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"zugangUser",   "",           "STRING",   "nein",     "nein",       null,               null},
        {"zugangPw",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"router",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"routerIp",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"routerUser",   "",           "STRING",   "nein",     "nein",       null,               null},
        {"routerPw",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"repeater",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"repeaterStk",  "",           "INTEGER",  "nein",     "nein",       null,               null},
        {"repeaterUser", "",           "STRING",   "nein",     "nein",       null,               null},
        {"repeaterPw",   "",           "STRING",   "nein",     "nein",       null,               null},
        {"wlanName",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"wlanPw",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges",    "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbInternetanbindung[] alleIaAuslesen () {
        String sqlBefehl = "SELECT * FROM Internetanbindung WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbInternetanbindung> alleDbia = new ArrayList<DbInternetanbindung>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbInternetanbindung dbia = new DbInternetanbindung();
                dbia.id = rs.getInt("id");
                dbia.kdId = rs.getInt("kdid");
                if (!(rs.getString("anbieter").equals(null))) {
                    dbia.anbieter = rs.getString("anbieter");
                }
                if (!(rs.getString("zugangUser").equals(null))) {
                    dbia.zugangUser = rs.getString("zugangUser");
                }
                if (!(rs.getString("zugangPw").equals(null))) {
                    dbia.zugangPw = rs.getString("zugangPw");
                }
                if (!(rs.getString("Router").equals(null))) {
                    dbia.router = rs.getString("Router");
                }
                if (!(rs.getString("RouterIp").equals(null))) {
                    dbia.routerIp = rs.getString("RouterIp");
                }
                if (!(rs.getString("RouterUser").equals(null))) {
                    dbia.routerUser = rs.getString("RouterUser");
                }
                if (!(rs.getString("RouterPw").equals(null))) {
                    dbia.routerPw = rs.getString("RouterPw");
                }
                if (!(rs.getString("Repeater").equals(null))) {
                    dbia.repeater = rs.getString("Repeater");
                }
                dbia.repeaterStk = rs.getInt("RepeaterStk");
                if (!(rs.getString("RepeaterUser").equals(null))) {
                    dbia.repeaterUser = rs.getString("RepeaterUser");
                }
                if (!(rs.getString("RepeaterPw").equals(null))) {
                    dbia.repeaterPw = rs.getString("RepeaterPw");
                }
                if (!(rs.getString("wlanName").equals(null))) {
                    dbia.wlanName = rs.getString("wlanName");
                }
                if (!(rs.getString("wlanPw").equals(null))) {
                    dbia.wlanPw = rs.getString("wlanPw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbia.sonstiges = rs.getString("sonstiges");
                }
                dbia.tabellenDefinition[0][1] = String.valueOf(dbia.getId());
                dbia.tabellenDefinition[1][1] = String.valueOf(dbia.getKdId());
                dbia.tabellenDefinition[2][1] = dbia.getAnbieter();
                dbia.tabellenDefinition[3][1] = dbia.getZugangUser();
                dbia.tabellenDefinition[4][1] = dbia.getZugangPw();
                dbia.tabellenDefinition[5][1] = dbia.getRouter();
                dbia.tabellenDefinition[6][1] = dbia.getRouterIp();
                dbia.tabellenDefinition[7][1] = dbia.getRouterUser();
                dbia.tabellenDefinition[8][1] = dbia.getRouterPw();
                dbia.tabellenDefinition[9][1] = dbia.getRepeater();
                dbia.tabellenDefinition[10][1] = String.valueOf(dbia.getRepeaterStk());
                dbia.tabellenDefinition[11][1] = dbia.getRepeaterPw();
                dbia.tabellenDefinition[12][1] = dbia.getWlanName();
                dbia.tabellenDefinition[13][1] = dbia.getWlanPw();
                dbia.tabellenDefinition[14][1] = dbia.getSonstiges();
                alleDbia.add(dbia);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Internetanbindungen nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbInternetanbindung[] tempDbia = new DbInternetanbindung[alleDbia.size()];
        alleDbia.toArray(tempDbia);
        return (tempDbia);
    }

    public void iaSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Internetanbindung (";
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
                pstmt.setString(5, this.tabellenDefinition[5][1]);
                pstmt.setString(6, this.tabellenDefinition[6][1]);
                pstmt.setString(7, this.tabellenDefinition[7][1]);
                pstmt.setString(8, this.tabellenDefinition[8][1]);
                pstmt.setString(9, this.tabellenDefinition[9][1]);
                pstmt.setInt(10, Integer.valueOf(this.tabellenDefinition[10][1]));
                pstmt.setString(11, this.tabellenDefinition[11][1]);
                pstmt.setString(12, this.tabellenDefinition[12][1]);
                pstmt.setString(13, this.tabellenDefinition[13][1]);
                pstmt.setString(14, this.tabellenDefinition[14][1]);
                pstmt.setString(15, this.tabellenDefinition[15][1]);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Internetanbindungstabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Internetanbindung SET ";
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
                pstmt.setString(5, this.tabellenDefinition[5][1]);
                pstmt.setString(6, this.tabellenDefinition[6][1]);
                pstmt.setString(7, this.tabellenDefinition[7][1]);
                pstmt.setString(8, this.tabellenDefinition[8][1]);
                pstmt.setString(9, this.tabellenDefinition[9][1]);
                pstmt.setInt(10, Integer.valueOf(this.tabellenDefinition[10][1]));
                pstmt.setString(11, this.tabellenDefinition[11][1]);
                pstmt.setString(12, this.tabellenDefinition[12][1]);
                pstmt.setString(13, this.tabellenDefinition[13][1]);
                pstmt.setString(14, this.tabellenDefinition[14][1]);
                pstmt.setString(15, this.tabellenDefinition[15][1]);
                pstmt.setString(16, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Internetanbindungstabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void iaLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Internetanbindung WHERE id = ?";
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

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
public class DbSoftware {
    private int id;
    private int kdId;
    private String art;
    private String standort;
    private String software;
    private String interf;
    private String mail;
    private String user;
    private String pw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",        "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",      "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"art",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"standort",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"software",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"interf",    "",           "STRING",   "nein",     "nein",       null,               null},
        {"mail",      "",           "STRING",   "nein",     "nein",       null,               null},
        {"user",      "",           "STRING",   "nein",     "nein",       null,               null},
        {"pw",        "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges", "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbSoftware[] alleSwAuslesen () {
        String sqlBefehl = "SELECT * FROM Software WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbSoftware> alleDbsw = new ArrayList<DbSoftware>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbSoftware dbsw = new DbSoftware();
                dbsw.id = rs.getInt("id");
                dbsw.kdId = rs.getInt("kdid");
                if (!(rs.getString("art").equals(null))) {
                    dbsw.art = rs.getString("art");
                }
                if (!(rs.getString("standort").equals(null))) {
                    dbsw.standort = rs.getString("standort");
                }
                if (!(rs.getString("software").equals(null))) {
                    dbsw.software = rs.getString("software");
                }
                if (!(rs.getString("interf").equals(null))) {
                    dbsw.interf = rs.getString("interf");
                }
                if (!(rs.getString("mail").equals(null))) {
                    dbsw.mail = rs.getString("mail");
                }
                if (!(rs.getString("user").equals(null))) {
                    dbsw.user = rs.getString("user");
                }
                if (!(rs.getString("pw").equals(null))) {
                    dbsw.pw = rs.getString("pw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbsw.sonstiges = rs.getString("sonstiges");
                }
                dbsw.tabellenDefinition[0][1] = String.valueOf(dbsw.getId());
                dbsw.tabellenDefinition[1][1] = String.valueOf(dbsw.getKdId());
                dbsw.tabellenDefinition[2][1] = dbsw.getArt();
                dbsw.tabellenDefinition[3][1] = dbsw.getStandort();
                dbsw.tabellenDefinition[4][1] = dbsw.getSoftware();
                dbsw.tabellenDefinition[5][1] = dbsw.getInterf();
                dbsw.tabellenDefinition[6][1] = dbsw.getMail();
                dbsw.tabellenDefinition[7][1] = dbsw.getUser();
                dbsw.tabellenDefinition[8][1] = dbsw.getPw();
                dbsw.tabellenDefinition[9][1] = dbsw.getSonstiges();
                alleDbsw.add(dbsw);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Software-Verwaltung nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbSoftware[] tempDbsw = new DbSoftware[alleDbsw.size()];
        alleDbsw.toArray(tempDbsw);
        return (tempDbsw);
    }

    public void swSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Software (";
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
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Software-Verwaltungs Tabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Software SET ";
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
                pstmt.setString(9, this.tabellenDefinition[9][1]);
                pstmt.setString(10, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Software-Verwaltungs Tabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void swLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Software WHERE id = ?";
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

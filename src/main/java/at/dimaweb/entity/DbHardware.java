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
public class DbHardware {
    private int id;
    private int kdId;
    private String geraet;
    private String verwaltung;
    private String type;
    private String hersteller;
    private String ip;
    private String host;
    private String user;
    private String pw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",         "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",       "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"geraet",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"verwaltung", "",           "STRING",   "nein",     "nein",       null,               null},
        {"type",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"hersteller", "",           "STRING",   "nein",     "nein",       null,               null},
        {"ip",         "",           "STRING",   "nein",     "nein",       null,               null},
        {"host",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"user",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"pw",         "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges",  "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbHardware[] alleHwAuslesen () {
        String sqlBefehl = "SELECT * FROM Hardware WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbHardware> alleDbhw = new ArrayList<DbHardware>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbHardware dbhw = new DbHardware();
                dbhw.id = rs.getInt("id");
                dbhw.kdId = rs.getInt("kdid");
                if (!(rs.getString("geraet").equals(null))) {
                    dbhw.geraet = rs.getString("geraet");
                }
                if (!(rs.getString("verwaltung").equals(null))) {
                    dbhw.verwaltung = rs.getString("verwaltung");
                }
                if (!(rs.getString("type").equals(null))) {
                    dbhw.type = rs.getString("type");
                }
                if (!(rs.getString("hersteller").equals(null))) {
                    dbhw.hersteller = rs.getString("hersteller");
                }
                if (!(rs.getString("ip").equals(null))) {
                    dbhw.ip = rs.getString("ip");
                }
                if (!(rs.getString("host").equals(null))) {
                    dbhw.host = rs.getString("host");
                }
                if (!(rs.getString("user").equals(null))) {
                    dbhw.user = rs.getString("user");
                }
                if (!(rs.getString("pw").equals(null))) {
                    dbhw.pw = rs.getString("pw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbhw.sonstiges = rs.getString("sonstiges");
                }
                dbhw.tabellenDefinition[0][1] = String.valueOf(dbhw.getId());
                dbhw.tabellenDefinition[1][1] = String.valueOf(dbhw.getKdId());
                dbhw.tabellenDefinition[2][1] = dbhw.getGeraet();
                dbhw.tabellenDefinition[3][1] = dbhw.getVerwaltung();
                dbhw.tabellenDefinition[4][1] = dbhw.getType();
                dbhw.tabellenDefinition[5][1] = dbhw.getHersteller();
                dbhw.tabellenDefinition[6][1] = dbhw.getIp();
                dbhw.tabellenDefinition[7][1] = dbhw.getHost();
                dbhw.tabellenDefinition[8][1] = dbhw.getUser();
                dbhw.tabellenDefinition[9][1] = dbhw.getPw();
                dbhw.tabellenDefinition[10][1] = dbhw.getSonstiges();
                alleDbhw.add(dbhw);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Hardware-Verwaltung nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbHardware[] tempDbhw = new DbHardware[alleDbhw.size()];
        alleDbhw.toArray(tempDbhw);
        return (tempDbhw);
    }

    public void hwSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Hardware (";
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
                pstmt.setString(10, this.tabellenDefinition[10][1]);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Hardware-Verwaltungs Tabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Hardware SET ";
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
                pstmt.setString(10, this.tabellenDefinition[10][1]);
                pstmt.setString(11, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Hardware-Verwaltungs Tabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void hwLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Hardware WHERE id = ?";
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

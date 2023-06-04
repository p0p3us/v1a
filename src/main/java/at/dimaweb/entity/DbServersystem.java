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
public class DbServersystem {
    private int id;
    private int kdId;
    private String os;
    private String domain;
    private String ipAdresse;
    private String ipRangeVon;
    private String ipRangeBis;
    private String subnet;
    private String gateway;
    private String verwendung;
    private String adminUser;
    private String adminPw;
    private String lokalUser;
    private String lokalPw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",         "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",       "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"os",         "",           "STRING",   "nein",     "nein",       null,               null},
        {"domain",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"ipAdresse",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"ipRangeVon", "",           "STRING",   "nein",     "nein",       null,               null},
        {"ipRangeBis", "",           "STRING",   "nein",     "nein",       null,               null},
        {"subnet",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"gateway",    "",           "STRING",   "nein",     "nein",       null,               null},
        {"verwendung", "",           "STRING",   "nein",     "nein",       null,               null},
        {"adminUser",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"adminPw",    "",           "STRING",   "nein",     "nein",       null,               null},
        {"lokalUser",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"lokalPw",    "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges",  "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbServersystem[] alleSsAuslesen () {
        String sqlBefehl = "SELECT * FROM Serversystem WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbServersystem> alleDbss = new ArrayList<DbServersystem>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbServersystem dbss = new DbServersystem();
                dbss.id = rs.getInt("id");
                dbss.kdId = rs.getInt("kdid");
                if (!(rs.getString("os").equals(null))) {
                    dbss.os = rs.getString("os");
                }
                if (!(rs.getString("domain").equals(null))) {
                    dbss.domain = rs.getString("domain");
                }
                if (!(rs.getString("ipAdresse").equals(null))) {
                    dbss.ipAdresse = rs.getString("ipAdresse");
                }
                if (!(rs.getString("ipRangeVon").equals(null))) {
                    dbss.ipRangeVon = rs.getString("ipRangeVon");
                }
                if (!(rs.getString("ipRangeBis").equals(null))) {
                    dbss.ipRangeBis = rs.getString("ipRangeBis");
                }
                if (!(rs.getString("subnet").equals(null))) {
                    dbss.subnet = rs.getString("subnet");
                }
                if (!(rs.getString("gateway").equals(null))) {
                    dbss.gateway = rs.getString("gateway");
                }
                if (!(rs.getString("verwendung").equals(null))) {
                    dbss.verwendung = rs.getString("verwendung");
                }
                if (!(rs.getString("adminUser").equals(null))) {
                    dbss.adminUser = rs.getString("adminUser");
                }
                if (!(rs.getString("adminPw").equals(null))) {
                    dbss.adminPw = rs.getString("adminPw");
                }
                if (!(rs.getString("lokalUser").equals(null))) {
                    dbss.lokalUser = rs.getString("lokalUser");
                }
                if (!(rs.getString("lokalPw").equals(null))) {
                    dbss.lokalPw = rs.getString("lokalPw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbss.sonstiges = rs.getString("sonstiges");
                }
                dbss.tabellenDefinition[0][1] = String.valueOf(dbss.getId());
                dbss.tabellenDefinition[1][1] = String.valueOf(dbss.getKdId());
                dbss.tabellenDefinition[2][1] = dbss.getOs();
                dbss.tabellenDefinition[3][1] = dbss.getDomain();
                dbss.tabellenDefinition[4][1] = dbss.getIpAdresse();
                dbss.tabellenDefinition[5][1] = dbss.getIpRangeVon();
                dbss.tabellenDefinition[6][1] = dbss.getIpRangeBis();
                dbss.tabellenDefinition[7][1] = dbss.getSubnet();
                dbss.tabellenDefinition[8][1] = dbss.getGateway();
                dbss.tabellenDefinition[9][1] = dbss.getVerwendung();
                dbss.tabellenDefinition[10][1] = dbss.getAdminUser();
                dbss.tabellenDefinition[11][1] = dbss.getAdminPw();
                dbss.tabellenDefinition[12][1] = dbss.getLokalUser();
                dbss.tabellenDefinition[13][1] = dbss.getLokalPw();
                dbss.tabellenDefinition[14][1] = dbss.getSonstiges();
                alleDbss.add(dbss);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Serversystem nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbServersystem[] tempDbss = new DbServersystem[alleDbss.size()];
        alleDbss.toArray(tempDbss);
        return (tempDbss);
    }

    public void ssSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Serversystem (";
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
                pstmt.setString(11, this.tabellenDefinition[11][1]);
                pstmt.setString(12, this.tabellenDefinition[12][1]);
                pstmt.setString(13, this.tabellenDefinition[13][1]);
                pstmt.setString(14, this.tabellenDefinition[14][1]);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Serversystemtabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Serversystem SET ";
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
                pstmt.setString(10, this.tabellenDefinition[10][1]);
                pstmt.setString(11, this.tabellenDefinition[11][1]);
                pstmt.setString(12, this.tabellenDefinition[12][1]);
                pstmt.setString(13, this.tabellenDefinition[13][1]);
                pstmt.setString(14, this.tabellenDefinition[14][1]);
                pstmt.setString(15, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Serversystemtabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void ssLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Serversystem WHERE id = ?";
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

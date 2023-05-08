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
public class DbWebspace {
    private int id;
    private int kdId;
    private String anbieter;
    private String webspace;
    private String ftp;
    private String ftpUser;
    private String ftpPw;
    private String cms;
    private String cmsUser;
    private String cmsPw;
    private String sql;
    private String sqlUser;
    private String sqlPw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",        "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",      "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"anbieter",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"webspace",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"ftp",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"ftpUser",   "",           "STRING",   "nein",     "nein",       null,               null},
        {"ftpPw",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"cms",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"cmsUser",   "",           "STRING",   "nein",     "nein",       null,               null},
        {"cmsPw",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"sql",       "",           "INTEGER",  "nein",     "nein",       null,               null},
        {"sqlUser",   "",           "STRING",   "nein",     "nein",       null,               null},
        {"sqlPw",     "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges", "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbWebspace[] alleWsAuslesen () {
        String sqlBefehl = "SELECT * FROM Webspace WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbWebspace> alleDbws = new ArrayList<DbWebspace>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbWebspace dbws = new DbWebspace();
                dbws.id = rs.getInt("id");
                dbws.kdId = rs.getInt("kdid");
                if (!(rs.getString("anbieter").equals(null))) {
                    dbws.anbieter = rs.getString("anbieter");
                }
                if (!(rs.getString("webspace").equals(null))) {
                    dbws.webspace = rs.getString("webspace");
                }
                if (!(rs.getString("ftp").equals(null))) {
                    dbws.ftp = rs.getString("ftp");
                }
                if (!(rs.getString("ftpUser").equals(null))) {
                    dbws.ftpUser = rs.getString("ftpUser");
                }
                if (!(rs.getString("ftpPw").equals(null))) {
                    dbws.ftpPw = rs.getString("ftpPw");
                }
                if (!(rs.getString("cms").equals(null))) {
                    dbws.cms = rs.getString("cms");
                }
                if (!(rs.getString("cmsUser").equals(null))) {
                    dbws.cmsUser = rs.getString("cmsUser");
                }
                if (!(rs.getString("cmsPw").equals(null))) {
                    dbws.cmsPw = rs.getString("cmsPw");
                }
                if (!(rs.getString("sql").equals(null))) {
                    dbws.sql = rs.getString("sql");
                }
                if (!(rs.getString("sqlUser").equals(null))) {
                    dbws.sqlUser = rs.getString("sqlUser");
                }
                if (!(rs.getString("sqlPw").equals(null))) {
                    dbws.sqlPw = rs.getString("sqlPw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbws.sonstiges = rs.getString("sonstiges");
                }
                dbws.tabellenDefinition[0][1] = String.valueOf(dbws.getId());
                dbws.tabellenDefinition[1][1] = String.valueOf(dbws.getKdId());
                dbws.tabellenDefinition[2][1] = dbws.getAnbieter();
                dbws.tabellenDefinition[3][1] = dbws.getWebspace();
                dbws.tabellenDefinition[4][1] = dbws.getFtp();
                dbws.tabellenDefinition[5][1] = dbws.getFtpUser();
                dbws.tabellenDefinition[6][1] = dbws.getFtpPw();
                dbws.tabellenDefinition[7][1] = dbws.getCms();
                dbws.tabellenDefinition[8][1] = dbws.getCmsUser();
                dbws.tabellenDefinition[9][1] = dbws.getCmsPw();
                dbws.tabellenDefinition[10][1] = dbws.getSql();
                dbws.tabellenDefinition[11][1] = dbws.getSqlUser();
                dbws.tabellenDefinition[12][1] = dbws.getSqlPw();
                dbws.tabellenDefinition[13][1] = dbws.getSonstiges();
                alleDbws.add(dbws);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Webspace / FTP / CMS-Verwaltung nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbWebspace[] tempDbws = new DbWebspace[alleDbws.size()];
        alleDbws.toArray(tempDbws);
        return (tempDbws);
    }

    public void wsSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Webspace (";
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
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Webspace / FTP / CMS-Verwaltungstabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Webspace SET ";
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
                pstmt.setString(14, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Webspace / FTP / CMS-Verwaltungstabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void wsLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Webspace WHERE id = ?";
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

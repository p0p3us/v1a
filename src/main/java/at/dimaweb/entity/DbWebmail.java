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
public class DbWebmail {
    private int id;
    private int kdId;
    private String art;
    private String eingang;
    private String ausgang;
    private String adminInterface;
    private String adminUser;
    private String adminPw;
    private String mail;
    private String mailUser;
    private String mailPw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",             "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",           "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"art",            "",           "STRING",   "nein",     "nein",       null,               null},
        {"eingang",        "",           "STRING",   "nein",     "nein",       null,               null},
        {"ausgang",        "",           "STRING",   "nein",     "nein",       null,               null},
        {"adminInterface", "",           "STRING",   "nein",     "nein",       null,               null},
        {"adminUser",      "",           "STRING",   "nein",     "nein",       null,               null},
        {"adminPw",        "",           "STRING",   "nein",     "nein",       null,               null},
        {"mail",           "",           "STRING",   "nein",     "nein",       null,               null},
        {"mailUser",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"mailPw",         "",           "INTEGER",  "nein",     "nein",       null,               null},
        {"sonstiges",      "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbWebmail[] alleWmAuslesen () {
        String sqlBefehl = "SELECT * FROM Webmail WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbWebmail> alleDbwm = new ArrayList<DbWebmail>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbWebmail dbwm = new DbWebmail();
                dbwm.id = rs.getInt("id");
                dbwm.kdId = rs.getInt("kdid");
                if (!(rs.getString("art").equals(null))) {
                    dbwm.art = rs.getString("art");
                }
                if (!(rs.getString("eingang").equals(null))) {
                    dbwm.eingang = rs.getString("eingang");
                }
                if (!(rs.getString("ausgang").equals(null))) {
                    dbwm.ausgang = rs.getString("ausgang");
                }
                if (!(rs.getString("adminInterface").equals(null))) {
                    dbwm.adminInterface = rs.getString("adminInterface");
                }
                if (!(rs.getString("adminUser").equals(null))) {
                    dbwm.adminUser = rs.getString("adminUser");
                }
                if (!(rs.getString("adminPw").equals(null))) {
                    dbwm.adminPw = rs.getString("adminPw");
                }
                if (!(rs.getString("mail").equals(null))) {
                    dbwm.mail = rs.getString("mail");
                }
                if (!(rs.getString("mailUser").equals(null))) {
                    dbwm.mailUser = rs.getString("mailUser");
                }
                if (!(rs.getString("mailPw").equals(null))) {
                    dbwm.mailPw = rs.getString("mailPw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbwm.sonstiges = rs.getString("sonstiges");
                }
                dbwm.tabellenDefinition[0][1] = String.valueOf(dbwm.getId());
                dbwm.tabellenDefinition[1][1] = String.valueOf(dbwm.getKdId());
                dbwm.tabellenDefinition[2][1] = dbwm.getArt();
                dbwm.tabellenDefinition[3][1] = dbwm.getEingang();
                dbwm.tabellenDefinition[4][1] = dbwm.getAusgang();
                dbwm.tabellenDefinition[5][1] = dbwm.getAdminInterface();
                dbwm.tabellenDefinition[6][1] = dbwm.getAdminUser();
                dbwm.tabellenDefinition[7][1] = dbwm.getAdminPw();
                dbwm.tabellenDefinition[8][1] = dbwm.getMail();
                dbwm.tabellenDefinition[9][1] = dbwm.getMailUser();
                dbwm.tabellenDefinition[10][1] = dbwm.getMailPw();
                dbwm.tabellenDefinition[11][1] = dbwm.getSonstiges();
                alleDbwm.add(dbwm);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db E-Mail-Verwaltung (Webmail) nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbWebmail[] tempDbwm = new DbWebmail[alleDbwm.size()];
        alleDbwm.toArray(tempDbwm);
        return (tempDbwm);
    }

    public void wmSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Webmail (";
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
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die E-Mail-Verwaltung (Webmail) Tabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Webmail SET ";
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
                pstmt.setString(12, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der E-Mail-Verwaltung (Webmail) Tabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void wmLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Webmail WHERE id = ?";
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

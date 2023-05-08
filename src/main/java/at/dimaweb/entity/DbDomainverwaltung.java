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
public class DbDomainverwaltung {
    private int id;
    private int kdId;
    private String domainName;
    private String registrar;
    private String ip;
    private String webportal;
    private String name;
    private String pw;
    private String sonstiges;
    
    // 2/4: alle Spalten der Tabelle (die oben als Variable definiert wurden) als Tabelle definieren
    private String[][] tabellenDefinition = {
    // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
        {"id",         "-1",         "INTEGER",  "ja",       "ja",         null,               null},
        {"kdId",       "",           "INTEGER",  "nein",     "nein",       "Kunde",            "id"},
        {"domainName", "",           "STRING",   "nein",     "nein",       null,               null},
        {"registrar",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"ip",         "",           "STRING",   "nein",     "nein",       null,               null},
        {"webportal",  "",           "STRING",   "nein",     "nein",       null,               null},
        {"name",       "",           "STRING",   "nein",     "nein",       null,               null},
        {"pw",         "",           "STRING",   "nein",     "nein",       null,               null},
        {"sonstiges",  "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public DbDomainverwaltung[] alleDvAuslesen () {
        String sqlBefehl = "SELECT * FROM Domainverwaltung WHERE (kdId = " + Var.kd.getId() + ");";
        List<DbDomainverwaltung> alleDbdv = new ArrayList<DbDomainverwaltung>();
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbDomainverwaltung dbdv = new DbDomainverwaltung();
                dbdv.id = rs.getInt("id");
                dbdv.kdId = rs.getInt("kdid");
                if (!(rs.getString("domainName").equals(null))) {
                    dbdv.domainName = rs.getString("domainName");
                }
                if (!(rs.getString("registrar").equals(null))) {
                    dbdv.registrar = rs.getString("registrar");
                }
                if (!(rs.getString("ip").equals(null))) {
                    dbdv.ip = rs.getString("ip");
                }
                if (!(rs.getString("webportal").equals(null))) {
                    dbdv.webportal = rs.getString("webportal");
                }
                if (!(rs.getString("name").equals(null))) {
                    dbdv.name = rs.getString("name");
                }
                if (!(rs.getString("pw").equals(null))) {
                    dbdv.pw = rs.getString("pw");
                }
                if (!(rs.getString("sonstiges").equals(null))) {
                    dbdv.sonstiges = rs.getString("sonstiges");
                }
                dbdv.tabellenDefinition[0][1] = String.valueOf(dbdv.getId());
                dbdv.tabellenDefinition[1][1] = String.valueOf(dbdv.getKdId());
                dbdv.tabellenDefinition[2][1] = dbdv.getDomainName();
                dbdv.tabellenDefinition[3][1] = dbdv.getRegistrar();
                dbdv.tabellenDefinition[4][1] = dbdv.getIp();
                dbdv.tabellenDefinition[5][1] = dbdv.getWebportal();
                dbdv.tabellenDefinition[6][1] = dbdv.getName();
                dbdv.tabellenDefinition[7][1] = dbdv.getPw();
                dbdv.tabellenDefinition[8][1] = dbdv.getSonstiges();
                alleDbdv.add(dbdv);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Daten konnten aus der Db Domainverwaltung nicht ausgelesen werden");
            e.printStackTrace();
        }
        DbDomainverwaltung[] tempDbdv = new DbDomainverwaltung[alleDbdv.size()];
        alleDbdv.toArray(tempDbdv);
        return (tempDbdv);
    }

    public void dvSpeichern () {
        if (this.getId() == -1) {
            String sqlBefehl = "INSERT INTO Domainverwaltung (";
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
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in die Domainverwaltungstabelle geschrieben werden");
                e.printStackTrace();
            }
        } else {
            //startet bei 1, da die Id nicht überschrieben werden muss und auch nicht sollte
            String sqlBefehl = "UPDATE Domainverwaltung SET ";
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
                pstmt.setString(9, String.valueOf(this.getId()));
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Wz.fehler("Daten konnten nicht in der Domainverwaltungstabelle geändert werden");
                e.printStackTrace();
            }
        }
    }

    public void dvLoeschen (int loescheId) {
        if (Wz.sicherheitsabfrage("Wollen Sie den Datensatz wirklich UNWIDERUFLICH löschen?")) {
            String sqlBefehl = "DELETE FROM Domainverwaltung WHERE id = ?";
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

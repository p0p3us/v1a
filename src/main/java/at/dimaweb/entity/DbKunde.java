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
public class DbKunde {
    private int id;
    private String name;
    private String[][] tabellenDefinition = {
        // Spaltenname,      Spaltenwerte, SpaltenTyp, PrimaryKey, Autoincrment, Foreignkeytabelle, Foreignkeyspalte
            {"id",           "-1",         "INTEGER",  "ja",       "ja",         null,               null},
            {"name",         "",           "STRING",   "nein",     "nein",       null,               null}
    };

    public void kundeInTabelle () {
        String sqlBefehl = "INSERT INTO Kunde (";
        if (Integer.valueOf(this.tabellenDefinition[0][1]) == -1) {
            sqlBefehl += "name) VALUES (?)";
            try {
                PreparedStatement pstmt = Var.dbba.getConn().prepareStatement(sqlBefehl);
                pstmt.setString(1, this.tabellenDefinition[1][1]);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                Wz.fehler("Daten von " + tabellenDefinition[1][1] + "können nicht in die Kundendatenbank geschrieben werden ");
                e.printStackTrace();
            }
        } else {
            sqlBefehl += "id, name) VALUES (?, ?)";
            try {
                PreparedStatement pstmt = Var.dbba.getConn().prepareStatement(sqlBefehl);
                pstmt.setString(1, this.tabellenDefinition[0][1]);
                pstmt.setString(2, this.tabellenDefinition[1][1]);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                Wz.fehler("Daten von " + tabellenDefinition[1][1] + "können nicht in die Kundendatenbank geschrieben werden ");
                e.printStackTrace();
            }
        }
    }

    public DbKunde[] kundeAusTabelle () {
        List<DbKunde> ergebnis = new ArrayList<DbKunde>();
        String sqlBefehl = "SELECT * FROM Kunde;";
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            while (rs.next()) {
                DbKunde tempKunde = new DbKunde();
                tempKunde.id = rs.getInt("id");
                tempKunde.name = rs.getString("name");
                tempKunde.tabellenDefinition[0][1] = String.valueOf(tempKunde.id);
                tempKunde.tabellenDefinition[1][1] = tempKunde.name;
                ergebnis.add(tempKunde);
            }
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler(sqlBefehl + "\nkonnte nicht ausgeführt werden\nSiehe Konsolenausgabe");
            e.printStackTrace();
        }
        DbKunde[] tempKunde = new DbKunde[ergebnis.size()];
        ergebnis.toArray(tempKunde);
        return (tempKunde);
    }

    public int nameInId () {
        String sqlBefehl = "SELECT id FROM Kunde WHERE name = \"" + this.name + "\";";
        try {
            Statement stmt = Var.dbba.getConn().createStatement();
            ResultSet rs =  stmt.executeQuery(sqlBefehl);
            return rs.getInt("id");
        } catch (SQLException e) {
            Wz.fehler(sqlBefehl + "\nkonnte nicht ausgeführt werden\nSiehe Konsolenausgabe");
            e.printStackTrace();
        }
        return -1;
    }
}

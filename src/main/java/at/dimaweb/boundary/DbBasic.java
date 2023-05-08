package at.dimaweb.boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import at.dimaweb.control.Wz;
import lombok.Data;

@Data
public class DbBasic {

    private String dbName = "jdbc:sqlite:";
    private Connection conn = null;
    
    public DbBasic(String dbname) {
        this.dbName += dbname;
        try {
            Class.forName("org.sqlite.JDBC"); 
        } catch (ClassNotFoundException e) {
            Wz.fehler("Fehler beim Laden des JDBC Treibers zum Erstellen der Datenbank");
            e.printStackTrace();
        }
        try {
            this.conn = DriverManager.getConnection(this.dbName);
        } catch (SQLException e1) {
            Wz.fehler("Verbindung zur Datenbank " + this.dbName + " kann nicht hergestellt werden");
            e1.printStackTrace();
        }
    }
    
    public void erstelleTabelle (String tabellenName, String[][] tabelle) {
        String sqlBefehl ="CREATE TABLE IF NOT EXISTS " + tabellenName + " (";
        for (int i = 0; i < tabelle.length; i++) {
            sqlBefehl += tabelle[i][0] + " " + tabelle[i][2];
            if (tabelle[i][3].equals("ja")) {
                sqlBefehl += " PRIMARY KEY";
            }
            if (tabelle[i][4].equals("ja")) {
                sqlBefehl += " AUTOINCREMENT";
            }
            if (i == tabelle.length-1) {
                // anzahlFopreignKeys zählt wie viele Foreign Keyss vergeben werden, damit nachher beim Anlegen des SQLbefehls bekannt ist, 
                // nach dem wievielten Foreignkey der Befehl mit ) geschlossen werden kann.
                int anzahlForeignKeys = 0;
                for (int k = 0; k < tabelle.length; k++) {
                    if ((tabelle[k][5] != null) && (tabelle[k][6] != null)) {
                        anzahlForeignKeys++;
                    }
                }
                int foreignKey = 1;
                for (int j = 0; j < tabelle.length; j++) {
                    if ((tabelle[j][5] != null) && (tabelle[j][6] != null)) {
                        if (foreignKey < anzahlForeignKeys) {
                            sqlBefehl += ", FOREIGN KEY (" + tabelle[j][0] + ") REFERENCES " + tabelle[j][5] + "(" + tabelle[j][6] + ")";
                        } else if (foreignKey == anzahlForeignKeys) {
                            sqlBefehl += ", FOREIGN KEY (" + tabelle[j][0] + ") REFERENCES " + tabelle[j][5] + "(" + tabelle[j][6] + ") ";
                        } else {
                            sqlBefehl += ")";
                        }
                        foreignKey++;
                    }
                }
            } else {
                sqlBefehl += ", ";
            }
        }
        sqlBefehl += ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sqlBefehl);
            stmt.close();
        } catch (SQLException e) {
            Wz.fehler("Tabelle " + tabellenName + " konnte nicht erzeugt werden. Siehe DbBasic.java - erstelleTabelle.");
            e.printStackTrace();
        }
    }

}

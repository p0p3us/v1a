package at.dimaweb.control;

import java.awt.Toolkit;

import at.dimaweb.boundary.DbBasic;
import at.dimaweb.boundary.Hauptschirm;
import at.dimaweb.entity.DbKunde;
import javafx.geometry.Insets;

public class Var {
    // Hauptschirm für generellen Zugang erstellen
    public static Hauptschirm hs = new Hauptschirm();

    // Datenbank
    public static DbBasic dbba = new DbBasic("dimaweb_v1.db");

    // augewählter Kunde
    public static DbKunde kd = new DbKunde();

    // Pfad und Dateiname des Logos im Hauptfenster
    public static String logo = "/at/dimaweb/dimaweb.png";

    // Bildschirmgröße 
    public static double breite = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static double hoehe = Toolkit.getDefaultToolkit().getScreenSize().height;

    // Grafische Elemente
    public static double hlSize = 48;
    public static String hlFont = "CASTELLAR";

    public static double footerSize = 18;
    public static String footerFont = "CASTELLAR";

    public static Insets vbPadding = new Insets(25);
    public static double vbSpacing = 25;

    public static Insets hbPadding = new Insets(50);
    public static double hbSpacing = breite*0.1;

    public static Insets lblPadding = new Insets(10);
    public static double lblSpacing = breite*0.01;

    public static Insets gpPadding = new Insets(10);
    public static double gpHgap = 25;
    public static double gpVgap = 25;

    //alle Datenblätter
    public static String [][] alleDatenblaetter = {
        {"ia", "Internetanbindung"},
        {"dv", "Domainverwaltung"},
        {"ws", "Webspace / FTP / CMS-Verwaltung"},
        {"ex", "E-Mail Verwaltung (Exchange)"},
        {"wm", "E-Mail Verwaltung (Webmail)"},
        {"hw", "Hardwareverwaltung"},
        {"ss", "Serversystem"},
        {"sc", "Serversystem Clients"},
        {"sw", "Softwareverwaltung"},
        {"vu", "Videoüberwachung"},
        {"if", "Infopoints"},
        {"ip", "IP-Range (Vergabe)"},
        {"sh", "Sicherheitshinweis"}
    };
}

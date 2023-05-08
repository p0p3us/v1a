package at.dimaweb.entity;

import javafx.beans.property.SimpleStringProperty;

// wird benötigt um im Hauptschirm links die Tableview mit den verschiedenen Datenblättern anzuzeigen
public class PropDatenblatt {
    private SimpleStringProperty abr;
    private SimpleStringProperty name;

    public PropDatenblatt () {
        this.abr = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }

    public String getAbr() { 
        return abr.get();
    }

    public void setAbr(String abrInput) { 
        abr.set(abrInput);
    }

    public SimpleStringProperty abrProperty() {
        return abr;
    }

    public String getName() { 
        return name.get();
    }

    public void setName(String nameInput) { 
        name.set(nameInput);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

}

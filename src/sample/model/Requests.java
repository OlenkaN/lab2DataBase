package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Requests {
    private IntegerProperty yearProperty;
    private IntegerProperty volumeProperty;
    private StringProperty nameProperty;
    private StringProperty surnameProperty;

    public void setYearProperty(int yearProperty) {
        this.yearProperty.set(yearProperty);
    }

    public void setVolumeProperty(int volumeProperty) {
        this.volumeProperty.set(volumeProperty);
    }

    public int getYearProperty() {
        return yearProperty.get();
    }

    public IntegerProperty yearPropertyProperty() {
        return yearProperty;
    }

    public int getVolumeProperty() {
        return volumeProperty.get();
    }

    public IntegerProperty volumePropertyProperty() {
        return volumeProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public void setSurnameProperty(String surnameProperty) {
        this.surnameProperty.set(surnameProperty);
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public String getSurnameProperty() {
        return surnameProperty.get();
    }

    public StringProperty surnamePropertyProperty() {
        return surnameProperty;
    }

    public Requests() {
        this.volumeProperty = new SimpleIntegerProperty();
        this.yearProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.surnameProperty = new SimpleStringProperty();
    }
}

package sample.model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Brands {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private IntegerProperty yearProperty;

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public void setYearProperty(int yearProperty) {
        this.yearProperty.set(yearProperty);
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public int getYearProperty() {
        return yearProperty.get();
    }

    public IntegerProperty yearPropertyProperty() {
        return yearProperty;
    }

    public Brands() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.yearProperty = new SimpleIntegerProperty();

    }
}

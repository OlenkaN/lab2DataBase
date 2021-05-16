package sample.model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Brands {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private IntegerProperty year_of_foundationProperty;

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public void setYearProperty(int yearProperty) {
        this.year_of_foundationProperty.set(yearProperty);
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
        return year_of_foundationProperty.get();
    }

    public IntegerProperty yearPropertyProperty() {
        return year_of_foundationProperty;
    }

    public Brands() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.year_of_foundationProperty = new SimpleIntegerProperty();

    }
}

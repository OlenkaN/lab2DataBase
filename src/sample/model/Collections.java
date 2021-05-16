package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Collections {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private IntegerProperty yearProperty;
    private IntegerProperty brandIdProperty;
    public Collections() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.yearProperty = new SimpleIntegerProperty();
        this.brandIdProperty = new SimpleIntegerProperty();
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public void setYearProperty(int yearProperty) {
        this.yearProperty.set(yearProperty);
    }

    public void setBrandIdProperty(int brandIdProperty) {
        this.brandIdProperty.set(brandIdProperty);
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

    public int getBrandIdProperty() {
        return brandIdProperty.get();
    }

    public IntegerProperty brandIdPropertyProperty() {
        return brandIdProperty;
    }


}

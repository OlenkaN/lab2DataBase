package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Products {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private IntegerProperty volumeProperty;
    private IntegerProperty collectionIdProperty;
    public Products() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.volumeProperty = new SimpleIntegerProperty();
        this.collectionIdProperty = new SimpleIntegerProperty();
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public void setVolumeProperty(int volumeProperty) {
        this.volumeProperty.set(volumeProperty);
    }

    public void setCollectionIdProperty(int collectionIdProperty) {
        this.collectionIdProperty.set(collectionIdProperty);
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

    public int getVolumeProperty() {
        return volumeProperty.get();
    }

    public IntegerProperty volumePropertyProperty() {
        return volumeProperty;
    }

    public int getCollectionIdProperty() {
        return collectionIdProperty.get();
    }

    public IntegerProperty collectionIdPropertyProperty() {
        return collectionIdProperty;
    }
}

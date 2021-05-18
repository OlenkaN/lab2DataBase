package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users_brand_roles {
    private IntegerProperty idProperty;
    private StringProperty nameUserProperty;
    private StringProperty nameRoleProperty;
    private StringProperty nameBrandProperty;
    private IntegerProperty userIdProperty;
    private IntegerProperty brandIdProperty;

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameUserProperty(String nameUserProperty) {
        this.nameUserProperty.set(nameUserProperty);
    }

    public void setNameRoleProperty(String nameRoleProperty) {
        this.nameRoleProperty.set(nameRoleProperty);
    }

    public void setNameBrandProperty(String nameBrandProperty) {
        this.nameBrandProperty.set(nameBrandProperty);
    }

    public void setUserIdProperty(int userIdProperty) {
        this.userIdProperty.set(userIdProperty);
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

    public String getNameUserProperty() {
        return nameUserProperty.get();
    }

    public StringProperty nameUserPropertyProperty() {
        return nameUserProperty;
    }

    public String getNameRoleProperty() {
        return nameRoleProperty.get();
    }

    public StringProperty nameRolePropertyProperty() {
        return nameRoleProperty;
    }

    public String getNameBrandProperty() {
        return nameBrandProperty.get();
    }

    public StringProperty nameBrandPropertyProperty() {
        return nameBrandProperty;
    }

    public int getUserIdProperty() {
        return userIdProperty.get();
    }

    public IntegerProperty userIdPropertyProperty() {
        return userIdProperty;
    }

    public int getBrandIdProperty() {
        return brandIdProperty.get();
    }

    public IntegerProperty brandIdPropertyProperty() {
        return brandIdProperty;
    }

    public Users_brand_roles() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameBrandProperty = new SimpleStringProperty();
        this.nameRoleProperty = new SimpleStringProperty();
        this.nameUserProperty = new SimpleStringProperty();
        this.userIdProperty = new SimpleIntegerProperty();
        this.brandIdProperty = new SimpleIntegerProperty();
    }
}

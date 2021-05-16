package sample.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Users {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private StringProperty surnameProperty;
    private StringProperty genderProperty;
    private ObjectProperty<LocalDateTime> birthday = new SimpleObjectProperty<>();
    private IntegerProperty userProperty;

    public Users() {
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.surnameProperty = new SimpleStringProperty();
        this.genderProperty = new SimpleStringProperty();
        this.birthday = new SimpleObjectProperty<>();
        this.userProperty = new SimpleIntegerProperty();

    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public void setSurnameProperty(String surnameProperty) {
        this.surnameProperty.set(surnameProperty);
    }

    public void setGenderProperty(String genderProperty) {
        this.genderProperty.set(genderProperty);
    }

    public LocalDateTime getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDateTime> birthdayProperty() {
        return birthday;
    }

    public void setUserProperty(int userProperty) {
        this.userProperty.set(userProperty);
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

    public String getSurnameProperty() {
        return surnameProperty.get();
    }

    public StringProperty surnamePropertyProperty() {
        return surnameProperty;
    }

    public String getGenderProperty() {
        return genderProperty.get();
    }

    public StringProperty genderPropertyProperty() {
        return genderProperty;
    }


    public int getUserProperty() {
        return userProperty.get();
    }

    public IntegerProperty userPropertyProperty() {
        return userProperty;
    }
}
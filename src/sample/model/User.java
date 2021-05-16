package sample.model;

import javafx.beans.property.IntegerProperty;

import java.time.LocalDate;

public class User {
    private Integer id=-1;
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthday;
    private Integer marriage;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getMarriage() {
        return marriage;
    }



    public Integer getId() {
        return id;
    }

    public User(String name, String surname, String gender, LocalDate birthday, String marriage, String id) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.marriage = marriage.equals("") ? null : Integer.parseInt(marriage);
        this.id = id.equals("")? null : Integer.parseInt(id);;
    }

    public User(String name, String surname, String gender, LocalDate birthday, String marriage) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.marriage = marriage.equals("") ? null : Integer.parseInt(marriage);
    }
    public boolean userHaveEmptyFields()
    {
        return name.equals("")||surname.equals("")||gender.equals("")||birthday==null||id==null;
    }
}

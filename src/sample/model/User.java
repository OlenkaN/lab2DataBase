package sample.model;

import java.time.LocalDate;

public class User {
    private String name;

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

    public Integer getMarrige() {
        return marrige;
    }

    private String surname;
    private String gender;
    private LocalDate birthday;
    private Integer marrige;

    public User(String name, String surname, String gender, LocalDate birthday, String marrige) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.marrige = marrige.equals("") ? null : Integer.parseInt(marrige);
    }
    public boolean userHaveEmptyFields()
    {
        return name.equals("")||surname.equals("")||gender.equals("")||birthday==null;
    }
}

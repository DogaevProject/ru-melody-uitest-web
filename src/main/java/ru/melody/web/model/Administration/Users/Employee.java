package ru.melody.web.model.Administration.Users;

/**
 * Модель объекта системы - Пользователь
 */
public class Employee {

    private String fullName;
    private String lastName;
    private String name;
    private String patronymic;
    private Sex sex;
    private String birthDate;
    private String loginName;
    private String password;
    private String email;

    /**
     * Полное имя пользователя (ФИО)
     */
    public String getFullName() {
        return fullName;
    }

    public Employee setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Фамилия
     */
    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Имя
     */
    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Отчество
     */
    public String getPatronymic() {
        return patronymic;
    }

    public Employee setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    /**
     * Мужик ли?
     */
    public Sex getSex() {
        return sex;
    }

    public Employee setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    /**
     * Дата рождения
     */
    public String getBirthDate() {
        return birthDate;
    }

    public Employee setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Имя пользователя (login)
     */
    public String getLoginName() {
        return loginName;
    }

    public Employee setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    /**
     * Пароль
     */
    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Email
     */
    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "lastName='" + lastName + '\'' +
                ", name=" + name +
                ", patronymic=" + patronymic +
                "}";
    }

}

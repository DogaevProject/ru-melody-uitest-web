package ru.melody.web.model.Administration.Fields.Settings;

/**
 * Перечисления настроек полей;
 */
public enum SettingsForFields {

    VALUE_IS_NOT_DEFINED("Значение не задано"), NO("Нет"), YES("Да");

    private String nameOfTheEnumerationValues;

    SettingsForFields(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }
}


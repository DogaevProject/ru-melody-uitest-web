package ru.melody.web.model.Administration.Fields.TypesOfFields;

import ru.melody.web.model.Administration.Fields.Settings.SettingsForFields;

/**
 * Модель объекта системы - Тип поля "Строка" ссылка на пользователя
 */
public class TypeListFieldsStringWithListEmployers extends FieldObject {

    private SettingsForFields listChoice;
    private String valuesList = "";


    /**
     * Нужен ли выбор из списка
     */
    public SettingsForFields getIsListChoice() {
        return listChoice;
    }

    public TypeListFieldsStringWithListEmployers setIsListChoice(SettingsForFields listChoice) {
        this.listChoice = listChoice;
        return this;
    }

    /**
     * Поле ввода значений для выбора из списка (Список значений)
     */
    public String getValuesList() {
        return valuesList;
    }

    public TypeListFieldsStringWithListEmployers setValuesList(String valuesList) {
        this.valuesList = valuesList;
        return this;
    }

}


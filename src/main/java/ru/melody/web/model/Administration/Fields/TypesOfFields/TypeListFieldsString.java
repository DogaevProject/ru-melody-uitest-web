package ru.melody.web.model.Administration.Fields.TypesOfFields;

import ru.melody.web.model.Administration.Fields.Settings.SettingsForFields;

/**
 * Модель объекта системы - Тип поля "Строка"
 */
public class TypeListFieldsString extends FieldObject {

    private SettingsForFields listChoice;
    private String[] valuesList;


    /**
     * Нужен ли выбор из списка
     */
    public SettingsForFields getIsListChoice() {
        return listChoice;
    }

    public TypeListFieldsString setIsListChoice(SettingsForFields listChoice) {
        this.listChoice = listChoice;
        return this;
    }

    /**
     * Доступные значения для выбора из списка (Список значений)
     */
    public String[] getValuesList() {
        return valuesList;
    }

    public TypeListFieldsString setValuesList(String[] valuesList) {
        this.valuesList = valuesList;
        return this;
    }

}


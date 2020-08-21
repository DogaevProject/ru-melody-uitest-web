package ru.melody.web.model.Administration.Fields.TypesOfFields;


/**
 * Модель объекта системы - Тип поля текст
 */
public class TypeListFieldsText extends FieldObject  {

    public boolean isResizable() {
        return resizable;
    }

    public TypeListFieldsText setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    private boolean resizable; // авторасширяемое при вводе

}

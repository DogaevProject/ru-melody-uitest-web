package ru.melody.web.model.Administration.Fields.TypesOfFields;



/**
 * Модель объекта - Поле
 */
public class FieldObject {

    private String fieldName = "";
    private boolean editable = true;
    private FieldObject field;
    private boolean multipleChoice;
    private boolean hasLink; // запись в поле имеет ссылку, нужно для выбора локатора в поле типа "Строка"
    private String valueField;
    private String[] listValueField;
    private boolean valueBooleanField;

    /**
     * Название поля
     */
    public String getFieldName() {
        return fieldName;
    }

    public FieldObject setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Тип поля
     */
    public FieldObject getFieldType() {
        return field;
    }

    public FieldObject setFieldType(FieldObject field) {
        this.field = field;
        return this;
    }

    /**
     * Мн. выбор
     */
    public boolean isMultipleChoice() {
        return multipleChoice;
    }

    public FieldObject setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
        return this;
    }

    /**
     * Запись в поле имеет ссылку
     *
     */
    public boolean isHasLink() {
        return hasLink;
    }

    public FieldObject setHasLink(boolean hasLink) {
        this.hasLink = hasLink;
        return this;
    }


    /**
     * Значение поля - Строка
     */
    public String getValueField() {
        return valueField;
    }

    public FieldObject setValueField(String valueField) {
        this.valueField = valueField;
        return this;
    }


    /**
     * Значение поля - Список строк
     */
    public String[] getListValueField() {
        return listValueField;
    }

    public FieldObject setListValueField(String[] listValueField) {
        this.listValueField = listValueField;
        return this;
    }


    /**
     * Значение поля  - тип Логический
     */
    public boolean getValueBooleanField() {
        return valueBooleanField;
    }

    public FieldObject setValueBooleanField(Boolean valueBooleanField) {
        this.valueBooleanField = valueBooleanField;
        return this;
    }


    /**
     * Доступно для редактирования
     *
     * @return
     */
    public boolean isEditable() {
        return editable;
    }

    public FieldObject setEditable(boolean editable) {
        this.editable = editable;
        return this;
    }
}

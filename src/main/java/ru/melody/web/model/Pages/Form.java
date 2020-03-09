package ru.melody.web.model.Pages;

import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;


/**
 * Модель объекта - Форма
 */
public class Form {

    private FieldObject[] fields; // Полный список полей которые находятся в форме
    private FieldObject[] fieldsForAddValue; // Поля в которые нужно добавлять значения
    private FieldObject[] fieldsForDeleteValue; // Поля в которых нужно удалять значения
    private String[] valueFiles; // Файлы прикрепленные к объекту
    private Operation[] operation;
    private RouteScheme routeScheme;
    private ru.melody.web.model.LocationOfElement.Dialog.Form LocationForm; // расположение формы

    /**
     * Название по которому можно открыть документ в гриде.
     * <p>
     * Если открываем через openItemInGridInNewWindow, то это название должно быть ссылкой.
     * Если открываем через двойной клик в текущем окне - openItemInGridInCurrentWindow - то любой уникальный текст для данной записи грида
     */
    private String nameOfObjectForOpenInTheGrid;


    public String getNameOfObjectForOpenInTheGrid() {
        return nameOfObjectForOpenInTheGrid;
    }

    /**
     * Название по которому можно открыть документ в гриде.
     * <p>
     * Если открываем через openItemInGridInNewWindow, то это название должно быть ссылкой.
     * Если открываем через двойной клик в текущем окне - openItemInGridInCurrentWindow - то любой уникальный текст для данной записи грида
     */
    public Form setNameOfObjectForOpenInTheGrid(String nameOfObjectForOpenInTheGrid) {
        this.nameOfObjectForOpenInTheGrid = nameOfObjectForOpenInTheGrid;
        return this;
    }

    public FieldObject[] getFields() {
        return fields;
    }

    public Form setFields(FieldObject[] fields) {
        this.fields = fields;
        return this;
    }


    public FieldObject[] getFieldsForAddValue() {
        return fieldsForAddValue;
    }

    public Form setFieldsForAddValue(FieldObject[] fieldsForAddValue) {
        this.fieldsForAddValue = fieldsForAddValue;
        return this;
    }

    public FieldObject[] getFieldsForDeleteValue() {
        return fieldsForDeleteValue;
    }

    public Form setFieldsForDeleteValue(FieldObject[] fieldsForDeleteValue) {
        this.fieldsForDeleteValue = fieldsForDeleteValue;
        return this;
    }

    public String[] getValueFiles() {
        return valueFiles;
    }

    public Form setValueFiles(String[] valueFiles) {
        this.valueFiles = valueFiles;
        return this;
    }


    public Operation[] getOperation() {
        return operation;
    }

    public Form setOperation(Operation[] operation) {
        this.operation = operation;
        return this;
    }


    public ru.melody.web.model.LocationOfElement.Dialog.Form getFormLocation() {
        return LocationForm;
    }

    public Form setFormLocation(ru.melody.web.model.LocationOfElement.Dialog.Form page) {
        this.LocationForm = page;
        return this;
    }

    /**
     * Маршрутная схема
     */
    public RouteScheme getRouteScheme() {
        return routeScheme;
    }

    public Form setRouteScheme(RouteScheme routeScheme) {
        this.routeScheme = routeScheme;
        return this;
    }

}

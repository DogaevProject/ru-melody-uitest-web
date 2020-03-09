package ru.melody.web.elements.FieldsVerifications;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.FieldsVerifications;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

/**
 * Локаторы необходимые методам проверки значений полей
 * В подклассах реализация конкретных страниц
 */
public abstract class ValuesOfFieldsVerifications extends FieldsVerifications {

    public abstract SelenideElement getInputStringField(String fieldName);

    public abstract SelenideElement getInputStringChoiceInListField(String fieldName);

    public abstract ElementsCollection getMultipleInputStringChoiceInListField(String fieldName);

    public abstract SelenideElement getInputStringChoiceInListEmployers(String fieldName);

    public abstract ElementsCollection getMultipleInputStringChoiceInListEmployers(String fieldName);

    /**
     * Поле типа "Текст" - доступно для редактирования
     *
     */
    public abstract SelenideElement getInputText(String fieldName);

    /**
     * Поле типа "Текст" - недоступно для редактирования
     * недоступное для редактирования имеет другой локатор
     *
     */
    public abstract SelenideElement getInputTextNotEditable(String fieldName);

}

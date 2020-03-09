package ru.melody.web.elements.FieldsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/**
 * Локаторы необходимые методам проверки полей
 * В подклассах реализация конкретных страниц
 */
public abstract class FieldsVerifications {
    public abstract ElementsCollection getElementsOfNamesFields();

    public abstract SelenideElement getElementsOfNameField(String fieldName);
}

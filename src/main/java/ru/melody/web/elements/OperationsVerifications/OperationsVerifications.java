package ru.melody.web.elements.OperationsVerifications;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.page;

/**
 * Локаторы необходимые методам проверки операций
 * В подклассах реализация конкретных страниц
 */
public abstract class OperationsVerifications {

    public abstract SelenideElement getOperationOnToolbarByName(String name);

    public abstract SelenideElement getOperationAddFile();

    public abstract SelenideElement getOperationAddition();

    public abstract ElementsCollection getAllOperationOnToolbar();

    public abstract SelenideElement getToolbar();

    public abstract SelenideElement getMaskOfToolbar();
}

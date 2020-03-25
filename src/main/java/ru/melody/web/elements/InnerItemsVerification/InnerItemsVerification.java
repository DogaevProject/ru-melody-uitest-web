package ru.melody.web.elements.InnerItemsVerification;


import com.codeborne.selenide.ElementsCollection;

/**
 * Локаторы необходимые методам проверки вложенных объектов/записей
 * В подклассах реализация конкретных страниц
 */
public abstract class InnerItemsVerification {

    public abstract ElementsCollection getElementsOfInnerItems();

}

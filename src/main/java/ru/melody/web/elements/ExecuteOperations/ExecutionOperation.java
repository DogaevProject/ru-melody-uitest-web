package ru.melody.web.elements.ExecuteOperations;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Локаторы необходимые методам выполнения различных операций на страницах
 * В подклассах реализация конкретных страниц
 */
public abstract class ExecutionOperation {

    public abstract SelenideElement clickButton(String nameOfOperation);

    public abstract SelenideElement clickButtonAddFile();

    public abstract SelenideElement selectTab(String nameOfTab);

    public abstract SelenideElement selectItem(String nameOfItem);

    /**
     * Поле «Вы работаете за:»
     */
    public abstract SelenideElement inputInDeputyField();

}

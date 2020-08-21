package ru.melody.web.elements.FillFields;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Локаторы необходимые методам заполнения полей
 * В подклассах реализация конкретных страниц
 */
public abstract class FillFields {

    public abstract SelenideElement getInputStringField(String fieldName);

    public abstract SelenideElement getInputStringChoiceInListField(String fieldName);

    /**
     * Поле ввода в контексном поиске в строковом поле. Подходит для всех форм.
     */
    public SelenideElement getInputContextSearchInStringField() {
        return $(By.xpath("//div[@class=\"x-container x-layer x-container-default x-border-box x-box-layout-ct\" and not(contains(@style,\"display: none\"))]//input[@class=\"x-form-field x-form-text x-form-text-default  x-form-empty-field x-form-empty-field-default x-form-focus x-field-form-focus x-field-default-form-focus\"]"));
    }

    /**
     * Запись в списке контексного поиска в строковом поле. Подходит для всех форм.
     */
    public SelenideElement getItemInListOfContextSearchInStringField(String value) {
        return $(By.xpath("//div[contains(@class,\"x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box\") and not(contains(@style,\"display: none\"))]//div[contains(text(),'" + value + "')] "));

    }

    public abstract SelenideElement getTextArea(String fieldName);

    public abstract SelenideElement getTextBox(String fieldName);

    public abstract SelenideElement getCheckBox(String fieldName);

    public abstract SelenideElement getAddButtonFile(String fieldName); //кнопка добавления файла в поле типа Файл

}

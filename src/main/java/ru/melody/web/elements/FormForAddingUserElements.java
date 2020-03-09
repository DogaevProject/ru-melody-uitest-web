package ru.melody.web.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Форма добавления пользователей
 *
 */
public class FormForAddingUserElements {

    /**
     * Поле поиска
     */
    public SelenideElement getInputForSearchUsers() {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//input[contains(@id,\"mainsearchfield\")] "));
    }

    /**
     * кнопка "Поиск"
     */
    public SelenideElement getSearchButton() {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//div[contains(@class,\"x-form-trigger x-form-trigger-default m4-search-field-trigger\") and not(contains(@style,\"display:none\"))]"));
    }

    /**
     * Набор элементов = кол-во найденных пользователей в списке формы добавления пользователей
     */
    public ElementsCollection getListOfUsers() {
        return $$(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//div[@class=\"x-grid-scrollbar-clipper \" and not(contains(@style,\"hidden\"))]//div[@class=\"x-grid-view x-grid-with-row-lines x-fit-item x-grid-view-default x-scroller\"]//table"));
    }

    /**
     * запись с именем пользователя в списке среди найденных пользователей
     */
    public SelenideElement getUserFromList(String lastName) {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//div[@class=\"x-grid-scrollbar-clipper \" and not(contains(@style,\"hidden\"))]//div[@class=\"x-grid-view x-grid-with-row-lines x-fit-item x-grid-view-default x-scroller\"]//table//span[contains(text(),'" + lastName + "')]"));
    }

    /**
     *  запись с именем пользователя в списке среди добавленных пользователей
     */
    public SelenideElement getAddedUserFromList(String lastName) {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//div[@class=\"x-panel-body x-grid-with-row-lines x-grid-body x-panel-body-default x-panel-body-default x-noborder-rbl\" and not(contains(@style,\"hidden\"))]//div[@class=\"x-grid-view x-grid-with-row-lines x-fit-item x-grid-view-default x-scroller\"]//table//div[contains(text(),'" + lastName + "')]"));
    }

    /**
     * Кнопка "Выбрать"
     */
    public SelenideElement getButtonSelectUsers() {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//span[contains(text(),'Выбрать')]"));
    }




}

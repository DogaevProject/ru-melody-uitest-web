package ru.melody.web.elements;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Формы - общие элементы для всех форм
 */
public class FormElements {

    /**
     * Маска загрузки
     */
    public SelenideElement getMaskOfLoading() {
        return $(By.xpath("//div[@class=\"x-mask x-border-box x-mask-fixed\"]//*[@class=\"x-mask-msg-text\"]"));
    }

    /**
     * Хедер формы с текстом названия формы
     */
    public SelenideElement getHeaderWithTextNameOfForm(String text) {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box x-window-maximized\")]//div[@class=\"x-title-text x-title-text-default x-title-item\" and contains(text(),'" + text + "')]"));
    }

    /**
     * Вкладка
     */
    public SelenideElement getTab(String nameOfTab) {
        return $(By.xpath("//span[contains(text(),'" + nameOfTab + "')]/ancestor::a[contains(@class,\"x-tab x-unselectable x-box-item x-tab-default\")]"));
    }


    /**
     * Вкладка открытая в данный момент
     */
    public SelenideElement getActiveTab(String nameOfTab) {
        return $(By.xpath("//span[contains(text(),'" + nameOfTab + "')]/ancestor::a[contains(@class,\"x-tab-active\")]"));
    }

    /**
     * Элемент последнего диалога - окно развернуто на весь экран (используется для построения локаторов)
     */
    public String getElementOfLastMaximizedDialog() {
        return "(//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\")])[last()]";
    }

    /**
     * Элемент активной вкладки
     * Используется для подстановки в локатор, который требуется для работы с полями находящимися только на открытой в данный момент вкладке
     */
    public String getElementOfActiveTabInLastMaximizedDialog() {
        if ($(By.xpath(getElementOfLastMaximizedDialog() + "//div[@class=\"x-panel x-tabpanel-child x-panel-default\" and @aria-hidden=\"false\"]")).exists()) {
            // если вкладка есть, то передаем ее для подстановки в локатор
            return "//div[@class=\"x-panel x-tabpanel-child x-panel-default\" and @aria-hidden=\"false\"]";
        } else return "";
    }

    /**
     * Элемент Поля поиска
     * - нужно работать с элементом, который является последним среди всех аналогичных полей открытых ранее в формах, т.к ранее открытые элементы могут оставаться в DOM
     * - Для получения элемента Поля поиска на тулбаре с вложенными объектами - нужно в локаторе использовать Элемент активной вкладки
     *
     *  Метод получения элемента актуален только, если поле поиска одно на странице. Если будет два поля, то нужно реализовывать элементы для конкретных тулбаров в классе ExecutionOperation
     */
    public SelenideElement getElementOfLastInputForSearch() {
        return $(By.xpath("(" + getElementOfActiveTabInLastMaximizedDialog() + "//input[contains(@id,\"mainsearchfield\")])[last()]"));
    }

}

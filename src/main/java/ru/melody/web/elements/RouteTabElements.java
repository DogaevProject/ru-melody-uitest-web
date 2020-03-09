package ru.melody.web.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Элементы - форма Создания документа (Документы / Создать документ) - вкладка - Маршруты
 */
public class RouteTabElements {

    /**
     * Добавить пользователя
     */
    public SelenideElement getAddAUserToBlockDiagram(String nameItem) {
        return $(By.xpath("//span[@class=\"x-tree-node-text \" and text()='"+ nameItem +"']/ancestor::table//img[@data-qtip=\"Добавить пользователя\"]"));
    }

    /**
     * Добавить пользователя во вложенный блок - Последовательно
     */
    public SelenideElement getAddAUserToInnerBlockTypeSequence(String nameItem) {
        return $(By.xpath("//span[@class=\"x-tree-node-text \" and text()='"+ nameItem +"']/ancestor::table/following::table//span[@class=\"x-tree-node-text \" and text()=\"Последовательно\"]/ancestor::table//img[@data-qtip=\"Добавить пользователя\"]"));
    }

    /**
     * Добавить пользователя во вложенный блок - Параллельно
     */
    public SelenideElement getAddAUserToInnerBlockTypeParallel(String nameItem) {
        return $(By.xpath("//span[@class=\"x-tree-node-text \" and text()='"+ nameItem +"']/ancestor::table/following::table//span[@class=\"x-tree-node-text \" and text()=\"Параллельно\"]/ancestor::table//img[@data-qtip=\"Добавить пользователя\"]"));
    }

    /**
     * Удалить пользователя
     */
    public SelenideElement getDeleteAUserFromBlockDiagram(String nameItem) {
        // TODO
        return $(By.xpath(""));
    }

    /**
     * Добавить подуровень
     */
    public SelenideElement getAddInnerBlockToDiagram(String nameItem) {
        return $(By.xpath("//span[@class=\"x-tree-node-text \" and text()='"+ nameItem +"']/ancestor::table//img[@data-qtip=\"Добавить узел\"]"));
    }

    /**
     * Тип Добавляемого узла - Параллельно
     */
    public SelenideElement getTypeParallelForNewInnerBlock() {
        return $(By.xpath("//div[@class=\"x-menu x-layer x-menu-default x-border-box\" and not(contains(@style,\"visibility: hidden\"))]//span[text()=\"Параллельно\"]"));
    }

    /**
     * Тип Добавляемого узла - Последовательно
     */
    public SelenideElement getTypeSequenceForNewInnerBlock() {
        return $(By.xpath("//div[@class=\"x-menu x-layer x-menu-default x-border-box\" and not(contains(@style,\"visibility: hidden\"))]//span[text()=\"Последовательно\"]"));
    }


    /**
     * Длительность рассмотрения
     */
    public SelenideElement getReviewDuration() {
        // TODO
        return $(By.xpath(""));

    }

    /**
     * Поле ввода - Длительность рассмотрения
     */
    public SelenideElement getInputReviewDuration() {
        // TODO
        return $(By.xpath(""));
    }

}

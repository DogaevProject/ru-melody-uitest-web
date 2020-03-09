package ru.melody.web.elements;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Элементы на странице гридов ПМ
 */
public class FoldersElements {

    /**
     * Элемент для выбора главного ПМ
     */
    public SelenideElement getMainItemOfMenuTree(String nameOfFolder) {
        return $(By.xpath("//*[contains(@class,\"x-menu\")]//*[text() ='" + nameOfFolder + "']"));

    }

    /**
     * Элемент для открытия главного ПМ
     */
    public SelenideElement getTriggerForOpenMainItemOfMenuTree(String nameOfFolder) {
        return $(By.xpath("//tr[@class=\"  x-grid-row\"]//*[contains(@class,\"x-menu\")]//*[text()='" + nameOfFolder + "']/ancestor::div[@class=\"x-menu-item-wrapper\"]//div[@class=\"x-menu-block  x-tree-elbow-img x-tree-elbow-plus x-tree-expander\"]"));
    }

    /**
     * Элемент для закрытия главного ПМ
     */
    public SelenideElement getTriggerForCloseMainItemOfMenuTree(String nameOfFolder) {
        return $(By.xpath("//tr[@class=\"x-grid-tree-node-expanded  x-grid-row\"]//*[contains(@class,\"x-menu\")]//*[text()='" + nameOfFolder + "']/ancestor::div[@class=\"x-menu-item-wrapper\"]//div[@class=\"x-menu-block  x-tree-elbow-img x-tree-elbow-plus x-tree-expander\"]"));
    }

    /**
     * Элемент для  выбора вложенного ПМ
     */
    public SelenideElement getChildItemOfMenuTree(String nameOfParentFolder, String nameOfChildFolder) {
        return $(By.xpath("//*[text() ='" + nameOfParentFolder + "']/ancestor::table/following-sibling::table//*[text()='" + nameOfChildFolder + "']"));

    }

    /**
     * Элемент для открытия объекта в гриде ПМ
     * Используется для перехода в картчоку объекта
     *
     * @param nameOfItem отображаемое название объекта
     * @return
     */
    public SelenideElement getHrefOfItemInTheGrid(String nameOfItem) {
        return $(By.xpath("//a[contains(@href,'/open') and text()='" + nameOfItem + "']"));
    }

    /**
     * Элемент для открытия объекта в гриде ПМ
     * Используется для перехода в картчоку объекта в текущем окне
     *
     * @param nameOfItem отображаемое название объекта
     */
    public SelenideElement getItemInTheGrid(String nameOfItem) {
        return $(By.xpath("//*[text()='" + nameOfItem + "']//ancestor::td"));
    }

    /**
     * Элемент для выбора объекта в гриде ПМ
     * Используется для отображения списка операций доступных для этого объекта
     *
     * @param nameOfItem - отображаемое в гриде название объекта.
     *                                     Это м.б любой текст по которому можно отличить нужный объект от остальных.     *
     */
    public SelenideElement getItemInTheGridForSelect(String nameOfItem) {
        return $(By.xpath("//*[text()='" + nameOfItem + "']//ancestor::table"));
    }


    /**
     * Набор видимых объектов (документы) в гриде ПМ
     */
    public ElementsCollection getAllItemsInTheGridOfFolder() {
        return $$(By.xpath("//a[contains(@href,'/open')]//ancestor::table"));
    }


}

package ru.melody.web.elements.OperationsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class OperationsVerificationsOnTabWithInnerObjectsInMaximizedDialog extends OperationsVerifications {

    private FormElements formElements = page(FormElements.class);

    @Override
    public SelenideElement getOperationOnToolbarByName(String name) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//a[contains(@class,\"x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[contains(@class,\"x-btn-button-left\")]" +
                "//span[@data-ref=\"btnInnerEl\" and text()='" + name + "']"));
    }


    @Override
    public SelenideElement getOperationAddFile() {
        return null;
    }

    @Override
    public SelenideElement getOperationAddition() {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//a[contains(@class,\"x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[contains(@class,\"x-btn-button-left\")]" +
                "//span[@style=\"background-image:url(../icons/ext/main/additional.svg);\"]" +
                "/following-sibling::span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public ElementsCollection getAllOperationOnToolbar() {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//a[contains(@class,\"x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[contains(@class,\"x-btn-button-left\")]" +
                "//span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public SelenideElement getToolbar() {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-default x-docked-top x-toolbar-docked-top x-toolbar-default-docked-top x-box-layout-ct x-noborder-trl\") and not(contains(@class,\"masked\"))]"));

    }

    @Override
    public SelenideElement getMaskOfToolbar() {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-default x-docked-top x-toolbar-docked-top x-toolbar-default-docked-top x-box-layout-ct x-noborder-trl\") and contains(@class,\"masked\")]"));
    }
}

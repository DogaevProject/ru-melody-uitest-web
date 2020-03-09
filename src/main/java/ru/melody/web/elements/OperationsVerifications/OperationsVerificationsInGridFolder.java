package ru.melody.web.elements.OperationsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OperationsVerificationsInGridFolder extends OperationsVerifications {

    @Override
    public SelenideElement getOperationOnToolbarByName(String name) {
        return $(By.xpath("//div[@class=\"x-panel-body x-panel-body-default x-panel-body-default x-noborder-trbl\"]" +
                "//a[contains(@class,\"x-btn x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"btnInnerEl\" and text()='" + name + "']"));
    }

    @Override
    public SelenideElement getOperationAddFile() {
        return null;
    }

    @Override
    public SelenideElement getOperationAddition() {
        return $(By.xpath("//a[contains(@class,\"x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[@style=\"background-image:url(../icons/ext/main/additional.svg);\"]" +
                "/following-sibling::span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public ElementsCollection getAllOperationOnToolbar() {
        return $$(By.xpath("//div[@class=\"x-panel-body x-panel-body-default x-panel-body-default x-noborder-trbl\"]" +
                "//div[@class=\"x-panel x-border-item x-box-item x-panel-default\" and not(contains(@id,'grid'))]" +
                "//a[contains(@class,\"x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]//span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public SelenideElement getToolbar() {
        return $(By.xpath("//div[@class=\"x-panel-body x-panel-body-default x-panel-body-default x-noborder-trbl\"]" +
                "//div[@class=\"x-panel x-border-item x-box-item x-panel-default\" and not(contains(@id,'grid'))]" +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-default x-docked-top x-toolbar-docked-top x-toolbar-default-docked-top x-box-layout-ct x-noborder-trl\") and not(contains(@class,\"masked\"))]"));

    }

    @Override
    public SelenideElement getMaskOfToolbar() {
        return $(By.xpath("//div[@class=\"x-panel-body x-panel-body-default x-panel-body-default x-noborder-trbl\"]" +
                "//div[@class=\"x-panel x-border-item x-box-item x-panel-default\" and not(contains(@id,'grid'))]" +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-default x-docked-top x-toolbar-docked-top x-toolbar-default-docked-top x-box-layout-ct x-noborder-trl\") and contains(@class,\"masked\")]"));
    }
}

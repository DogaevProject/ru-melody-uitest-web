package ru.melody.web.elements.OperationsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class OperationsVerificationsInMaximizedDialog extends OperationsVerifications {

    private FormElements formElements = page(FormElements.class);


    @Override
    public SelenideElement getOperationOnToolbarByName(String name) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//a[contains(@class,\"m4-button-in-form x-unselectable\") and not(contains(@style,\"display: none\"))]//span[@data-ref=\"btnInnerEl\" and text()='" + name + "']"));
    }

    @Override
    public SelenideElement getOperationAddFile() {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//a[contains(@class,\"m4-button-in-form x-unselectable\") " +
                "and not(contains(@style,\"display: none\")) " +
                "and contains(@data-qtip,\"Добавить файл\")]" +
                "//span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public SelenideElement getOperationAddition() {
        return $(By.xpath("//div[contains(@class,\"x-window x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\") and (contains(@class,'x-window-maximized'))]" +
                "//a[contains(@class,\"m4-button-in-form x-unselectable\") " +
                "and not(contains(@style,\"display: none\"))]" +
                "//span[@style=\"background-image:url(../icons/ext/main/additional.svg);\"]" +
                "/following-sibling::span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public ElementsCollection getAllOperationOnToolbar() {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//a[contains(@class,\"m4-button-in-form x-unselectable\") and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"btnInnerEl\"]"));
    }


    @Override
    public SelenideElement getToolbar() {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-noborder-rbl\") and not(contains(@class,\"masked\"))]"));
    }

    @Override
    public SelenideElement getMaskOfToolbar() {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-default x-docked-bottom x-toolbar-docked-bottom x-toolbar-default-docked-bottom x-box-layout-ct x-noborder-rbl\") and contains(@class,\"masked\")]"));

    }
}

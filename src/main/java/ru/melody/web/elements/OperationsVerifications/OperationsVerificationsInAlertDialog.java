package ru.melody.web.elements.OperationsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Форма параметров перед созданием нового документа
 * Форма перед выполнением операции - Возврат на доработку и.т.д
 */
public class OperationsVerificationsInAlertDialog extends OperationsVerifications {
    @Override
    public SelenideElement getOperationOnToolbarByName(String name) {
        return $(By.xpath("//div[contains(@class,\"x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\") and @role=\"alertdialog\"]" +
                "//a[contains(@class,\"x-btn x-unselectable x-box-item x-toolbar-item x-btn-default-small\") and not(contains(@style,\"display: none\"))]//span[@data-ref=\"btnInnerEl\" and text()='" + name + "']"));
    }

    @Override
    public SelenideElement getOperationAddFile() {
        return null;
    }

    @Override
    public SelenideElement getOperationAddition() {
        return null;
    }

    @Override
    public ElementsCollection getAllOperationOnToolbar() {
        return $$(By.xpath("//div[contains(@class,\"x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\") and @role=\"alertdialog\"]" +
                "//a[not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"btnInnerEl\"]"));
    }

    @Override
    public SelenideElement getToolbar() {
        return $(By.xpath("//div[contains(@class,\"x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\") and @role=\"alertdialog\"]" +
                "//div[contains(@class,\"x-toolbar x-docked x-toolbar-footer x-box-layout-ct\") and not(contains(@class,\"masked\"))]"));

    }

    @Override
    public SelenideElement getMaskOfToolbar() {
        return null;
    }
}

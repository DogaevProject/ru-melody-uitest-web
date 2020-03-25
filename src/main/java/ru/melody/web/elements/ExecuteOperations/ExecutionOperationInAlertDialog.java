package ru.melody.web.elements.ExecuteOperations;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ExecutionOperationInAlertDialog extends  ExecutionOperation {

    @Override
    public SelenideElement clickButtonAddFile() {
        return null;
    }

    @Override
    public SelenideElement clickButton(String nameOfOperation) {
        return $(By.xpath("(//div[contains(@class,\"x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\")  and @role=\"alertdialog\"])[last()]" +
                "//a[contains(@class,\"x-btn x-unselectable x-box-item x-toolbar-item x-btn-default-small\") and not(contains(@style,\"display: none\"))]//span[@data-ref=\"btnInnerEl\" and text()='" + nameOfOperation + "']"));
    }

    @Override
    public SelenideElement selectTab(String nameOfTab) {
        return null;
    }

    @Override
    public SelenideElement selectItem(String nameOfItem) {
        return null;
    }

    @Override
    public SelenideElement inputInDeputyField() {
        return null;
    }
}

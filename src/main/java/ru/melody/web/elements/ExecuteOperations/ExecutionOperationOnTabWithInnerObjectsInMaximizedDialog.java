package ru.melody.web.elements.ExecuteOperations;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class ExecutionOperationOnTabWithInnerObjectsInMaximizedDialog extends  ExecutionOperation{

    private FormElements formElements = page(FormElements.class);

    @Override
    public SelenideElement clickButton(String nameOfOperation) {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//a[contains(@class,\"x-btn x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[contains(@class,\"x-btn-button-left\")]" +
                "//span[@data-ref=\"btnInnerEl\" and text()='" + nameOfOperation + "']")).last();
    }

    @Override
    public SelenideElement clickButtonAddFile() {
        return null;
    }

    @Override
    public SelenideElement selectTab(String nameOfTab) {
        return null;
    }

    @Override
    public SelenideElement selectItem(String nameOfItem) {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//*[text()='" + nameOfItem + "']//ancestor::td")).last();
    }

    @Override
    public SelenideElement inputInDeputyField() {
        return null;
    }
}

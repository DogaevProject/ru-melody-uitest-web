package ru.melody.web.elements.ExecuteOperations;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class ExecutionOperationInMaximizedDialog extends ExecutionOperation {

    private FormElements formElements = page(FormElements.class);

    @Override
    public SelenideElement clickButtonAddFile() {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//a[contains(@class,\"x-btn m4-button-in-form x-unselectable\") and not(contains(@style,\"display: none\")) and contains(@data-qtip,\"Добавить файл\")]" +
                "//span[@data-ref=\"btnIconEl\"]")).last();
    }

    @Override
    public SelenideElement clickButton(String nameOfOperation) {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//a[contains(@class,\"x-btn m4-button-in-form x-unselectable\") and not(contains(@style,\"display: none\"))]//span[@data-ref=\"btnInnerEl\" and text()='" + nameOfOperation + "']")).last();
    }

    @Override
    public SelenideElement selectTab(String nameOfTab) {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//span[contains(text(),'"+ nameOfTab +"')]/ancestor::a[contains(@class,\"x-tab x-unselectable x-box-item x-tab-default\")]")).last();
    }

    @Override
    public SelenideElement selectItem(String nameOfItem) {
        return null;
    }

    @Override
    public SelenideElement inputInDeputyField() {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-container x-box-item x-container-default x-box-layout-ct x-container-after-title\"]//input")).last();
    }

}

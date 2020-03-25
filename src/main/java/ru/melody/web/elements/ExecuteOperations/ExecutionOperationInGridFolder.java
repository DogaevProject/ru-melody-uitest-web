package ru.melody.web.elements.ExecuteOperations;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ExecutionOperationInGridFolder extends ExecutionOperation  {

    @Override
    public SelenideElement clickButton(String nameOfOperation) {
        return $(By.xpath("//div[@class=\"x-panel-body x-panel-body-default x-panel-body-default x-noborder-trbl\"]" +
                "//a[contains(@class,\"x-btn x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\") and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"btnInnerEl\" and text()='" + nameOfOperation + "']"));
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
    public SelenideElement inputInDeputyField() {
        return null;
    }

    @Override
    public SelenideElement selectItem(String nameOfItem) {
        return $(By.xpath("//*[text()='" + nameOfItem + "']//ancestor::td"));
    }
}

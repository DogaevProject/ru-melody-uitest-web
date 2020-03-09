package ru.melody.web.elements.TextVerifications;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TextMessageVerificationsInAlertDialog extends TextMessageVerifications {

    @Override
    public SelenideElement getTextMessage() {
        return $(By.xpath("//div[contains(@class,\"x-window x-message-box x-layer x-window-default x-closable x-window-closable x-window-default-closable x-border-box\") and @role=\"alertdialog\"]" +
                "//div[@class=\"x-window-body x-window-body-default x-box-layout-ct x-closable x-window-body-closable x-window-body-default-closable x-window-body-default x-window-body-default-closable x-scroller x-noborder-trbl\"]" +
                "//div[@class=\"x-component x-window-text x-box-item x-component-default\"]"));
    }
}

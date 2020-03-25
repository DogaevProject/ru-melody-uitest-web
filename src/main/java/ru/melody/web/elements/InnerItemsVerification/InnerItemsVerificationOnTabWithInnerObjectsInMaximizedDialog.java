package ru.melody.web.elements.InnerItemsVerification;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class InnerItemsVerificationOnTabWithInnerObjectsInMaximizedDialog extends InnerItemsVerification {

    private FormElements formElements = page(FormElements.class);

    @Override
    public ElementsCollection getElementsOfInnerItems() {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                "//div[@class=\"x-panel x-tabpanel-child x-panel-default\"]" +
                "//table[not(@data-ref=\"item\")]//td"));
    }
}

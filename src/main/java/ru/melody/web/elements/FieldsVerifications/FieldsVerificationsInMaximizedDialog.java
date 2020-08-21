package ru.melody.web.elements.FieldsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class FieldsVerificationsInMaximizedDialog extends FieldsVerifications {

    private FormElements formElements = page(FormElements.class);


    public ElementsCollection getElementsOfNamesFields() {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@class=\"x-form-item-label-text\" and contains(@id,\"fieldcontainer\")]"));
    }

    @Override
    public SelenideElement getElementsOfNameField(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@class=\"x-form-item-label-text\" and contains(@id,\"fieldcontainer\") and contains(text(),\'"+ fieldName + "') or (@data-qtip and contains(text(),\'" + fieldName + "'))]"));
    }



}

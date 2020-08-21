package ru.melody.web.elements.FieldsVerifications;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.*;

public class ValuesOfFieldsInMaximizedDialogVerifications extends ValuesOfFieldsVerifications {

    private FormElements formElements = page(FormElements.class);

    @Override
    public SelenideElement getInputStringField(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//input"));
    }

    @Override
    public SelenideElement getInputStringChoiceInListField(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//a"));
    }

    @Override
    public ElementsCollection getMultipleInputStringChoiceInListField(String fieldName) {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//a"));

    }

    @Override
    public SelenideElement getInputStringChoiceInListEmployers(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//a/span"));
    }

    @Override
    public ElementsCollection getMultipleInputStringChoiceInListEmployers(String fieldName) {
        return $$(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//a/span"));
    }

    @Override
    public SelenideElement getInputText(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//textarea"));
    }

    @Override
    public SelenideElement getInputTextNotEditable(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//div[@data-ref=\"inputEl\"]"));
    }

    @Override
    public SelenideElement getCheckBox(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[(@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')) or (@data-qtip and contains(text(),\'" + fieldName + "'))]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//input[@type=\"checkbox\"]"));
    }
}

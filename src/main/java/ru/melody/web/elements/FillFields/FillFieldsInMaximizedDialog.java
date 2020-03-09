package ru.melody.web.elements.FillFields;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.elements.FormElements;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class FillFieldsInMaximizedDialog extends FillFields {

    private FormElements formElements = page(FormElements.class);

    @Override
    public SelenideElement getTextArea(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//textarea"));

    }

    @Override
    public SelenideElement getInputStringField(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//input"));
    }

    @Override
    public SelenideElement getInputStringChoiceInListField(String fieldName) {
        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//li"));

    }


    @Override
    public SelenideElement getAddButtonFile(String fieldName) {
        // Выставляем фокус в поле
        $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]" +
                "/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//li")).click();

        return $(By.xpath(formElements.getElementOfLastMaximizedDialog() +
                formElements.getElementOfActiveTabInLastMaximizedDialog() +
                "//div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\" and not(contains(@style,\"display: none\"))]" +
                "//span[@data-ref=\"labelTextEl\" and contains(text(),'" + fieldName + "')]" +
                "/ancestor::div[@class=\"x-container x-form-fieldcontainer x-form-item x-form-item-default x-container-default x-autocontainer-form-item\"]//li" +
                "//span[@class=\"x-boundlist-action x-boundlist-action-last-1 m4_span_add_file_sys_icon x-boundlist-add\"]"));
    }

}

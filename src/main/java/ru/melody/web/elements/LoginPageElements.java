package ru.melody.web.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Элементы - страница авторизации
 */
public class LoginPageElements {

    public SelenideElement getLogin() {
        return $(By.xpath("//input[@name=\"j_username\"]"));
    }

    public SelenideElement getPassword() {
        return $(By.xpath("//input[@name=\"j_password\"]"));
    }

    public SelenideElement getLogon() {
        return $(By.xpath("//input[@onclick=\"document.forms.F1.submit()\"]"));
    }

}

package ru.melody.web.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


/**
 * Главная страница системы
 */
public class MainPageElements {

    /**
     * Кнопка открытия главного меню
     */
    public SelenideElement getButtonMainMenu() {
        return $(By.xpath("//div[@class=\"x-toolbar x-docked x-toolbar-default x-docked-top x-toolbar-docked-top x-toolbar-default-docked-top x-box-layout-ct x-noborder-trl\"]//div[@class=\"x-title-text x-title-text-default x-title-item\"]"));
    }

    /*
     * Кнопка выхода из системы
     */
    public SelenideElement getLogout() {
        return $(By.xpath("//div[@class=\"x-menu-block x-menu-div-span\"]//*[text()=\"Выход\"]"));
    }

}

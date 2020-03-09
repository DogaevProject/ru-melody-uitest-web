package ru.melody.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Взаимодействие с элементами на стр.
 */
public abstract class ElementUtil {

    private static Actions action = new Actions(getWebDriver());

    /**
     * Имитации нажатия правой кнопки мыши. Клик осуществляется в центр элемента и
     * ожидание появляющегося элемента
     *
     * @param element               передаваемая переменная для взаимодействия
     * @param elementWaitVisibility передаваемая переменная (элемент DOM) для взаимодействия, ожидание
     *                              появления данного элемента
     */
    public static void contextClickAction(SelenideElement element, SelenideElement elementWaitVisibility) {
        action.contextClick(element).perform();
        elementWaitVisibility.shouldBe(Condition.visible);
    }

    /**
     * Кликнуть по невидимому элементу с помощью javascript
     *
     * @param element переменная для взаимодействия
     */
    public static void clickOnInvisibleElement(SelenideElement element) {

        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        ((JavascriptExecutor) getWebDriver()).executeScript(script, element);
    }


    /**
     * Скроллинг к элементу вниз и выбор (сlick) данного элемента из списка
     * <p/>
     * пример - scrollToAndClick()
     *
     * @param locator элемент к к-му необходимо проскроллировать список
     */
    public static void scrollToAndClick(String locator) {
        SelenideElement element = $(By.xpath(locator));
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();"
                , element);
        sleep(200);
        element.click();
    }

    /**
     * Скроллинг к элементу вниз и выбор (сlick) данного элемента из списка
     * <p/>
     * пример - scrollToAndClick()
     *
     * @param element элемент к к-му необходимо проскроллировать список
     */
    public static void scrollToAndClick(SelenideElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();"
                , element);
        sleep(200);
        element.click();
    }

    /**
     * Скроллинг к элементу
     * <p/>
     * пример - scrollToAndClick()
     *
     * @param element элемент к к-му необходимо проскроллировать список
     */
    public static void scrollToElement(SelenideElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();"
                , element);
        sleep(200);
    }

}
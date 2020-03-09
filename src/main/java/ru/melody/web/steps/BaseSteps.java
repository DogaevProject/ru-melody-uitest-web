package ru.melody.web.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ru.melody.web.elements.MainPageElements;
import ru.melody.web.elements.LoginPageElements;
import ru.melody.web.elements.FormElements;
import ru.melody.web.model.LocationOfElement.Dialog.AlertDialog;
import ru.melody.web.model.LocationOfElement.Dialog.MaximizedDialog;
import ru.melody.web.model.LocationOfElement.Toolbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.melody.utils.ElementUtil.scrollToElement;


/**
 * Базовые методы
 */
public abstract class BaseSteps {

    protected LoginPageElements loginPageElements = page(LoginPageElements.class);
    protected MainPageElements mainPageElements =page(MainPageElements.class);
    protected FormElements formElements = page(FormElements.class);
    protected MaximizedDialog maximizedDialog = new MaximizedDialog();
    protected AlertDialog alertDialog = new AlertDialog();


    /**
     * Закрываем окно
     */
    protected void closeWindow() {
        getWebDriver().close();
    }

    /**
     * Переход во фрейм объекта системы для взаимодействия
     *
     * @param frameObject локатор frame
     */
    protected void getFrameObject(SelenideElement frameObject) {
        switchTo().frame(frameObject);
    }


    /**
     * Проверяем кол-во вкладок в форме редактирования объекта и их имена (отображение)
     *
     * @param tabsLocator     locator element tabs
     * @param numberOfTabs    передаваемое кол-во вкладок в форме редактирования объекта
     * @param tabsNameLocator locator element name tabs
     * @param tabNames        передаваемое имя вкладок для проверки отображение в форме
     */
    protected static void checkDisplayedTabsInTheShapeOfAnObject(By tabsLocator, int numberOfTabs, By tabsNameLocator, String[] tabNames) {
        $$(tabsLocator).shouldBe(CollectionCondition.size(numberOfTabs)); // проверка отображения кол-во вкладок
        $$(tabsNameLocator).shouldHave(CollectionCondition.exactTexts(tabNames));
    }


    /**
     * Получить разницу между двумя списками строк (ArrayLists)
     *
     * @param shouldBeList Список строк, которые должны отображаться
     * @param currentList  Список строк, которые действительно отображаются в интерфейсе
     * @return
     */
    protected String getReportOfDifferenceBetweenTwoArrayLists(ArrayList<String> shouldBeList, ArrayList<String> currentList) {

        Collection shouldBeCollection = new ArrayList(shouldBeList); //  Список строк, которые должны отображаться
        Collection currentCollection = new ArrayList(currentList);  //  Список строк, которые отображаются в интерфейсе

        Collection ShouldBeButNotFound = new ArrayList(shouldBeList); //  Список строк, которые должны отображаться, но не отображаются
        Collection stringThatNotShouldBeButCurrentlyFind = new ArrayList(currentList); //  Список строк, которые не должны отображаться, но отображаются

        // Получаем разницу между строками, которые должны отображаться и действительно отображаемыми в данный момент
        ShouldBeButNotFound.removeAll(currentCollection);
        stringThatNotShouldBeButCurrentlyFind.removeAll(shouldBeCollection);


        return String.valueOf(" Список элементов, которые присутствуют - Current list: " + currentCollection + "\n"
                + " Список элементов, которых не должно быть, но они есть  - Wrong Values in Current list: " + stringThatNotShouldBeButCurrentlyFind + "\n"
                + " Список элементов, которые должны быть, но не их нет - ShouldBe But Not Found: " + ShouldBeButNotFound + "\n"
                + " Ожидаемый список элементов - Full exp list: " + shouldBeList);
    }

    /**
     * Проверяем причину разницы между двумя списками строк - причина в порядке перечесления (true) или нет (false).
     *
     * @param valuesThatShouldBe
     * @param currentValues
     * @return
     */
    protected boolean valuesHasWrongOrder(ArrayList<String> currentValues, ArrayList<String> valuesThatShouldBe) {
        ArrayList<String> shouldBeList = new ArrayList(valuesThatShouldBe); //  Список строк, которые должны отображаться
        ArrayList<String> currentList = new ArrayList(currentValues);  //  Список строк, которые отображаются в интерфейсе

        // Сортируем значения в списках перед их сравнением - нужно, если не важен порядок в котором происходит перечисление в dataprovider-е
        Collections.sort(currentList);
        Collections.sort(shouldBeList);

        try {
            // Если после сравнения отсортированных списков строк разница между ними все равно есть, то прична не в сортировке
            assertEquals(currentList, shouldBeList);
        } catch (Error e) {
            return false;
        } return true;
    }

    /**
     * Проверка наличия теста в списке элементов
     *
     * @param text проверяемый текст
     *   false  если среди элементов в списке не находим с нужным текстом
     *   true   если среди элементов в списке находим с нужным текстом
     */
    protected boolean verifyThatTextContainsInTheListElements(String text, ElementsCollection elementsCollection) {
        for (SelenideElement element : elementsCollection) {
            if ($(element).is((exactText(text)))) {
                return true;
            }
        }
        return false;
    }


    /**
     * Наличия элемента
     *
     * @param locator передаваемый локатор элемента для представления
     */
    protected static boolean isElementPresent(By locator) {
        try {
            sleep(300);
            getWebDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }


    /**
     * Проверка значений в чебоксах для последующего сравнения с передаваемым значением методом assertTrue
     */
    protected boolean verifyCheckboxIsSelected(boolean state, SelenideElement inputCheckbox) {
        if (state) {
            inputCheckbox.shouldBe(selected);
            return true;
        } else {
            inputCheckbox.shouldNotBe(selected);
            return true;
        }
    }


    protected boolean isElementVisible(By by) {
        try {
            getWebDriver().findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        } catch (ElementNotVisibleException ignored) {
            return false;
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }

    protected boolean isElementTextPresent(By by, String text) {
        try {
            if (getWebDriver().findElement(by).getText().equals(text)) {
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Реализация переход по разделам системы напрямую по точному URL
     *
     * @param URL передаваемая ссылка для навигации по меню
     */
    public static void openSectionOnURL(String URL) {
        switchTo().defaultContent();
        open(URL);
        assertEquals(baseUrl + URL, getWebDriver().getCurrentUrl());
        switchTo().frame($(By.cssSelector("#flow")));
    }

    /**
     * Развернем все ветви объекта
     *
     * @param knot  локатор узла если есть как таковой
     * @param nodes локатор элемента для взаиммодействия
     */
    protected void unwrapAllNodes(SelenideElement knot, By nodes) {
        try {
            while (isElementPresent(nodes)) {
                if (isElementPresent(nodes)) {
                    knot.click();
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Устанавливаем курсор мыши на элемент с текстом - прим. на поле
     *
     * @param elementOfFieldWithItem
     * @param valueOfField
     */
    protected void setCursorOnElementWithText(ElementsCollection elementOfFieldWithItem, String valueOfField) {
        Actions builder = new Actions(getWebDriver());
        // Устанавливаем фокус на элемент
        for (SelenideElement element: elementOfFieldWithItem) {
            element.waitUntil(visible, 2000);
            sleep(500);
            if (element.has(Condition.text(valueOfField))) {
                builder.moveToElement(element)
                        .moveByOffset(0, 0).release().perform();
            }
        }
    }

    /**
     * Очищаем поле через Ctrl+a+BACK_SPACE
     *
     */
    protected void clearTextInInputViaHotKeys(SelenideElement element) {
       element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
    }

    /**
     * Ожидание открытия главной страницы после авторизации
     */
    protected void waitMaskOfLoading() {
        try {
            // Ожидание появления маски загрузки
            formElements.getMaskOfLoading().waitUntil(Condition.appear, 500);
        } catch (Error e) {
            System.out.println("маски загрузки не появилась");
            e.printStackTrace();
        } finally {
            // Ожидание скрытия маски загрузки
            formElements.getMaskOfLoading().waitUntil(Condition.disappear, 30000);
        }
    }

    /**
     * Клик на кнопку на тулбаре
     *
     * @param toolbar текущее расположение тулбара
     * @param nameOfOperation название на кнопке операции
     */
    protected void clickButton(Toolbar toolbar, String nameOfOperation) {
        toolbar.executionOperation().clickButton(nameOfOperation).waitUntil(Condition.visible, 15000).click();
        waitMaskOfLoading();
    }


    /**
     * Поиск записи в гриде - ПМ, вкладка с вложенными объектами
     * <p>
     * Ищем объект среди доступных на первой странице грида - Если не находим, то далее ищем через основной поиск
     *
     * @param nameOfObjectInTheGrid - отображаемое в гриде название объекта.
     *                                     Это м.б любой текст по которому можно отличить нужный объект от остальных.
     */
    protected void findItemInGrid(String nameOfObjectInTheGrid, SelenideElement itemInTheGrid) {
        try {
            itemInTheGrid.waitUntil(visible, 5000);
        } catch (Error error) {
            System.out.println("Объект НЕ найден в гриде, возможно отсутствует на первой странице");
            error.printStackTrace();
            // возможно элемента нет на первой странице бесконечного скролла  - ищем через поиск.
            findItemInGridWiaSearchField(nameOfObjectInTheGrid);
            itemInTheGrid.waitUntil(visible, 5000);
        }
        scrollToElement(itemInTheGrid);
    }

    /**
     * Поиск записи в гриде через поле поиска
     */
    protected void findItemInGridWiaSearchField(String textForSearch) {
        formElements.getElementOfLastInputForSearch().setValue(textForSearch).pressEnter();
        waitMaskOfLoading();
    }


    /**
     * Универсальный выход из системы (где бы не находился пользователь) через очистку кеша с перезагрузкой страницы
     */
    public void logoutViaClearingBrowserCache() {
        clearBrowserCache();
        refresh(); // Очитска кэша и перезагрузка страницы т.к после логаута могут быть проблемы с повторной автворизацией.
        loginPageElements.getLogon().waitUntil(Condition.visible, 5000); // Ждем появление формы авторизации (страница приведена в состояние пригодное к дальнейшему взаимодействию)
    }

}

package ru.melody.web.steps.DialogSteps;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.melody.web.model.LocationOfElement.Dialog.PageWithInnerObjects;
import ru.melody.web.steps.BaseSteps;
import ru.melody.web.elements.FormForAddingUserElements;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Pages.Document;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.LocationOfElement.Toolbar;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.AssertsForDialog;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.fail;

/**
 * Стандартные методы для работы в диалогах, формах
 */
public class DialogSteps extends BaseSteps {

    public static Form currentDialog; // окно с которым работаем в данный момент
    public static PageWithInnerObjects currentLocationOfTabWithInnerObjects; // расположение тулбара со вложенными объектами с которым работаем в данный момент
    protected FormForAddingUserElements formForAddingUserElements = page(FormForAddingUserElements.class);

    /**
     * Текущий диалог - предполагается конкретная реализация в подклассах
     * <p>
     *
     * @param form
     * @return предполагается, что будет возвращаться по умолчанию тот диалог, на инстансе которого вызывали первый метод. т.е  будет возвращаться конкретная реализация диалога в овверайте этого метода в подклассах.
     * Нужно для того, чтобы в методах, которые вызываются следующими, в которых требуется getCurrentDialog можно было получить значение currentDialog
     * в случае, если вызываем метод, который возвращает новый класс, который наследуется от DialogSteps, то вызывается метод getCurrentDialog из DialogSteps, а не подкласса с конкретной реализацией
     */
    Form getCurrentDialog(Form form) {
        return form.setFormLocation(currentDialog.getFormLocation());
    }

    /**
     * Текущий диалог - предполагается конкретная реализация в подклассах
     * <p>
     * в качестве диалога по умолчанию new Document() - просто используется в качестве объекта по умолчанию, который наследуется от род. класса всех диалогов и которому можно выставить значение .setFormLocation
     *
     * @return
     */
    Form getCurrentDialog() {
        return new Document().setFormLocation(currentDialog.getFormLocation());
    }

    PageWithInnerObjects getCurrentLocationOfTabWithInnerObjects() {
        return currentLocationOfTabWithInnerObjects;
    }

    /**
     * Клик на кнопку на основном тулбаре формы (расположен внизу формы)
     *
     * @param nameOfOperation название на кнопке операции
     */
    public DialogSteps clickButtonInMainToolbar(String nameOfOperation) {
        Toolbar page = getCurrentDialog(new Document()).getFormLocation();
        clickButton(page, nameOfOperation);
        return this;
    }


    /**
     * Клик на кнопку в алерте - прим. окно успешного создания объекта, окно ошибки
     *
     * @param nameOfOperation название на кнопке операции
     */
    public DialogSteps clickButtonInAlertDialog(String nameOfOperation) {
        clickButton(alertDialog, nameOfOperation);
        return this;
    }

    /**
     * Клик на кнопку тулбара на вкладке со вложенными объектами -  прим.  Вкладка Резолюции, Проверка исполнения и др.
     *
     * @param nameOfOperation название на кнопке операции
     */
    public DialogSteps clickButtonInTabWithInnerObjects(String nameOfOperation) {
        clickButton(getCurrentLocationOfTabWithInnerObjects(), nameOfOperation);
        return this;
    }

    /**
     * Переход на вкладку
     *
     * @param nameOfTab название вкладки
     */
    public DialogSteps clickTab(String nameOfTab) {
        clickTab(getCurrentDialog(new Document()).getFormLocation(), nameOfTab);
        return this;
    }

    private void clickTab(Toolbar toolbar, String nameOfTab) {
        toolbar.executionOperation().selectTab(nameOfTab).waitUntil(Condition.visible, 5000).click();
        formElements.getActiveTab(nameOfTab).waitUntil(Condition.visible, 2000);
        waitMaskOfLoading();
    }

    /**
     * Выбор вложенного объекта -  прим.  Вкладка Резолюции - резолюция, Проверка исполнения - отчет и др.
     *
     * @param form - форма которую надо выбрать
     */
    public DialogSteps selectInnerItem(ru.melody.web.model.Pages.Form form) {
        findItemInGrid(form.getNameOfObjectForOpenInTheGrid(), innerItemOnTabWithInnerObjects(new Document().setNameOfObjectForOpenInTheGrid(form.getNameOfObjectForOpenInTheGrid())));
        innerItemOnTabWithInnerObjects(form).waitUntil(Condition.visible, 10000).click();
        return this;
    }

    /**
     * Выбор вложенного объекта -  прим.  Вкладка Резолюции - резолюция, Проверка исполнения - отчет и др.
     *
     * @param nameOfObjectForOpenInTheGrid - отображаемое в гриде название объекта.
     *                                     Это м.б любой текст по которому можно отличить нужный объект от остальных.
     */
    public DialogSteps selectInnerItem(String nameOfObjectForOpenInTheGrid) {
        findItemInGrid(nameOfObjectForOpenInTheGrid, innerItemOnTabWithInnerObjects(new Document().setNameOfObjectForOpenInTheGrid(nameOfObjectForOpenInTheGrid)));
        innerItemOnTabWithInnerObjects(new Document().setNameOfObjectForOpenInTheGrid(nameOfObjectForOpenInTheGrid)).waitUntil(Condition.visible, 10000).click();
        return this;
    }

    /**
     * Открытие вложенного объекта -  прим.  Вкладка Резолюции - резолюция, Проверка исполнения - отчет и др.
     *
     * @param form - форма которую надо открыть
     */
    public DialogSteps openItemOnTabWithInnerObjects(ru.melody.web.model.Pages.Form form) {
        findItemInGrid(form.getNameOfObjectForOpenInTheGrid(), innerItemOnTabWithInnerObjects(new Document().setNameOfObjectForOpenInTheGrid(form.getNameOfObjectForOpenInTheGrid())));
        innerItemOnTabWithInnerObjects(form).waitUntil(Condition.visible, 5000).doubleClick();
        return this;
    }

    /**
     * Открытие вложенного объекта -  прим.  Вкладка Резолюции - резолюция, Проверка исполнения - отчет и др.
     *
     * @param nameOfObjectForOpenInTheGrid - отображаемое в гриде название объекта.
     *                                     Это м.б любой текст по которому можно отличить нужный объект от остальных.
     *                                     Этот текст содержащийся в одной колонке грида должен быть передан полностью, без сокрашений. см. что в DOM
     */
    public DialogSteps openItemOnTabWithInnerObjects(String nameOfObjectForOpenInTheGrid) {
        findItemInGrid(nameOfObjectForOpenInTheGrid, innerItemOnTabWithInnerObjects(new Document().setNameOfObjectForOpenInTheGrid(nameOfObjectForOpenInTheGrid)));
        innerItemOnTabWithInnerObjects(new Document().setNameOfObjectForOpenInTheGrid(nameOfObjectForOpenInTheGrid)).waitUntil(Condition.visible, 5000).doubleClick();
        waitMaskOfLoading();
        return this;
    }

    /**
     * Элемент для открытия/выбора вложенного объекта - прим.  Вкладка Резолюции - резолюция, Проверка исполнения - отчет и др.
     *
     * @param form - форма которую надо открыть
     */
    protected SelenideElement innerItemOnTabWithInnerObjects(ru.melody.web.model.Pages.Form form) {
        return getCurrentLocationOfTabWithInnerObjects().executionOperation().selectItem(form.getNameOfObjectForOpenInTheGrid());
    }

    /**
     * Добавление пользователей в форме добавления
     */
    protected void choiceUserInForm(Employee[] employees) {
        for (Employee employee : employees) {
            if (employee.getLastName() != null) {
                waitMaskOfLoading();
                formElements.getElementOfLastInputForSearch().setValue(employee.getLastName()).pressEnter();
                waitMaskOfLoading();
                //Выбор пользователя в списке
                formForAddingUserElements.getUserFromList(employee.getLastName() + " " + employee.getName() + " " + employee.getPatronymic()).waitUntil(visible, 10000).doubleClick();
                try {
                    formForAddingUserElements.getAddedUserFromList(employee.getLastName() + " " + employee.getName() + " " + employee.getPatronymic()).shouldBe(visible);
                } catch (Error ignored) {
                    // иногда интерфейс не успевает отработать doubleClick - пробуем добавлять повторно
                    formForAddingUserElements.getUserFromList(employee.getLastName() + " " + employee.getName() + " " + employee.getPatronymic()).doubleClick();
                    formForAddingUserElements.getAddedUserFromList(employee.getLastName() + " " + employee.getName() + " " + employee.getPatronymic()).shouldBe(visible);
                }
            }
        }
        formForAddingUserElements.getButtonSelectUsers().click();
    }

    /**
     * Удаление пользователей в форме добавления/удаления пользователей
     */
    protected void deleteUserInForm(Employee[] employees) {
        for (Employee employee : employees) {
            if (employee.getLastName() != null) {
                formForAddingUserElements.getAddedUserFromList(employee.getLastName()).waitUntil(visible, 5000).doubleClick();
                formForAddingUserElements.getAddedUserFromList(employee.getLastName()).shouldNotBe(visible);
            }
        }
        formForAddingUserElements.getButtonSelectUsers().click();
    }

    /**
     * Удаление пользователей в форме добавления/удаления пользователей
     */
    protected void deleteUserInForm(String[] employees) {
        if (employees != null) {
            for (String employee : employees) {
                formForAddingUserElements.getAddedUserFromList(employee).waitUntil(visible, 5000).doubleClick();
                try {
                    formForAddingUserElements.getAddedUserFromList(employee).shouldNotBe(visible);
                } catch (Error ignored) {
                    formForAddingUserElements.getAddedUserFromList(employee).doubleClick();
                    formForAddingUserElements.getAddedUserFromList(employee).shouldNotBe(visible);
                }
            }
        }
        formForAddingUserElements.getButtonSelectUsers().click();
    }


    /**
     * Аттачминг файлов в форме
     *
     * @param nameOfFiles названия файлов
     * @param buttonAddFile кнопка в интерфейсе, после нажатия на которую открывается окно проводника ОС
     */
    protected void addAttachFiles(SelenideElement buttonAddFile, String[] nameOfFiles) {
        if (nameOfFiles != null)
            for (String nameOfFile : nameOfFiles) {

                String mainFilePath = "src" + File.separator + "main" + File.separator +
                        "resources" + File.separator + "attachfiles" + File.separator;

                try {
                    Robot r = new Robot(); //создаем робота для взаимодействия с формой добавления файлов ОС
                    buttonAddFile.click();
                    sleep(1000);
                    //закрываем форму добавления файла
                    r.keyPress(KeyEvent.VK_ESCAPE);
                    r.keyRelease(KeyEvent.VK_ESCAPE);
                } catch (AWTException e) {
                    fail("AWTException");
                }
                $(By.xpath("//input[@ data-ref=\"fileInputEl\"]")).uploadFile(new File(mainFilePath, nameOfFile)); // универсальный input для всех форм
                sleep(1000); // ожидание для окончания загрузки файла.
            }
    }

    /**
     * Выбор руководителя в поле «Вы работаете за:»
     * Здесь new Document() - просто используется в качестве объекта по умолчанию, который наследуется от род. класса всех диалогов и которому можно выставить значение .setFormLocation в методе getCurrentDialog
     *
     * @param nameOfDeputy - имя руководителя - формат: можно ФИО полностью или только фамилия
     */
    public DialogSteps switchUserInDeputyField(String nameOfDeputy) {
        clearTextInInputViaHotKeys(getCurrentDialog(new Document()).getFormLocation().executionOperation().inputInDeputyField());
        getCurrentDialog(new Document()).getFormLocation().executionOperation().inputInDeputyField().setValue(nameOfDeputy).sendKeys(" ");
        $(By.xpath("//div[contains(@class,\"x-boundlist x-boundlist-floating x-layer x-boundlist-default x-border-box\") and not(contains(@style,'display: none;'))]//li[contains(text(),'" + nameOfDeputy + "')]")).click(); // универсальный локатор?
        return this;
    }

    /**
     * Для тех проверок, когда нет нужды передавать форму содержащую данные, а нужно провести какую-то проверку просто передавав конкретные значения в аргументе методу из AssertsForDialog
     */
    public AssertsForDialog assertThat() {
        return new AssertsForDialog(getCurrentDialog(), getCurrentLocationOfTabWithInnerObjects());
    }

    /**
     * Проверки для форм
     *
     * @param form  - форма для которой будем вызывать методы проверок
     * @return AssertsForDialog - класс содержащий методы проверок форм
     */
    public AssertsForDialog assertThat(Form form) {
        return new AssertsForDialog(getCurrentDialog(form), getCurrentLocationOfTabWithInnerObjects());
    }


    /**
     * Работа с Маршрутной схемой
     */
    public EditRouteSchemeSteps editRouteScheme() {
        return new EditRouteSchemeSteps(getCurrentDialog(), getCurrentLocationOfTabWithInnerObjects());
    }

    /**
     * Работа с файлами
     */
    public EditFilesOfFormSteps editFiles() {
        return new EditFilesOfFormSteps(getCurrentDialog(new Document()), getCurrentLocationOfTabWithInnerObjects());
    }

}

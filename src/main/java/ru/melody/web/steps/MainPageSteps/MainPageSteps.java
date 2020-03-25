package ru.melody.web.steps.MainPageSteps;

import com.codeborne.selenide.ex.ElementNotFound;
import ru.melody.web.elements.FoldersElements;
import ru.melody.web.steps.MainPageSteps.AssertsForMainPageSteps.AssertionsForGridOfMainPage;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.LocationOfElement.Grid.GridFolder;
import ru.melody.web.model.LocationOfElement.Toolbar;
import ru.melody.web.steps.BaseSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.fail;

/*
 * Главная Страница - Пункты меню, Папка, Гриды
 */

public class MainPageSteps extends BaseSteps {

    private FoldersElements folderElements = page(FoldersElements.class);

    /**
     * Открытие корневого пункта меню
     */
    private void openMainItemOfMenuTree(Folder folder) {
        try {
            folderElements.getMainItemOfMenuTree(folder.getNameFolder()).waitUntil(visible, 10000).click();
            waitMaskOfLoading();
        } catch (ElementNotFound notFoundFolder) {
            notFoundFolder.printStackTrace();
            fail("Не найдена папка");
        }
    }

    /**
     * Открытие вложенного пункта меню
     */
    private void openChildItemOfMenuTree(Folder folder) {
        folderElements.getTriggerForOpenMainItemOfMenuTree("Помощь").waitUntil(visible, 10000).click(); // разворачиваем ПМ, который есть у всех пользователей, чтобы свернуть все остальные узлы
        try {
            folderElements.getMainItemOfMenuTree(folder.getParentFolder().getNameFolder()).waitUntil(visible, 10000).click();
            sleep(500);
            folderElements.getTriggerForOpenMainItemOfMenuTree(folder.getParentFolder().getNameFolder()).click();
            sleep(500);
            folderElements.getChildItemOfMenuTree(folder.getParentFolder().getNameFolder(), folder.getNameFolder()).click();
            waitMaskOfLoading();
        } catch (ElementNotFound notFoundFolder) {
            notFoundFolder.printStackTrace();
            fail("Не найдена папка");
        }
    }


    /**
     * Открытие пункта меню
     */
    public MainPageSteps openItemOfMenuTree(Folder folder) {
        if (folder.getParentFolder() != null) {
            openChildItemOfMenuTree(folder);
        } else {
            openMainItemOfMenuTree(folder);
        }
        return this;
    }


    /**
     * Открываем объект в гриде ПМ -  в новом окне
     */
    public MainPageSteps openItemInGridInNewWindow(Form form) {
        findItemInGrid(form.getNameOfObjectForOpenInTheGrid(), folderElements.getItemInTheGrid(form.getNameOfObjectForOpenInTheGrid()));
        folderElements.getHrefOfItemInTheGrid(form.getNameOfObjectForOpenInTheGrid()).click();
        // переход в открывшиеся окно switchTo().window
        //switchTo().frame($(By.cssSelector("#mainframe"))); //нужно когда открываем форму в новом окне
        return page(MainPageSteps.class);
    }


    /**
     * Открываем объект в гриде ПМ -  в текущем окне
     */
    public MainPageSteps openItemInGridInCurrentWindow(Form form) {
        waitMaskOfLoading();
        findItemInGrid(form.getNameOfObjectForOpenInTheGrid(),folderElements.getItemInTheGrid(form.getNameOfObjectForOpenInTheGrid()));
        folderElements.getItemInTheGrid(form.getNameOfObjectForOpenInTheGrid()).doubleClick();
        waitMaskOfLoading();
        return page(MainPageSteps.class);
    }

    /**
     * Открываем объект в гриде ПМ -  в текущем окне
     *
     * @param nameOfObjectForOpenInTheGrid - отображаемое в гриде название объекта.
     *                                     Это м.б любой текст по которому можно отличить нужный объект от остальных.
     *                                     Этот текст содержащийся в одной колонке грида должен быть передан полностью, без сокрашений. см. что в DOM
     */
    public MainPageSteps openItemInGridInCurrentWindow(String nameOfObjectForOpenInTheGrid) {
        waitMaskOfLoading();
        findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
        folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).doubleClick();
        waitMaskOfLoading();
        return page(MainPageSteps.class);
    }


    /**
     * Выбрать объект в гриде ПМ
     * Используется для проверки отображения списка операций доступных для этого объекта
     */
    public MainPageSteps selectItemInGrid(Form form) {
        waitMaskOfLoading();
        findItemInGrid(form.getNameOfObjectForOpenInTheGrid(), folderElements.getItemInTheGrid(form.getNameOfObjectForOpenInTheGrid()));
        folderElements.getItemInTheGridForSelect(form.getNameOfObjectForOpenInTheGrid()).click();
        return page(MainPageSteps.class);
    }

    /**
     * Выбрать объект в гриде ПМ
     * Используется для проверки отображения списка операций доступных для этого объекта
     *
     * @param nameOfObjectForOpenInTheGrid - отображаемое в гриде название объекта.
     *                                     Это м.б любой текст по которому можно отличить нужный объект от остальных.
     */
    public MainPageSteps selectItemInGrid(String nameOfObjectForOpenInTheGrid) {
        waitMaskOfLoading();
        findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
        folderElements.getItemInTheGridForSelect(nameOfObjectForOpenInTheGrid).click();
        return page(MainPageSteps.class);
    }

    /**
     * Клик на кнопку тулбара (расположен вверху грида)
     *
     * @param nameOfOperation
     */
    public MainPageSteps clickButtonInGrid(String nameOfOperation) {
        Toolbar page = new GridFolder();
        clickButton(page, nameOfOperation);
        return this;
    }


    /**
     * Проверки для грида и объектов, которые он содержит
     *
     * @return
     */
    public AssertionsForGridOfMainPage assertThatInGrid() {
        return page(AssertionsForGridOfMainPage.class);
    }
}


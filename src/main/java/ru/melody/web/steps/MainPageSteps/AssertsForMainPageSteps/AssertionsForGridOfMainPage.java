package ru.melody.web.steps.MainPageSteps.AssertsForMainPageSteps;

import ru.melody.web.elements.FoldersElements;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.AssertsForDialog;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.InnerItemVerification;
import ru.melody.web.steps.MainPageSteps.MainPageSteps;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.OperationsVerifications;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.model.LocationOfElement.Grid.GridFolder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Проверки для грида ПМ и объектов, которые он содержит
 */
public class AssertionsForGridOfMainPage extends MainPageSteps {

    /**
     * Проверяем отображение объекта в гриде ПМ
     *
     * @param form   - getNameOfObjectForOpenInTheGrid  уникальный текст по которому ищем объект в гриде (наименование объекта)
     */
    public AssertionsForGridOfMainPage itemDisplayed(Form form) {
        itemDisplayed(form.getNameOfObjectForOpenInTheGrid());
        return this;
    }

    /**
     * Проверяем отображение объекта в гриде ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование объекта)
     */
    public AssertionsForGridOfMainPage itemDisplayed(String nameOfObjectForOpenInTheGrid) {
        new InnerItemVerification().itemDisplayed(nameOfObjectForOpenInTheGrid, new GridFolder());
        return this;
    }

    /**
     * Проверяем отображение объекта в гриде ПМ
     *
     * @param form   - getNameOfObjectForOpenInTheGrid  уникальный текст по которому ищем объект в гриде (наименование объекта)
     * @param folder ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(Form form, Folder folder) {
        itemDisplayed(form.getNameOfObjectForOpenInTheGrid(), folder);
        return this;
    }

    /**
     * Проверяем отображение объекта в гриде ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование объекта)
     * @param folder                       ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(String nameOfObjectForOpenInTheGrid, Folder folder) {
        openItemOfMenuTree(folder);
        itemDisplayed(nameOfObjectForOpenInTheGrid);
        return this;
    }


    /**
     * Проверяем отображение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param form    - getNameOfObjectForOpenInTheGrid  уникальный текст по которому ищем объект в гриде (наименование объекта)
     * @param folders ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(Form form, Folder[] folders) {
        itemDisplayed(form.getNameOfObjectForOpenInTheGrid(), folders);
        return this;
    }

    /**
     * Проверяем отображение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param nameOfObjectForOpenInTheGrid -  уникальный текст по которому ищем объект в гриде (наименование объекта)
     * @param folders                      ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);
            itemDisplayed(nameOfObjectForOpenInTheGrid);
        }
        return this;
    }


    /**
     * Проверяем исчезновение объекта в гриде ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде
     */
    public AssertionsForGridOfMainPage itemDisappear(String nameOfObjectForOpenInTheGrid) {
        new InnerItemVerification().itemDisappear(nameOfObjectForOpenInTheGrid, new GridFolder());
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ
     *
     * @param form - getNameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование объекта)
     */
    public AssertionsForGridOfMainPage itemDisappear(Form form) {
        itemDisappear(form.getNameOfObjectForOpenInTheGrid());
        return this;
    }


    /**
     * Проверяем исчезновение объекта в гриде ПМ
     *
     * @param form - getNameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование объекта)
     */
    public AssertionsForGridOfMainPage itemDisappear(Form form, Folder folder) {
        itemDisappear(form.getNameOfObjectForOpenInTheGrid(), folder);
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде
     */
    public AssertionsForGridOfMainPage itemDisappear(String nameOfObjectForOpenInTheGrid, Folder folder) {
        openItemOfMenuTree(folder);
        itemDisappear(nameOfObjectForOpenInTheGrid);
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param form - getNameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде
     */
    public AssertionsForGridOfMainPage itemDisappear(Form form, Folder[] folders) {
        itemDisappear(form.getNameOfObjectForOpenInTheGrid(), folders);
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде
     */
    public AssertionsForGridOfMainPage itemDisappear(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        }
        return this;
    }

    /**
     * Проверяем, что цвет фона объекта в гриде ПМ - Зеленый. Проверка с переходом в ПМ.
     *
     */
    public AssertionsForGridOfMainPage itemHasGreenBackground(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);
            itemHasGreenBackground(nameOfObjectForOpenInTheGrid);
        }
        return this;
    }

    /**
     *  Проверяем, что цвет фона объекта в гриде ПМ - Зеленый. Проверка без перехода в ПМ.
     *
     */
    public AssertionsForGridOfMainPage itemHasGreenBackground(String nameOfObjectForOpenInTheGrid) {
        new InnerItemVerification().innerItemHasGreenBackground(nameOfObjectForOpenInTheGrid, new GridFolder());
        return this;
    }

    /**
     * Проверяем, что у объекта в гриде ПМ отсутствует цвет фона. Проверка с переходом в ПМ.
     *
     */
    public AssertionsForGridOfMainPage itemDoesNotHaveColorOnBackground(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);
            itemDoesNotHaveColorOnBackground(nameOfObjectForOpenInTheGrid);
        }
        return this;
    }

    /**
     *  Проверяем, что у объекта в гриде ПМ отсутствует цвет фона. Проверка без перехода в ПМ.
     *
     */
    public AssertionsForGridOfMainPage itemDoesNotHaveColorOnBackground(String nameOfObjectForOpenInTheGrid) {
        new InnerItemVerification().innerItemDoesNotHaveColorOnBackground(nameOfObjectForOpenInTheGrid, new GridFolder());
        return this;
    }

    /**
     * Проверка присутствия кнопок операций/отсутствия лишних операций среди доступных на тулбаре
     * @param operations массив операций
     */
    public AssertionsForGridOfMainPage hasOperation(Operation[] operations) {
        if (operations != null) {
            ArrayList<Operation> operationArrayList = new ArrayList<>();
            // для объектов Operation добавляем текущий диалог в setLocationToolbar. Нужно для метода проверки доступных операций
            Arrays.asList(operations).forEach(operation -> operationArrayList.add(operation.setLocationToolbar(new GridFolder())));
            new OperationsVerifications().verifyOperations(operationArrayList.toArray(new Operation[0]));
        }
        return this;
    }


}

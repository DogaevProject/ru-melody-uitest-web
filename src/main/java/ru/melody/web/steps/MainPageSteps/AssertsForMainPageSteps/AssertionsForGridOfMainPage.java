package ru.melody.web.steps.MainPageSteps.AssertsForMainPageSteps;

import ru.melody.web.elements.FoldersElements;
import ru.melody.web.steps.MainPageSteps.MainPageSteps;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.OperationsVerifications;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.model.LocationOfElement.Grid.ToolbarOfGridFolder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Проверки для грида ПМ и объектов, которые он содержит
 */
public class AssertionsForGridOfMainPage extends MainPageSteps {

    private FoldersElements folderElements = page(FoldersElements.class);

    /**
     * Проверяем отображение объекта в гриде ПМ
     *
     * @param form   - getNameOfObjectForOpenInTheGrid  уникальный текст по которому ищем объект в гриде (наименование документа)
     * @param folder ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(Form form, Folder folder) {
        itemDisplayed(form.getNameOfObjectForOpenInTheGrid(), folder);
        return this;
    }

    /**
     * Проверяем отображение объекта в гриде ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование документа)
     * @param folder                       ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(String nameOfObjectForOpenInTheGrid, Folder folder) {
        openItemOfMenuTree(folder);  // входим в созданную папку
        findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
        return this;
    }


    /**
     * Проверяем отображение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param form    - getNameOfObjectForOpenInTheGrid  уникальный текст по которому ищем объект в гриде (наименование документа)
     * @param folders ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(Form form, Folder[] folders) {
        itemDisplayed(form.getNameOfObjectForOpenInTheGrid(), folders);
        return this;
    }

    /**
     * Проверяем отображение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param nameOfObjectForOpenInTheGrid -  уникальный текст по которому ищем объект в гриде (наименование документа)
     * @param folders                      ПМ в к-м будет содержаться объект
     */
    public AssertionsForGridOfMainPage itemDisplayed(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);  // входим в созданную папку
            findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
        }
        return this;
    }


    /**
     * Проверяем исчезновение объекта в гриде ПМ
     *
     * @param form - getNameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование документа)
     */
    public AssertionsForGridOfMainPage itemDisappear(Form form, Folder folder) {
        itemDisappear(form.getNameOfObjectForOpenInTheGrid(), folder);
        return this;
    }

    /**
     * Проверяем, что цвет фона объекта в гриде ПМ - Зеленый. Проверка с переходом в ПМ.
     *
     *
     */
    public AssertionsForGridOfMainPage itemHasGreenBackground(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);  // входим в созданную папку
            findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
            assertThat(folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).getAttribute("class")).contains("bc_ccffcc");
        }
        return this;
    }

    /**
     *  Проверяем, что цвет фона объекта в гриде ПМ - Зеленый. Проверка без перехода в ПМ.
     *
     *
     */
    public AssertionsForGridOfMainPage itemHasGreenBackground(String nameOfObjectForOpenInTheGrid) {
            findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
            assertThat(folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).getAttribute("class")).contains("bc_ccffcc");
        return this;
    }

    /**
     * Проверяем, что у объекта в гриде ПМ отсутствует цвет фона. Проверка с переходом в ПМ.
     *
     *
     */
    public AssertionsForGridOfMainPage itemDoesNotHaveColorOnBackground(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);  // входим в созданную папку
            findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
            assertThat(folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).getAttribute("class")).doesNotContain("bc_");
        }
        return this;
    }

    /**
     *  Проверяем, что у объекта в гриде ПМ отсутствует цвет фона. Проверка без перехода в ПМ.
     *
     */
    public AssertionsForGridOfMainPage itemDoesNotHaveColorOnBackground(String nameOfObjectForOpenInTheGrid) {
        findItemInGrid(nameOfObjectForOpenInTheGrid, folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid));
        assertThat(folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).getAttribute("class")).doesNotContain("bc_");
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование документа)
     */
    public AssertionsForGridOfMainPage itemDisappear(String nameOfObjectForOpenInTheGrid, Folder folder) {
        openItemOfMenuTree(folder);  // открываем пм
        if (!folderElements.getAllItemsInTheGridOfFolder().isEmpty()) {
            try {
                folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).shouldNotBe(visible);
            } catch (Error e) {
                System.out.println("документ не должен отображаться в гриде");
                e.printStackTrace();
                // в случае, если документ еще отображается в гриде, т.к грид автоматически не обновился, то принудительно обновляем его через поиск
            }
            // всегда проверяем наличие документа через поиск, т.к возможно документа нет только на первой странице грида с бесконечным скроллом.
            findItemInGridWiaSearchField(nameOfObjectForOpenInTheGrid);
            folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).shouldNotBe(visible);
        }
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param form - getNameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование документа)
     */
    public AssertionsForGridOfMainPage itemDisappear(Form form, Folder[] folders) {
        itemDisappear(form.getNameOfObjectForOpenInTheGrid(), folders);
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде ПМ - массовая проверка в нескольких ПМ
     *
     * @param nameOfObjectForOpenInTheGrid уникальный текст по которому ищем объект в гриде (наименование документа)
     */
    public AssertionsForGridOfMainPage itemDisappear(String nameOfObjectForOpenInTheGrid, Folder[] folders) {
        for (Folder folder : folders) {
            openItemOfMenuTree(folder);  // входим в созданную папку
            if (!folderElements.getAllItemsInTheGridOfFolder().isEmpty()) {
                folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).shouldNotBe(visible);
                // возможно элемента нет на первой странице бесконечного скролла  - ищем через поиск.
                findItemInGridWiaSearchField(nameOfObjectForOpenInTheGrid);
                folderElements.getItemInTheGrid(nameOfObjectForOpenInTheGrid).shouldNotBe(visible);
            }
        }
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
            Arrays.asList(operations).forEach(operation -> operationArrayList.add(operation.setLocationToolbar(new ToolbarOfGridFolder())));
            new OperationsVerifications().verifyOperations(operationArrayList.toArray(new Operation[0]));
        }
        return this;
    }


}

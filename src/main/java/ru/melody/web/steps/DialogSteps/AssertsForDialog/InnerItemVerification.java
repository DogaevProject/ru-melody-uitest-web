package ru.melody.web.steps.DialogSteps.AssertsForDialog;

import org.assertj.core.api.Assertions;
import ru.melody.web.model.LocationOfElement.Dialog.PageWithInnerObjects;
import ru.melody.web.model.Pages.Document;

import static com.codeborne.selenide.Condition.visible;

/**
 * Проверки вложенных объектов - записи в гридах
 */
public class InnerItemVerification extends AssertsForDialog {

    /**
     * Проверяем отображение объекта в гриде
     *
     * @param nameOfObjectInTheGrid уникальный текст по которому ищем объект в гриде (наименование записи)
     */
    public AssertsForDialog itemDisplayed(String nameOfObjectInTheGrid, PageWithInnerObjects pageLocation) {
        findItemInGrid(nameOfObjectInTheGrid, pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid));
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде
     *
     * @param nameOfObjectInTheGrid уникальный текст по которому ищем объект в гриде (наименование записи)
     */
    public AssertsForDialog itemDisappear(String nameOfObjectInTheGrid, PageWithInnerObjects pageLocation) {

        if (!pageLocation.verifyInnerItems().getElementsOfInnerItems().isEmpty()) {
            try {
                pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).shouldNotBe(visible);
            } catch (Error e) {
                System.out.println("запись не должна отображаться в гриде");
                e.printStackTrace();
                // в случае, если объекта еще отображается в гриде, т.к грид автоматически не обновился, то принудительно обновляем его через поиск
                findItemInGridWiaSearchField("");
                pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).shouldNotBe(visible);
            }
            // всегда проверяем наличие объекта через поиск, т.к возможно документа нет только на первой странице грида.
            findItemInGridWiaSearchField(nameOfObjectInTheGrid);
            pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).shouldNotBe(visible);
        }
        return this;
    }

    /**
     * Проверяем, что цвет фона объекта - Зеленый.
     */
    public AssertsForDialog innerItemHasGreenBackground(String nameOfObjectInTheGrid, PageWithInnerObjects pageLocation) {
        try {
            pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).shouldBe(visible);
        } catch (Error e) {
            System.out.println("объект не найден в гриде");
            e.printStackTrace();
            findItemInGridWiaSearchField(nameOfObjectInTheGrid);
        }
        Assertions.assertThat(pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).getAttribute("class")).contains("bc_ccffcc");
        return this;
    }

    /**
     * Проверяем, что у объекта отсутствует цвет фона.
     */
    public AssertsForDialog innerItemDoesNotHaveColorOnBackground(String nameOfObjectInTheGrid, PageWithInnerObjects pageLocation) {
        try {
            pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).shouldBe(visible);
        } catch (Error e) {
            System.out.println("объект не найден в гриде");
            e.printStackTrace();
            findItemInGridWiaSearchField(nameOfObjectInTheGrid);
        }
        Assertions.assertThat(pageLocation.executionOperation().selectItem(nameOfObjectInTheGrid).getAttribute("class")).doesNotContain("bc_");
        return this;
    }


}

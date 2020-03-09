package ru.melody.web.steps.DialogSteps;

import com.codeborne.selenide.Condition;
import ru.melody.web.elements.RouteTabElements;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.RouteScheme;
import ru.melody.web.model.ItemRouteScheme;

import static com.codeborne.selenide.Selenide.page;

/**
 * Редактирование Маршрутной схемы
 */
public class EditRouteSchemeSteps extends DialogSteps {

    public EditRouteSchemeSteps() {
    }

    public EditRouteSchemeSteps(Form currentDialog, ru.melody.web.model.LocationOfElement.Toolbar currentTabWithInnerObjects) {
        DialogSteps.currentDialog = currentDialog;
        DialogSteps.currentLocationOfTabWithInnerObjects = currentTabWithInnerObjects;
    }

    private RouteTabElements routeTabElements = page(RouteTabElements.class);

    /**
     * Добавление пользователей в блоки МС
     *
     * @param form должен содерать значение routeScheme
     */
    public EditRouteSchemeSteps fillRouteScheme(Form form) {
        RouteScheme routeScheme = form.getRouteScheme();
        if (routeScheme.getItemRouteSchemeForEdit() != null) {
            for (ItemRouteScheme itemRouteScheme : routeScheme.getItemRouteSchemeForEdit()) {
                addUserToBlockDiagram(itemRouteScheme);
            }
        }
        return this;
    }


    /**
     * Добавление пользователей в блок МС
     *
     * @param itemRouteScheme блок МС
     */
    private void addUserToBlockDiagram(ItemRouteScheme itemRouteScheme) {
        if (itemRouteScheme.isNewInnerBlock()) {
            //Добавление пользователей в новый блок
            routeTabElements.getAddInnerBlockToDiagram(itemRouteScheme.getNameItem()).click();
            if (itemRouteScheme.isTypeItemIsSequenceBlock()) {
                routeTabElements.getTypeSequenceForNewInnerBlock().click();
            } else if (itemRouteScheme.isTypeItemIsParallelBlock()) {
                routeTabElements.getTypeParallelForNewInnerBlock().click();
            }
            addUserInDifferentTypeBlock(itemRouteScheme);
        } else if (itemRouteScheme.isInnerBlock()) {
            //Добавление пользователей в существующий вложенный блок
            addUserInDifferentTypeBlock(itemRouteScheme);
        } else {
            routeTabElements.getAddAUserToBlockDiagram(itemRouteScheme.getNameItem()).waitUntil(Condition.visible, 2000).click();
        }
        choiceUserInForm(itemRouteScheme.getUserRoute());
    }

    /**
     * Добавление пользователей в новый блок в зависимости от типа - Параллельно, Последовательно
     *
     * @param itemRouteScheme блок МС
     */
    private void addUserInDifferentTypeBlock(ItemRouteScheme itemRouteScheme) {
        if (itemRouteScheme.isTypeItemIsSequenceBlock()) {
            routeTabElements.getAddAUserToInnerBlockTypeSequence(itemRouteScheme.getNameItem()).waitUntil(Condition.visible, 2000).click();
        } else if (itemRouteScheme.isTypeItemIsParallelBlock()) {
            routeTabElements.getAddAUserToInnerBlockTypeParallel(itemRouteScheme.getNameItem()).waitUntil(Condition.visible, 2000).click();
        }
    }
}

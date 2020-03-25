package ru.melody.web.steps.DialogSteps.AssertsForDialog;

import org.assertj.core.api.Assertions;
import ru.melody.web.model.LocationOfElement.Dialog.PageWithInnerObjects;
import ru.melody.web.model.Pages.Document;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.steps.DialogSteps.DialogSteps;

import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class AssertsForDialog extends DialogSteps {


    public AssertsForDialog() {
    }

    public AssertsForDialog(Form currentDialog, PageWithInnerObjects currentTabWithInnerObjects) {
        DialogSteps.currentDialog = currentDialog;
        DialogSteps.currentLocationOfTabWithInnerObjects = currentTabWithInnerObjects;
    }


    /**
     * Проверка присутсвия названий полей, которые должны отображаться и отсутствия лишних названий полей
     * В значении .setFields проверямой формы должен быть полный перечень названий полей, все остальные найденные поля в форме будут считаться ошибочно отображаемыми
     */
    public AssertsForDialog hasFields() {
        return new ValuesOfFieldsVerifications().verifyValueOfNamesOfCustomFields(currentDialog);
    }

    /**
     * Проверка значений в полях
     * В значении .setValueField или .setListValueField проверямого поля должно быть полное значение.
     * Чтобы проверить, что в поле нет никакого значения - надо сетить строку без символов "" или см. что по факту в DOM
     */
    public AssertsForDialog hasValuesInFields() {
        return new ValuesOfFieldsVerifications().verifyValuesInFields(currentDialog);
    }

    /**
     * Проверка значения в поле тип СТРОКА (Выбор из списка == Да; Мн. выбор  == Да;)
     * <p>
     * Нужно для автоматически формируемого текста резолюции
     */
    public AssertsForDialog hasValueInInFieldStringWithChoiceInListMultiple(String fieldName, String value) {
        return new ValuesOfFieldsVerifications().verifyValueInFieldStringWithChoiceInListMultiple(fieldName, value);
    }


    /**
     * Ожидание и Проверка значения в поле тип Текст (поле доступно для редактирования)
     * <p>
     * Нужно для автоматически формируемого текста резолюции
     */
    public AssertsForDialog hasValueInFieldTextAfterEditing(String fieldName, String value) {
        return new ValuesOfFieldsVerifications().verifyValueInFieldTextAfterEditing(fieldName, value);
    }

    /**
     * Ожидание и Проверка значения в поле тип Текст (поле недоступно для редактирования)
     * <p>
     * Нужно для автоматически формируемого текста резолюции
     */
    public AssertsForDialog hasValueInNotEditableFieldText(String fieldName, String value) {
        return new ValuesOfFieldsVerifications().verifyValueInNotEditableFieldText(fieldName, value);
    }

    /**
     * Проверка текста в алерте
     */
    public AssertsForDialog hasTextMessageInAlertDialog(String textMessage) {
        return new TextMessagesVerifications().verifyTextMessageInAlertDialog(textMessage);
    }

    /**
     * Проверка присутствия кнопок операций/отсутствия лишних операций среди доступных на тулбаре
     */
    public AssertsForDialog hasOperation() {
        if (currentDialog.getOperation() == null) {
            fail("null");
        }
        // для объектов Operation добавляем в setLocationToolbar - текущий диалог. Нужно для метода проверки доступных операций
        ArrayList<Operation> operations = new ArrayList<>();
        Arrays.asList(currentDialog.getOperation()).forEach(operation -> operations.add(operation.setLocationToolbar(currentDialog.getFormLocation())));
        return new OperationsVerifications().verifyOperations(operations.toArray(new Operation[0]));
    }

    /**
     * Проверка присутствия кнопок операций/отсутствия лишних операций среди доступных на тулбаре
     *
     * @param operations массив операций
     */
    public AssertsForDialog hasOperationInTabWithInnerObjects(Operation[] operations) {
        if (operations == null) {
            fail("null");
        }
        // для каждого объекта Operation полученного из массива операций добавляем текущее расположение вкладки с вложенными объектами в setLocationToolbar. Это нужно для метода проверки доступных операций
        ArrayList<Operation> operationArrayList = new ArrayList<>();
        Arrays.asList(operations).forEach(operation -> operationArrayList.add(operation.setLocationToolbar(currentLocationOfTabWithInnerObjects)));
        return new OperationsVerifications().verifyOperations(operationArrayList.toArray(new Operation[0]));
    }

    /**
     * Проверяем отображение объекта в гриде
     *
     * @param nameOfObjectInTheGrid уникальный текст по которому ищем объект в гриде (наименование объекта)
     */
    public AssertsForDialog itemDisplayed(String nameOfObjectInTheGrid) {
        return new InnerItemVerification().itemDisplayed(nameOfObjectInTheGrid, currentLocationOfTabWithInnerObjects);
    }

    /**
     * Проверяем исчезновение объекта в гриде
     *
     * @param nameOfObjectInTheGrid уникальный текст по которому ищем объект в гриде (наименование объекта)
     */
    public AssertsForDialog itemDisappear(String nameOfObjectInTheGrid) {
        return new InnerItemVerification().itemDisappear(nameOfObjectInTheGrid, currentLocationOfTabWithInnerObjects);
        }

    /**
     * Проверяем, что цвет фона объекта - Зеленый.
     */
    public AssertsForDialog innerItemHasGreenBackground(String nameOfObjectInTheGrid) {
        return new InnerItemVerification().innerItemHasGreenBackground(nameOfObjectInTheGrid, currentLocationOfTabWithInnerObjects);
    }

    /**
     * Проверяем, что цвет фона объекта - Зеленый.
     */
    public AssertsForDialog innerItemHasGreenBackground(ru.melody.web.model.Pages.Form form) {
        return innerItemHasGreenBackground(form.getNameOfObjectForOpenInTheGrid());
    }

    /**
     * Проверяем, что у объекта отсутствует цвет фона.
     */
    public AssertsForDialog innerItemDoesNotHaveColorOnBackground(String nameOfObjectInTheGrid) {
        return new InnerItemVerification().innerItemDoesNotHaveColorOnBackground(nameOfObjectInTheGrid, currentLocationOfTabWithInnerObjects);
    }

    /**
     * Проверяем, что у объекта отсутствует цвет фона.
     */
    public AssertsForDialog innerItemDoesNotHaveColorOnBackground(ru.melody.web.model.Pages.Form form) {
        return innerItemDoesNotHaveColorOnBackground(form.getNameOfObjectForOpenInTheGrid());
    }

}

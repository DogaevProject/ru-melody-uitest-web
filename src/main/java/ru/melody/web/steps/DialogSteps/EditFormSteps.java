package ru.melody.web.steps.DialogSteps;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import ru.melody.web.model.Administration.Fields.Settings.SettingsForFields;
import ru.melody.web.model.Administration.Fields.TypesOfFields.*;
import ru.melody.web.model.Pages.Form;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.fail;
import static ru.melody.web.model.Administration.Fields.Settings.SettingsForFields.NO;

public class EditFormSteps extends DialogSteps {

    /**
     * Наполнение значениями полей формы
     *
     * @param form - должен содержать значения для заполнения в .setFieldsForAddValue()
     */
    public EditFormSteps fillValuesInFields(Form form) {
        fillValues(getCurrentDialog(form));
        return this;
    }

    private void fillValues(Form form) {
        if (form.getFieldsForAddValue() == null) {
            return;
        }
        if (form.getFormLocation() == null) {
            AssertJUnit.fail("Отсутствует параметр FormLocation");
        }
        for (FieldObject fieldObject : form.getFieldsForAddValue()) {
            if (fieldObject.getListValueField() == null & fieldObject.getValueField() == null) {
                continue;
            }

            // СТРОКА
            if (fieldObject.getFieldType() instanceof TypeListFieldsString) {
                TypeListFieldsString fieldString = (TypeListFieldsString) fieldObject.getFieldType();
                if (fieldString.getIsListChoice() == SettingsForFields.YES) {
                    // СТРОКА с выбором из списка
                    if (fieldObject.isMultipleChoice()) {
                        enterValueInFieldTypeStringWithMultipleChoiceInList(form, fieldObject);
                    } else if (fieldObject.isHasLink()) {
                        // поля типа Строка, запись в которых имеет ссылку на справочник
                        enterValueInFieldInputTypeItemWithLinkWithChoiceInList(form.getFormLocation().fillFields().getInputStringChoiceInListField(fieldObject.getFieldName()), form.getFormLocation(), fieldObject.getValueField());
                    } else
                        enterValueInFieldInputWithSingleChoiceInList(form.getFormLocation().fillFields().getInputStringField(fieldObject.getFieldName()), form.getFormLocation(), fieldObject.getValueField());
                } else if (fieldString.getIsListChoice() == NO) {
                    enterValueInFieldInput(form.getFormLocation(), fieldObject.getFieldName(), fieldObject.getValueField());
                }
            }

            // СТРОКА ссылка на пользователя
            else if (fieldObject.getFieldType() instanceof TypeListFieldsStringWithListEmployers) {
                enterValueInFieldTypeStringWithMultipleChoiceInList(form, fieldObject);
            }

            // ТЕКСТ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsText) {
                enterValueInFieldText(form.getFormLocation(), fieldObject.getFieldName(), fieldObject.getValueField());
            }

            // ФАЙЛ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsFile) {
                if (fieldObject.getListValueField() != null) {
                    addFiles(form.getFormLocation(), fieldObject.getFieldName(), fieldObject.getListValueField());
                } else if (fieldObject.getValueField() != null) {
                    addFiles(form.getFormLocation(), fieldObject.getFieldName(), new String[]{fieldObject.getValueField()});
                }
            }
            form.getFormLocation().verifyFields().getElementsOfNameField(fieldObject.getFieldName()).click(); // снимаем фокус с поля для запуска пересчета значений полей по умолчанию.
            sleep(700); // ожидание для окончания пересчета полей. Бывает, что не успевают значения по умолчанию подставится.
        }
    }


    /**
     * Общий метод заполнения пользовательских полей типа Строка, Целое, Вещественное, Дата
     *
     * @param page      расположение поля
     * @param nameField имя поля документа для заполнения
     * @param valueLine передаваемое значение для заполнения
     */
    private void enterValueInFieldInput(ru.melody.web.model.LocationOfElement.Dialog.Form page, String nameField, String valueLine) {
        if (valueLine == null || nameField == null || page == null) {
            fail(null);
        }
        page.fillFields().getInputStringField(nameField).click();
        page.fillFields().getInputStringField(nameField).setValue(valueLine);
    }

    /**
     * Общий метод заполнения пользовательских полей типа Строка, Целое, Вещественное, Дата - мн.выбор, конт.поиск
     *
     * @param field
     * @param page      расположение поля
     * @param valueList передаваемое значение для заполнения
     */
    private void enterValueInFieldInputWithMultipleChoiceInList(SelenideElement field, ru.melody.web.model.LocationOfElement.Dialog.Form page, String[] valueList) {
        if (valueList == null || field == null || page == null) {
            fail(null);
        }
        for (String value : valueList) {
            field.click();
            page.fillFields().getInputContextSearchInStringField().setValue(value);
            // выбор из списка
            page.fillFields().getItemInListOfContextSearchInStringField(value).waitUntil(Condition.visible, 2000).click();
        }
    }


    /**
     * Общий метод заполнения пользовательских полей типа Строка, Целое, Вещественное, Дата - конт.поиск
     * подходит к инпутам полей, записи которых имеют ссылку на справочник
     *
     * @param page  расположение поля
     * @param value передаваемое значение для заполнения
     */
    private void enterValueInFieldInputTypeItemWithLinkWithChoiceInList(SelenideElement field, ru.melody.web.model.LocationOfElement.Dialog.Form page, String value) {
        if (value == null || field == null || page == null) {
            fail(null);
        }
        field.click();  //todo если в поле имеется значение, то при клике происходит выбор записи, а не выставление курсора в поле
        page.fillFields().getInputContextSearchInStringField().setValue(value);
        // выбор из списка
        page.fillFields().getItemInListOfContextSearchInStringField(value).waitUntil(Condition.visible, 5000).click();
    }

    /**
     * Общий метод заполнения пользовательских полей типа Строка, Целое, Вещественное, Дата - конт.поиск
     * подходит к инпутам полей, записи которых не имеют ссылку на справочник
     *
     * @param page  расположение поля
     * @param value передаваемое значение для заполнения
     */
    private void enterValueInFieldInputWithSingleChoiceInList(SelenideElement field, ru.melody.web.model.LocationOfElement.Dialog.Form page, String value) {
        if (value == null || field == null || page == null) {
            fail(null);
        }
        field.click();
        field.setValue(value);
        // выбор из списка
        page.fillFields().getItemInListOfContextSearchInStringField(value).waitUntil(Condition.visible, 5000).click();
    }


    /**
     * Выбор метода заполнения поля типа СТРОКА в зависимости от наличия знаечний в параметрах getListValueField и getValueField
     */
    private void enterValueInFieldTypeStringWithMultipleChoiceInList(Form form, FieldObject fieldObject) {
        if (form == null || fieldObject == null) {
            fail(null);
        }
        if (fieldObject.getListValueField() != null) {
            enterValueInFieldInputWithMultipleChoiceInList(form.getFormLocation().fillFields().getInputStringChoiceInListField(fieldObject.getFieldName()), form.getFormLocation(), fieldObject.getListValueField());

        } else if (fieldObject.getValueField() != null) {
            enterValueInFieldInputTypeItemWithLinkWithChoiceInList(form.getFormLocation().fillFields().getInputStringChoiceInListField(fieldObject.getFieldName()), form.getFormLocation(), fieldObject.getValueField());
        }
    }


    /**
     * Общий метод заполнения пользовательских полей типа Текст
     *
     * @param page      расположение поля
     * @param nameField имя поля для заполнения
     * @param valueLine передаваемое значение для заполнения
     */
    private void enterValueInFieldText(ru.melody.web.model.LocationOfElement.Dialog.Form page, String nameField, String valueLine) {
        if (valueLine == null || nameField == null || page == null) {
            fail(null);
        }
        page.fillFields().getTextArea(nameField).click();
        page.fillFields().getTextArea(nameField).setValue(valueLine);
    }

    /**
     * Прикрепление файлов в поле типа "Файл"
     *
     * @param page        расположение поля
     * @param nameOfFiles названия файлов
     */
    private void addFiles(ru.melody.web.model.LocationOfElement.Dialog.Form page, String nameField, String[] nameOfFiles) {
        if (nameOfFiles == null || nameField == null || page == null) {
            fail(null);
        }
        addAttachFiles(page.fillFields().getAddButtonFile(nameField), nameOfFiles);
    }

    /**
     * Удаление значений полей формы
     *
     * @param form - должен содержать значения для удаления в .setFieldsForDeleteValue()
     */
    public EditFormSteps deleteValuesInFields(Form form) {
        deleteValues(getCurrentDialog(form));
        return this;
    }

    private void deleteValues(Form form) {
        if (form.getFieldsForDeleteValue() == null) {
            return;
        }
        for (FieldObject fieldObject : form.getFieldsForDeleteValue()) {
            if (fieldObject.getListValueField() == null & fieldObject.getValueField() == null) {
                continue;
            }

            // СТРОКА
            if (fieldObject.getFieldType() instanceof TypeListFieldsString) {
                TypeListFieldsString fieldString = (TypeListFieldsString) fieldObject.getFieldType();
                // TODO
            }

            // СТРОКА ссылка на пользователя
            else if (fieldObject.getFieldType() instanceof TypeListFieldsStringWithListEmployers) {
                deleteUserInFormManagementUsers(form.getFormLocation().verifyValuesOfFields().getMultipleInputStringChoiceInListEmployers(fieldObject.getFieldName()), fieldObject.getListValueField());
                // todo  удаление пользователей в том случае, если передавать пользователей будем как объекты Employee, а не как имена в строках
            }

            // ТЕКСТ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsText) {
                // TODO
            }

            // ФАЙЛ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsFile) {
                // TODO
            }
            sleep(500); // ожидание для окончания пересчета полей. Бывает, что не успевают значения по умолчанию подставится.
        }
    }

    /**
     * Удаление записи из поля через кнопку "Удалить" (расположена напротив записи)
     *
     * @param elementsOfFieldWithItems - коллекция элементов содержащая набор видимых записей в поле
     * @param valueOfField
     */
    private void deleteItemInField(ElementsCollection elementsOfFieldWithItems, String valueOfField) {
        if (valueOfField == null || elementsOfFieldWithItems == null) {
            fail(null);
        }
        setCursorOnElementWithText(elementsOfFieldWithItems, valueOfField);  // Устанавливаем фокус на поле
        $(By.xpath("//li[@class=\"x-boundlist-item m4-boundlist-item x-boundlist-item-over\"]//span[contains(@class,\"x-boundlist-action x-boundlist-remove\")]")).click(); // кнопка удаления
        sleep(700);
    }

    /**
     * Удаление пользователей из формы добавления/удаления пользователей
     */
    private void deleteUserInFormManagementUsers(ElementsCollection elementsOfFieldWithItems, String[] employees) {
        if (employees == null || elementsOfFieldWithItems == null) {
            fail(null);
        }
        setCursorOnElementWithText(elementsOfFieldWithItems, employees[0]);  // Устанавливаем фокус на поле
        $(By.xpath("//li[@class=\"x-boundlist-item m4-boundlist-item x-boundlist-item-over\"]//span[contains(@class,\"x-boundlist-action x-boundlist-select\")]")).click(); // кнопка открытия формы добавления/удаления пользователей
        deleteUserInForm(employees);
    }

    /**
     * Удаление пользователя из поля
     * @param nameOfUser можно указывать частично фио пользоватлея
     */
    public EditFormSteps deleteUserInField(String fieldName, String nameOfUser) {
        deleteItemInField(getCurrentDialog().getFormLocation().verifyValuesOfFields().getMultipleInputStringChoiceInListEmployers(fieldName), nameOfUser);
        return this;
    }

    /**
     * Удаление записи из поля типа СТРОКА - запись содержит ссылку
     */
    public EditFormSteps deleteItemInStringField(String fieldName, String valueOfField) {
        deleteItemInField(getCurrentDialog().getFormLocation().verifyValuesOfFields().getMultipleInputStringChoiceInListField(fieldName), valueOfField);
        return this;
    }

    /**
     * Получение значение из поля типа Строка
     *
     * @param fieldName
     */
    public String getValueFromInputStringField(String fieldName) {
        return getCurrentDialog().getFormLocation().verifyValuesOfFields().getInputStringField(fieldName).getValue();
    }

}

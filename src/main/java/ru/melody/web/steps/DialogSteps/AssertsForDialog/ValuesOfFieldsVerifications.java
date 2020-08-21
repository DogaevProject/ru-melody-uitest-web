package ru.melody.web.steps.DialogSteps.AssertsForDialog;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.melody.web.model.Administration.Fields.Settings.SettingsForFields;
import ru.melody.web.model.Administration.Fields.TypesOfFields.*;
import ru.melody.web.model.Pages.Form;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;
import static ru.melody.web.model.Administration.Fields.Settings.SettingsForFields.NO;

/**
 * Методы для проверки значений в полях диалога
 */
class ValuesOfFieldsVerifications extends FieldsVerifications {

    /**
     * Проверка значений в полях
     */
    ValuesOfFieldsVerifications verifyValuesInFields(Form form) {
        if (form.getFields() == null) {
            return this;
        }
        if (form.getFormLocation() == null) {
            fail("Отсутствует параметр FormLocation");
        }
        for (FieldObject fieldObject : form.getFields()) {
            // СТРОКА
            if (fieldObject.getFieldType() instanceof TypeListFieldsString) {
                TypeListFieldsString fieldString = (TypeListFieldsString) fieldObject.getFieldType();
                if (fieldString.getIsListChoice() == SettingsForFields.YES) {
                    // СТРОКА с выбором из списка
                    if (fieldObject.isMultipleChoice()) {
                        InTypeStringWithLink(fieldObject);
                    } else
                        InTypeString(fieldObject);
                } else if (fieldString.getIsListChoice() == NO) {
                    InTypeString(fieldObject);
                }
            }

            // СТРОКА ссылка на пользователя
            else if (fieldObject.getFieldType() instanceof TypeListFieldsStringWithListEmployers) {
                InTypeStringWithChoiceInListEmployers(fieldObject);
            }

            // ТЕКСТ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsText) {
                if (fieldObject.isEditable()) {
                    InTypeText(fieldObject);
                } else
                    InNotEditableTypeText(fieldObject);
            }

            // Логический
            else if (fieldObject.getFieldType() instanceof TypeListFieldsBoolean) {
                verifyValueInInputOfFieldBoolean(fieldObject.getValueBooleanField(), currentDialog.getFormLocation().verifyValuesOfFields().getCheckBox(fieldObject.getFieldName()));
            }

            // ФАЙЛ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsFile) {
                InTypeStringWithLink(fieldObject);
            }
        }
        return this;
    }

    /**
     * Проверка значения в поле тип СТРОКА (Выбор из списка == Да; Мн. выбор  == Да;)
     */
    ValuesOfFieldsVerifications verifyValueInFieldStringWithChoiceInListMultiple(String fieldName, String value) {
        FieldObject fieldObject = new TypeListFieldsString()
                .setFieldName(fieldName)
                .setValueField(value);
        InTypeStringWithLink(fieldObject);
        return this;
    }


    /**
     * Ожидание и Проверка значения в поле тип Текст (поле недоступно для редактирования)
     * <p>
     * Нужно для автоматически формируемого текста резолюции
     */
    ValuesOfFieldsVerifications verifyValueInNotEditableFieldText(String fieldName, String value) {
        FieldObject fieldObject = new TypeListFieldsString()
                .setFieldName(fieldName)
                .setValueField(value);
        InNotEditableTypeText(fieldObject);
        return this;
    }


    /**
     * Ожидание и Проверка значения в поле тип Текст (поле доступно для редактирования)
     * Нужно для автоматически формируемого текста резолюции
     */
    ValuesOfFieldsVerifications verifyValueInFieldTextAfterEditing(String fieldName, String value) {
        FieldObject fieldObject = new TypeListFieldsString()
                .setFieldName(fieldName)
                .setValueField(value);
        InTypeText(fieldObject);
        return this;
    }


    // СТРОКА
    private void InTypeString(FieldObject fieldObject) {
        verifyValueInInputOfField(fieldObject.getValueField(), currentDialog.getFormLocation().verifyValuesOfFields().getInputStringField(fieldObject.getFieldName()));
    }

    // СТРОКА - запись в которой содержит ссылку, бывает с выбором из списка значений
    private void InTypeStringWithLink(FieldObject fieldObject) {
        if (fieldObject.getListValueField() != null) {
            verifyTextInMultipleInputOfField(fieldObject, currentDialog.getFormLocation().verifyValuesOfFields().getMultipleInputStringChoiceInListField(fieldObject.getFieldName()));
        } else if (fieldObject.getValueField() != null) {
            verifyTextInInputOfField(fieldObject.getValueField(), currentDialog.getFormLocation().verifyValuesOfFields().getInputStringChoiceInListField(fieldObject.getFieldName()));
        }
    }

    // СТРОКА ссылка на пользователя
    private void InTypeStringWithChoiceInListEmployers(FieldObject fieldObject) {
        if (fieldObject.getListValueField() != null) {
            verifyTextInMultipleInputOfField(fieldObject, currentDialog.getFormLocation().verifyValuesOfFields().getMultipleInputStringChoiceInListEmployers(fieldObject.getFieldName()));
        } else if (fieldObject.getValueField() != null) {
            verifyTextInInputOfField(fieldObject.getValueField(), currentDialog.getFormLocation().verifyValuesOfFields().getInputStringChoiceInListEmployers(fieldObject.getFieldName()));
        }
    }

    // ТЕКСТ
    private void InTypeText(FieldObject fieldObject) {
        verifyValueInInputOfField(fieldObject.getValueField(), currentDialog.getFormLocation().verifyValuesOfFields().getInputText(fieldObject.getFieldName()));
    }

    // ТЕКСТ - недоступное для редактирования
    private void InNotEditableTypeText(FieldObject fieldObject) {
        verifyTextInInputOfField(fieldObject.getValueField(), currentDialog.getFormLocation().verifyValuesOfFields().getInputTextNotEditable(fieldObject.getFieldName()));
    }

    /**
     * Проверка значений в инпутах
     *
     * @param valueInInput передаваемое значенние поля
     * @param element      элемент
     */
    private void verifyValueInInputOfField(String valueInInput, SelenideElement element) {
        if (valueInInput == null) {
            return;
        }
        element.waitUntil(Condition.exist, 2000).waitUntil(Condition.exactValue(valueInInput), 4000);
    }


    /**
     * Проверка присутсвия значений в полях и отсутствия лишних значений полей
     * Сравнение значений в полях отображаемых в данный момент в форме со значениями полей, которые должны быть
     *
     * @param fieldObject           проверяемое поле
     * @param elementsOfNamesFields элементы со значениями полей, которые отображаются в данный момент
     */
    private void verifyTextInMultipleInputOfField(FieldObject fieldObject, ElementsCollection elementsOfNamesFields) {
        if (fieldObject.getListValueField() == null) {
            return;
        }
        // значения всех полей, которые присутствуют
        ArrayList<String> valuesThatShouldBe = new ArrayList<>(Arrays.asList(fieldObject.getListValueField()));

        ArrayList<String> currentTexts = new ArrayList<>();
        // каждый отображаемый в данный момент элемент помещаем в список в виде строки
        for (SelenideElement element : elementsOfNamesFields) {
            element.waitUntil(Condition.exist, 2000);
            currentTexts.add(element.getText());
        }

        // Проверяем присутствие нужных/отсутствие лишних значений полей
        try {
            assertEquals(currentTexts, valuesThatShouldBe);
        } catch (Error e) {
            //доп. проверка для выяснения причины ошибки - отсутсвие нужных полей/присутствие лишних значений или Неверный порядок
            if (valuesHasWrongOrder(currentTexts, valuesThatShouldBe)) {
                fail("\n" + " Проверка списка значений в поле " + fieldObject.getFieldName() + " : " + "\n"
                        + getReportOfDifferenceBetweenTwoArrayLists(valuesThatShouldBe, currentTexts) + "\n"
                        + " см. Неверный порядок"
                );
            } else
                fail("\n" + " Проверка списка значений в поле " + fieldObject.getFieldName() + " : " + "\n"
                        + getReportOfDifferenceBetweenTwoArrayLists(valuesThatShouldBe, currentTexts));
        }
    }


    /**
     * Проверка значений в инпутах
     * с ожиданием значения - Нужно для автоматически формируемых значений - прим. текста резолюции
     *
     * @param valueInInput передаваемое значенние поля
     * @param element      элемент
     */
    private void verifyTextInInputOfField(String valueInInput, SelenideElement element) {
        if (valueInInput == null) {
            return;
        }
        element.waitUntil(Condition.exist, 2000).waitUntil(Condition.exactText(valueInInput), 4000);
    }

    /**
     * Проверка значения в поле типа Логический
     *
     * @param value   передаваемое значенние поля
     * @param element элемент
     */
    private void verifyValueInInputOfFieldBoolean(Boolean value, SelenideElement element) {
        if (value) {
            element.waitUntil(Condition.exist, 2000).waitUntil(Condition.checked, 4000);
        } else element.waitUntil(Condition.exist, 2000).waitUntil(Condition.not(Condition.checked), 4000);
    }

}

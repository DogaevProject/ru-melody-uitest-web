package ru.melody.web.steps.DialogSteps.AssertsForDialog;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;
import ru.melody.web.model.Pages.Form;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;


/**
 * Методы для проверки полей формы
 */
public class FieldsVerifications extends AssertsForDialog {

    public FieldsVerifications verifyValueOfNamesOfCustomFields(Form form) {
        if (form.getFields() == null) {
            return this;
        }
        form.getFormLocation().verifyFields().getElementsOfNamesFields().get(0).waitUntil(Condition.visible, 5000);
        verifyNamesOfFields(form.getFields(), form.getFormLocation().verifyFields().getElementsOfNamesFields());
        return this;
    }

    /**
     * Проверка присутсвия названий полей, которые должны отображаться и отсутствия лишних названий полей
     *
     * @param fields                набор полей, которые должны отображаться
     * @param elementsOfNamesFields элементы со значениями названий полей, которые отображаются в данный момент в форме
     */
    private void verifyNamesOfFields(FieldObject[] fields, ElementsCollection elementsOfNamesFields) {
        if (fields == null || elementsOfNamesFields == null) {
            Assert.fail(null);
        }
        // Названия всех полей, которые присутствуют
        ArrayList<String> valuesThatShouldBe = new ArrayList<String>();
        for (FieldObject field : fields) {
            valuesThatShouldBe.add(field.getFieldName());
        }

        ArrayList<String> currentValuesInInputs = new ArrayList<>();
        // каждый отображаемый в данный момент элемент помещаем в список в виде строки
        for (SelenideElement element : elementsOfNamesFields) {
                element.waitUntil(Condition.exist, 2000);
                currentValuesInInputs.add(element.getText().replaceAll("[*:]",""));
        }

        // Проверяем присутствие нужных/отсутствие лишних названий полей
        try {
            assertEquals(currentValuesInInputs, valuesThatShouldBe);
        } catch (Error e) {
            //доп. проверка для выяснения причины ошибки - отсутсвие нужных полей/присутствие лишних полей или Неверный порядок
            if (valuesHasWrongOrder(currentValuesInInputs, valuesThatShouldBe)) {
                fail("\n" + " Проверка названий списка полей: " + "\n"
                        + getReportOfDifferenceBetweenTwoArrayLists(valuesThatShouldBe, currentValuesInInputs) + "\n"
                        + " см. Неверный порядок полей"
                );
            } else
            fail("\n" + " Проверка названий списка полей: " + "\n"
                    + getReportOfDifferenceBetweenTwoArrayLists(valuesThatShouldBe, currentValuesInInputs));
        }
    }

}

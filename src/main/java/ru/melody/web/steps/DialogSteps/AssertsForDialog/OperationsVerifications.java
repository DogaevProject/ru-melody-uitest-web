package ru.melody.web.steps.DialogSteps.AssertsForDialog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.melody.web.model.Pages.Operations.AddFileOperation;
import ru.melody.web.model.Pages.Operations.AdditionOperation;
import ru.melody.web.model.Pages.Operations.Operation;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

/**
 * Проверки операций документа
 */
public class OperationsVerifications extends AssertsForDialog {

    /**
     * Проверка присутствия кнопок операций/отсутствия лишних операций среди доступных на тулбаре
     * Проверяем порядок отображаемых операций - должен быть такой, в котором они перечислены операции в dataprovider-е
     */
    public OperationsVerifications verifyOperations(Operation[] operations) {
        waitToolBar(operations);

        // Элементы операций
        ArrayList<SelenideElement> elementsOfOperationsWhichShouldBeVisible = new ArrayList<>(getElementsOfOperationsThatShouldBeVisible(operations)); // Элементы которые должны отображаться
        ArrayList<SelenideElement> elementsOfCurrentVisibleOperations = new ArrayList<>(getElementsOfOperationsThatVisibleNow(operations)); // Элементы которые отображаются

        // Каждый элемент, котрый должен отображаться, помещаем в список в виде строки
        ArrayList<String> elementsOfOperationsWhichShouldBeVisibleString = new ArrayList<>();
        for (SelenideElement element : elementsOfOperationsWhichShouldBeVisible) {
            elementsOfOperationsWhichShouldBeVisibleString.add(element.toString());
        }

        // Каждый отображаемый в данный момент элемент помещаем в список в виде строки
        ArrayList<String> elementsOfCurrentVisibleOperationsString = new ArrayList<>();
        for (SelenideElement element : elementsOfCurrentVisibleOperations) {
            elementsOfCurrentVisibleOperationsString.add(element.toString());
        }

        // Сортируем значения в списках перед их сравнением - нужно, если не важен порядок в котором перечислены операции в dataprovider-е
        // Collections.sort(elementsOfOperationsWhichShouldBeVisibleString);
        // Collections.sort(elementsOfCurrentVisibleOperationsString);

        // Проверяем присутствие нужных/отсутствие лишних операций
        try {
            assertEquals(elementsOfOperationsWhichShouldBeVisibleString, elementsOfCurrentVisibleOperationsString);
        } catch (Error e) {
            // Формирование сообщения об ошибке

            // Текст на кнопках операций
            ArrayList<String> textOnOperationsWhichShouldBeVisible = getNameOfOperationsThatShouldBeVisible(operations);
            ArrayList<String> textOnCurrentVisibleOperations = getNameOfCurrentVisibleOperations(operations);

            //доп. проверка для выяснения причины ошибки - отсутсвие нужных операций/присутствие лишних операций или Неверный порядок
            if (valuesHasWrongOrder(elementsOfCurrentVisibleOperationsString, elementsOfOperationsWhichShouldBeVisibleString)) {
                fail("\n" + " Проверка текста операций: " + "\n"
                        + getReportOfDifferenceBetweenTwoArrayLists(textOnOperationsWhichShouldBeVisible, textOnCurrentVisibleOperations) + "\n"
                        + " см. Неверный порядок операций");
            } else
                fail("\n" + " Проверка текста операций: " + "\n"
                        + getReportOfDifferenceBetweenTwoArrayLists(textOnOperationsWhichShouldBeVisible, textOnCurrentVisibleOperations) + "\n"
                        + "\n" + " Проверка элементов операций: " + "\n"
                        + getReportOfDifferenceBetweenTwoArrayLists(elementsOfOperationsWhichShouldBeVisibleString, elementsOfCurrentVisibleOperationsString));
        }
        return this;
    }

    private void waitToolBar(Operation[] operations) {
        if (operations[0].getLocationToolbar().verifyOperation().getMaskOfToolbar() != null) {
            try {
                operations[0].getLocationToolbar().verifyOperation().getMaskOfToolbar().waitUntil(Condition.visible, 500);
            } catch (Error ignored) {
            }
        }
        operations[0].getLocationToolbar().verifyOperation().getToolbar().waitUntil(Condition.visible, 10000);
    }


    /**
     * Элементы которые отображаются
     *
     *  @return спискок элементов операций
     */
    private ArrayList<SelenideElement> getElementsOfOperationsThatVisibleNow(Operation[] operations) {
        ArrayList<SelenideElement> allVisibleOperation = new ArrayList<>();
        allVisibleOperation.addAll(operations[0].getLocationToolbar().verifyOperation().getAllOperationOnToolbar());
        return allVisibleOperation;
    }

    /**
     * Получаем спискок элементов операций, которые должны отображаться
     *
     * @return спискок элементов операций
     */
    private ArrayList<SelenideElement> getElementsOfOperationsThatShouldBeVisible(Operation[] operations) {
        // определяем список элементов, которые должны отображаться
        ArrayList<SelenideElement> allShouldVisibleElement = new ArrayList<>();

        for (Operation operation : operations) {
            if (operation.getName() != null) {
                allShouldVisibleElement.add(operation.getLocationToolbar().verifyOperation().getOperationOnToolbarByName(operation.getName()));
            } else if (operation instanceof AddFileOperation) {
                SelenideElement element = operation.getLocationToolbar().verifyOperation().getOperationAddFile();
                if (element != null) {
                    allShouldVisibleElement.add(element);
                } else fail("Нет элемента для данной операции");
            } else if (operation instanceof AdditionOperation) {
                SelenideElement element = operation.getLocationToolbar().verifyOperation().getOperationAddition();
                if (element != null) {
                    allShouldVisibleElement.add(element);
                } else fail("Нет элемента для данной операции");
            }
        }
        return allShouldVisibleElement;
    }

    /**
     * Получаем спискок названий на операциях, которые должны отображаться
     *
     * @return спискок названий операций
     */
    private ArrayList<String> getNameOfOperationsThatShouldBeVisible(Operation[] operations) {
        // определяем список элементов, которые должны отображаться
        ArrayList<String> allShouldVisibleElement = new ArrayList<>();
        for (Operation operation : operations) {
            if (operation.getName() != null) {
                allShouldVisibleElement.add(operation.getName());
            } else
                allShouldVisibleElement.add(getNamesOfSystemButtonThatShouldBeVisible(operation));
        }
        return allShouldVisibleElement;
    }


    /**
     * Получаем спискок названий операций, которые отображаются в данный момент
     *
     * @return спискок названий операций
     */
    public ArrayList<String> getNameOfCurrentVisibleOperations(Operation[] operations) {
        ArrayList<String> textOnCurrentVisibleOperations = new ArrayList<>();
        for (SelenideElement elementOfOperation : operations[0].getLocationToolbar().verifyOperation().getAllOperationOnToolbar()) {
            if (!(elementOfOperation.getText().equals(""))) {
                // операции которые содержат текст на кнопке
                textOnCurrentVisibleOperations.add(elementOfOperation.getText());
            } else
                // операции которые не содержат текста на кнопке
                textOnCurrentVisibleOperations.add(getNamesOfVisibleSystemButton(operations, elementOfOperation));
        }
        return textOnCurrentVisibleOperations;
    }

    /**
     * Получаем названия системных операций (не содержат текста на кнопке) - добавления файла, дополнительно.
     *
     * @param operations
     * @param elementOfOperation
     */
    public String getNamesOfVisibleSystemButton(Operation[] operations, SelenideElement elementOfOperation) {
        String textOnCurrentVisibleOperations = "";
        if (operations[0].getLocationToolbar().verifyOperation().getOperationAddFile() != null && operations[0].getLocationToolbar().verifyOperation().getOperationAddition() != null) {
            if (operations[0].getLocationToolbar().verifyOperation().getOperationAddFile().exists() && operations[0].getLocationToolbar().verifyOperation().getOperationAddition().exists()) {
                if (elementOfOperation.equals(operations[0].getLocationToolbar().verifyOperation().getOperationAddFile())) {
                    textOnCurrentVisibleOperations = ("Добавить файл");
                } else if (elementOfOperation.equals(operations[0].getLocationToolbar().verifyOperation().getOperationAddition())) {
                    textOnCurrentVisibleOperations = ("Дополнительно");
                } else textOnCurrentVisibleOperations = ("Неопознанная операция");
            } else if (operations[0].getLocationToolbar().verifyOperation().getOperationAddFile().exists() && !(operations[0].getLocationToolbar().verifyOperation().getOperationAddition().exists())) {
                if (elementOfOperation.equals(operations[0].getLocationToolbar().verifyOperation().getOperationAddFile())) {
                    textOnCurrentVisibleOperations = ("Добавить файл");
                } else textOnCurrentVisibleOperations = ("Неопознанная операция");
            } else if (operations[0].getLocationToolbar().verifyOperation().getOperationAddition().exists() && !(operations[0].getLocationToolbar().verifyOperation().getOperationAddFile().exists())) {
                if (elementOfOperation.equals(operations[0].getLocationToolbar().verifyOperation().getOperationAddition())) {
                    textOnCurrentVisibleOperations = ("Дополнительно");
                } else textOnCurrentVisibleOperations = ("Неопознанная операция");
            } else textOnCurrentVisibleOperations = ("Неопознанная операция");
        } else if (operations[0].getLocationToolbar().verifyOperation().getOperationAddFile() == null && operations[0].getLocationToolbar().verifyOperation().getOperationAddition() != null) {
            if (operations[0].getLocationToolbar().verifyOperation().getOperationAddition().exists()) {
                if (elementOfOperation.equals(operations[0].getLocationToolbar().verifyOperation().getOperationAddition())) {
                    textOnCurrentVisibleOperations = ("Дополнительно");
                } else textOnCurrentVisibleOperations = ("Неопознанная операция");
            } else textOnCurrentVisibleOperations = ("Неопознанная операция");
        } else if (operations[0].getLocationToolbar().verifyOperation().getOperationAddFile() != null && operations[0].getLocationToolbar().verifyOperation().getOperationAddition() == null) {
            if (operations[0].getLocationToolbar().verifyOperation().getOperationAddFile().exists()) {
                if (elementOfOperation.equals(operations[0].getLocationToolbar().verifyOperation().getOperationAddFile())) {
                    textOnCurrentVisibleOperations = ("Добавить файл");
                } else textOnCurrentVisibleOperations = ("Неопознанная операция");
            } else textOnCurrentVisibleOperations = ("Неопознанная операция");
        } else textOnCurrentVisibleOperations = ("Неопознанная операция"); // если обе операции  == null
        return textOnCurrentVisibleOperations;
    }


    /**
     * Получаем названия системных операций - добавления файла, дополнительно.
     *
     * @param operation
     */
    public String getNamesOfSystemButtonThatShouldBeVisible(Operation operation) {
        String textOnCurrentVisibleOperations = "";

        if (operation instanceof AddFileOperation) {
            textOnCurrentVisibleOperations = ("Добавить файл");
        } else if (operation instanceof AdditionOperation) {
            textOnCurrentVisibleOperations = ("Дополнительно");
        }
        return textOnCurrentVisibleOperations;
    }


}


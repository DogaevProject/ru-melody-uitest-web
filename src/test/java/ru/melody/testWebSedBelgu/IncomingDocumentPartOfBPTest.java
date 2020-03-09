package ru.melody.testWebSedBelgu;

import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.melody.web.steps.MainPageSteps.MainPageSteps;
import ru.melody.web.steps.LoginSteps.LoginSteps;
import ru.melody.web.steps.DialogSteps.MaximizedDialogSteps;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Document;
import ru.melody.web.model.Pages.Resolution;

import static com.codeborne.selenide.Selenide.page;

@Listeners({ScreenShooter.class, TextReport.class})
@Report
public class IncomingDocumentPartOfBPTest extends ru.melody.data.dataproviders.IncomingDocumentPartOfBPTest {


    private LoginSteps loginSteps;
    private MainPageSteps mainPageSteps;
    private MaximizedDialogSteps dialogSteps;

    @BeforeClass
    public void beforeTest() {
        loginSteps = page(LoginSteps.class);
        mainPageSteps = page(MainPageSteps.class);
        dialogSteps = page(MaximizedDialogSteps.class);
    }

    // Регистрация входящего документа с резолюцией из ПМ «Входящие документы» (Асина М.В.)
    @Test(dataProvider = "Step_Incoming_PartOfBPTest_1", dataProviderClass = IncomingDocumentPartOfBPTest.class)
    public void verifyDocument1(Employee employee, Folder folder, Document document, Resolution resolution) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .clickButtonInGrid("Добавить");
        dialogSteps
                .fillValuesInFields(document)
                .assertThat(document).hasValuesInFields();
        nameOfObjectForOpenInTheGrid = dialogSteps.getValueFromInputStringField("Входящий номер");
        dialogSteps
                .editFiles().addFiles(document)
                .clickButtonInMainToolbar("Зарегистрировать с резолюцией");
        dialogSteps.fillValuesInFields(resolution)
                .assertThat().hasValueInNotEditableFieldText("Резолюция", resolution.getTextOfResolution())
                .clickButtonInMainToolbar("Сохранить")
                .clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Принятие документа на контроль сотрудником КАЦ (Немыкина Т.В.)
    @Test(dataProvider = "Step_Incoming_4", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument1")
    public void verifyDocument2(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Принять на контроль");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Ознакомление с документом начальником КАЦ (Голубёвская И.А.)
    @Test(dataProvider = "Step_Incoming_5", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument2")
    public void verifyDocument3(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Ознакомиться");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Создание резолюции исполнителем резолюции (Банчук Ю.А.)
    @Test(dataProvider = "Step_Incoming_PartOfBPTest_4", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument3")
    public void verifyDocument4(Employee employee, Folder folder, Document document, Resolution resolution) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Создать резолюцию");
        dialogSteps
                .fillValuesInFields(resolution)
                .clickButtonInMainToolbar("Сохранить")
                .clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Создание исходящего документа (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_Incoming_20", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument4")
    public void verifyDocument5(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, Document document2, Document document3) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .clickButtonInTabWithInnerObjects("Добавить");
        dialogSteps.clickButtonInMainToolbar("Добавить");
        dialogSteps.fillValuesInFields(document2)
                .assertThat(document2)
                .hasValuesInFields()
                .hasValueInInFieldStringWithChoiceInListMultiple("Связанные документы", nameOfObjectForOpenInTheGrid + " - Входящий документ")
                .clickTab("Маршрут")
                .editRouteScheme().fillRouteScheme(document2)
                .clickTab("Карточка")
                .clickButtonInMainToolbar("Сохранить")
                .assertThat().hasTextMessageInAlertDialog("Проект документа успешно сохранен")
                .clickButtonInAlertDialog("OK")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Регистрация исходящего документа (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_Outcoming__1", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument5")
    public void verifyDocument6(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Отправить на согласование")
                .assertThat(document).hasValuesInFields().hasValueInInFieldStringWithChoiceInListMultiple("Связанные документы", nameOfObjectForOpenInTheGrid + " - Входящий документ")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Внутреннее согласование документа. Возврат исполнителю (Мурье С.С.)
//    @Test(dataProvider = "Step_2", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument6")
//    public void verifyDocument7(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
//        loginSteps.loginAs(employee).waitLoadMainPage();
//        foldersSteps.openItemOfMenuTree(folder);
//        foldersSteps.openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
//        baseStepsM.clickTabInDocForm("Исходящие документы");
//        baseStepsM.selectInnerItemInDoc(document);
//        baseStepsM.clickButtonInTabWithResolutions("Редактировать");
//        baseStepsM.clickButtonInSecondMaximizedDialog("Вернуть исполнителю");
//        editFieldOfFormSteps.fillValueInFields(acceptFormBeforeOperation_Step_1);
//        baseStepsM.clickButtonInAcceptForm("Вернуть исполнителю");
//        baseStepsM.clickButtonInDocForm("Закрыть");
//        internalSteps.logout();
//    }
//
//    // Доработка проекта служебного документа после этапа «Внутреннее согласование»
//    @Test(dataProvider = "Step_3", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument7")
//    public void verifyDocument8(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
//        loginSteps.loginAs(employee).waitLoadMainPage();
//        //foldersSteps.openMainItemOfMenuTree(folder);
//        foldersSteps.openItemOfMenuTree(folder);
//        foldersSteps.openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
//        baseStepsM.clickTabInDocForm("Исходящие документы");
//        baseStepsM.selectInnerItemInDoc(document);
//        baseStepsM.clickButtonInTabWithResolutions("Редактировать");
//        baseStepsM.clickButtonInSecondMaximizedDialog("Вернуть на согласование");
//        editFieldOfFormSteps.fillValueInFields(acceptFormBeforeOperation_Step_1);
//        baseStepsM.clickButtonInAcceptForm("Вернуть на согласование");
//        baseStepsM.clickButtonInDocForm("Закрыть");
//        internalSteps.logout();
//    }
//
//    // Внутреннее согласование документа. (Мурье С.С.)
//    // Внутреннее согласование документа (Старусев И.В.)
//    @Test(dataProvider = "Step_4", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument8")
//    public void verifyDocument9(Employee employee, Folder folder, Document document) throws Exception {
//        loginSteps.loginAs(employee).waitLoadMainPage();
//        foldersSteps.openItemOfMenuTree(folder);
//        foldersSteps.openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
//        baseStepsM.clickTabInDocForm("Исходящие документы");
//        baseStepsM.selectInnerItemInDoc(document);
//        baseStepsM.clickButtonInTabWithResolutions("Редактировать");
//        baseStepsM.clickButtonInSecondMaximizedDialog("Согласовать");
//        baseStepsM.clickButtonInDocForm("Закрыть");
//        internalSteps.logout();
//    }

    // Внешнее согласование документа (Немцев А.Н.)
    @Test(dataProvider = "Step_5", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument6")
    public void verifyDocument10(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Не согласовать");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation_Step_1)
                .clickButtonInMainToolbar("Не согласовать");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Согласование руководителем исполнителя (Банчук Ю.А.)
    @Test(dataProvider = "Step_6", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument10")
    public void verifyDocument11(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача бумажного оригинала» (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_7", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument11")
    public void verifyDocument12(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Передано на подпись");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача на подписание» (Кашина А.П.)
    @Test(dataProvider = "Step_8", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument12")
    public void verifyDocument13(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps
                .fillValuesInFields(document)
                .clickButtonInMainToolbar("Оригинал принят");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Подписание» (Никулина Е.В.)
    @Test(dataProvider = "Step_9", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument13")
    public void verifyDocument14(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_9) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Привлечь согласующего");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation_Step_9)
                .clickButtonInMainToolbar("Привлечь согласующего");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Этап «Дополнительное согласование»  (Маматов А.В.)
    @Test(dataProvider = "Step_10", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument14")
    public void verifyDocument15(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps
                .clickButtonInMainToolbar("Согласовать");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача бумажного оригинала» (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_11", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument15")
    public void verifyDocument16(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Передано на подпись");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача на подписание» (Асина М.В.)
    @Test(dataProvider = "Step_12", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument16")
    public void verifyDocument17(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps
                .fillValuesInFields(document)
                .clickButtonInMainToolbar("Оригинал принят");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    //  Этап «Подписание» (Никулина Е.В.)
    @Test(dataProvider = "Step_13", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument17")
    public void verifyDocument18(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps
                .clickButtonInMainToolbar("Подписано")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Отправка документа исполнителем (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_14", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument18")
    public void verifyDocument19(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_14) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps
                .clickButtonInMainToolbar("Документ подготовлен");

        dialogSteps.fillValuesInFields(acceptFormBeforeOperation_Step_14)
                .assertThat(acceptFormBeforeOperation_Step_14).hasValuesInFields()
                .clickButtonInMainToolbar("Документ подготовлен");
        dialogSteps
                .assertThat(document).hasValuesInFields()
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Снятие документа с контроля (Голубевская И.А.)
   @Test(dataProvider = "Step_15", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument19")
    public void verifyDocument20(Employee employee, Folder folder, Folder folder2, Document document, Document document2) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Исходящие документы")
                .selectInnerItem(document2)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps.clickButtonInMainToolbar("Снять с контроля");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        mainPageSteps
                .openItemOfMenuTree(folder2)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .assertThat(document).hasValuesInFields()
                .clickTab("Исходящие документы")
                .selectInnerItem(document2)
                .clickButtonInTabWithInnerObjects("Редактировать");
        dialogSteps
                .assertThat(document2).hasValuesInFields()
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

}

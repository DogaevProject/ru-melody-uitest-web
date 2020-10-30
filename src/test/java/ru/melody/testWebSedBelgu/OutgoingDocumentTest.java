package ru.melody.testWebSedBelgu;

import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.melody.data.dataproviders.SedBelgu.OutgoingDocument;
import ru.melody.web.steps.DialogSteps.MaximizedDialogSteps;
import ru.melody.web.steps.MainPageSteps.MainPageSteps;
import ru.melody.web.steps.LoginSteps.LoginSteps;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Document;

import static com.codeborne.selenide.Selenide.page;

@Listeners({ScreenShooter.class, TextReport.class})
@Report
public class OutgoingDocumentTest extends OutgoingDocument {

    private LoginSteps loginSteps;
    private MainPageSteps mainPageSteps;
    private MaximizedDialogSteps dialogSteps;

    @BeforeClass
    public void beforeTest() {
        loginSteps = page(LoginSteps.class);
        mainPageSteps = page(MainPageSteps.class);
        dialogSteps = page(MaximizedDialogSteps.class);
    }

    // Регистрация исходящего документа (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_1", dataProviderClass = OutgoingDocument.class)
    public void verifyDocument1(Employee employee, Folder folder, Document document, Form formWithParametersForNewDocument_Step_1) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .clickButtonInGrid("Добавить");
        dialogSteps.assertThat(formWithParametersForNewDocument_Step_1)
                .hasFields()
                .hasValuesInFields()
                .hasOperation()
                .clickButtonInMainToolbar("Добавить");
        dialogSteps
                .fillValuesInFields(document)
                .clickTab("Маршрут")
                .editRouteScheme().fillRouteScheme(document)
                .clickTab("Карточка")
                .clickButtonInMainToolbar("Отправить на согласование")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Внутреннее согласование документа. Возврат исполнителю (Мурье С.С.)
    @Test(dataProvider = "Step_2", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument1")
    public void verifyDocument2(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Вернуть исполнителю");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation_Step_1)
                .clickButtonInMainToolbar("Вернуть исполнителю");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Доработка проекта служебного документа после этапа «Внутреннее согласование»
    @Test(dataProvider = "Step_3", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument2")
    public void verifyDocument3(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Вернуть на согласование");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation_Step_1)
                .clickButtonInMainToolbar("Вернуть на согласование");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Внутреннее согласование документа. (Мурье С.С.)
    // Внутреннее согласование документа (Старусев И.В.)
    @Test(dataProvider = "Step_4", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument3")
    public void verifyDocument4(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Внешнее согласование документа (Немцев А.Н.)
    @Test(dataProvider = "Step_5", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument4")
    public void verifyDocument5(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Не согласовать");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation_Step_1)
                .clickButtonInMainToolbar("Не согласовать");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Согласование руководителем исполнителя (Банчук Ю.А.)
    @Test(dataProvider = "Step_6", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument5")
    public void verifyDocument6(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача бумажного оригинала» (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_7", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument6")
    public void verifyDocument7(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Передано на подпись");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача на подписание» (Кашина А.П.)
    @Test(dataProvider = "Step_8", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument7")
    public void verifyDocument8(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps
                .fillValuesInFields(document)
                .clickButtonInMainToolbar("Оригинал принят");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Подписание» (Никулина Е.В.)
    @Test(dataProvider = "Step_9", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument8")
    public void verifyDocument9(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_9) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Привлечь согласующего");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation_Step_9)
                .clickButtonInMainToolbar("Привлечь согласующего");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Этап «Дополнительное согласование»  (Маматов А.В.)
    @Test(dataProvider = "Step_10", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument9")
    public void verifyDocument10(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача бумажного оригинала» (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_11", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument10")
    public void verifyDocument11(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Передано на подпись");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Этап «Передача на подписание» (Асина М.В.)
    @Test(dataProvider = "Step_12", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument11")
    public void verifyDocument12(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Оригинал принят");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    //  Этап «Подписание» (Никулина Е.В.)
    @Test(dataProvider = "Step_13", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument12")
    public void verifyDocument13(Employee employee, Folder folder, Document document) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps
                .clickButtonInMainToolbar("Подписано")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Отправка документа исполнителем (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_14", dataProviderClass = OutgoingDocument.class, dependsOnMethods = "verifyDocument13")
    public void verifyDocument14(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_14) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(document);
        dialogSteps.clickButtonInMainToolbar("Документ подготовлен");
        dialogSteps.fillValuesInFields(acceptFormBeforeOperation_Step_14)
                .assertThat(acceptFormBeforeOperation_Step_14).hasValuesInFields()
                .clickButtonInMainToolbar("Документ подготовлен");
        dialogSteps
                .assertThat(document).hasValuesInFields()
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }


}

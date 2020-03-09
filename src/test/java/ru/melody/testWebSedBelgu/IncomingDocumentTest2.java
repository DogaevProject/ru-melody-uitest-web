package ru.melody.testWebSedBelgu;

import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.melody.data.dataproviders.IncomingDocumentPartOfBPTest;
import ru.melody.web.steps.DialogSteps.MaximizedDialogSteps;
import ru.melody.web.steps.MainPageSteps.MainPageSteps;

import ru.melody.web.steps.LoginSteps.LoginSteps;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Document;
import ru.melody.web.model.Pages.Resolution;

import static com.codeborne.selenide.Selenide.page;

@Listeners({ScreenShooter.class, TextReport.class})
@Report
public class IncomingDocumentTest2 extends ru.melody.data.dataproviders.IncomingDocumentTest2 {

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
    @Test(dataProvider = "Step_Incoming_PartOfBPTest_1", dataProviderClass = IncomingDocumentTest2.class)
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
                .clickButtonInAlertDialog("OK")
                .clickButtonInMainToolbar("Закрыть");
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
    @Test(dataProvider = "Step_Incoming_6", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument3")
    public void verifyDocument6(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        //mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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


    // Создание резолюции соисполнителем первой резолюции (Немцев А.Н.)
    @Test(dataProvider = "Step_Incoming_8", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument6")
    public void verifyDocument8(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        //mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Создать резолюцию");
        dialogSteps
                .fillValuesInFields(resolution)
                .deleteItemInStringField("Контроль", "На контроле")
                .clickButtonInMainToolbar("Сохранить")
                .clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        //mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Создание резолюции исполнителем резолюции (Беленко В.А.)
    @Test(dataProvider = "Step_Incoming_9", dataProviderClass = IncomingDocumentTest2.class, dependsOnMethods = "verifyDocument8")
    public void verifyDocument9(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        // mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Создать резолюцию");
        dialogSteps
                .fillValuesInFields(resolution)
                .deleteItemInStringField("Контроль", "На контроле")
                .clickButtonInMainToolbar("Сохранить")
                .clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        //mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Предоставление отчета исполнителем резолюции (Беленко В.А.)
    @Test(dataProvider = "Step_Incoming_13", dataProviderClass = IncomingDocumentTest2.class, dependsOnMethods = "verifyDocument9")
    public void verifyDocument13(Employee employee, Folder folder, Document document, Resolution resolution, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe, Folder[] foldersInWhichDocumentShouldBe2) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        // mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Резолюции")
                .selectInnerItem(resolution)
                .clickButtonInTabWithInnerObjects("Предоставить информацию");
        dialogSteps
                .fillValuesInFields(report2)
                .clickButtonInMainToolbar("Сохранить");
        //mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();

    }


    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Немцев А.В.)
    @Test(dataProvider = "Step_Incoming_14", dataProviderClass = IncomingDocumentTest2.class, dependsOnMethods = "verifyDocument13")
    public void verifyDocument14(Employee employee, Folder folder, Document document, Resolution resolution, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe, Folder[] foldersInWhichDocumentShouldBe2) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        //mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Подтвердить исполнение резолюции");
        //mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        //mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe2);

       dialogSteps
               .clickTab("Резолюции")
               .selectInnerItem(resolution)
               .clickButtonInTabWithInnerObjects("Предоставить информацию");
        dialogSteps
                .fillValuesInFields(report2)
                .clickButtonInMainToolbar("Сохранить");
        // mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Проверка отчета исполнителя. (Банчук Ю.А.)
    @Test(dataProvider = "Step_Incoming_17", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument14")
    public void verifyDocument17(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        // mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Подтвердить исполнение резолюции")
                .clickButtonInMainToolbar("Закрыть");
        // mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Создание исходящего документа (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_Incoming_20", dataProviderClass = IncomingDocumentTest2.class, dependsOnMethods = "verifyDocument17")
    public void verifyDocument20(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, Document document2, Document document3) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        //mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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
                .clickButtonInMainToolbar("Сохранить") // todo В этап "Внешнее согласование" проекта ИД по умолчанию не добавляются участники поля "Соисполнители" первой резолюции ВД 426034
                .assertThat().hasTextMessageInAlertDialog("Проект документа успешно сохранен")
                .clickButtonInAlertDialog("OK")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        // mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Регистрация исходящего документа (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_Outcoming__1", dataProviderClass = IncomingDocumentTest2.class, dependsOnMethods = "verifyDocument20")
    public void verifyDocument21(Employee employee, Folder folder, Document document) throws Exception {
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


//    // Внутреннее согласование документа (Старусев И.В.)
    @Test(dataProvider = "Step_4", dataProviderClass = IncomingDocumentTest2.class, dependsOnMethods = "verifyDocument21")
    public void verifyDocument21_2(Employee employee, Folder folder, Document document) throws Exception {
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

    // Внешнее согласование документа (Немцев А.Н.)
    @Test(dataProvider = "Step_5", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument21_2")
    public void verifyDocument22(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_1) throws Exception {
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
    @Test(dataProvider = "Step_6", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument22")
    public void verifyDocument23(Employee employee, Folder folder, Document document) throws Exception {
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
    @Test(dataProvider = "Step_7", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument23")
    public void verifyDocument24(Employee employee, Folder folder, Document document) throws Exception {
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
    @Test(dataProvider = "Step_8", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument24")
    public void verifyDocument25(Employee employee, Folder folder, Document document) throws Exception {
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
    @Test(dataProvider = "Step_13", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument25")
    public void verifyDocument30(Employee employee, Folder folder, Document document) throws Exception {
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
    @Test(dataProvider = "Step_14", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument30")
    public void verifyDocument31(Employee employee, Folder folder, Document document, Form acceptFormBeforeOperation_Step_14) throws Exception {
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
    // @Test(dataProvider = "Step_15", dataProviderClass = IncomingDocumentPartOfBPTest.class, dependsOnMethods = "verifyDocument31")
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

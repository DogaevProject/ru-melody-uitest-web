package ru.melody.testWebSedBelgu;

import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.melody.data.dataproviders.SedBelgu.IncomingDocument;
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
public class IncomingDocumentTest extends IncomingDocument {

    private LoginSteps loginSteps;
    private MainPageSteps mainPageSteps;
    private MaximizedDialogSteps dialogSteps;

    @BeforeClass
    public void beforeTest() {
        loginSteps = page(LoginSteps.class);
        mainPageSteps = page(MainPageSteps.class);
        dialogSteps = page(MaximizedDialogSteps.class);

    }

    // test полного прохождени по БП

    // Регистрация входящего документа с проектом резолюции из ПМ «Входящие документы» (Асина М.В.)
    @Test(dataProvider = "Step_Incoming_1", dataProviderClass = IncomingDocument.class)
    public void verifyDocument1(Employee employee, Folder folder, Document document, Resolution resolution) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder)
                .clickButtonInGrid("Добавить");
        dialogSteps
                .fillValuesInFields(document)
                .assertThat(document)
                //.hasOperation()
                //.hasFields()
                .hasValuesInFields();
        nameOfObjectForOpenInTheGrid = dialogSteps.getValueFromInputStringField("Входящий номер");
        dialogSteps
                .editFiles().addFiles(document)
                .clickButtonInMainToolbar("Зарегистрировать с проектом резолюции");
        dialogSteps.fillValuesInFields(resolution)
                .assertThat().hasValueInNotEditableFieldText("Резолюция", resolution.getNameOfObjectForOpenInTheGrid())
                .clickButtonInMainToolbar("Сохранить")
                .assertThat().hasTextMessageInAlertDialog("Создан проект резолюции Форма резолюции - Сопроводительный бланк.docx")
                .clickButtonInAlertDialog("OK")
                .clickTab("Резолюции")
                .selectInnerItem(resolution).clickButtonInTabWithInnerObjects("Редактировать").assertThat(resolution).hasFields().hasValuesInFields()
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Проверка проекта резолюции (Кашина А.П.)
    @Test(dataProvider = "Step_Incoming_2", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument1")
    public void verifyDocument2(Employee employee, Folder folder, Document document, Resolution resolution, Resolution resolution2, Folder[] foldersInWhichDocumentShouldBe, Folder[] foldersInWhichDocumentShouldBe2, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe)
                .openItemOfMenuTree(folder)
                .assertThatInGrid().itemHasGreenBackground(nameOfObjectForOpenInTheGrid)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Резолюции").openItemOnTabWithInnerObjects(resolution);
        dialogSteps.fillValuesInFields(resolution2)
                .assertThat(resolution2).hasValuesInFields().hasValueInFieldTextAfterEditing("Резолюция", resolution2.getTextOfResolution())
                .clickButtonInMainToolbar("Сохранить")
                .clickButtonInMainToolbar("Закрыть");
        dialogSteps.clickTab("Карточка");
        dialogSteps
                .fillValuesInFields(document)
                .clickButtonInMainToolbar("Сохранить");
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать резолюцию");
        mainPageSteps.assertThatInGrid()
                .itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe2)
                .itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Утверждение проекта резолюции (Савелова Т.П.)
    @Test(dataProvider = "Step_Incoming_3", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument2")
    public void verifyDocument3(Employee employee, Folder folder, Document document, Folder[] foldersInWhichDocumentShouldBe, Folder[] foldersInWhichDocumentShouldBe2, Folder[] foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .switchUserInDeputyField("Полухин Олег Николаевич")
                .clickButtonInMainToolbar("Утвердить резолюцию");
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe2);
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Принятие документа на контроль сотрудником КАЦ (Немыкина Т.В.)
    @Test(dataProvider = "Step_Incoming_4", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument3")
    public void verifyDocument4(Employee employee, Folder folder, Document document, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe); // todo в Меня ждут не попал
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Принять на контроль");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Просмотр контрольного документа сотрудниками КАЦ после принятия на контроль (Гаркуненко Е.А.)
    @Test(dataProvider = "Step_Incoming_4_2", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument4")
    public void verifyDocument4_2(Employee employee, Document document, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Ознакомление с документом начальником КАЦ (Голубёвская И.А.)
    @Test(dataProvider = "Step_Incoming_5", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument4_2")
    public void verifyDocument5(Employee employee, Folder folder, Document document, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Ознакомиться");
        //foldersSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe); // todo в Меня ждут" не попал
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Создание резолюции исполнителем резолюции (Банчук Ю.А.)
    @Test(dataProvider = "Step_Incoming_6", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument5")
    public void verifyDocument6(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Создать резолюцию");
        dialogSteps
                .fillValuesInFields(resolution)
                .clickButtonInMainToolbar("Сохранить").clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Создание резолюции исполнителем резолюции (Старусёв И.В.)
    @Test(dataProvider = "Step_Incoming_7", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument6")
    public void verifyDocument7(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Создание резолюции исполнителем резолюции (Мурье С.С.)
    @Test(dataProvider = "Step_Incoming_7_1", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument7")
    public void verifyDocument7_1(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Создание резолюции соисполнителем первой резолюции (Немцев А.Н.)
    @Test(dataProvider = "Step_Incoming_8", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument7_1")
    public void verifyDocument8(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Создание резолюции исполнителем резолюции (Беленко В.А.)
    @Test(dataProvider = "Step_Incoming_9", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument8")
    public void verifyDocument9(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Создание резолюции исполнителем резолюции (Гальцев О.В.)
    @Test(dataProvider = "Step_Incoming_10", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument9")
    public void verifyDocument10(Employee employee, Folder folder, Document document, Resolution resolution, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Предоставление отчета исполнителем резолюции (Чеботарев А.А.)
    @Test(dataProvider = "Step_Incoming_11", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument10")
    public void verifyDocumen11(Employee employee, Folder folder, Document document, Resolution resolution, ru.melody.web.model.Pages.Report report, Folder[] foldersInWhichDocumentShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Резолюции")
                .selectInnerItem(resolution)
                .clickButtonInTabWithInnerObjects("Предоставить информацию");
        dialogSteps
                .fillValuesInFields(report)
                .clickButtonInMainToolbar("Сохранить");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Гальцев О.В.)
    @Test(dataProvider = "Step_Incoming_12", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocumen11")
    public void verifyDocument12(Employee employee, Folder folder, Document document, Resolution resolution, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe, Folder[] foldersInWhichDocumentShouldBe2) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Подтвердить исполнение резолюции")
                .clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid()
                .itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe)
                .itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe2);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();

    }

    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Беленко В.А.)
    @Test(dataProvider = "Step_Incoming_13", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument12")
    public void verifyDocument13(Employee employee, Folder folder, Document document, Resolution resolution, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe, Folder[] foldersInWhichDocumentShouldBe2) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid()
                .itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe)
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Подтвердить исполнение резолюции")
                .clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe2);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();

    }


    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Немцев А.В.)
    @Test(dataProvider = "Step_Incoming_14", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument13")
    public void verifyDocument14(Employee employee, Folder folder, Document document, Resolution resolution, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe, Folder[] foldersInWhichDocumentShouldBe2) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Подтвердить исполнение резолюции")
                .clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe2);
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
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Возврат отчета исполнителя на доработку (Банчук Ю.А.)
    @Test(dataProvider = "Step_Incoming_15", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument14")
    public void verifyDocument15(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, Form acceptFormBeforeOperation, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Вернуть на доработку");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation)
                .clickButtonInMainToolbar("Вернуть на доработку");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Возврат отчета исполнителя на проверку (Немцев А.В.)
    @Test(dataProvider = "Step_Incoming_16", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument15")
    public void verifyDocument16(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, Form acceptFormBeforeOperation, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Вернуть на проверку");
        dialogSteps
                .fillValuesInFields(acceptFormBeforeOperation)
                .clickButtonInMainToolbar("Вернуть на проверку");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //Проверка отчета исполнителя. Направление информации автором резолюции (Банчук Ю.А.)
    @Test(dataProvider = "Step_Incoming_17", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument16")
    public void verifyDocument17(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Подтвердить исполнение резолюции")
                .clickButtonInMainToolbar("Направить информацию");
        dialogSteps
                .deleteValuesInFields(report2) // todo  отсутсвуют пользователи в поле "Кому" по умолчанию. 426033
                .fillValuesInFields(report2)
                .clickButtonInMainToolbar("Сохранить");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Ознакомление с направленной информацией. Направление информации автором резолюции (Старусёв И.В.)
    @Test(dataProvider = "Step_Incoming_18", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument17")
    public void verifyDocument18(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Ознакомлен")
                .clickButtonInMainToolbar("Направить информацию");
        dialogSteps
                .deleteValuesInFields(report2)
                .fillValuesInFields(report2)
                .clickButtonInMainToolbar("Сохранить");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Ознакомление с направленной информацией. Направление информации автором резолюции (Мурье С.С.)
    @Test(dataProvider = "Step_Incoming_19", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument18")
    public void verifyDocument19(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, ru.melody.web.model.Pages.Report report2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Ознакомлен")
                .clickButtonInMainToolbar("Направить информацию");
        dialogSteps
                .fillValuesInFields(report2)
                .clickButtonInMainToolbar("Сохранить");
        dialogSteps.clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, folder);
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Ознакомление с направленной информацией. Создание исходящего документа (Прокофьева Ю.А.)
    @Test(dataProvider = "Step_Incoming_20", dataProviderClass = IncomingDocument.class, dependsOnMethods = "verifyDocument19")
    public void verifyDocument20(Employee employee, Folder folder, Document document, ru.melody.web.model.Pages.Report report, Document document2, Folder[] foldersInWhichDocumentShouldBe, Folder foldersInWhichDocumentNotShouldBe) throws Exception {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps.assertThatInGrid().itemDisplayed(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentShouldBe);
        mainPageSteps
                .openItemOfMenuTree(folder)
                .openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps
                .clickTab("Проверка исполнения")
                .selectInnerItem(report)
                .clickButtonInTabWithInnerObjects("Ознакомлен")
                .clickTab("Исходящие документы")
                .clickButtonInTabWithInnerObjects("Добавить")
                .clickButtonInMainToolbar("Добавить");
        dialogSteps.fillValuesInFields(document2)
                .assertThat(document2)
                .hasValuesInFields()
                .hasValueInInFieldStringWithChoiceInListMultiple("Связанные документы", nameOfObjectForOpenInTheGrid + " - Входящий документ")
                .clickTab("Маршрут")
                .editRouteScheme().fillRouteScheme(document2)
                .clickTab("Карточка")
                .clickButtonInMainToolbar("Сохранить")
                .clickButtonInAlertDialog("OK")
                .clickButtonInMainToolbar("Закрыть")
                .clickButtonInMainToolbar("Закрыть");
        mainPageSteps.assertThatInGrid().itemDisappear(nameOfObjectForOpenInTheGrid, foldersInWhichDocumentNotShouldBe);
        dialogSteps.logoutViaClearingBrowserCache();
    }

}

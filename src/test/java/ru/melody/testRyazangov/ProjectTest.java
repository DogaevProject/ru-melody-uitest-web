package ru.melody.testRyazangov;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.melody.data.dataproviders.ryazangov.ProjectBP;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Pages.Document;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.steps.DialogSteps.MaximizedDialogSteps;
import ru.melody.web.steps.LoginSteps.LoginSteps;
import ru.melody.web.steps.MainPageSteps.MainPageSteps;

import static com.codeborne.selenide.Selenide.*;

@Listeners({ScreenShooter.class, TextReport.class})
@Report
public class ProjectTest extends ProjectBP {

    LoginSteps loginSteps;
    MainPageSteps mainPageSteps;
    MaximizedDialogSteps dialogSteps;

    @BeforeClass
    public void beforeTest() {
        loginSteps = page(LoginSteps.class);
        mainPageSteps = page(MainPageSteps.class);
        dialogSteps = page(MaximizedDialogSteps.class);

    }

    // test полного прохождени по БП

    //Создание предложения по проекту - АбаеваРХ
    @Test(dataProvider = "Step_Project_1", dataProviderClass = ProjectBP.class)
    public void verifyInitialOfProject1(Employee employee, Folder folder, Form initialOfProject) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).clickButtonInGrid("Добавить");

        dialogSteps.fillValuesInFields(initialOfProject)
                .assertThat(initialOfProject).hasValuesInFields()
                .clickButtonInMainToolbar("Сохранить");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Регистрация Предложения по проекту - БезукладовДА - Руководитель регионального проектного офиса
    @Test(dataProvider = "Step_Project_2", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyInitialOfProject1")
    public void verifyInitialOfProject2(Employee employee, Folder folder, Form initialOfProject, Form formBeforeRegistration_Step_2) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(initialOfProject);
        dialogSteps.clickButtonInMainToolbar("Зарегистрировать");
        dialogSteps.fillValuesInFields(formBeforeRegistration_Step_2).clickButtonInMainToolbar("Зарегистрировать")
                .assertThat().hasTextMessageInAlertDialog("Предложение по проекту зарегистрировано");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Рассмотрение Предложения по проекту Координирующим органом - МайоровМА
    @Test(dataProvider = "Step_Project_3", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyInitialOfProject2")
    public void verifyProject1(Employee employee, Folder folder, Form projectTab2_Step_3, Form projectTab1_Step_3) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Файлы").assertThat().hasValueInInFieldStringWithChoiceInListMultiple("Предложение по проекту", "Проектное предложение.docx");
        dialogSteps.clickTab("Общая информация");
        dialogSteps.fillValuesInFields(projectTab1_Step_3);
        dialogSteps.clickTab("Инициирование");
        dialogSteps.fillValuesInFields(projectTab2_Step_3).clickButtonInMainToolbar("Сохранить");


        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Направить руководителю");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Подготовка основных параметров проекта - ФедюнинаИА
    @Test(dataProvider = "Step_Project_4", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject1")
    public void verifyProject2(Employee employee, Folder folder, Form projectTab1_Step_4, Form projectTab2_Step_4, Form taskAndResult_Step_4, Form task_Step_4, Form indicators_Step_4, Form financeTab1_Step_4, Form financeTab2_Step_4) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);


        dialogSteps.fillValuesInFields(projectTab1_Step_4).clickTab("Инициирование");
        dialogSteps.fillValuesInFields(projectTab2_Step_4);

        dialogSteps.clickTab("Показатели проекта").clickButtonInTabWithInnerObjects("Добавить");
        dialogSteps.fillValuesInFields(indicators_Step_4).clickButtonInMainToolbar("Сохранить");


        dialogSteps.clickTab("Задачи и результаты проекта").clickButtonInTabWithInnerObjects("Добавить");
        dialogSteps.addItemInField("Наименование задачи").fillValuesInFields(task_Step_4).clickButtonInMainToolbar("Сохранить");
        dialogSteps.fillValuesInFields(taskAndResult_Step_4);
        dialogSteps.clickButtonInMainToolbar("Сохранить")
                .clickTab("Финансовое обеспечение проекта")
                .clickButtonInTabWithInnerObjects("Добавить");
        dialogSteps.fillValuesInFields(financeTab1_Step_4);
        dialogSteps.clickTab("Источники финансирования (план)");
        dialogSteps.fillValuesInFields(financeTab2_Step_4);
        dialogSteps.clickButtonInMainToolbar("Сохранить");

        dialogSteps.clickButtonInMainToolbar("Направить на согласование");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование основных параметров проекта - МайоровМА
    @Test(dataProvider = "Step_Project_5", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject2")
    public void verifyProject3(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Файлы").assertThat().hasValueInInFieldStringWithChoiceInListMultiple("Основные параметры проекта", "Основные параметры проекта.docx");
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование основных параметров проекта - ШемякинБВ
    @Test(dataProvider = "Step_Project_6", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject3")
    public void verifyProject4(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }


     // Согласование основных параметров проекта - БезукладовДА
    @Test(dataProvider = "Step_Project_6_2", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject4")
    public void verifyProject4_2(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Согласование основных параметров проекта - МайоровМА
    @Test(dataProvider = "Step_Project_7", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject4_2")
    public void verifyProject5(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Определение уровня сложности проекта и Отправка на согласование уровня сложности проекта - ФедюнинаИА
    @Test(dataProvider = "Step_Project_8", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject5")
    public void verifyProject6(Employee employee, Folder folder, Form tab) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Инициирование").clickTab("Уровень сложности");
        dialogSteps.fillValuesInFields(tab);
        dialogSteps.clickButtonInMainToolbar("Направить на согласование");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //  Согласование уровня сложности проекта - БезукладовДА
    @Test(dataProvider = "Step_Project_9", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject6")
    public void verifyProject7(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать уровень сложности");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    //Рассмотрение Проектным комитетом основных параметров проекта - МайоровМА
    @Test(dataProvider = "Step_Project_10", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject7")
    public void verifyProject8(Employee employee, Folder folder, Form project_Step_10) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Файлы");
        dialogSteps
                .fillValuesInFields(project_Step_10)
                .clickButtonInMainToolbar("Одобрить");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Рассмотрение на заседании Совета основных параметров проекта - ЛюбимовНВ
    @Test(dataProvider = "Step_Project_11", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject8")
    public void verifyProject9(Employee employee, Folder folder, Folder folder2, Form project_Step_11) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Утвердить");
        mainPageSteps
                .openItemOfMenuTree(folder2).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.assertThat(project_Step_11).hasValuesInFields();
        dialogSteps.logoutViaClearingBrowserCache();
    }


    //Подготовка паспорта проекта - ФедюнинаИА
    @Test(dataProvider = "Step_Project_12", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject9")
    public void verifyProject10(Employee employee, Folder folder, Form item_1, Form item_2, Form item_3, Form tab1_Step10, Form tab2_items_Step10) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Подготовка");
        $(By.xpath("//b[text()='Сформировать паспорт']/ancestor::a[@class=\"x-btn m4-button-in-form x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\"]"))
                .waitUntil(Condition.visible, 5000).click();
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Паспорт проекта.docx").clickButtonInAlertDialog("OK");

        dialogSteps.clickTab("План мероприятий");
        // Добавить в Результат отв.исполнителя Белова О.В.
        dialogSteps.openItemOnTabWithInnerObjects(item_1);
        dialogSteps.fillValuesInFields(item_1).clickButtonInMainToolbar("Сохранить");
        // Добавить КТ, Мероприятие отв.исполнителя Белова О.В.
        dialogSteps.clickButtonInTabWithInnerObjects("Добавить ");
        dialogSteps.fillValuesInFields(item_2).clickButtonInMainToolbar("Сохранить");
        dialogSteps.clickButtonInTabWithInnerObjects("Развернуть").selectInnerItem(item_2).clickButtonInTabWithInnerObjects("Добавить ");
        dialogSteps.fillValuesInFields(item_3).clickButtonInMainToolbar("Сохранить");

        dialogSteps.clickTab("Методика расчета показателей");
        dialogSteps.clickButtonInTabWithInnerObjects("Добавить");
        dialogSteps.fillValuesInFields(tab1_Step10).clickButtonInMainToolbar("Сохранить");


        dialogSteps.clickTab("Участники проекта");
        dialogSteps.openItemOnTabWithInnerObjects("Администратор проекта");
        dialogSteps.fillValuesInFields(tab2_items_Step10).clickButtonInMainToolbar("Сохранить");


        dialogSteps.clickTab("Участники проекта");
        dialogSteps.openItemOnTabWithInnerObjects("Руководитель проекта");
        dialogSteps.fillValuesInFields(tab2_items_Step10).clickButtonInMainToolbar("Сохранить");

        dialogSteps.openItemOnTabWithInnerObjects("Ответственный за достижение результата проекта");
        dialogSteps.fillValuesInFields(tab2_items_Step10).clickButtonInMainToolbar("Сохранить");


        dialogSteps.openItemOnTabWithInnerObjects("Участник проекта");
        dialogSteps.fillValuesInFields(tab2_items_Step10).clickButtonInMainToolbar("Сохранить");


        dialogSteps.clickButtonInMainToolbar("Направить на согласование");
        dialogSteps.logoutViaClearingBrowserCache();

    }


    // Согласование паспорта проекта - МайоровМА
    @Test(dataProvider = "Step_Project_14", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject10")
    public void verifyProject11(Employee employee, Folder folder, Form files) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Файлы");
        dialogSteps.fillValuesInFields(files).clickButtonInMainToolbar("Сохранить");
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Согласование паспорта проекта - ШемякинБВ
    @Test(dataProvider = "Step_Project_13", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject11")
    public void verifyProject12(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование паспорта проекта проектным офисом - БезукладовДА
    @Test(dataProvider = "Step_Project_15", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject12")
    public void verifyProject13(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование паспорта проекта - МайоровМА
    @Test(dataProvider = "Step_Project_15_2", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject13")
    public void verifyProject13_2(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }



    //Утверждение паспорта проекта Проектным комитетом. - МайоровМА
    @Test(dataProvider = "Step_Project_16", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject13_2")
    public void verifyProject14(Employee employee, Folder folder, Form project_Step_14) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Файлы");
        dialogSteps
                .fillValuesInFields(project_Step_14)
                .clickButtonInMainToolbar("Одобрить");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Утверждение паспорта проекта на заседании Совета - ЛюбимовНВ
    @Test(dataProvider = "Step_Project_17", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject14")
    public void verifyProject15(Employee employee, Folder folder, Folder folder2, Form project_Step_17, Form project_Step_17_2) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Файлы");
        dialogSteps.fillValuesInFields(project_Step_17)
                .clickButtonInMainToolbar("Утвердить");
        mainPageSteps
                .openItemOfMenuTree(folder2).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.assertThat(project_Step_17_2).hasValuesInFields();
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Реализация проекта - МайоровМА
    @Test(dataProvider = "Step_Project_18", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject15")
    public void verifyProject16(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Рабочий план").assertThat().itemDisplayed("результат-1").itemDisplayed("кт-1").itemDisplayed("м-1");
        dialogSteps.clickTab("Реализация и контроль").clickButtonInTabWithInnerObjects("Развернуть");
        dialogSteps.assertThat().itemDisplayed("результат-1").itemDisplayed("кт-1").itemDisplayed("м-1");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование Мероприятий и Подмероприятий с ответственными исполнителями - БеловаОВ
    @Test(dataProvider = "Step_Project_19", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject16")
    public void verifyProject17(Employee employee, Folder folder, Form item_1, Form item_2, Form item_3, Form report) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);

        dialogSteps.clickTab("Реализация и контроль");
        dialogSteps.clickButtonInTabWithInnerObjects("Развернуть");

        dialogSteps.selectInnerItem(item_3).clickButtonInTabWithInnerObjects("Предоставить отчет");
        dialogSteps.fillValuesInFields(report).clickButtonInMainToolbar("Предоставить отчет");
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Предоставлен отчет").clickButtonInAlertDialog("OK");


        dialogSteps.selectInnerItem(item_2).clickButtonInTabWithInnerObjects("Предоставить отчет");
        dialogSteps.fillValuesInFields(report).clickButtonInMainToolbar("Предоставить отчет");
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Предоставлен отчет").clickButtonInAlertDialog("OK");

        dialogSteps.openItemOnTabWithInnerObjects(item_1).clickTab("Бюджет");
        dialogSteps.openItemOnTabWithInnerObjects("результат-1").clickTab("Источники финансирования (факт)");
        dialogSteps.fillValuesInFields(item_1).clickButtonInMainToolbar("Сохранить").clickButtonInMainToolbar("Сохранить");

        dialogSteps.clickButtonInTabWithInnerObjects("Предоставить отчет");
        dialogSteps.fillValuesInFields(report).clickButtonInMainToolbar("Предоставить отчет");
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Предоставлен отчет").clickButtonInAlertDialog("OK");

        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Подтверждение выполнения Результата, Кт, Мероприятий и Подмероприятия - Завершение этапа Реализация - ЛюбимовНВ
    @Test(dataProvider = "Step_Project_20", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject17")
    public void verifyProject18(Employee employee, Folder folder, Folder folder2, Form item_1, Form item_2, Form item_3, Form project_Step_20) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);

        dialogSteps.clickTab("Реализация и контроль");
        dialogSteps.clickButtonInTabWithInnerObjects("Развернуть");
        dialogSteps.selectInnerItem(item_3).clickButtonInTabWithInnerObjects("Подтвердить выполнение");
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Выполнение подтверждено").clickButtonInAlertDialog("OK");


        dialogSteps.clickButtonInTabWithInnerObjects("Развернуть");
        dialogSteps.selectInnerItem(item_2).clickButtonInTabWithInnerObjects("Подтвердить выполнение");
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Выполнение подтверждено").clickButtonInAlertDialog("OK");

        dialogSteps.clickButtonInTabWithInnerObjects("Развернуть");
        dialogSteps.selectInnerItem(item_1).clickButtonInTabWithInnerObjects("Подтвердить выполнение");
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Выполнение подтверждено").clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Закрыть");

        mainPageSteps
                .openItemOfMenuTree(folder2).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.assertThat(project_Step_20).hasValuesInFields();

        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Завершение проекта - МайоровМА
    @Test(dataProvider = "Step_Project_21", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject18")
    public void verifyProject19(Employee employee, Folder folder, Form project_Step_21) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);

        dialogSteps.clickTab("Завершение");
        dialogSteps.fillValuesInFields(project_Step_21).clickButtonInMainToolbar("Сохранить");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Отправка итогового отчета на согласование - ФедюнинаИА
    @Test(dataProvider = "Step_Project_22", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject19")
    public void verifyProject20(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Завершение");
        $(By.xpath("//b[text()='Итоговый отчёт']/ancestor::a[@class=\"x-btn m4-button-in-form x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small\"]"))
                .waitUntil(Condition.visible, 5000).click();
        dialogSteps.assertThat().hasTextMessageInAlertDialog("Итоговый отчет.docx").clickButtonInAlertDialog("OK");
        dialogSteps.clickButtonInMainToolbar("Направить на согласование");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование итогового отчета - МайоровМА
    @Test(dataProvider = "Step_Project_24", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject20")
    public void verifyProject21(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Согласование итогового отчета - ШемякинБВ
    @Test(dataProvider = "Step_Project_23", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject21")
    public void verifyProject22(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    // Согласование итогового отчета Региональным проектным офисом - БезукладовДА
    @Test(dataProvider = "Step_Project_25", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject22")
    public void verifyProject23(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }


    // Согласование -  МайоровМА
    @Test(dataProvider = "Step_Project_24", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject23")
    public void verifyProject23_2(Employee employee, Folder folder) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Согласовать");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //  "Утверждение итогового отчета Проектным комитетом" - МайоровМА
    @Test(dataProvider = "Step_Project_26", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject23_2")
    public void verifyProject24(Employee employee, Folder folder, Form project_Step_26) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickTab("Завершение");
        dialogSteps.fillValuesInFields(project_Step_26)
                .clickButtonInMainToolbar("Сохранить");
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Одобрить");
        dialogSteps.logoutViaClearingBrowserCache();
    }

    //  "Утверждение итогового отчета Губернатором" - ЛюбимовНВ
    @Test(dataProvider = "Step_Project_27", dataProviderClass = ProjectBP.class, dependsOnMethods = "verifyProject24")
    public void verifyProject25(Employee employee, Folder folder, Folder folder2, Form project_Step_27) {
        loginSteps.loginAs(employee).waitLoadMainPage();
        mainPageSteps
                .openItemOfMenuTree(folder).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.clickButtonInMainToolbar("Завершить проект");
        mainPageSteps
                .openItemOfMenuTree(folder2).openItemInGridInCurrentWindow(nameOfObjectForOpenInTheGrid);
        dialogSteps.assertThat(project_Step_27).hasValuesInFields();
        dialogSteps.logoutViaClearingBrowserCache();
    }


}

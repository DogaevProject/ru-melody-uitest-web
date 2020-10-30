package ru.melody.data.dataproviders.SedBelgu;

import ru.melody.data.BaseTest;
import ru.melody.data.dataproviders.BaseDataProvider;
import ru.melody.web.model.Administration.Fields.Settings.SettingsForFields;
import ru.melody.web.model.Administration.Fields.TypesOfFields.*;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.FilesForAttachment;
import ru.melody.web.model.Folder.Folder;

/**
 * Данные
 */
public abstract class SedBelgu extends BaseDataProvider {

    //------------------------  Пользователи

    Employee employee1 = new Employee().setLoginName("Prokofieva_ya").setPassword("123");
    Employee employee2 = new Employee().setLoginName("Mure").setPassword("123").setLastName("Мурье").setName("Станислав").setPatronymic("Семенович");
    Employee employee3 = new Employee().setLoginName("Starusev").setPassword("123").setLastName("Старусёв").setName("Игорь").setPatronymic("Васильевич");
    Employee employee4 = new Employee().setLoginName("Nemzev").setPassword("123").setLastName("Немцев").setName("Александр").setPatronymic("Николаевич");
    Employee employee5 = new Employee().setLoginName("Banchuk").setPassword("123").setLastName("Банчук").setName("Юрий").setPatronymic("Анатольевич");
    Employee employee6 = new Employee().setLoginName("Kashina").setPassword("123");
    Employee employee7 = new Employee().setLoginName("Nikulina_E").setPassword("123");
    Employee employee8 = new Employee().setLoginName("MamatovAV").setPassword("123");
    Employee employee9 = new Employee().setLoginName("Asina").setPassword("123");
    Employee employee10 = new Employee().setLoginName("Savelova").setPassword("123");
    Employee employee11 = new Employee().setLoginName("Nemykina_T").setPassword("123");
    Employee employee12 = new Employee().setLoginName("Golubevskaya").setPassword("123");
    Employee employee13 = new Employee().setLoginName("VBelenko").setPassword("123");
    Employee employee14 = new Employee().setLoginName("Galtsev_O").setPassword("123");
    Employee employee15 = new Employee().setLoginName("Chebotarev_A").setPassword("123");
    Employee employee16 = new Employee().setLoginName("Garkunenko").setPassword("123");


    //------------------------  ПМ

    Folder OutgoingFolder = new Folder().setNameFolder("Исходящие документы");
    Folder OutgoingAllFolder = new Folder().setParentFolder(OutgoingFolder).setNameFolder("Все");
    Folder OutgoingMeWaitFolder = new Folder().setParentFolder(OutgoingFolder).setNameFolder("Меня ждут");


    Folder folderIncoming = new Folder().setNameFolder("Входящие документы");
    Folder folderIncomingAll = new Folder().setParentFolder(folderIncoming).setNameFolder("Все");
    Folder folderIncomingMeWait = new Folder().setParentFolder(folderIncoming).setNameFolder("Меня ждут");
    Folder folderIncomingConsideration = new Folder().setParentFolder(folderIncoming).setNameFolder("Рассмотрение");
    Folder folderIncomingExecution = new Folder().setParentFolder(folderIncoming).setNameFolder("Исполнение");
    Folder folderIncomingPeriodicExecution = new Folder().setParentFolder(folderIncoming).setNameFolder("Периодическое исполнение");
    Folder folderIncomingReportOfEndExecution = new Folder().setParentFolder(folderIncoming).setNameFolder("Отчеты об исполнении");

}

package ru.melody.data.dataproviders.ryazangov;

import ru.melody.data.dataproviders.BaseDataProvider;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Folder.Folder;

public abstract class Ryazangov  extends BaseDataProvider {

    //------------------------  Пользователи
    Employee employee1 = new Employee().setLoginName("АбаеваРХ").setPassword("123");
    Employee employee2 = new Employee().setLoginName("БезукладовДА").setPassword("123");
    Employee employee3 = new Employee().setLoginName("МайоровМА").setPassword("123");
    Employee employee4 = new Employee().setLoginName("ФедюнинаИА").setPassword("123");
    Employee employee5 = new Employee().setLoginName("ШемякинБВ").setPassword("123");
    Employee employee6 = new Employee().setLoginName("ЛюбимовНВ").setPassword("123");
    Employee employee7 = new Employee().setLoginName("БеловаОВ").setPassword("123");



    //------------------------  ПМ
    Folder Projects = new Folder().setNameFolder("Проекты");
    Folder Archive = new Folder().setNameFolder("Архив");
    Folder Podgotovka = new Folder().setParentFolder(Projects).setNameFolder("Подготовка");
    Folder Predlojeniya = new Folder().setParentFolder(Projects).setNameFolder("Предложения по проекту");
    Folder VsePredlojeniya = new Folder().setParentFolder(Projects).setNameFolder("Предложения по проекту (все)");
    Folder Initiation = new Folder().setParentFolder(Projects).setNameFolder("Инициирование");
    Folder Realisation = new Folder().setParentFolder(Projects).setNameFolder("Реализация и контроль");
    Folder Completion = new Folder().setParentFolder(Projects).setNameFolder("Завершение");
    Folder ArchiveOfProjects = new Folder().setParentFolder(Archive).setNameFolder("Архив проектов");


}

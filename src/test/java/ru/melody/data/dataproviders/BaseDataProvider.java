package ru.melody.data.dataproviders;

import ru.melody.data.BaseTest;
import ru.melody.web.model.FilesForAttachment;
import ru.melody.web.model.Administration.Fields.Settings.SettingsForFields;
import ru.melody.web.model.Administration.Fields.TypesOfFields.*;
import ru.melody.web.model.Administration.Users.Employee;

import ru.melody.web.model.Folder.Folder;

/**
 * Данные
 */
public abstract class BaseDataProvider extends BaseTest {

    // Инициализация объекта - Названия Файлов задачи
    protected String[] file = new String[]{
            FilesForAttachment.FILE_1.getNameFile(),
            FilesForAttachment.FILE_2.getNameFile(),
            FilesForAttachment.FILE_3.getNameFile(),
            FilesForAttachment.FILE_4.getNameFile(),
    };


    //------------------------  Типы полей

    /**
     * СТРОКА (Выбор из списка == Нет;)
     * локатор - Заполнение: поле в теге <input>. Проверка поля: value находится в теге  <input>
     */
    protected FieldObject getFieldString() {
        return new TypeListFieldsString()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(SettingsForFields.NO)); // Выбор из списка
    }

    /**
     * СТРОКА (Выбор из списка == Да; Мн. выбор  == Да/Нет (мн.выбор может быть в этом типе поля, но бывает без мн. выбора);)
     * <p>
     * локатор - Заполнение: поле в теге <li>. Проверка поля: текст находится в теге  <a>
     */
    protected FieldObject getFieldStringWithChoiceInListMultiple() {
        return new TypeListFieldsString()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
                .setMultipleChoice(true)
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(SettingsForFields.YES)); // Выбор из списка
    }

    /**
     * СТРОКА (Выбор из списка == Да; Мн. выбор  == Нет;)
     * локатор - Заполнение: поле в теге <input>. Проверка поля: value находится в теге  <input>
     */
    protected FieldObject getFieldStringWithChoiceInList() {
        return new TypeListFieldsString()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
                .setMultipleChoice(false)
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(SettingsForFields.YES)); // Выбор из списка
    }

    /**
     * СТРОКА (Выбор из списка == Да; Мн. выбор  == Нет; Запись в поле имеет ссылку;)
     * локатор - Заполнение: поле в теге <li>. Проверка поля: value находится в теге  <input>
     */
    protected FieldObject getFieldStringTypeLinkWithChoiceInList() {
        return new TypeListFieldsString()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
                .setMultipleChoice(false)
                .setHasLink(true)
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(SettingsForFields.YES)); // Выбор из списка
    }

    /**
     * СТРОКА ссылка на пользователя
     * локатор - Заполнение: поле в теге <li>. Проверка поля: текст находится в теге  <span>
     */
    protected FieldObject getFieldStringWithChoiceInListEmployers() {
        return new TypeListFieldsStringWithListEmployers()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
                .setFieldType(new TypeListFieldsStringWithListEmployers()
                        .setValuesList(randomString(10) + "\n" + randomString(10) + "\n" + randomString(10)));
    }


    /**
     * ТЕКСТ - поле доступно для редактирования
     * локатор - Заполнение: поле в теге <textarea>. Проверка поля: value находится в теге  <textarea>
     */
    protected FieldObject getFieldText() {
        return new TypeListFieldsText()
                .setFieldName("Текст " + randomString(10))
                .setFieldType(new TypeListFieldsText());
    }

    /**
     * ТЕКСТ - поле доступно для редактирования, авторасширяемое при вводе
     * локатор - Заполнение: поле в теге <div>, но после клика в него заполнение в <textarea>.
     * Проверка поля:  если в поле не был установлен курсор, то текст находится в теге  <div>. Если был установлен курсор, то <textarea>
     */
    protected FieldObject getFieldTextResizable() {
        return new TypeListFieldsText()
                .setFieldName("Текст " + randomString(10))
                .setFieldType(new TypeListFieldsText().setResizable(true));
    }

    /**
     * ТЕКСТ - поле недоступно для редактирования
     * локатор - Проверка поля: текст находится в теге  <div>
     */
    protected FieldObject getFieldTextNotEditable() {
        return new TypeListFieldsText()
                .setEditable(false)
                .setFieldName("Текст " + randomString(10))
                .setFieldType(new TypeListFieldsText());
    }

    /**
     * ФАЙЛ
     */
    protected FieldObject getFieldFile() {
        return new TypeListFieldsFile()
                .setFieldType(new TypeListFieldsFile());
    }

    /**
     * Логическое
     */
    protected FieldObject getFieldBoolean() {
        return new TypeListFieldsBoolean()
                .setFieldType(new TypeListFieldsBoolean());
    }

    //------------------------  Пользователи
    Employee platform = new Employee().setLoginName("platform").setPassword("melodym4");

}

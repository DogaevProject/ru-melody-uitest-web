package ru.melody.data.dataproviders.SedBelgu;

import org.testng.annotations.DataProvider;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Folder.Folder;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.AdditionOperation;
import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.model.Pages.Resolution;
import ru.melody.web.model.Pages.RouteScheme;
import ru.melody.web.model.ItemRouteScheme;

import static ru.melody.utils.GenericDate.nowDateWithoutTime;

/**
 * Данные
 */
public abstract class IncomingDocument extends SedBelgu {


    protected String nameOfObjectForOpenInTheGrid = "Р-9*"; // nameOfObjectForOpenInTheGrid - отображаемое в гриде название объекта.  Это м.б любой текст по которому можно отличить нужный объект от остальных.

    // Регистрация входящего документа с проектом резолюции из ПМ «Входящие документы» (Асина М.В.)

    RouteScheme routeSchemeForIncomingDocument_Step_1 = new RouteScheme()
            .setItemRouteSchemeForEdit(new ItemRouteScheme[]{
//                    new ItemRouteScheme("Внутреннее согласование")
//                            .setNewInnerBlock(true)
//                            .setTypeItemIsSequenceBlock(true)
//                            .setUserRoute(new Employee[]{employee2, employee3}),
//                    new ItemRouteScheme("Внешнее согласование")
//                            .setNewInnerBlock(true)
//                            .setTypeItemIsParallelBlock(true)
//                            .setUserRoute(new Employee[]{employee4}),
//                    new ItemRouteScheme("Согласование с руководителем исполнителя ")
//                    .setUserRoute(new Employee[]{employee5}),
            });

    Form getGeneralIncomingDocument() {
        return new ru.melody.web.model.Pages.Document()
                //.setNameOfObjectForOpenInTheGrid(nameOfObjectForOpenInTheGrid)
                //.setRouteScheme(routeSchemeForIncomingDocument_Step_1)
                ;
    }

    Form IncomingDocument_Step_1 = getGeneralIncomingDocument()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Содержание")
                            .setValueField("01/12 5"),
                    getFieldString()
                            .setFieldName("Подписант")
                            .setValueField("Подписант"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Корреспондент")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ доставки")
                            .setValueField("Факс"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Контроль")
                            .setValueField("На контроле"),
                    getFieldString()
                            .setFieldName("Количество страниц")
                            .setValueField("1"),
            })
            .setFields(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Содержание")
                            .setValueField("01/12 5"),
                    getFieldString()
                            .setFieldName("Подписант")
                            .setValueField("Подписант"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Корреспондент")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ доставки")
                            .setValueField("Факс"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Контроль")
                            .setValueField("На контроле"),
                    getFieldString()
                            .setFieldName("Количество страниц")
                            .setValueField("1"),
            })
            .setValueFiles(new String[]{file[1]})
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    Form IncomingDocument_Periodic_Step_1 = getGeneralIncomingDocument()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Содержание")
                            .setValueField("01/12 5"),
                    getFieldString()
                            .setFieldName("Подписант")
                            .setValueField("Подписант"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Корреспондент")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ доставки")
                            .setValueField("Факс"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Контроль")
                            .setValueField("На контроле"),
                    getFieldString()
                            .setFieldName("Количество страниц")
                            .setValueField("1"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Периодическое исполнение")
                            .setValueField("Ежегодно"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Месяц")
                            .setValueField("Январь"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("День месяца")
                            .setValueField("1"),
                    getFieldString()
                            .setFieldName("Дата окончания")
                            .setValueField("19.12.2021"),

            })
            .setFields(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Содержание")
                            .setValueField("01/12 5"),
                    getFieldString()
                            .setFieldName("Подписант")
                            .setValueField("Подписант"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Корреспондент")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ доставки")
                            .setValueField("Факс"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Контроль")
                            .setValueField("На контроле"),
                    getFieldString()
                            .setFieldName("Количество страниц")
                            .setValueField("1"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Периодическое исполнение")
                            .setValueField("Ежегодно"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Месяц")
                            .setValueField("Январь"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("День месяца")
                            .setValueField("1"),
                    getFieldString()
                            .setFieldName("Дата окончания")
                            .setValueField("19.12.2021"),
            })
            .setValueFiles(new String[]{file[1]})
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    // resolution
    Form creatingResolution_IncomingDocument_Step_1 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Банчуку Ю.А., Немцеву А.Н. О.Полухин. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Контролеры")
//                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
//                    getFieldText()
//                            .setFieldName("Текст резолюции")
//                            .setValueField("03.11 1"),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField("03.12.2019"),
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Автор")
                            .setValueField("Полухин Олег Николаевич (Ректор)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldTextNotEditable()
                            .setFieldName("Текст резолюции")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Исполнить до")
                            //.setValueField(getDateWithDaysShifting(30)), // НЕ переодическое исполнение
                            .setValueField("01.01.2021"), // переодическое исполнение
                    getFieldString()
                            .setFieldName("Контроль")
                            .setValueField("На контроле"),
                    getFieldTextNotEditable()
                            .setFieldName("Резолюция")
                            .setValueField("Банчуку Ю.А., Немцеву А.Н. О.Полухин. " + nowDateWithoutTime() + " г."),
                    getFieldString()
                            .setFieldName("Статус")
                            .setValueField("Проект"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_1() {
        return new Object[][]{
                {
                        employee9,
                        folderIncomingMeWait,
                        IncomingDocument_Periodic_Step_1,
                        creatingResolution_IncomingDocument_Step_1
                        //acceptFormBeforeOperation_Step_1,
                        //formWithParametersForNewDocument_Step_1
                        //gridFolder_Step_1
                }
        };
    }

    // Проверка проекта резолюции (Кашина А.П.)

    Folder[] foldersInWhichDocumentShouldBe_Step_2 = new Folder[]{
            folderIncoming,
            folderIncomingMeWait,
            folderIncomingAll,
    };

    Folder[] foldersInWhichDocumentShouldBe_Step_2_2 = new Folder[]{
            folderIncoming,
            folderIncomingAll,
    };

    Form IncomingDocument_Step_2 = getGeneralIncomingDocument()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Папка")
                            .setValueField("ОД. Срочно"),
            })
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_2 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Банчуку Ю.А., Немцеву А.Н. О.Полухин. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Исполнитель")
//                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Контролеры")
//                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField("Провести работы"),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField("03.12.2019"),
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Автор")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Исполнить до")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Резолюция")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Статус")
                            .setValueField(""),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_2_Editing = new ru.melody.web.model.Pages.Resolution()
            .setTextOfResolution("Банчуку Ю.А., Немцеву А.Н. Провести работы. О.Полухин. " + nowDateWithoutTime() + " г.")
            .setNameOfObjectForOpenInTheGrid("Банчуку Ю.А., Немцеву А.Н. Провести работы. О.Полухин. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Исполнитель")
//                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Контролеры")
//                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField("Провести работы"),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField("03.12.2019"),
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Автор")
                            .setValueField("Полухин Олег Николаевич (Ректор)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField("Провести работы"),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField("16.01.2020"),
                    getFieldText()
                            .setFieldName("Резолюция")
                            .setValueField("Банчуку Ю.А., Немцеву А.Н. Провести работы. О.Полухин. " + nowDateWithoutTime() + " г."),
                    getFieldString()
                            .setFieldName("Статус")
                            .setValueField("Проект"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_2() {
        return new Object[][]{
                {
                        employee6,
                        folderIncomingMeWait,
                        IncomingDocument_Step_2,
                        resolution_IncomingDocument_Step_2,
                        resolution_IncomingDocument_Step_2_Editing,
                        foldersInWhichDocumentShouldBe_Step_2,
                        foldersInWhichDocumentShouldBe_Step_2_2,
                        folderIncomingMeWait
                }
        };
    }


    // Утверждение проекта резолюции (Савелова Т.П.)

    Folder[] foldersInWhichDocumentShouldBe_Step_3 = new Folder[]{
            folderIncoming,
            folderIncomingMeWait,
            folderIncomingConsideration,
            folderIncomingAll,
    };

    Folder[] foldersInWhichDocumentShouldBe_Step_3_2 = new Folder[]{
            folderIncoming,
            folderIncomingExecution,
            folderIncomingPeriodicExecution,
            folderIncomingAll,
    };

    Folder[] foldersInWhichDocumentNotShouldBe_Step_3 = new Folder[]{
            folderIncomingMeWait,
            folderIncomingConsideration,
    };

    Form IncomingDocument_Step_3 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_3() {
        return new Object[][]{
                {
                        employee10,
                        folderIncomingMeWait,
                        IncomingDocument_Step_3,
                        foldersInWhichDocumentShouldBe_Step_3,
                        foldersInWhichDocumentShouldBe_Step_3_2,
                        foldersInWhichDocumentNotShouldBe_Step_3
                }
        };
    }

    // Принятие документа на контроль сотрудником КАЦ (Немыкина Т.В.)

    Folder[] foldersInWhichDocumentShouldBe_Step_Execution = new Folder[]{
            folderIncoming,
            folderIncomingMeWait,
            folderIncomingExecution,
            folderIncomingPeriodicExecution,
            folderIncomingAll,
    };

    Form IncomingDocument_Step_4 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_4() {
        return new Object[][]{
                {
                        employee11,
                        folderIncomingMeWait,
                        IncomingDocument_Step_4,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // Просмотр контрольного документа сотрудниками КАЦ после принятия на контроль (Гаркуненко Е.А.)

    @DataProvider
    public Object[][] Step_Incoming_4_2() {
        return new Object[][]{
                {
                        employee16,
                        getGeneralIncomingDocument(),
                        folderIncomingMeWait
                }
        };
    }

    //Ознакомление с документом начальником КАЦ (Голубёвская И.А.)
    Form IncomingDocument_Step_5 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_5() {
        return new Object[][]{
                {
                        employee12,
                        folderIncomingMeWait,
                        IncomingDocument_Step_5,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    //Создание резолюции исполнителем резолюции (Банчук Ю.А.)
    Form IncomingDocument_Step_6 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_6 = new Resolution()
            .setNameOfObjectForOpenInTheGrid("Старусёву И.В. Ю.Банчук. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Старусёв Игорь Васильевич (Заместитель директора департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField("Табачикова Юлия Валерьевна (Старший инспектор)"),
//                    getFieldText()
//                            .setFieldName("Текст резолюции")
//                            .setValueField("Провести работы"),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField("03.12.2019"),
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Автор")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Исполнить до")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Резолюция")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Статус")
                            .setValueField(""),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_6() {
        return new Object[][]{
                {
                        employee5,
                        folderIncomingMeWait,
                        IncomingDocument_Step_6,
                        resolution_IncomingDocument_Step_6,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    //Создание резолюции исполнителем резолюции (Старусёв И.В.)
    Form IncomingDocument_Step_7 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_7 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Мурье С.С. И.Старусёв. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Мурье Станислав Семенович (Начальник центра)"),
//                    getFieldText()
//                            .setFieldName("Текст резолюции")
//                            .setValueField("Провести работы"),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField("03.12.2019"),
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Автор")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Исполнить до")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Резолюция")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Статус")
                            .setValueField(""),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_7() {
        return new Object[][]{
                {
                        employee3,
                        folderIncomingMeWait,
                        IncomingDocument_Step_7,
                        resolution_IncomingDocument_Step_7,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // resolution
    Form resolution_IncomingDocument_Step_7_1 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Прокофьевой Ю.А. С.Мурье. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Прокофьева Юлия Александровна (Лаборант)"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_7_1() {
        return new Object[][]{
                {
                        employee2,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        resolution_IncomingDocument_Step_7_1,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }


    // Создание резолюции соисполнителем первой резолюции (Немцев А.Н.)
    Form IncomingDocument_Step_8 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_8 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Беленко В.А. А.Немцев. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Беленко Владимир Алексеевич (Начальник управления)")
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Автор")
                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Беленко Владимир Алексеевич (Начальник управления)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Контролеры")
                            .setValueField(""),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Соисполнители")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Текст резолюции")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Исполнить до")
                            .setValueField(""),
                    getFieldText()
                            .setFieldName("Резолюция")
                            .setValueField(""),
                    getFieldString()
                            .setFieldName("Статус")
                            .setValueField(""),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_8() {
        return new Object[][]{
                {
                        employee4,
                        folderIncomingMeWait,
                        IncomingDocument_Step_8,
                        resolution_IncomingDocument_Step_8,
                        foldersInWhichDocumentShouldBe_Step_Execution,
                }
        };
    }


    // Создание резолюции исполнителем резолюции (Беленко В.А.)
    Form IncomingDocument_Step_9 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_9 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Гальцеву О.В. В.Беленко. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Гальцев Олег Владимирович (Начальник отдела)")
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_9() {
        return new Object[][]{
                {
                        employee13,
                        folderIncomingMeWait,
                        IncomingDocument_Step_9,
                        resolution_IncomingDocument_Step_9,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // Создание резолюции исполнителем резолюции (Гальцев О.В.)
    Form IncomingDocument_Step_10 = getGeneralIncomingDocument()
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    // resolution
    Form resolution_IncomingDocument_Step_10 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Чеботареву А.А. О.Гальцев. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Чеботарев Алексей Андреевич (Программист)")
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_10() {
        return new Object[][]{
                {
                        employee14,
                        folderIncomingMeWait,
                        IncomingDocument_Step_10,
                        resolution_IncomingDocument_Step_10,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // Предоставление отчета исполнителем резолюции (Чеботарев А.А.)

    // отчет
    Form reportFromResolution_IncomingDocument_Step_11 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 1")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 1"),
                    getFieldFile()
                            .setFieldName("Файлы")
                            .setListValueField(new String[]{file[1]})
            })
            .setFields(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Информация об исполнении направлена для свода;"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_11() {
        return new Object[][]{
                {
                        employee15,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        resolution_IncomingDocument_Step_10,
                        reportFromResolution_IncomingDocument_Step_11,
                        foldersInWhichDocumentShouldBe_Step_Execution,
                }
        };
    }


    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Гальцев О.В.)

    Folder[] foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution = new Folder[]{
            folderIncoming,
            folderIncomingMeWait,
            folderIncomingExecution,
            folderIncomingPeriodicExecution,
            folderIncomingReportOfEndExecution,
            folderIncomingAll,
    };


    // отчет
    Form reportFromResolution_IncomingDocument_Step_12 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 2")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 2")
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_12() {
        return new Object[][]{
                {
                        employee14,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        resolution_IncomingDocument_Step_9,
                        reportFromResolution_IncomingDocument_Step_11,
                        reportFromResolution_IncomingDocument_Step_12,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Беленко В.А.)
    // отчет
    Form reportFromResolution_IncomingDocument_Step_13 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 3")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 3")
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_13() {
        return new Object[][]{
                {
                        employee13,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        resolution_IncomingDocument_Step_8,
                        reportFromResolution_IncomingDocument_Step_12,
                        reportFromResolution_IncomingDocument_Step_13,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Немцев А.В.)
    // отчет
    Form reportFromResolution_IncomingDocument_Step_14 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 4")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 4")
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_14() {
        return new Object[][]{
                {
                        employee4,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        resolution_IncomingDocument_Step_2_Editing,
                        reportFromResolution_IncomingDocument_Step_13,
                        reportFromResolution_IncomingDocument_Step_14,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }

    // Возврат отчета исполнителя на доработку (Банчук Ю.А.)
    // отчет
    Form reportFromResolution_IncomingDocument_Step_15 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 4")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 4")
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    Form acceptFormBeforeOperation__IncomingDocument_Step_14 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Комментарий")
                            .setValueField("Вернуть"),
            });

    @DataProvider
    public Object[][] Step_Incoming_15() {
        return new Object[][]{
                {
                        employee5,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_14,
                        acceptFormBeforeOperation__IncomingDocument_Step_14,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution,
                }
        };
    }

    // Возврат отчета исполнителя на проверку (Немцев А.В.)
    // отчет
    Form acceptFormBeforeOperation__IncomingDocument_Step_16 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Комментарии")
                            .setValueField("Вернуть на проверку"),
            });

    @DataProvider
    public Object[][] Step_Incoming_16() {
        return new Object[][]{
                {
                        employee4,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_14,
                        acceptFormBeforeOperation__IncomingDocument_Step_16,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution
                }
        };
    }


    ////////


    //Проверка отчета исполнителя. Направление информации автором резолюции (Банчук Ю.А.)


    // отчет
    Form reportFromResolution_IncomingDocument_Step_17 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 5")
            .setFieldsForDeleteValue(new FieldObject[]{
                            getFieldStringWithChoiceInListEmployers()
                                    .setFieldName("Кому")
                                    .setListValueField(new String[]{"Мурье Станислав Семенович", "Прокофьева"})
                    }
            )
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 5"),
            })
            .setFields(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Информация об исполнении направлена для свода;"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Incoming_17() {
        return new Object[][]{
                {
                        employee5,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_14,
                        reportFromResolution_IncomingDocument_Step_17,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution
                }
        };
    }


    // Ознакомление с направленной информацией. Направление информации автором резолюции (Старусёв И.В.)
    // отчет
    Form reportFromResolution_IncomingDocument_Step_18 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 6")
            .setFieldsForDeleteValue(new FieldObject[]{
                            getFieldStringWithChoiceInListEmployers()
                                    .setFieldName("Кому")
                                    .setListValueField(new String[]{"Прокофьева"})
                    }
            )
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 6"),
            })
            .setFields(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Информация об исполнении направлена для свода;"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_18() {
        return new Object[][]{
                {
                        employee3,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_17,
                        reportFromResolution_IncomingDocument_Step_18,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution
                }
        };
    }


    // Ознакомление с направленной информацией. Направление информации автором резолюции (Мурье С.С.)
    // отчет
    Form reportFromResolution_IncomingDocument_Step_19 = new ru.melody.web.model.Pages.Report()
            .setNameOfObjectForOpenInTheGrid("Текст отчета исполнителя 7")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета исполнителя")
                            .setValueField("Текст отчета исполнителя 7"),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_19() {
        return new Object[][]{
                {
                        employee2,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_18,
                        reportFromResolution_IncomingDocument_Step_19,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution
                }
        };
    }

    // Ознакомление с направленной информацией. Создание исходящего документа (Прокофьева Ю.А.)

    Form routeSchemeForDocument_Step_20 = new RouteScheme()
            .setItemRouteSchemeForEdit(new ItemRouteScheme[]{
                    new ItemRouteScheme("Внешнее согласование")
                            .setNewInnerBlock(true)
                            .setTypeItemIsParallelBlock(true)
                            .setUserRoute(new Employee[]{employee4})
            });


    // исходящий создаваемый во входящем
    Form document_Step_20 = getGeneralOutgoingDocument()
            .setRouteScheme((RouteScheme) routeSchemeForDocument_Step_20)
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Заголовок")
                            .setValueField(getGeneralOutgoingDocument().getNameOfObjectForOpenInTheGrid()),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Подписант")
                            .setListValueField(new String[]{"Полухин Олег Николаевич (Ректор)"}),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ отправки")
                            .setValueField("Факс"),

            })
            .setFields(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Код")
                            .setValueField("Р"),
                    getFieldString()
                            .setFieldName("Дата создания")
                            .setValueField(nowDateWithoutTime()),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ отправки")
                            .setValueField("Факс"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Адресат")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldString()
                            .setFieldName("Адресат (ФИО)")
                            .setValueField("Подписант"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Подписант")
                            .setListValueField(new String[]{"Полухин Олег Николаевич (Ректор)"}),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Руководитель исполнителя")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Прокофьева Юлия Александровна (Лаборант)"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Связанные документы"),
            })
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });

    Operation[] operationsInTabWithInnerObjects_Step_Incoming_20 = new Operation[]{
            new Operation("Добавить"),
            new Operation("Редактировать"),
            new Operation("Экспорт"),
            new AdditionOperation(),
    };

    Operation[] operationsInGrid_Step_Incoming_20 = new Operation[]{
            new Operation("Редактировать"),
            new AdditionOperation(),
            new Operation("Экспорт"),
    };

    @DataProvider
    public Object[][] Step_Incoming_20() {
        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_19,
                        document_Step_20,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution,
                        //          operationsInTabWithInnerObjects_Step_Incoming_20,
//                        operationsInGrid_Step_Incoming_20
                }
        };
    }


    //исходящий

    Form routeSchemeForDocument_Step_1 = new RouteScheme()
            .setItemRouteSchemeForEdit(new ItemRouteScheme[]{
                    new ItemRouteScheme("Внутреннее согласование")
                            .setNewInnerBlock(true)
                            .setTypeItemIsSequenceBlock(true)
                            .setUserRoute(new Employee[]{employee2, employee3}),
                    new ItemRouteScheme("Внешнее согласование")
                            .setNewInnerBlock(true)
                            .setTypeItemIsParallelBlock(true)
                            .setUserRoute(new Employee[]{employee4}),
//                    new ItemRouteScheme("Согласование с руководителем исполнителя ")
//                    .setUserRoute(new Employee[]{employee5}),
            });

    Form getGeneralOutgoingDocument() {
        return new ru.melody.web.model.Pages.Document()
                .setNameOfObjectForOpenInTheGrid("10/01 1")
                .setRouteScheme((RouteScheme) routeSchemeForDocument_Step_1)
                ;
    }

    Form document_Step_21 = getGeneralOutgoingDocument()
            .setFields(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Код")
                            .setValueField("Р"),
                    getFieldString()
                            .setFieldName("Дата создания")
                            .setValueField(nowDateWithoutTime()),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ отправки")
                            .setValueField("Факс"),
                    getFieldText()
                            .setFieldName("Заголовок")
                            .setValueField(getGeneralOutgoingDocument().getNameOfObjectForOpenInTheGrid()),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Адресат")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldString()
                            .setFieldName("Адресат (ФИО)")
                            .setValueField("Подписант"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Подписант")
                            .setListValueField(new String[]{"Полухин Олег Николаевич (Ректор)"}),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Руководитель исполнителя")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Прокофьева Юлия Александровна (Лаборант)"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Связанные документы"),
            })
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


}

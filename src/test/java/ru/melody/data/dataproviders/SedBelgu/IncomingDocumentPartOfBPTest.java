package ru.melody.data.dataproviders.SedBelgu;

import org.testng.annotations.DataProvider;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.AdditionOperation;
import ru.melody.web.model.Pages.Operations.Operation;

import static ru.melody.utils.GenericDate.nowDateWithoutTime;

public class IncomingDocumentPartOfBPTest extends IncomingDocument {


    // resolution
    Form resolution_IncomingDocument_Step_1 = new ru.melody.web.model.Pages.Resolution()
            .setTextOfResolution("Банчуку Ю.А., Немцеву А.Н. О.Полухин. " + nowDateWithoutTime() + " г.")
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
    public Object[][] Step_Incoming_PartOfBPTest_1() {
        return new Object[][]{
                {
                        employee9,
                        folderIncomingMeWait,
                        IncomingDocument_Periodic_Step_1,
                        //IncomingDocument_Step_1,
                        resolution_IncomingDocument_Step_1
                }
        };
    }  // resolution

    Form resolution_IncomingDocument_Step_4 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Прокофьевой Ю.А. Ю.Банчук. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Прокофьева Юлия Александровна (Лаборант)"),
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
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Автор")
//                            .setValueField("Немцев Александр Николаевич (Директор департамента)"),
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Исполнитель")
//                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Контролеры")
//                            .setValueField("Голубёвская Ирина Александровна (Старший инспектор)"),
//                    getFieldStringWithChoiceInListEmployers()
//                            .setFieldName("Соисполнители")
//                            .setValueField(""),
//                    getFieldText()
//                            .setFieldName("Текст резолюции")
//                            .setValueField(""),
//                    getFieldString()
//                            .setFieldName("Исполнить до")
//                            .setValueField(""),
//                    getFieldText()
//                            .setFieldName("Резолюция")
//                            .setValueField(""),
//                    getFieldString()
//                            .setFieldName("Статус")
//                            .setValueField(""),
            })
            .setOperation(new Operation[]{
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_PartOfBPTest_4() {
        return new Object[][]{
                {
                        employee5,
                        folderIncomingMeWait,
                        IncomingDocument_Step_6,
                        resolution_IncomingDocument_Step_4
                }
        };
    }

    @DataProvider
    public Object[][] Step_Incoming_4() {
        return new Object[][]{
                {
                        employee11,
                        folderIncomingMeWait,
                        IncomingDocument_Step_4,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Incoming_5() {
        return new Object[][]{
                {
                        employee12,
                        folderIncomingMeWait,
                        IncomingDocument_Step_5,
                }
        };
    }

    // исходящий
    Form document_Step_20_AfterSave = getGeneralOutgoingDocument()
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Статус")
                            .setValueField("Черновик"),
            })
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Incoming_20() {
        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_19,
                        document_Step_20,
                        document_Step_20_AfterSave
                }
        };
    }

    Form getGeneralOutgoingDocument() {
        return new ru.melody.web.model.Pages.Document()
                .setNameOfObjectForOpenInTheGrid("исх.1");
    }

    Form document_Step_Outcoming_1 = getGeneralOutgoingDocument()
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Статус")
                            .setValueField("Согласование"),
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

    @DataProvider
    public Object[][] Step_Outcoming__1() {

        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        document_Step_Outcoming_1
                }
        };
    }

    Form acceptFormBeforeOperation_Step_1 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Комментарии")
                            .setValueField("Вернуть на согласование"),
            })
            .setOperation(new Operation[]{
                    new Operation("Вернуть на согласование"),
                    new Operation("Отмена")
            });

    @DataProvider
    public Object[][] Step_2() {

        return new Object[][]{
                {
                        employee2,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                        acceptFormBeforeOperation_Step_1
                }
        };
    }

    @DataProvider
    public Object[][] Step_3() {

        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                        acceptFormBeforeOperation_Step_1
                }
        };
    }


    @DataProvider
    public Object[][] Step_4() {

        return new Object[][]{
                {
                        employee2,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                },
                {
                        employee3,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }


    Form acceptFormBeforeOperation_Step_5 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Комментарий")
                            .setValueField("Вернуть на согласование"),
            })
            .setOperation(new Operation[]{
                    new Operation("Вернуть на согласование"),
                    new Operation("Отмена")
            });


    @DataProvider
    public Object[][] Step_5() {

        return new Object[][]{
                {
                        employee4,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                        acceptFormBeforeOperation_Step_5
                }
        };
    }

    @DataProvider
    public Object[][] Step_6() {

        return new Object[][]{
                {
                        employee5,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }

    @DataProvider
    public Object[][] Step_7() {

        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }


    // Этап «Передача на подписание» (Кашина А.П.)
    Form document_Step_8 = getGeneralOutgoingDocument()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Папка")
                            .setValueField("ОД. Срочно"),

            });

    @DataProvider
    public Object[][] Step_8() {
        return new Object[][]{
                {
                        employee6,
                        folderIncomingMeWait,
                        document_Step_8,
                }
        };
    }

    Form acceptFormBeforeOperation_Step_9 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Согласующие")
                            .setValueField("Маматов Александр Васильевич (Проректор по образовательной деятельности)"),
                    getFieldText()
                            .setFieldName("Комментарий")
                            .setValueField("kkk"),
            })
            .setOperation(new Operation[]{
                    new Operation("Привлечь согласующего"),
                    new Operation("Отмена")
            });


    @DataProvider
    public Object[][] Step_9() {
        return new Object[][]{
                {
                        employee7,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                        acceptFormBeforeOperation_Step_9
                }
        };
    }

    @DataProvider
    public Object[][] Step_10() {
        return new Object[][]{
                {
                        employee8,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }

    @DataProvider
    public Object[][] Step_11() {

        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }

    // Этап «Передача на подписание» (Асина М.В.)
    @DataProvider
    public Object[][] Step_12() {

        return new Object[][]{
                {
                        employee9,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }

    //  Этап «Подписание» (Никулина Е.В.)
    @DataProvider
    public Object[][] Step_13() {

        return new Object[][]{
                {
                        employee7,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }

    // Отправка документа исполнителем (Прокофьева Ю.А.)
    Form document_Step_Outcoming_14 = getGeneralOutgoingDocument()
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Статус")
                            .setValueField("Зарегистрировано"),
            });

    Form acceptFormBeforeOperation_Step_14 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldFile()
                            .setFieldName("Файл")
                            .setValueField(file[1])
            })
            .setFields(new FieldObject[]{
                            getFieldFile()
                                    .setFieldName("Файл")
                                    .setValueField(file[1]),
                            getFieldStringWithChoiceInList()
                                    .setFieldName("Способ отправки")
                                    .setValueField("Факс")
                    }
            )
            .setOperation(new Operation[]{
                    new Operation("Документ подготовлен"),
                    new Operation("Отмена")
            });

    @DataProvider
    public Object[][] Step_14() {
        return new Object[][]{
                {
                        employee1,
                        folderIncomingMeWait,
                        document_Step_Outcoming_14,
                        acceptFormBeforeOperation_Step_14
                }
        };
    }

    // Снятие документа с контроля (Голубевская И.А.)
    Form IncomingDocument_Step_15 = getGeneralIncomingDocument()
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Статус")
                            .setValueField("Архив"),
            });

    // Отправка документа исполнителем (Прокофьева Ю.А.)
    Form document_Step_15 = getGeneralOutgoingDocument()
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Статус")
                            .setValueField("Архив")});

    @DataProvider
    public Object[][] Step_15() {

        return new Object[][]{
                {
                        employee12,
                        folderIncomingMeWait,
                        folderIncomingAll,
                        IncomingDocument_Step_15,
                        document_Step_15,
                }
        };
    }

}

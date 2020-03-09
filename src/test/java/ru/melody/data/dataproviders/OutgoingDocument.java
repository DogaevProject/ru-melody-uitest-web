package ru.melody.data.dataproviders;

import org.testng.annotations.DataProvider;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.AdditionOperation;
import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.model.Pages.RouteScheme;
import ru.melody.web.model.ItemRouteScheme;

/**
 * Данные
 */
public abstract class OutgoingDocument extends BaseDataProvider {

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

    Form getGeneralDocument() {
        return new ru.melody.web.model.Pages.Document()
                .setNameOfObjectForOpenInTheGrid("10/01 1")
                .setRouteScheme((RouteScheme) routeSchemeForDocument_Step_1);
    }

    Form document_Step_1 = getGeneralDocument()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Заголовок")
                            .setValueField(getGeneralDocument().getNameOfObjectForOpenInTheGrid()),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Адресат")
                            .setListValueField(new String[]{"Администрация Губернатора Белгородской области"}),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Подписант")
                            .setListValueField(new String[]{"Полухин Олег Николаевич (Ректор)"}),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Руководитель исполнителя")
                            .setValueField("Банчук Юрий Анатольевич (Директор департамента)"),
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Способ отправки")
                            .setValueField("Факс"),
            })
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


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

    Form formWithParametersForNewDocument_Step_1 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Код")
                            .setValueField("Р"),
            })
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Тип")
                            .setValueField("Проект"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Код")
                            .setValueField("Ф"),
            })
            .setOperation(new Operation[]{
                    new Operation().setName("Добавить"),
                    new Operation().setName("Отмена")
            });


    @DataProvider
    public Object[][] Step_1() {

        return new Object[][]{
                {
                        employee1,
                        OutgoingAllFolder,
                        document_Step_1,
                        formWithParametersForNewDocument_Step_1

                }
        };


    }

    // Внутреннее согласование документа. Возврат исполнителю (Мурье С.С.)

    Form document_Step_2 = getGeneralDocument();


    @DataProvider
    public Object[][] Step_2() {

        return new Object[][]{
                {
                        employee2,
                        OutgoingMeWaitFolder,
                        document_Step_2,
                        acceptFormBeforeOperation_Step_1
                }
        };


    }

    //Доработка проекта служебного документа после этапа «Внутреннее согласование»
    Form document_Step_3 = getGeneralDocument();


    @DataProvider
    public Object[][] Step_3() {

        return new Object[][]{
                {
                        employee1,
                        OutgoingMeWaitFolder,
                        document_Step_3,
                        acceptFormBeforeOperation_Step_1
                }
        };
    }


    // Внутреннее согласование документа. (Мурье С.С.)
    // Внутреннее согласование документа (Старусев И.В.)
    Form document_Step_4 = getGeneralDocument();


    @DataProvider
    public Object[][] Step_4() {

        return new Object[][]{
                {
                        employee2,
                        OutgoingMeWaitFolder,
                        document_Step_4,
                },
                {
                        employee3,
                        OutgoingMeWaitFolder,
                        document_Step_4,
                }
        };
    }

    // Внешнее согласование документа (Немцев А.Н.)
    Form document_Step_5 = getGeneralDocument();

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
                        OutgoingMeWaitFolder,
                        document_Step_5,
                        acceptFormBeforeOperation_Step_5
                }
        };
    }


    // Согласование руководителем исполнителя (Банчук Ю.А.)
    Form document_Step_6 = getGeneralDocument();


    @DataProvider
    public Object[][] Step_6() {

        return new Object[][]{
                {
                        employee5,
                        OutgoingMeWaitFolder,
                        document_Step_6,
                }
        };
    }

    // Этап «Передача бумажного оригинала» (Прокофьева Ю.А.)
    Form document_Step_7 = getGeneralDocument();


    @DataProvider
    public Object[][] Step_7() {

        return new Object[][]{
                {
                        employee1,
                        OutgoingMeWaitFolder,
                        document_Step_7,
                }
        };
    }

    // Этап «Передача на подписание» (Кашина А.П.)
    Form document_Step_8 = getGeneralDocument()
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
                        OutgoingMeWaitFolder,
                        document_Step_8,
                }
        };
    }


    // Этап «Подписание» (Никулина Е.В.)
    Form document_Step_9 = getGeneralDocument()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Папка")
                            .setValueField("ОД. Срочно")});

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
                        OutgoingMeWaitFolder,
                        document_Step_9,
                        acceptFormBeforeOperation_Step_9
                }
        };
    }

    // Этап «Дополнительное согласование»  (Маматов А.В.)
    Form document_Step_10 = getGeneralDocument();


    @DataProvider
    public Object[][] Step_10() {

        return new Object[][]{
                {
                        employee8,
                        OutgoingMeWaitFolder,
                        document_Step_10,
                }
        };
    }

    // Этап «Передача бумажного оригинала» (Прокофьева Ю.А.)
    @DataProvider
    public Object[][] Step_11() {

        return new Object[][]{
                {
                        employee1,
                        OutgoingMeWaitFolder,
                        getGeneralDocument(),
                }
        };
    }


    // Этап «Передача на подписание» (Асина М.В.)
    @DataProvider
    public Object[][] Step_12() {

        return new Object[][]{
                {
                        employee9,
                        OutgoingMeWaitFolder,
                        getGeneralDocument(),
                }
        };
    }

    //  Этап «Подписание» (Никулина Е.В.)
    @DataProvider
    public Object[][] Step_13() {

        return new Object[][]{
                {
                        employee7,
                        OutgoingMeWaitFolder,
                        getGeneralDocument(),
                }
        };
    }

    // Отправка документа исполнителем (Прокофьева Ю.А.)
    Form document_Step_14 = getGeneralDocument()
            .setFields(new FieldObject[]{
                    getFieldStringWithChoiceInListMultiple()
                            .setFieldName("Статус")
                            .setValueField("Архив")});

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
                        OutgoingMeWaitFolder,
                        document_Step_14,
                        acceptFormBeforeOperation_Step_14
                }
        };
    }
}

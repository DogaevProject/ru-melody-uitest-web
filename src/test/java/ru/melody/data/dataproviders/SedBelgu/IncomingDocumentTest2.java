package ru.melody.data.dataproviders.SedBelgu;

import org.testng.annotations.DataProvider;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.AdditionOperation;
import ru.melody.web.model.Pages.Operations.Operation;
import ru.melody.web.model.Pages.RouteScheme;

import static ru.melody.utils.GenericDate.nowDateWithoutTime;

public class IncomingDocumentTest2  extends IncomingDocumentPartOfBPTest {

    @DataProvider
    public Object[][] Step_Incoming_2_1() {
        return new Object[][]{
                {
                        employee9,
                        folderIncomingMeWait,
                        IncomingDocument_Step_1,
                        //IncomingDocument_Step_1,
                        resolution_IncomingDocument_Step_1
                }
        };
    }  // resolution


    // Создание резолюции исполнителем резолюции (Беленко В.А.)
    // resolution
    Form resolution_IncomingDocument_Step_9 = new ru.melody.web.model.Pages.Resolution()
            .setNameOfObjectForOpenInTheGrid("Прокофьевой Ю.А. В.Беленко. " + nowDateWithoutTime() + " г.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Исполнитель")
                            .setValueField("Прокофьева Юлия Александровна (Лаборант)")
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


    // Проверка отчета исполнителя. Предоставление отчета исполнителем резолюции (Беленко В.А.)


    @DataProvider
    public Object[][] Step_Incoming_13() {
        return new Object[][]{
                {
                        employee13,
                        folderIncoming,
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

    @DataProvider
    public Object[][] Step_Incoming_14() {
        return new Object[][]{
                {
                        employee4,
                        folderIncomingMeWait,
                        getGeneralIncomingDocument(),
                        resolution_IncomingDocument_Step_1,
                        reportFromResolution_IncomingDocument_Step_13,
                        reportFromResolution_IncomingDocument_Step_14,
                        foldersInWhichDocumentShouldBe_StepWithReportOfEndExecution,
                        folderIncomingReportOfEndExecution,
                        foldersInWhichDocumentShouldBe_Step_Execution
                }
        };
    }


    // Создание исходящего документа (Прокофьева Ю.А.)

    Form routeSchemeForDocument_Step_20 = new RouteScheme()
//            .setItemRouteSchemeForEdit(new ItemRouteScheme[]{
//                    new ItemRouteScheme("Внешнее согласование")
//                            .setNewInnerBlock(true)
//                            .setTypeItemIsParallelBlock(true)
//                            .setUserRoute(new Employee[]{employee4})
//            })
             ;


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

    @DataProvider
    public Object[][] Step_Incoming_20() {
        return new Object[][]{
                {
                        employee1,
                        folderIncoming,
                        getGeneralIncomingDocument(),
                        reportFromResolution_IncomingDocument_Step_19,
                        document_Step_20,
                        document_Step_20_AfterSave
                }
        };
    }



    @DataProvider
    public Object[][] Step_Outcoming__1() {

        return new Object[][]{
                {
                        employee1,
                        folderIncoming,
                        document_Step_Outcoming_1,
                }
        };
    }


    @DataProvider
    public Object[][] Step_4() {

        return new Object[][]{

                {
                        employee3,
                        folderIncomingMeWait,
                        getGeneralOutgoingDocument(),
                }
        };
    }

}

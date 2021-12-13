package ru.melody.data.dataproviders.ryazangov;

import org.testng.annotations.DataProvider;
import ru.melody.web.model.Administration.Fields.TypesOfFields.FieldObject;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.Pages.Operations.AdditionOperation;
import ru.melody.web.model.Pages.Operations.Operation;

import static ru.melody.utils.GenericDate.getDateWithDaysShifting;

public class ProjectBP extends Ryazangov {

    protected String nameOfObjectForOpenInTheGrid = "13/12 1"; // nameOfObjectForOpenInTheGrid - отображаемое в гриде название объекта.  Это м.б любой текст по которому можно отличить нужный объект от остальных.

    private String mayor = "Майоров М.А. (министр имущественных и земельных отношений Рязанской области)";


    Form getGeneralInitialOfProject() {
        return new ru.melody.web.model.Pages.Form()
                .setNameOfObjectForOpenInTheGrid(nameOfObjectForOpenInTheGrid);
    }

    Form InitialOfProject_Step_1 = getGeneralInitialOfProject()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Инициатор проекта")
                            .setValueField("Абакумова Н.В."),
                    getFieldTextResizable()
                            .setFieldName("Предполагаемое наименование проекта")
                            .setValueField(getGeneralInitialOfProject().getNameOfObjectForOpenInTheGrid()),
                    getFieldTextResizable()
                            .setFieldName("Решаемые проблемы")
                            .setValueField("Решаемые проблемы"),
                    getFieldTextResizable()
                            .setFieldName("Цель проекта")
                            .setValueField("Цель проекта"),
                    getFieldTextResizable()
                            .setFieldName("Способ достижения цели")
                            .setValueField("Способ достижения цели"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Функциональный заказчик")
                            .setValueField(mayor),
            })
            .setFields(new FieldObject[]{
                    getFieldTextResizable()
                            .setFieldName("Решаемые проблемы")
                            .setValueField("Решаемые проблемы"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("ФИО заявителя")
                            .setValueField("Абаева Р.Х. (начальник отдела экологической безопасности)"),
            })
            .setValueFiles(new String[]{file[1]})
            .setOperation(new Operation[]{
                    new AdditionOperation(),
                    new Operation("Сохранить"),
                    new Operation("Закрыть")
            });


    @DataProvider
    public Object[][] Step_Project_1() {
        return new Object[][]{
                {
                        employee1,
                        Predlojeniya,
                        InitialOfProject_Step_1
                }
        };
    }

    Form formBeforeRegistration_Step_2 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Координирующий орган")
                            .setValueField("Министерство имущественных и земельных отношений Рязанской области"),
            })
            .setOperation(new Operation[]{
                    new Operation().setName("Зарегистрировать"),
                    new Operation().setName("Отмена")
            });

    @DataProvider
    public Object[][] Step_Project_2() {
        return new Object[][]{
                {
                        employee2,
                        VsePredlojeniya,
                        InitialOfProject_Step_1,
                        formBeforeRegistration_Step_2
                }
        };
    }





    // вкладка Инициирование
    Form projectTab1_Step_3 = getGeneralInitialOfProject()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Руководитель проекта")
                            .setValueField("Федюнина И.А.")
            });


    // вкладка Инициирование
    Form projectTab2_Step_3 = getGeneralInitialOfProject()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Связь с портфелем")
                            .setValueField("№ 1. «Повышение эффективности управления общественными финансами Рязанской области»")
            });


    @DataProvider
    public Object[][] Step_Project_3() {
        return new Object[][]{
                {
                        employee3,
                        Initiation,
                        projectTab1_Step_3,
                        projectTab2_Step_3
                }
        };
    }

    // вкладка Общая информация
    Form projectTab1_Step_4 = getGeneralInitialOfProject()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Связь с государственными программами Рязанской области")
                            .setValueField("4. ГП РО «О развитии сферы занятости»")
            });


    // вкладка Инициирование
    Form projectTab2_Step_4 = getGeneralInitialOfProject()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Куратор проекта")
                            .setValueField(mayor),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Администратор проекта")
                            .setValueField("Семенченко Ю.А."),
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Исполнители проекта")
                            .setValueField("Министерство сельского хозяйства и продовольствия Рязанской области")
            });


    // запись на вкладке Задачи и результаты проекта
    Form taskAndResult = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Наименование результата")
                            .setValueField("результат-1"),
                    getFieldString()
                            .setFieldName("Срок")
                            .setValueField(getDateWithDaysShifting(10)),
                    getFieldBoolean()
                            .setFieldName("Требуется финансирование")
                            .setValueBooleanField(true)
            });

    // форма Задачи
    Form task = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Наименование")
                            .setValueField("Задача-1")
            });

    //  Показатели
    Form indicators = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Наименование показателя, единица измерения")
                            .setValueField("показатель-1"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Тип показателя")
                            .setValueField("основной")

            });


    //  Финансовое обеспечение проекта
    Form financeTab1_Step_4 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Наименование результата")
                            .setValueField("результат-1"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Год реализации")
                            .setValueField("2020")

            });


    //  Финансовое обеспечение проекта
    Form financeTab2_Step_4 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Федеральный бюджет, тыс. руб")
                            .setValueField("1000000")

            });

    @DataProvider
    public Object[][] Step_Project_4() {
        return new Object[][]{
                {
                        employee4,
                        Initiation,
                        projectTab1_Step_4,
                        projectTab2_Step_4,
                        taskAndResult,
                        task,
                        indicators,
                        financeTab1_Step_4,
                        financeTab2_Step_4
                }
        };
    }



    @DataProvider
    public Object[][] Step_Project_5() {
        return new Object[][]{
                {
                        employee3,
                        Initiation,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_6() {
        return new Object[][]{
                {
                        employee5,
                        Initiation,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_6_2() {
        return new Object[][]{
                {
                        employee2,
                        Initiation,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_7() {
        return new Object[][]{
                {
                        employee3,
                        Initiation,
                }
        };
    }

    // вкладка - Уровень сложности
    Form tab1_Step_8 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Территория реализации проекта")
                            .setValueField("2 и более муниципальных"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Бюджет проекта")
                            .setValueField("Более 150 млн. рублей"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Источники финансирования")
                            .setValueField("бюджетные и внебюджетные"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Количество идентифицированных рисков")
                            .setValueField("4 и более"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Количество государственных программ Рязанской области, на достижение целей которых направлен проект")
                            .setValueField("2 и более"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Количество исполнительных органов государственной власти Рязанской области (подведомственных государственных учреждений Рязанской области), территориальных органов федеральных органов исполнительной власти и иных организаций, задействованных в реализации проекта")
                            .setValueField("5 и более"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Срок реализации проекта")
                            .setValueField("2 года и более"),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Охват населения социальными благами за 1 год, тыс. человек")
                            .setValueField("25 тыс.человек и более"),
            });

    @DataProvider
    public Object[][] Step_Project_8() {
        return new Object[][]{
                {
                        employee4,
                        Initiation,
                        tab1_Step_8
                }
        };
    }


    @DataProvider
    public Object[][] Step_Project_9() {
        return new Object[][]{
                {
                        employee2,
                        Initiation
                }
        };
    }

    Form project_Step_10 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldFile()
                            .setFieldName("Протокол одобрения основных параметров проекта")
                            .setValueField(file[1])
            })
            .setOperation(new Operation[]{
                    new Operation().setName("Сохранить"),
                    new Operation().setName("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Project_10() {
        return new Object[][]{
                {
                        employee3,
                        Initiation,
                        project_Step_10
                }
        };
    }

    Form project_Step_11 = new ru.melody.web.model.Pages.Form()
            .setFields(new FieldObject[]{
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Этап проекта")
                            .setValueField("Подготовка"),
            });


    @DataProvider
    public Object[][] Step_Project_11() {
        return new Object[][]{
                {
                        employee6,
                        Initiation,
                        Podgotovka,
                        project_Step_11,
                }
        };
    }

    Form item_1_Step_12 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("результат-1")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Ответственный исполнитель")
                            .setValueField("Белова О.В."),
                    getFieldText()
                            .setFieldName("Вид документа и характеристика")
                            .setValueField("Вид документа и характеристика для результат-1")

            });

    Form item_2_Step_12 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("кт-1")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Наименование")
                            .setValueField("кт-1"),
                    getFieldString()
                            .setFieldName("Окончание")
                            .setValueField(getDateWithDaysShifting(7)),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Уровень контроля")
                            .setValueField("Совет по проектной деятельности"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Ответственный исполнитель")
                            .setValueField("Белова О.В."),
                    getFieldText()
                            .setFieldName("Вид документа и характеристика")
                            .setValueField("Вид документа и характеристика для кт-1")
            });

    Form item_3_Step_12 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("м-1")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Наименование")
                            .setValueField("м-1"),
                    getFieldString()
                            .setFieldName("Начало")
                            .setValueField(getDateWithDaysShifting(1)),
                    getFieldString()
                            .setFieldName("Окончание")
                            .setValueField(getDateWithDaysShifting(7)),
                    getFieldStringWithChoiceInList()
                            .setFieldName("Уровень контроля")
                            .setValueField("Совет по проектной деятельности"),
                    getFieldStringWithChoiceInListEmployers()
                            .setFieldName("Ответственный исполнитель")
                            .setValueField("Белова О.В."),
                    getFieldText()
                            .setFieldName("Вид документа и характеристика")
                            .setValueField("Вид документа и характеристика для м-1")
            });


    // Методика расчета показателей
    Form tab1_Step12 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldStringWithChoiceInList()
                            .setFieldName("Наименование показателя")
                            .setValueField("показатель-1"),
            });

    // Участники проекта - Администратор проекта
    Form tab2_items1_Step12 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("Семенченко Ю.А.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Занятость в проекте, процентов (план)")
                            .setValueField("25"),
            });

    // Участники проекта - Руководитель проекта
    Form tab2_items2_Step12 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("Федюнина И.А.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Занятость в проекте, процентов (план)")
                            .setValueField("25"),
            });

    // Участники проекта - Участник проекта
    Form tab2_items3_Step12 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("Белова О.В.")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Занятость в проекте, процентов (план)")
                            .setValueField("25"),
            });
    @DataProvider
    public Object[][] Step_Project_12() {
        return new Object[][]{
                {
                        employee4,
                        Podgotovka,
                        item_1_Step_12,
                        item_2_Step_12,
                        item_3_Step_12,
                        tab1_Step12,
                        tab2_items1_Step12,
                        tab2_items2_Step12,
                        tab2_items3_Step12
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_13() {
        return new Object[][]{
                {
                        employee5,
                        Podgotovka,
                }
        };
    }

    Form files_Step_14 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldFile()
                            .setFieldName("Заключение общественно-экспертного совета")
                            .setValueField(file[1])
            });

    @DataProvider
    public Object[][] Step_Project_14() {
        return new Object[][]{
                {
                        employee3,
                        Podgotovka,
                        files_Step_14

                }
        };
    }


    @DataProvider
    public Object[][] Step_Project_15() {
        return new Object[][]{
                {
                        employee2,
                        Podgotovka,

                }
        };
    }



    @DataProvider
    public Object[][] Step_Project_15_2() {
        return new Object[][]{
                {
                        employee3,
                        Podgotovka
                }
        };
    }


    Form project_Step_16 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldFile()
                            .setFieldName("Протокол утверждения паспорта")
                            .setValueField(file[1])
            })
            .setOperation(new Operation[]{
                    new Operation().setName("Запрос на изменение"),
                    new Operation().setName("Сохранить"),
                    new Operation().setName("Закрыть")
            });

    @DataProvider
    public Object[][] Step_Project_16() {
        return new Object[][]{
                {
                        employee3,
                        Podgotovka,
                        project_Step_16
                }
        };

    }


    Form project_Step_17 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldFile()
                            .setFieldName("Протокол заседания совета об утверждении паспорта")
                            .setValueField(file[1])
            })
            .setOperation(new Operation[]{
                    new Operation().setName("Приостановить"),
                    new Operation().setName("Запрос на изменение"),
                    new Operation().setName("Сохранить"),
                    new Operation().setName("Закрыть")
            });

    Form project_Step_17_2 = new ru.melody.web.model.Pages.Form()
            .setFields(new FieldObject[]{
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Этап проекта")
                            .setValueField("Реализация"),
            });


    @DataProvider
    public Object[][] Step_Project_17() {
        return new Object[][]{
                {
                        employee6,
                        Podgotovka,
                        Realisation,
                        project_Step_17,
                        project_Step_17_2,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_18() {
        return new Object[][]{
                {
                        employee3,
                        Realisation,
                }
        };
    }

    Form report_Step_19 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldText()
                            .setFieldName("Текст отчета")
                            .setValueField("Текст отчета"),
                    getFieldFile()
                            .setFieldName("Подтверждающий документ")
                            .setValueField(file[1])
            });


    Form item_1_Step_19 = new ru.melody.web.model.Pages.Form()
            .setNameOfObjectForOpenInTheGrid("результат-1")
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldString()
                            .setFieldName("Федеральный бюджет, тыс. руб.")
                            .setValueField("50500")

            });

    @DataProvider
    public Object[][] Step_Project_19() {
        return new Object[][]{
                {
                        employee7,
                        Realisation,
                        item_1_Step_19,
                        item_2_Step_12,
                        item_3_Step_12,
                        report_Step_19
                }
        };
    }

    Form project_Step_20 = new ru.melody.web.model.Pages.Form()
            .setFields(new FieldObject[]{
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Этап проекта")
                            .setValueField("Завершение"),
            });


    @DataProvider
    public Object[][] Step_Project_20() {
        return new Object[][]{
                {
                        employee6,
                        Realisation,
                        Completion,
                        item_1_Step_12,
                        item_2_Step_12,
                        item_3_Step_12,
                        project_Step_20
                }
        };
    }


    Form project_Step_21 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldBoolean()
                            .setFieldName("Цель проекта достигнута полностью, результат получен")
                            .setValueBooleanField(true),
                    getFieldBoolean()
                            .setFieldName("Показатели проекта достигнуты")
                            .setValueBooleanField(true),
                    getFieldBoolean()
                            .setFieldName("Выполнены требования к результату проекта")
                            .setValueBooleanField(true),
                    getFieldBoolean()
                            .setFieldName("Сроки проекта соблюдены")
                            .setValueBooleanField(true),
                    getFieldBoolean()
                            .setFieldName("Бюджет проекта соблюден")
                            .setValueBooleanField(true),
            });

    @DataProvider
    public Object[][] Step_Project_21() {
        return new Object[][]{
                {
                        employee3,
                        Completion,
                        project_Step_21
                }
        };
    }


    @DataProvider
    public Object[][] Step_Project_22() {
        return new Object[][]{
                {
                        employee4,
                        Completion,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_23() {
        return new Object[][]{
                {
                        employee5,
                        Completion,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_24() {
        return new Object[][]{
                {
                        employee3,
                        Completion,
                }
        };
    }

    @DataProvider
    public Object[][] Step_Project_25() {
        return new Object[][]{
                {
                        employee2,
                        Completion,
                }
        };
    }


    Form project_Step_26 = new ru.melody.web.model.Pages.Form()
            .setFieldsForAddValue(new FieldObject[]{
                    getFieldFile()
                            .setFieldName("Подписанный итоговый отчёт")
                            .setValueField(file[1]),
                    getFieldFile()
                            .setFieldName("Протокол Проектного комитета с решением о закрытии проекта")
                            .setValueField(file[1])
            });


    @DataProvider
    public Object[][] Step_Project_26() {
        return new Object[][]{
                {
                        employee3,
                        Completion,
                        project_Step_26
                }
        };
    }


    Form project_Step_27 = new ru.melody.web.model.Pages.Form()
            .setFields(new FieldObject[]{
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Этап проекта")
                            .setValueField("Архив"),
                    getFieldStringTypeLinkWithChoiceInList()
                            .setFieldName("Статус реализации")
                            .setValueField("Проект реализован успешно без отклонений"),
            });

    @DataProvider
    public Object[][] Step_Project_27() {
        return new Object[][]{
                {
                        employee6,
                        Completion,
                        ArchiveOfProjects,
                        project_Step_27
                }
        };
    }
}

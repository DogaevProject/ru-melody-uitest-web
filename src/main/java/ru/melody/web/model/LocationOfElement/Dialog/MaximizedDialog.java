package ru.melody.web.model.LocationOfElement.Dialog;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.ExecuteOperations.ExecutionOperationInMaximizedDialog;
import ru.melody.web.elements.FillFields.FillFields;
import ru.melody.web.elements.FillFields.FillFieldsInMaximizedDialog;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;
import ru.melody.web.elements.OperationsVerifications.OperationsVerificationsInMaximizedDialog;
import ru.melody.web.elements.FieldsVerifications.FieldsVerifications;
import ru.melody.web.elements.FieldsVerifications.FieldsVerificationsInMaximizedDialog;
import ru.melody.web.elements.FieldsVerifications.ValuesOfFieldsVerifications;
import ru.melody.web.elements.FieldsVerifications.ValuesOfFieldsInMaximizedDialogVerifications;


/**
 * Диалоговое окно с формой
 *
 * - окно развернуто на весь экран
 * - с возможностью или без возможности уменьшить размер окна
 *
 * - прим. форма создания резолюции из грида - входящие, формы создания документа из грида
 */
public class MaximizedDialog implements Form {

    @Override
    public ValuesOfFieldsVerifications verifyValuesOfFields() {
        return new ValuesOfFieldsInMaximizedDialogVerifications();
    }

    @Override
    public FieldsVerifications verifyFields() {
        return new FieldsVerificationsInMaximizedDialog();
    }

    @Override
    public FillFields fillFields() {
        return new FillFieldsInMaximizedDialog();
    }

    @Override
    public OperationsVerifications verifyOperation() {
        return new OperationsVerificationsInMaximizedDialog();
    }

    @Override
    public ExecutionOperation executionOperation() {
        return new ExecutionOperationInMaximizedDialog();
    }
}

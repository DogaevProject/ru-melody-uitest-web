package ru.melody.web.model.LocationOfElement.Dialog;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.ExecuteOperations.ExecutionOperationInAlertDialog;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;
import ru.melody.web.elements.OperationsVerifications.OperationsVerificationsInAlertDialog;
import ru.melody.web.elements.TextVerifications.TextMessageVerifications;
import ru.melody.web.elements.TextVerifications.TextMessageVerificationsInAlertDialog;

/**
 * Диалоговое окно - Alert
 *
 * - прим. окно успешного создания объекта, окно ошибки
 */
public class AlertDialog implements Alert {

    @Override
    public OperationsVerifications verifyOperation() {
        return new OperationsVerificationsInAlertDialog();
    }

    @Override
    public ExecutionOperation executionOperation() {
        return new ExecutionOperationInAlertDialog();
    }

    @Override
    public TextMessageVerifications verifyTextMessage() {
        return new TextMessageVerificationsInAlertDialog();
    }
}

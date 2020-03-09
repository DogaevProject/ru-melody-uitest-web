package ru.melody.web.model.LocationOfElement.Dialog;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.ExecuteOperations.ExecutionOperationOnTabWithInnerObjectsInMaximizedDialog;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;
import ru.melody.web.elements.OperationsVerifications.OperationsVerificationsOnTabWithInnerObjectsInMaximizedDialog;
import ru.melody.web.model.LocationOfElement.Toolbar;

/**
 * Вкладка Резолюции, Проверка исполнения и др.
 */
public class TabWithInnerObjectsInMaximizedDialog implements Toolbar {

    @Override
    public OperationsVerifications verifyOperation() {
        return new OperationsVerificationsOnTabWithInnerObjectsInMaximizedDialog();
    }

    @Override
    public ExecutionOperation executionOperation() {
        return new ExecutionOperationOnTabWithInnerObjectsInMaximizedDialog();
    }

}

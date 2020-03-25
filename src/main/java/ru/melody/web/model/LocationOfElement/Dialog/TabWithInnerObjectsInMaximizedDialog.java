package ru.melody.web.model.LocationOfElement.Dialog;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.ExecuteOperations.ExecutionOperationOnTabWithInnerObjectsInMaximizedDialog;
import ru.melody.web.elements.InnerItemsVerification.InnerItemsVerification;
import ru.melody.web.elements.InnerItemsVerification.InnerItemsVerificationOnTabWithInnerObjectsInMaximizedDialog;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;
import ru.melody.web.elements.OperationsVerifications.OperationsVerificationsOnTabWithInnerObjectsInMaximizedDialog;

/**
 * Вкладка Резолюции, Проверка исполнения и др.
 */
public class TabWithInnerObjectsInMaximizedDialog extends PageWithInnerObjects {

    @Override
    public OperationsVerifications verifyOperation() {
        return new OperationsVerificationsOnTabWithInnerObjectsInMaximizedDialog();
    }

    @Override
    public ExecutionOperation executionOperation() {
        return new ExecutionOperationOnTabWithInnerObjectsInMaximizedDialog();
    }


    @Override
    public InnerItemsVerification verifyInnerItems() {
        return new InnerItemsVerificationOnTabWithInnerObjectsInMaximizedDialog();
    }

}

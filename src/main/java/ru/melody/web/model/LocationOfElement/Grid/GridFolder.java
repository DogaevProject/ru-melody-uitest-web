package ru.melody.web.model.LocationOfElement.Grid;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.ExecuteOperations.ExecutionOperationInGridFolder;
import ru.melody.web.elements.InnerItemsVerification.InnerItemsVerification;
import ru.melody.web.elements.InnerItemsVerification.InnerItemsVerificationInGridFolder;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;
import ru.melody.web.elements.OperationsVerifications.OperationsVerificationsInGridFolder;
import ru.melody.web.model.LocationOfElement.Dialog.PageWithInnerObjects;

/**
 * Грид ПМ
 */
public class GridFolder extends PageWithInnerObjects {

    @Override
    public OperationsVerifications verifyOperation() {
        return new OperationsVerificationsInGridFolder();
    }

    @Override
    public ExecutionOperation executionOperation() {
        return new ExecutionOperationInGridFolder();
    }

    @Override
    public InnerItemsVerification verifyInnerItems() {
        return new InnerItemsVerificationInGridFolder();
    }
}

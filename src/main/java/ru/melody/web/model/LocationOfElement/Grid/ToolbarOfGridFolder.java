package ru.melody.web.model.LocationOfElement.Grid;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.ExecuteOperations.ExecutionOperationInGridFolder;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;
import ru.melody.web.elements.OperationsVerifications.OperationsVerificationsInGridFolder;
import ru.melody.web.model.LocationOfElement.Toolbar;

/**
 * Тулбар грида ПМ
 */
public class ToolbarOfGridFolder implements Toolbar {

    @Override
    public OperationsVerifications verifyOperation() {
        return new OperationsVerificationsInGridFolder();
    }

    @Override
    public ExecutionOperation executionOperation() {
        return new ExecutionOperationInGridFolder();
    }
}

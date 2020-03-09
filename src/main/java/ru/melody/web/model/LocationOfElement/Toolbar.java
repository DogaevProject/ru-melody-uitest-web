package ru.melody.web.model.LocationOfElement;

import ru.melody.web.elements.ExecuteOperations.ExecutionOperation;
import ru.melody.web.elements.OperationsVerifications.OperationsVerifications;

public interface Toolbar extends LocationOfElement {

    OperationsVerifications verifyOperation();

    ExecutionOperation executionOperation();

}

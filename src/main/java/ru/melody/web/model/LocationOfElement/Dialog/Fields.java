package ru.melody.web.model.LocationOfElement.Dialog;

import ru.melody.web.elements.FillFields.FillFields;
import ru.melody.web.elements.FieldsVerifications.FieldsVerifications;
import ru.melody.web.elements.FieldsVerifications.ValuesOfFieldsVerifications;
import ru.melody.web.model.LocationOfElement.LocationOfElement;

public interface Fields extends LocationOfElement {

    ValuesOfFieldsVerifications verifyValuesOfFields();

    FieldsVerifications verifyFields();

    FillFields fillFields();

}

package ru.melody.web.steps.DialogSteps;

import ru.melody.web.model.Pages.Document;
import ru.melody.web.model.Pages.Form;
import ru.melody.web.model.LocationOfElement.Dialog.TabWithInnerObjectsInMaximizedDialog;

public class MaximizedDialogSteps extends EditFormSteps {

    Form getCurrentDialog(Form form) {
        return form.setFormLocation(maximizedDialog);
    }

    // Здесь new Document() - просто используется в качестве объекта по умолчанию, который наследуется от род. класса всех диалогов и которому можно выставить значение .setFormLocation
    Form getCurrentDialog() {
        return new Document().setFormLocation(maximizedDialog);
    }


     ru.melody.web.model.LocationOfElement.Toolbar getCurrentLocationOfTabWithInnerObjects() {
        return new TabWithInnerObjectsInMaximizedDialog();
    }

}

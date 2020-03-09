package ru.melody.web.steps.DialogSteps;

import ru.melody.web.model.Pages.Form;

public class EditFilesOfFormSteps extends DialogSteps {

    public EditFilesOfFormSteps() {
    }

    public EditFilesOfFormSteps(Form currentDialog, ru.melody.web.model.LocationOfElement.Toolbar currentTabWithInnerObjects) {
        DialogSteps.currentDialog = currentDialog;
        DialogSteps.currentLocationOfTabWithInnerObjects = currentTabWithInnerObjects;
    }

    /**
     * Прикрепление файлов к объекту через операцию добавления файла на тулбаре - "скрепка"
     *
     */
   public EditFilesOfFormSteps addFiles(Form form){
       if (form.getValueFiles() != null) {
           addAttachFiles(form.getFormLocation().executionOperation().clickButtonAddFile(), form.getValueFiles());
           waitMaskOfLoading();
       }
       return this;
    };
}

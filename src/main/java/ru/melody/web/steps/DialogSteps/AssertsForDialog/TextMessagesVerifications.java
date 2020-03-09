package ru.melody.web.steps.DialogSteps.AssertsForDialog;

import com.codeborne.selenide.Condition;
import ru.melody.web.model.LocationOfElement.Dialog.Alert;
import ru.melody.web.model.LocationOfElement.Dialog.AlertDialog;

public class TextMessagesVerifications extends AssertsForDialog  {

    TextMessagesVerifications verifyTextMessageInAlertDialog(String textMessage) {
        Alert alertDialog = new AlertDialog();
        alertDialog.verifyTextMessage().getTextMessage().shouldHave(Condition.text(textMessage));
        return this;
    }

}

package ru.melody.web.elements.TextVerifications;

import com.codeborne.selenide.SelenideElement;
import ru.melody.web.steps.DialogSteps.AssertsForDialog.AssertsForDialog;

/**
 * Локаторы необходимые методам проверки текста в диалогах
 * В подклассах реализация конкретных страниц
 */
public abstract class TextMessageVerifications extends AssertsForDialog {

    public abstract SelenideElement getTextMessage();

}

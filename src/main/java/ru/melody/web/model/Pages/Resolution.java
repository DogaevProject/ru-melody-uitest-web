package ru.melody.web.model.Pages;

/**
 * Модель объекта - Резолюция
 */
public class Resolution extends Document {

    private String textOfResolution;


    /**
     *  Текст резолюции
     */
    public String getTextOfResolution() {
        return textOfResolution;
    }

    public Resolution setTextOfResolution(String textOfResolution) {
        this.textOfResolution = textOfResolution;
        return this;
    }

}

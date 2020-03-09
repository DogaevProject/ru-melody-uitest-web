package ru.melody.web.model.Pages;


/**
 * Модель объекта - Документ
 */
public class Document extends Form {

    private Resolution[] resolutionOfDocument;

    /**
     * Резолюция документа
     */
    public Resolution[] getResolutionOfDocument() {
        return resolutionOfDocument;
    }

    public Document setResolutionOfDocument(Resolution[] resolutionOfDocument) {
        this.resolutionOfDocument = resolutionOfDocument;
        return this;
    }


}

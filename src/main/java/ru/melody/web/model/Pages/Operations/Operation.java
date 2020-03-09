package ru.melody.web.model.Pages.Operations;


import ru.melody.web.model.LocationOfElement.Toolbar;

/**
 * Операция - кнопка на тулбаре
 */
public class Operation {

    public Operation() {
    }

    public Operation(String name) {
        this.name = name;
    }

    String name;

    Toolbar toolbar;


    public Toolbar getLocationToolbar() {
        return toolbar;
    }

    public Operation setLocationToolbar(Toolbar page) {
        this.toolbar = page;
        return this;
    }

    public String getName() {
        return name;
    }

    public Operation setName(String name) {
        this.name = name;
        return this;
    }

}

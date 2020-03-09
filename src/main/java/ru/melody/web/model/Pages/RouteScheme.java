package ru.melody.web.model.Pages;

import ru.melody.web.model.ItemRouteScheme;

public class RouteScheme extends Document {

    private ItemRouteScheme[] itemRouteSchemeForEdit;
    private ItemRouteScheme[] itemRouteScheme;
    private String ReviewDuration = "";


    /**
     * Длительность рассмотрения
     */
    public String getReviewDuration() {
        return ReviewDuration;
    }

    public void setReviewDuration(String reviewDuration) {
        ReviewDuration = reviewDuration;

    }

    public ItemRouteScheme[] getItemRouteSchemeForEdit() {
        return itemRouteSchemeForEdit;
    }

    public RouteScheme setItemRouteSchemeForEdit(ItemRouteScheme[] itemRouteSchemeForEdit) {
        this.itemRouteSchemeForEdit = itemRouteSchemeForEdit;
        return this;
    }

    public ItemRouteScheme[] getItemRouteScheme() {
        return itemRouteScheme;
    }

    public RouteScheme setItemRouteScheme(ItemRouteScheme[] itemRouteScheme) {
        this.itemRouteScheme = itemRouteScheme;
        return this;
    }
}

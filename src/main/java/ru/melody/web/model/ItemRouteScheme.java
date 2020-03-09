package ru.melody.web.model;

import ru.melody.web.model.Administration.Users.Employee;

/**
 * Модель объекта - Блок МС
 */
public class ItemRouteScheme {
    public ItemRouteScheme(String nameItem) {
        this.nameItem = nameItem;
    }

    private String nameItem;
    private boolean isInnerBlock; // существующий вложенный блок
    private boolean isNewInnerBlock; // для добавления нового внутреннего блока
    private boolean typeItemIsParallelBlock;
    private boolean typeItemIsSequenceBlock;
    private Employee[] userRoute;

    /**
     * Участники рассмотрения
     */
    public Employee[] getUserRoute() {
        return userRoute;
    }

    public ItemRouteScheme setUserRoute(Employee[] userRoute) {
        this.userRoute = userRoute;
        return this;

    }

    public boolean isTypeItemIsParallelBlock() {
        return typeItemIsParallelBlock;
    }

    public ItemRouteScheme setTypeItemIsParallelBlock(boolean typeItemIsParallelBlock) {
        this.typeItemIsParallelBlock = typeItemIsParallelBlock;
        return this;
    }

    public boolean isTypeItemIsSequenceBlock() {
        return typeItemIsSequenceBlock;
    }

    public ItemRouteScheme setTypeItemIsSequenceBlock(boolean typeItemIsSequenceBlock) {
        this.typeItemIsSequenceBlock = typeItemIsSequenceBlock;
        return this;
    }

    public String getNameItem() {
        return nameItem;
    }

    public ItemRouteScheme setNameItem(String nameItem) {
        this.nameItem = nameItem;
        return this;
    }


    public boolean isInnerBlock() {
        return isInnerBlock;
    }

    public ItemRouteScheme setInnerBlock(boolean innerBlock) {
        this.isInnerBlock = innerBlock;
        return this;
    }


    public boolean isNewInnerBlock() {
        return isNewInnerBlock;
    }

    public ItemRouteScheme setNewInnerBlock(boolean newInnerBlock) {
        isNewInnerBlock = newInnerBlock;
        return this;
    }

}

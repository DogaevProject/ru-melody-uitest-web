package ru.melody.web.model.Folder;

/**
 * Папка - ПМ
 */
public class Folder {

    private String nameFolder = "";
    private Folder parentFolder;
   
    /**
     * Наименование родительского папки
     */
    public Folder getParentFolder() {
        return parentFolder;
    }

    public Folder setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
        return this;
    }

    /**
    Имя папки
     */
    public String getNameFolder() {
        return nameFolder;
    }

    public Folder setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
        return this;
    }


}

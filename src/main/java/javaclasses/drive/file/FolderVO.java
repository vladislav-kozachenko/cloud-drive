package javaclasses.drive.file;

public class FolderVO {

    private FolderId id;

    private String name;

    public FolderVO(String name) {
        this.name = name;
    }

    public FolderId getId() {
        return id;
    }

    public void setId(FolderId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

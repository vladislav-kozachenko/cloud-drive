package javaclasses.drive.file;

public class FileVO {

    private FileId id;

    private String name;

    public FileVO(String name) {
        this.name = name;
    }

    public FileId getId() {
        return id;
    }

    public void setId(FileId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package javaclasses.drive.file;

public abstract class FileSystemItem {

    private FileSystemItemId id;

    private String name;

    private FileSystemItem parent;

    public FileSystemItemId getId() {
        return id;
    }

    public void setId(FileSystemItemId id) {
        this.id = id;
    }

    public FileSystemItem(String name) {
        this.name = name;
    }

    public FileSystemItem(String name, FileSystemItem parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract boolean isDirectory();

}

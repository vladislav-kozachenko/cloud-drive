package javaclasses.drive.file;

public abstract class FileSystemItem {

    private FileSystemItemId id;

    private String name;

    private FolderVO parent;

    public FileSystemItemId getId() {
        return id;
    }

    public void setId(FileSystemItemId id) {
        this.id = id;
    }

    public FileSystemItem(String name) {
        this.name = name;
    }

    public FileSystemItem(String name, FolderVO parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract boolean isDirectory();

}

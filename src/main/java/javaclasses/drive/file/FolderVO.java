package javaclasses.drive.file;

public class FolderVO extends FileSystemItem{

    public FolderVO(String name) {
        super(name);
    }

    public FolderVO(String name, FolderVO parent) {
        super(name, parent);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}

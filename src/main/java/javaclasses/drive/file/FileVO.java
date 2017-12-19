package javaclasses.drive.file;

public class FileVO extends FileSystemItem{

    public FileVO(String name) {
        super(name);
    }

    public FileVO(String name, FolderVO parent) {
        super(name, parent);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

}

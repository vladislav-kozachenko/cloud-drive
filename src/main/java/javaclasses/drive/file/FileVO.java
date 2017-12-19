package javaclasses.drive.file;

public class FileVO extends FileSystemItem{

    public FileVO(String name) {
        super(name);
    }

    public FileVO(String name, FileSystemItem parent) {
        super(name, parent);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

}

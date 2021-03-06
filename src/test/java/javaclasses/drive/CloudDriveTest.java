package javaclasses.drive;

import javaclasses.drive.file.FileSystemItem;
import javaclasses.drive.file.FileVO;
import javaclasses.drive.file.FolderVO;
import javaclasses.drive.service.FileManagementService;
import javaclasses.drive.service.FileSharingService;
import javaclasses.drive.service.UserService;
import javaclasses.drive.user.Credentials;
import javaclasses.drive.user.SecurityToken;
import javaclasses.drive.user.UserVO;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CloudDriveTest {

    private FileManagementService fileManagementService;
    private FileSharingService fileSharingService;
    private UserService userService;
    private InputStream stream;

    @Test
    public void testFileStoring() throws
            RegisterFailedException,
            LoginFailedException,
            NoPermissionException,
            FileUploadingError,
            FileNotFoundException {

        // registration
        UserVO user  = new UserVO(new Credentials("user", "password"));
        userService.register(user);

        // authentication
        SecurityToken token = userService.logIn(user.getCredentials());

        // uploading files
        fileManagementService.upload(token, new FileVO("example.txt"), stream);
        fileManagementService.upload(token, new FileVO("example2.txt"), stream);

        // downloading files
        List<FileSystemItem> files = fileManagementService.enterRoot(token);
        fileManagementService.download(token, files.get(0).getId());
        fileManagementService.download(token, files.get(1).getId());
    }

    @Test
    public void testFileRemoving() throws
            RegisterFailedException,
            LoginFailedException,
            NoPermissionException,
            FileUploadingError,
            FileNotFoundException {

        // registration
        UserVO user  = new UserVO(new Credentials("user", "password"));
        userService.register(user);

        // authentication
        SecurityToken token = userService.logIn(user.getCredentials());

        // uploading files
        FileVO file = new FileVO("example.txt");
        fileManagementService.upload(token, file, stream);
        fileManagementService.download(token, file.getId());
        fileManagementService.delete(token, file.getId());

    }

    @Test
    public void testFileSharing() throws
            RegisterFailedException,
            LoginFailedException,
            NoPermissionException,
            FileUploadingError,
            FileNotFoundException,
            UserNotFoundException {

        // registration
        UserVO vlad  = new UserVO(new Credentials("Vlad", "password"));
        UserVO vasya = new UserVO(new Credentials("Vasya", "password"));
        userService.register(vlad);
        userService.register(vasya);

        // authentication
        SecurityToken vladToken = userService.logIn(vlad.getCredentials());

        // uploading file
        FolderVO folder = new FolderVO("example");
        fileManagementService.createFolder(vladToken, folder);
        fileManagementService.upload(vladToken, new FileVO("example.txt"), stream);

        // sharing and downloading file
        FileSystemItem sampleFile = fileManagementService.enterFolder(vladToken, folder.getId()).get(0);
        List<UserVO> users = new ArrayList<>();
        users.add(vasya);
        fileSharingService.shareWithUsers(vladToken, sampleFile.getId(), users);

        SecurityToken vasyaToken = userService.logIn(vlad.getCredentials());
        fileManagementService.download(vasyaToken, sampleFile.getId());

        // account deleting
        userService.deleteAccount(vasyaToken, vasya.getId());

        // folder deleting
        fileManagementService.delete(vladToken, folder.getId());

        // logging out
        userService.logOut(vladToken);
    }

    @Test
    public void testFolderSharing() throws
            RegisterFailedException,
            LoginFailedException,
            NoPermissionException,
            FileUploadingError,
            FileNotFoundException,
            UserNotFoundException {

        // registration
        UserVO vlad  = new UserVO(new Credentials("Vlad", "password"));
        UserVO vasya = new UserVO(new Credentials("Vasya", "password"));
        userService.register(vlad);
        userService.register(vasya);

        // authentication
        SecurityToken vladToken = userService.logIn(vlad.getCredentials());

        // uploading file
        FolderVO folder = new FolderVO("example");
        fileManagementService.createFolder(vladToken, folder);
        fileManagementService.upload(vladToken, new FileVO("example.txt"), stream);

        // sharing folder and downloading file
        List<UserVO> users = new ArrayList<>();
        users.add(vasya);
        fileSharingService.shareWithUsers(vladToken, folder.getId(), users);

        SecurityToken vasyaToken = userService.logIn(vlad.getCredentials());
        FileSystemItem sampleFile = fileManagementService.enterFolder(vasyaToken, folder.getId()).get(0);
        fileManagementService.download(vasyaToken, sampleFile.getId());
        
    }

}

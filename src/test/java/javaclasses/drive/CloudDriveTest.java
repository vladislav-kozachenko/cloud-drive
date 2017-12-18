package javaclasses.drive;

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
    public void testFileSharing() throws
            RegisterFailedException,
            LoginFailedException,
            NoPermissionException,
            FileUploadingError,
            FileNotFoundException,
            UserNotFoundException {

        // registration
        UserVO user  = new UserVO(new Credentials("user1", "password"));
        UserVO user2 = new UserVO(new Credentials("user2", "password"));
        userService.register(user);
        userService.register(user2);

        // authentication
        SecurityToken token = userService.logIn(user.getCredentials());

        // uploading file
        fileManagementService.createFolder(token, new FolderVO("example"));
        FileVO sampleFile = fileManagementService.upload(token, new FileVO("example.txt"), stream);

        // sharing and downloading file
        List<UserVO> users = new ArrayList<>();
        users.add(user2);
        fileSharingService.shareWithUsers(token, sampleFile, users);

        SecurityToken token2 = userService.logIn(user.getCredentials());
        fileManagementService.download(token2, sampleFile.getId());

        // account deleting
        userService.deleteAccount(token2, user2.getId());

        // logging out
        userService.logOut(token);
    }

}

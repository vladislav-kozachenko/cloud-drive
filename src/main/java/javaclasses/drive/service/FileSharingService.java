package javaclasses.drive.service;

import javaclasses.drive.FileNotFoundException;
import javaclasses.drive.NoPermissionException;
import javaclasses.drive.UserNotFoundException;
import javaclasses.drive.file.FileVO;
import javaclasses.drive.file.FolderVO;
import javaclasses.drive.user.SecurityToken;
import javaclasses.drive.user.UserVO;

import java.util.List;

/**
 * Represents file sharing service. Provides sharing files and folders with other users.
 */
public interface FileSharingService {

    /**
     * Shares the file with selected users. Provides users access to file.
     *
     * @param securityToken is token used to get user and check if it has permission to share file.
     * @param file          is the file meta information, required to find file in cloud drive.
     * @param users         is the list of users used to share files with them.
     * @throws NoPermissionException if the user have no permission to share the file.
     * @throws FileNotFoundException if file that may be shared isn't found.
     * @throws UserNotFoundException if user in list of users for sharing isn't found.
     */
    void shareWithUsers(SecurityToken securityToken, FileVO file, List<UserVO> users)
            throws NoPermissionException, FileNotFoundException, UserNotFoundException;

    /**
     * Shares all files in the folder with selected users. Provides users access to file.
     *
     * @param securityToken is token used to get user and check if it has permission to share files.
     * @param folder        is the file meta information, required to find file in cloud drive.
     * @param users         is the list of users used to share files with them.
     * @throws NoPermissionException if the user have no permission to share the files.
     * @throws FileNotFoundException if folder that may be shared isn't found.
     * @throws UserNotFoundException if user in list of users for sharing isn't found.
     */
    void shareFolderWithUsers(SecurityToken securityToken, FolderVO folder, List<UserVO> users)
            throws NoPermissionException, FileNotFoundException, UserNotFoundException;

}

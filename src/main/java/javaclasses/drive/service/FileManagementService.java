package javaclasses.drive.service;

import javaclasses.drive.FileNotFoundException;
import javaclasses.drive.FileUploadingError;
import javaclasses.drive.NoPermissionException;
import javaclasses.drive.file.FileSystemItem;
import javaclasses.drive.file.FileSystemItemId;
import javaclasses.drive.file.FileVO;
import javaclasses.drive.file.FolderVO;
import javaclasses.drive.user.SecurityToken;

import java.io.InputStream;
import java.util.List;

/**
 * Represents file management service. Provides possibility to upload, download, browse and remove files.
 * Supports organizing files in folders.
 */
public interface FileManagementService {

    /**
     * Uploads file from client to server using input stream into current folder.
     *
     * @param securityToken is token used to get user and check if it has permission to upload file.
     * @param fileVO        is an object that contains file meta information.
     * @param inputStream   is a stream used to upload file.
     * @return object contains file meta information and its identifier in cloud.
     * @throws FileUploadingError    if file uploading failed.
     * @throws NoPermissionException if user has no permission to upload file in current folder.
     */
    FileVO upload(SecurityToken securityToken, FileVO fileVO, InputStream inputStream) throws FileUploadingError, NoPermissionException;

    /**
     * Downloads file from cloud server to client device.
     *
     * @param securityToken is token used to get user and check if it has permission to download file.
     * @param id            is a file identifier in cloud system, used to find file in file system.
     * @return stream that may be used to download file by client.
     * @throws FileNotFoundException if the searched file isn't exist.
     * @throws NoPermissionException if user has no permission to download file.
     */
    InputStream download(SecurityToken securityToken, FileSystemItemId id) throws FileNotFoundException, NoPermissionException;

    /**
     * Returns file system items of the root folder.
     *
     * @param securityToken is token used to get user and its root folder.
     * @return list of files and folders meta information, contained in root folder.
     * @throws NoPermissionException if user has no permission to open folder.
     */
    List<FileSystemItem> enterRoot(SecurityToken securityToken) throws NoPermissionException;

    /**
     * Returns file system items of the folder with inputted id.
     * Saves current folder to navigate and file uploading.
     *
     * @param securityToken is token used to get user and check if it has permission to read the folder.
     * @param folderId      is the identifier of required folder.
     * @return list of files and folders meta information, contained in the folder.
     * @throws FileNotFoundException if required folder isn't exist.
     * @throws NoPermissionException if user has no permission to open folder.
     */
    List<FileSystemItem> enterFolder(SecurityToken securityToken, FileSystemItemId folderId) throws FileNotFoundException, NoPermissionException;

    /**
     * Removes file or folder from cloud drive.
     *
     * @param securityToken is token used to get user and check if it has permission to delete the file.
     * @param fileSystemItemId        is the file or folder identifier required to find file in file system.
     * @throws FileNotFoundException if required file isn't exist.
     * @throws NoPermissionException if user has no permission to delete the file.
     */
    void delete(SecurityToken securityToken, FileSystemItemId fileSystemItemId) throws FileNotFoundException, NoPermissionException;

    /**
     * Creates a new folder in cloud drive.
     *
     * @param securityToken is token used to get user and check if it has permission to create the folder.
     * @param folderVO      is the object contains folder name and any meta information.
     * @throws NoPermissionException if user has no permission to create the folder.
     */
    void createFolder(SecurityToken securityToken, FolderVO folderVO) throws NoPermissionException;

}

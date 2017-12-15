package javaclasses.drive.service;

import javaclasses.drive.LoginFailedException;
import javaclasses.drive.NoPermissionException;
import javaclasses.drive.RegisterFailedException;
import javaclasses.drive.user.Credentials;
import javaclasses.drive.user.SecurityToken;
import javaclasses.drive.user.UserID;
import javaclasses.drive.user.UserVO;

/**
 * Represents user management service. Provides API for registration, authentication,
 * and  user account management.
 */
public interface UserService {

    /**
     * Registers new user in cloud.
     * @param user is the user object contains fields needed for registration.
     * @throws RegisterFailedException if user with inputted login already exists or some fields are invalid.
     */
    void register(UserVO user) throws RegisterFailedException;

    /**
     * Authenticates user using reported, creates user session and sends security token to client.
     * @param credentials is a combination of fields required for authentication.
     * @return security token may be saved by client and used for authorization.
     * @throws LoginFailedException
     */
    SecurityToken logIn(Credentials credentials) throws LoginFailedException;

    /**
     * Deletes session for the user.
     * @param securityToken is the token used for getting user to logout.
     */
    void logOut(SecurityToken securityToken);

    /**
     * Deletes account of user. It's possible to remove own account. Admin users can delete other accounts.
     * @param securityToken is token used to get user and check if it has permission to delete account.
     * @param id is the identifier of user account may be deleted.
     * @throws NoPermissionException if user has no permission to delete account.
     */
    void deleteAccount(SecurityToken securityToken, UserID id) throws NoPermissionException;

}

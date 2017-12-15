package javaclasses.drive.service;

import javaclasses.drive.user.Credentials;
import javaclasses.drive.user.SecurityToken;
import javaclasses.drive.user.UserID;
import javaclasses.drive.user.UserVO;

/**
 * Represents user management service.
 */
public interface UserService {

    SecurityToken register(UserVO user);

    SecurityToken logIn(Credentials credentials);

    void logOut(SecurityToken securityToken);

    void deleteAccount(SecurityToken securityToken, UserID id);

}

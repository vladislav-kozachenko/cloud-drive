package javaclasses.drive.user;


public class UserVO {

    private UserID id;

    private Credentials credentials;

    public UserVO(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public UserID getId() {
        return id;
    }
}

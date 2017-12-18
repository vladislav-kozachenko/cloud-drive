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

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public UserVO(UserID id) {
        this.id = id;
    }

    public UserID getId() {
        return id;
    }

    public void setId(UserID id) {
        this.id = id;
    }
}

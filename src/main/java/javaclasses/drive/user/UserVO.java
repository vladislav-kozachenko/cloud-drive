package javaclasses.drive.user;

public class UserVO {

    private UserID id;

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

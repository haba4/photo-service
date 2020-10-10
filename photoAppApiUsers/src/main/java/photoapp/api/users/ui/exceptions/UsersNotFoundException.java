package photoapp.api.users.ui.exceptions;

public class UsersNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 2841929413533705858L;

    public UsersNotFoundException() {
        super("Users not found");
    }
}

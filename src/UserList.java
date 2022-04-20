import java.util.ArrayList;

public class UserList {

    private ArrayList<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public ArrayList<User> getUserList() {
        return this.userList;
    }
    
}

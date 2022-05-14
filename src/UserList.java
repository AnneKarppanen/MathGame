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

    public boolean checkIfUserExists(String username) {
        boolean userExists = false;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userExists = true;
                return userExists;
            }
        }
        return userExists;
    }

    public User getExistingUser(String username) {
        User userToReturn = null;
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userToReturn = user;
            }
        }

        return userToReturn;
    }

    public int getSize() {
        return userList.size();
    }

}

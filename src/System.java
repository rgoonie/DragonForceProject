import java.util.ArrayList;

class System {

    private ArrayList <User> currentUsers;

    public System(){
        this.currentUsers = new ArrayList();
    }

    /**
     * @param User The user that wishes to log in
     * @return If the user object supplied was successfuly logged in
     */
    public boolean logIn(String id, String password) {
        return true;
    }

    /**
     * @param User The user that wishes to log out
     * @return If the user object supplied was suffessfuly logged out
     */
    public boolean logOut(User user) {
        return true;
    }

    /**
     * Used to generate new user accounts (Suppliers or Customers)
     * @return If the user account was successfuly created
     */
    public boolean createAccount() {
        return false;
    }


}
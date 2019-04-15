abstract class User {

    protected String id, password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * Creates a new User account
     * @return If the account was successfuly created
     */
    abstract boolean createAccount();

}
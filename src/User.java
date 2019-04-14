abstract class User {

    protected String id, password;

    public User() {

    }

    /**
     * Creates a new User account
     * @return If the account was successfuly created
     */
    abstract boolean createAccount();

}
class Supplier extends User {

    public Supplier(String id, String password) {
        super(id, password);
    }

    public boolean createAccount() {
        return false;
    }

}
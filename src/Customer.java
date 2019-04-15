class Customer extends User {

    private String phoneNumber, address, name, creditCardNumber;

    public Customer(String id, String password, String phoneNumber, String address, String name, String creditCardNumber) {
        super(id, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.name = name;
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return If the item was successfully added
     */
    public boolean orderItems() {
        return false;
    }

    public boolean createAccount() {
        return false;
    }

    /**
     * @return All the items in the users cart
     */
    public String viewItems() {
        return "";
    }

}
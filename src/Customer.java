class Customer extends User {

    private String phoneNumber, address, name, creditCardNumber;

    public Customer() {
        super();
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
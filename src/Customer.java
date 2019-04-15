class Customer extends User {

    private String phoneNumber, address, name, creditCardNumber;
    private Cart cart;

    public Customer(String id, String password, String phoneNumber, String address, String name, String creditCardNumber) {
        super(id, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.name = name;
        this.creditCardNumber = creditCardNumber;
        this.cart = new Cart();
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
    public String viewItems(Order order) {
        return "";
    }

}